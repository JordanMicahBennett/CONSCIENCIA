/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.packages.CONSCIENCIA.SYNTAX;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public class ExpVector extends Exp 
{
    ArrayList<Exp> exps;
    public ExpVector( ArrayList<Exp> exs )
    {
        exps = exs;
    }
    
    public ArrayList<Exp> getExps() {
	return exps;
    }

    public <T>  T visit(Visitor<T> v, Object arg) 
	throws Exception
    {
	return v.visitExpVector(this, arg);
    }

  public String toString() 
  {
      String str = "[: ";
      for(int i = 0; i < exps.size()-1; i++ )
        str +=  exps.get(i).toString() + " , " ;
      str += exps.get(exps.size()-1).toString() + " :]";      
      return str;           
  }
  
}
