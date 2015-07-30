/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.packages.CONSCIENCIA.SYNTAX;

/**
 *
 * @author fstlab
 */
public class ExpPrint extends Statement {

    Exp exp;
    String endStr;


    public ExpPrint(Exp e, String s) {
	exp = e;
        endStr = s;
    }

    public Exp getExp() {
	return exp;
    }

    public Object visit(Visitor v, Object arg)
	throws Exception
    {
	v.visitExpPrint(this, arg);
        return exp;
    }

    public String toString() {
	return exp.toString() + endStr;
    }
}
