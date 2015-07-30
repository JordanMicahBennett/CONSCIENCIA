//Author ~ Jordan Micah Bennett
package data.packages.CONSCIENCIA.SYNTAX;

import java.util.*;

public class Evaluator implements Visitor<AlgebraicValue> {
    /* For this visitor, the argument passed to all visit
       methods will be the environment object that used to
       be passed to the eval method in the first style of
       implementation. */

    // allocate state here
    protected AlgebraicValue result;	// result of evaluation

    public Evaluator() {
	// perform initialisations here
	result = new AlgebraicInt(0);
    }

    public AlgebraicValue visitArithProgram(ASTProgram p,
					    Object arg) throws Exception {
	result = p.getSeq().visit(this, arg);
	return result;
    }

    public AlgebraicValue visitStatement(Statement s, Object arg)
	throws Exception {
	return s.getExp().visit(this, arg);
    }

    public AlgebraicValue visitStmtSequence(StmtSequence sseq,
				    Object arg) throws Exception {
	// remember that arg is the environment
	Statement s;
	ArrayList seq = sseq.getSeq();
	Iterator iter = seq.iterator();
	AlgebraicValue result = new AlgebraicInt(0); // default result
	while(iter.hasNext()) {
	    s = (Statement) iter.next();
	    result = s.visit(this, arg);
	}
	// return last value evaluated
	return result;
    }

    public AlgebraicValue visitStmtDefinition(StmtDefinition sd, Object arg)
	throws Exception {
	Environment env = (Environment) arg;
	result = sd.getExp().visit(this, env);
	env.put(sd.getVar(), result);
	return result;
    }

    public AlgebraicValue visitFnDef(ExpFnDef fnDef, Object arg)
	throws Exception {
	// Need to create an instance of a function (the value in the language
	// that represents a function).
	result = new Closure(fnDef.getName(), fnDef.getParams(),
			      fnDef.getBody(), (Environment)arg);
	return result;
    }

    public AlgebraicValue visitFunCall(ExpFunCall callExp, Object env)
	throws Exception {
	Environment e = (Environment) env;
	ArrayList<AlgebraicValue> args = new ArrayList<AlgebraicValue>();
	for (Exp exp : callExp.getParams()) {
	    args.add(exp.visit(this, e));
	}
	Closure fn = (Closure) e.get(callExp.getFnName());
	String[] paramArray = fn.getParams().toArray(new String[0]);
	AlgebraicValue[] argsArray = args.toArray(new AlgebraicValue[0]);
	Environment callEnv = new Environment(fn.getEnv(),
					      paramArray, argsArray);
	return fn.getBody().visit(this, callEnv);
    }

    public AlgebraicValue visitExpAdd(ExpAdd exp, Object arg) throws Exception {
	AlgebraicValue val1, val2;
	val1 = exp.getExpL().visit(this, arg);
	val2 = exp.getExpR().visit(this, arg);
	return new AlgebraicInt(val1.intValue() + val2.intValue());
    }

    public AlgebraicValue visitExpSub(ExpSub exp, Object arg)
	throws Exception
    {
	AlgebraicValue val1, val2;
	val1 = exp.getExpL().visit(this, arg);
	val2 = exp.getExpR().visit(this, arg);
	return new AlgebraicInt(val1.intValue() - val2.intValue());
    }

    public AlgebraicValue visitExpMul(ExpMul exp, Object arg)
	throws Exception
    {
	AlgebraicValue val1, val2;
	val1 = exp.getExpL().visit(this, arg);
	val2 = exp.getExpR().visit(this, arg);
	return new AlgebraicInt(val1.intValue() * val2.intValue());
    }

    public AlgebraicValue visitExpDiv(ExpDiv exp, Object arg) throws Exception {
	AlgebraicValue val1, val2;
	val1 = exp.getExpL().visit(this, arg);
	val2 = exp.getExpR().visit(this, arg);
	return new AlgebraicInt(val1.intValue() / val2.intValue());
    }

    public AlgebraicValue visitExpMod(ExpMod exp, Object arg) throws Exception {
	AlgebraicValue val1, val2;
	val1 = exp.getExpL().visit(this, arg);
	val2 = exp.getExpR().visit(this, arg);
	return new AlgebraicInt(val1.intValue() % val2.intValue());
    }

    public AlgebraicValue visitExpLit(ExpLit exp, Object arg) throws Exception {
	return exp.getVal();
    }

    public AlgebraicValue visitExpVar(ExpVar exp, Object arg) throws Exception {
	// remember that arg is really the environment
	Environment env = (Environment) arg;
	AlgebraicValue val = env.get(exp.getVar());
	return val;
    }
}
