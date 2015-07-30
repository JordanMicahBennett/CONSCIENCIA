/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.packages.CONSCIENCIA.SYNTAX;

/**
 *
 * @author User
 */
public class ExpCdr extends Exp {

    ExpPair exp;
    String id;

    public ExpCdr( ExpPair e ) {
	exp = e;
    }
    
    public ExpCdr( String e ) {
	id = e;
    }
    
    public Exp getCdr() {
	return getCdr(exp);
    }

    public static Exp getCdr( ExpPair pair ) {
	return pair.exp2;
    }
    
    public String getVar() {
	return id;
    }

    public Object visit(Visitor v, Object arg) 
    throws Exception
    {
        return v.visitExpCdr(this, arg);
    }

    public String toString() {
	return getCdr(exp).toString();
    }
}
