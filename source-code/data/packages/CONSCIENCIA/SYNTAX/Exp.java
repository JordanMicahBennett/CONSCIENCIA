package data.packages.CONSCIENCIA.SYNTAX;

public abstract class Exp {

    public abstract <T> T visit(Visitor<T> v, Object arg)
	throws Exception ;

    public abstract String toString();
}
