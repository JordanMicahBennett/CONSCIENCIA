/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.packages.CONSCIENCIA.SYNTAX;

/**
 *
 * @author User
 */
public class ExpList extends Exp{
    
    ExpPair expPair;
    ExpPair currentExp;
    public ExpList( Exp ex )
    {
        
        expPair = new ExpPair( ex, new ExpLit( new Nil() )  );
        currentExp = expPair;
    }
    
    public Exp getExpP()
    {
        return expPair;
    }
    
    public void add( Exp ex )
    {        
        ExpPair newPair = new ExpPair( ex, new ExpLit( new Nil() )  );
        currentExp.exp2 = newPair;
        currentExp = newPair;
    }

    @Override
    public <T> T visit(Visitor<T> v, Object arg) throws Exception {
        return v.visitExpList(this, arg);
    }

    @Override
    public String toString() {
        return expPair.toString();
    }
}
