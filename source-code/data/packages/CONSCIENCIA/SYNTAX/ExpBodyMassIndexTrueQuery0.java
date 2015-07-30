//Author ~ Jordan Micah Bennett
package data.packages.CONSCIENCIA.SYNTAX;

public class ExpBodyMassIndexTrueQuery0 extends Exp {

  Exp exp1, exp2, exp3;
  Exp exp;

  public ExpBodyMassIndexTrueQuery0(Exp e) {
    exp = e;
  }
  public ExpBodyMassIndexTrueQuery0(Exp e1, Exp e2, Exp e3) {
    exp1 = e1;
	exp2 = e2;
	exp3 = e3;
  }
    public Exp getExp() {
	return exp;
    }
	
    public Exp getExpFirst() {
	return exp1;
    }

    public Exp getExpSecond() {
	return exp2;
    }
	
    public Exp getExpThird() {
	return exp3;
    }
    public <T>  T visit(Visitor<T> v, Object arg) 
	throws Exception
    {
	return v.visitExpBodyMassIndexTrueQuery0(this, arg);
    }

	public String toString() {
			String returnValue = "";
			
			if ( exp != null ) 
				returnValue = exp.toString ( );
			else
				returnValue = exp1.toString() + " + " + exp2.toString() + " + " + exp3.toString ();
		return returnValue;
	}
}
