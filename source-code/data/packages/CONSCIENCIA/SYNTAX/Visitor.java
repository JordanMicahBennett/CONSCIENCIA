//Author ~ Jordan Micah Bennett
package data.packages.CONSCIENCIA.SYNTAX;

 public interface Visitor<T> {

    // program
    public T visitASTProgram(ASTProgram p,
				    Object arg)
	throws Exception;

    // statements
    public T visitStatement(Statement exp, Object arg)
	throws Exception ;
    public T visitStmtSequence(StmtSequence exp,
				    Object arg)
	throws Exception ;
    public T visitStmtDefinition(StmtDefinition sd,
				      Object arg)
	throws Exception;
    public T visitAssignStmt(AssignStmt sd,
				      Object arg)
	throws Exception;
    

    
    
    public T visitFnDef(ExpFnDef fn, Object arg) throws Exception;
    public T visitFunCall(ExpFunCall fn, Object arg) throws Exception;

    // expressions
    public T visitExpConscienciaDefinitionConsciousness(ExpConscienciaDefinitionConsciousness exp, Object arg)
	throws Exception ;
    public T visitExpPair(ExpPair exp, Object arg)
	throws Exception ;
    public T visitExpList(ExpList exp, Object arg)
	throws Exception ;
    public T visitExpVector(ExpVector exp, Object arg)
	throws Exception ;
    public T visitBodyMassIndex(ExpBodyMassIndex exp, Object arg)
	throws Exception ;
    public T visitExpBodyMassIndexTrueQuery0(ExpBodyMassIndexTrueQuery0 exp, Object arg)
	throws Exception ;
    public T visitExpBodyMassIndexTrueQuery1(ExpBodyMassIndexTrueQuery1 exp, Object arg)
	throws Exception ;
    public T visitExpConscienciaSimulationSimpleCellularAutomata(ExpConscienciaSimulationSimpleCellularAutomata exp, Object arg)
	throws Exception ;
    public T visitExpConscienciaSimulatedOperatingSystem(ExpConscienciaSimulatedOperatingSystem exp, Object arg)
	throws Exception ;
    public T visitExpAdd(ExpAdd exp, Object arg)
	throws Exception ;
    public T visitExpSub(ExpSub exp, Object arg)
	throws Exception;
    public T visitExpMul(ExpMul exp, Object arg)
	throws Exception;
    public T visitExpDiv(ExpDiv exp, Object arg)
	throws Exception;
    public T visitExpMod(ExpMod exp, Object arg)
	throws Exception;
    public T visitExpLit(ExpLit exp, Object arg)
	throws Exception;
    public T visitExpVar(ExpVar exp, Object arg)
	throws Exception;
    public T visitExpCar(ExpCar exp, Object arg)
	throws Exception;
    public T visitExpCdr(ExpCdr exp, Object arg)
	throws Exception;
    
    public T visitExpPrint(ExpPrint exp, Object arg)
	throws Exception;
	
    public T visitExpVisualPrint(ExpVisualPrint exp, Object arg)
	throws Exception;
}
