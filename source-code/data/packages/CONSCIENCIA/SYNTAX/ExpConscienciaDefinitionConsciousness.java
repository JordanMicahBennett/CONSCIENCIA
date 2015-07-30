//Author ~ Jordan Micah Bennett
package data.packages.CONSCIENCIA.SYNTAX;

public class ExpConscienciaDefinitionConsciousness extends Exp {


  public ExpConscienciaDefinitionConsciousness() {

  }


    public <T>  T visit(Visitor<T> v, Object arg) 
	throws Exception
    {
	return v.visitExpConscienciaDefinitionConsciousness(this, arg);
    }

	public String toString() {
	 return "";
	}
}