//Author ~ Jordan Micah Bennett
package data.packages.CONSCIENCIA.SYNTAX;

public class ExpConscienciaSimulationSimpleCellularAutomata extends Exp {


  public ExpConscienciaSimulationSimpleCellularAutomata() {

  }


    public <T>  T visit(Visitor<T> v, Object arg) 
	throws Exception
    {
	return v.visitExpConscienciaSimulationSimpleCellularAutomata(this, arg);
    }

	public String toString() {
	 return "";
	}
}