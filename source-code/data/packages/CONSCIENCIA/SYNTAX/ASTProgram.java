package data.packages.CONSCIENCIA.SYNTAX;

public class ASTProgram extends Exp {
    StmtSequence seq;

    public ASTProgram(StmtSequence s) {
	seq = s;
    }

    public StmtSequence getSeq() {
	return seq;
    }

    public <T> T visit(Visitor<T> v, Object arg) throws Exception 
	{
	return v.visitASTProgram(this, arg);
    }

    public String toString() {
	return seq.toString();
    }
}
