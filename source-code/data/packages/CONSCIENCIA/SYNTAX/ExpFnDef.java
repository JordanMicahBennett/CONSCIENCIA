package data.packages.CONSCIENCIA.SYNTAX;

import java.util.ArrayList;

public class ExpFnDef extends Exp {

    String name;
    ArrayList parameters;
    StmtSequence body;

    public ExpFnDef(String nm, ArrayList params, StmtSequence b) {
	name = nm;
	parameters = params;
	body = b;
    }

    public String getName() {
	return name;
    }

    public ArrayList getParams() { 
	return parameters;
    }

    public StmtSequence getBody() {
	return body;
    }

    public <T> T visit(Visitor<T> v, Object arg) throws Exception {
	return v.visitFnDef(this, arg);
    }

    public static String makeParamString(ArrayList<String> parameters) {
	String result = "";
	if (parameters.isEmpty()) {
	    return result;
	} else
	    result = parameters.get(0).toString();

	for (int i = 1; i < parameters.size(); i++) {
	    result += ", " + parameters.get(i);
	}
	return result;
    }

    public String paramString() {
	return makeParamString(parameters);
    }

    public String toString() {
	return "lambda (" + paramString() + ")" + body;
    }
}
