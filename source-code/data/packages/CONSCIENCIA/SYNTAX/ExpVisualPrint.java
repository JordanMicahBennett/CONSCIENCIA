/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.packages.CONSCIENCIA.SYNTAX;

/**
 *
 * @author fstlab
 */
public class ExpVisualPrint extends Statement {

    Exp exp;
    String endStr;


    public ExpVisualPrint(Exp e, String s) {
	exp = e;
        endStr = s;
    }

    public Exp getExp() {
	return exp;
    }

    public Object visit(Visitor v, Object arg)
	throws Exception
    {
	v.visitExpVisualPrint(this, arg);
        return exp;
    }

    public String toString() {
	return exp.toString() + endStr;
    }
}
