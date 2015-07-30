//Author ~ Jordan Micah Bennett
package data.packages.CONSCIENCIA.SYNTAX;

public class ExpConscienciaSimulatedOperatingSystem extends Exp {


  public ExpConscienciaSimulatedOperatingSystem() {

  }


    public <T>  T visit(Visitor<T> v, Object arg) 
	throws Exception
    {
	return v.visitExpConscienciaSimulatedOperatingSystem(this, arg);
    }

	public String toString() {
	 return "";
	}
}