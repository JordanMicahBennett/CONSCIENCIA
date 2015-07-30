package data.packages.CONSCIENCIA.SYNTAX;

import java.util.ArrayList;

public class ExpFunCall extends Exp {

    String name;
    ArrayList<Exp> parameters;
    Exp body;

    public ExpFunCall(String nm, ArrayList<Exp> params) {
	name = nm;
	parameters = params;
    }

    public String getFnName() {
	return name;
    }

    public ArrayList<Exp> getParams() { 
	return parameters;
    }

    public <T> T visit(Visitor<T> v, Object arg) throws Exception {
	return v.visitFunCall(this, arg);
    }

    public static String makeParamString(ArrayList<Exp> parameters) {
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
	return name + " (" + paramString() + ")";
    }
}
