/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.packages.CONSCIENCIA.SYNTAX;

/**
 *
 * @author User
 */
public class ExpCar extends Exp {

    ExpPair exp;
    String id;

    public ExpCar( ExpPair e ) {
	exp = e;
    }
    
    public ExpCar( String e ) {
	id = e;
    }
    
    public Exp getCar() {
	return exp.exp1;
    }

    public static Exp getCar( ExpPair pair ) {
	return pair.exp1;
    }
    
    public String getVar() {
	return id;
    }

    public Object visit(Visitor v, Object arg) 
    throws Exception
    {
        return v.visitExpCar(this, arg);
    }

    public String toString() {
	return getCar(exp).toString();
    }
}
