//Author ~ Jordan Micah Bennett
package data.packages.CONSCIENCIA.SYNTAX;

public class ExpBodyMassIndexTrueQuery1 extends Exp {

Exp exp;
  public ExpBodyMassIndexTrueQuery1(Exp e) {
  exp = e;
  }
  
  public Exp getExp ( )
  {
  return exp;
  }

    public <T>  T visit(Visitor<T> v, Object arg) 
	throws Exception
    {
	return v.visitExpBodyMassIndexTrueQuery1(this, arg);
    }

	public String toString() {
		return exp.toString();
	}
}
