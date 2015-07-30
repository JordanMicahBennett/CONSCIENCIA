/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.packages.CONSCIENCIA.SYNTAX;

import java.util.ArrayList;

/**
 *
 * @author fstlab
 */
public class AssignStmt extends Statement {
    
    ArrayList<String> vars;
    //Exp exp;
            
    public AssignStmt( ArrayList<String> vrs, Exp ex )
    {
        vars = vrs;
        exp = ex;
    }
    /*public Exp getExp() {
	return exp;
    }
    */
    public Object visit(Visitor v, Object arg)
	throws Exception
    {
	return v.visitAssignStmt(this, arg);
    }

    public String toString() {
	return exp.toString();
    }
        
}
