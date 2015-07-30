package data.packages.CONSCIENCIA.SYNTAX;

import java.util.ArrayList;

public class Closure extends AlgebraicValue {
    String name;
    ArrayList<String> parameters;
    Exp body;
    Environment env;

    public Closure(String nm, ArrayList<String> params, Exp b, Environment e) {
	name = nm;
	parameters = params;
	body = b;
	env = e;
    }

    public String getName() {
	return name;
    }

    public ArrayList<String> getParams() {
	return parameters;
    }

    public String getParam(int index) {
	return parameters.get(index);
    }

    public Exp getBody() {
	return body;
    }

    public Environment getEnv() {
	return env;
    }

    public Object getValue() {
	throw new UnsupportedOperationException("No integer value for " +
					       "function " + name);
    }

    public String toString() {
	return name + "(" + ExpFnDef.makeParamString(parameters) + ")={"
	    + body + "}";
    }
}
