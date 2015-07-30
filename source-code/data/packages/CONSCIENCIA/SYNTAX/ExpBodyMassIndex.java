//Author ~ Jordan Micah Bennett
package data.packages.CONSCIENCIA.SYNTAX;

public class ExpBodyMassIndex extends Exp {

  Exp exp1, exp2;
  Exp exp;

  public ExpBodyMassIndex(Exp e) {
    exp = e;
  }
  public ExpBodyMassIndex(Exp e1, Exp e2) {
    exp1 = e1;
	exp2 = e2;
  }
    public Exp getExp() {
	return exp;
    }
	
    public Exp getExpL() {
	return exp1;
    }

    public Exp getExpR() {
	return exp2;
    }

    public <T>  T visit(Visitor<T> v, Object arg) 
	throws Exception
    {
	return v.visitBodyMassIndex(this, arg);
    }

	public String toString() {
			String returnValue = "";
			
			if ( exp != null ) 
				returnValue = exp.toString ( );
			else
				returnValue = exp1.toString() + " + " + exp2.toString();
		return returnValue;
	}
}
