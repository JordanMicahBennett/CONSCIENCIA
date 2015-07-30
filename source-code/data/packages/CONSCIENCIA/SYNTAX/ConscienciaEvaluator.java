//Author ~ Jordan Micah Bennett
package data.packages.CONSCIENCIA.SYNTAX;

import java.util.*;
import data.packages.UNICODE.*; //uni-code (tm)
import java.awt.Color;

public class ConscienciaEvaluator implements Visitor {

    Object result;

    public ConscienciaEvaluator() {
	result = null;
    }

    public Object getResult() {
	return result;
    }

    // program
    @Override
    public Object visitASTProgram(ASTProgram p,
				    Object arg)
	throws Exception {
	result = p.getSeq().visit(this, arg);
	return result;
    }

    // statements
    @Override
    public Object visitStatement(Statement stmt, Object arg)
	throws Exception {
	return stmt.getExp().visit(this, arg);
    }
    
    

    @Override
    public Object visitStmtSequence(StmtSequence exp,
				    Object arg)
	throws Exception {
	ArrayList stmts = exp.getSeq();
	if (stmts.size() == 1)
	    return ((Statement) stmts.get(0)).visit(this,
						    arg);
	else {
	    Iterator iter = stmts.iterator();
	    String resultSeq = "(begin ";
	    Statement stmt;
	    while (iter.hasNext()) {
		stmt = (Statement) iter.next();
		resultSeq += /*(String)*/ stmt.visit(this, arg).toString() +
		    "\n";
	    }
	    resultSeq += ")";
	    return resultSeq;
	}
    }
    
    @Override
    public Object visitExpVar(ExpVar exp, Object arg)
	throws Exception {
        
        Environment environment = (Environment) arg;
	Exp val = environment.get(exp.getVar());        
	//return exp.getVar();
        return val;
    }
    
    @Override
    public Object visitExpCar(ExpCar exp, Object arg)
	throws Exception {
        
        if( exp.getVar() == null )
            return exp.getCar();
        
        Environment environment = (Environment) arg;
	ExpPair val = (ExpPair)( environment.get(exp.getVar()) );        
	//return exp.getVar();
        return ExpCar.getCar(val);
    }
    
    @Override
    public Object visitExpCdr(ExpCdr exp, Object arg)
	throws Exception {
        
        if( exp.getVar() == null )
            return exp.getCdr();
        
        Environment environment = (Environment) arg;
	ExpPair val = (ExpPair)( environment.get(exp.getVar()) );        
	//return exp.getVar();
        return ExpCdr.getCdr(val);
    }

    @Override
    public Object visitStmtDefinition(StmtDefinition sd,
				      Object arg)
	throws Exception {
        Environment environment = (Environment) arg;
	Object valExp = sd.getExp().visit(this, environment);
	environment.put(sd.getVar(), valExp );//Call By Value      
	 
	return valExp;					
	//return "(define " + sd.getVar() + " " + valExp + ")";
    }
    
    @Override
    public Object visitAssignStmt(AssignStmt as, Object arg)
	throws Exception {
	Environment environment = (Environment) arg;       
        
	Object valExp = as.getExp().visit(this, environment);
        
        for( int a = 0;  a < as.vars.size(); a ++ )
            environment.put( as.vars.get(a), valExp );//Call By Value      
	 
	return valExp;
    }

    @Override
    public Object visitFnDef(ExpFnDef fnDef, Object args) throws Exception {
        /*result = new Closure(fnDef.getName(), fnDef.getParams(),
			      fnDef.getBody(), (Environment)arg);
	return result;*/
	return "(define " + fnDef.getName() +
	    " (lambda " + " (" + fnDef.paramString() + ") "
	    +  fnDef.getBody() +  ")";
    }

    @Override
    public Object visitFunCall(ExpFunCall fnCall, Object args) throws Exception {
	throw new UnsupportedOperationException("Fun Call not implemented");
    }
    
    @Override
    public Object visitExpLit(ExpLit exp, Object arg)
	throws Exception{
	//return "" + exp.getVal();
        return exp.getVal();
    }
    
    @Override
    public Object visitExpPair(ExpPair exp, Object arg)
	throws Exception 
    {
        Object left = exp.getExpL().visit(this, arg);
	Object right = exp.getExpR().visit(this, arg);
        
        try
        {
            
            if( left instanceof ExpPair)
                exp.exp1 = (ExpPair)left;
            else if( left instanceof ExpList)
                exp.exp1 = (ExpList)left;
            else if( left instanceof ExpVector)
                exp.exp1 = (ExpVector)left;
            else
                exp.exp1 = new ExpLit( left );
            
            if( right instanceof ExpPair)
                exp.exp2 = (ExpPair)right;
            else if( right instanceof ExpList)
                exp.exp2 = (ExpList)right;
            else if( right instanceof ExpVector)
                exp.exp2 = (ExpVector)right;
            else
                exp.exp2 = new ExpLit( right );
        }
        catch(Exception e) {}
	return exp;//.toString();
    }
    
    
    @Override
    public Object visitExpList(ExpList exp, Object arg)
	throws Exception 
    {
        Object left = exp.getExpP().visit(this, arg);

	return exp.toString();
    }
    
    @Override
    public Object visitExpVector(ExpVector exp, Object arg)
	throws Exception 
    {
        ArrayList <Exp> newExps = new ArrayList <Exp>();
        for( int i = 0; i < exp.getExps().size(); i++ )
        {
            Object ob = exp.getExps().get(i).visit(this, arg);
            
            if( ob instanceof ExpPair)
                newExps . add( (ExpPair)ob );
            else if( ob instanceof ExpList)
                newExps . add( (ExpList)ob );
            else if( ob instanceof ExpVector)
                newExps . add( (ExpVector)ob);
            else
                newExps . add( new ExpLit( ob ) );
        }
        exp.exps = newExps;

	return exp.toString();
    }
    
    @Override
    public Object visitExpPrint(ExpPrint exp, Object arg)
	throws Exception
    {
        Object ob = exp.getExp().visit(this, arg);
        
        if( ob instanceof ExpPair)
            exp.exp = (ExpPair)ob;
        else if( ob instanceof ExpList)
            exp.exp = (ExpList)ob;
        else if( ob instanceof ExpVector)
            exp.exp = (ExpVector)ob;
        else
            exp.exp = new ExpLit(ob);
 
        return exp.getExp();
    }
	
    @Override
    public Object visitExpVisualPrint(ExpVisualPrint exp, Object arg)
	throws Exception
    {
        Object ob = exp.getExp().visit(this, arg);
        
        if( ob instanceof ExpPair)
            exp.exp = (ExpPair)ob;
        else if( ob instanceof ExpList)
            exp.exp = (ExpList)ob;
        else if( ob instanceof ExpVector)
            exp.exp = (ExpVector)ob;
        else
            exp.exp = new ExpLit(ob);

		
		Environment environment = (Environment) arg;
		
		//pre-baked uni-code(tm) pure white style message box parameters. 
		environment.CONSCIENCIA_MESSAGE_BOX_VECTOR.add ( new UNICODE_MessageBoxWindow ( true, ""+exp.getExp ( ), 0.9f, new Color ( 255, 255, 255 ), new Color ( 255, 255, 255 ), new Color ( 255, 255, 255 ), new Color ( 0, 0, 0 ), false, "data/images/message box/okay/","rr", 64, 64, 0, 0, 0, 0 ) );  
		

	
        return exp.getExp();
    }


    //////////////////////////////////// expressions////////////////////////////////////
    @Override
    public Object visitExpBodyMassIndexTrueQuery0(ExpBodyMassIndexTrueQuery0 exp, Object arg)
	throws Exception {
		Environment environment = (Environment) arg;   
        environment.enableTrueQueryBmiFeeback (Double.parseDouble(""+exp.getExpFirst().visit(this, arg)),Integer.parseInt( ""+exp.getExpSecond().visit(this, arg)),""+exp.getExpThird());
		
		return "WHAT-IS-YOUR-WEIGHT?--(AF: I-weigh-<value>-pounds;)";
	}
	
    @Override
    public Object visitExpBodyMassIndexTrueQuery1(ExpBodyMassIndexTrueQuery1 exp, Object arg)
	throws Exception {
	String returnValue = "";
	
		Environment environment = (Environment) arg;   
        environment.CONCIENCIA_TRUE_QUERY_BMI_FEEDBACK.PERSON_WEIGHT_INPUT = Double.parseDouble(""+exp.getExp().visit(this, arg));
		
	int baseMaleKg = 52;
	int baseFemaleKg = 49;
	double kilogramToPoundRateMultiplier = 2.20462; //1kg = 2.20462 pounds
	double maleKilogramInchMultiplier = 1.9;
	double femaleKilogramInchMultiplier = 1.7;
	int theNumberOfInchesInAFoot = 12;
	int baseFeet = 0;
	double additionalMaleKg = 0.0, additionalFemaleKg = 0.0, remaindingFeetInInches = 0.0;
	String outputMaleSuffix = "-- pounds (for-males)--";
	String outputFemaleSuffix = "-- pounds (for-females)--";
	String source = "J.-D.-Robinson-Formula-(1983)... HOW-TALL-ARE-YOU?--[AF:-I-am-a--<value>-feet-<value>--male; (OR female)";
    java.text.DecimalFormat decimalFormater = new java.text.DecimalFormat("##.00");
	
	double feetInput = environment.CONCIENCIA_TRUE_QUERY_BMI_FEEDBACK.PERSON_HEIGHT_FEET_INPUT;
	int inchInput = environment.CONCIENCIA_TRUE_QUERY_BMI_FEEDBACK.PERSON_HEIGHT_INCH_INPUT;
	String gender = environment.CONCIENCIA_TRUE_QUERY_BMI_FEEDBACK.GENDER_STRING;
	double weightInput = environment.CONCIENCIA_TRUE_QUERY_BMI_FEEDBACK.PERSON_WEIGHT_INPUT;
	
		if(inchInput >= 1)
		{	
				//if inches are given
				if ( Integer.parseInt(""+feetInput) >= 5 )
				{
					//compute using base 5feet = ....
					baseFeet = 5;
					remaindingFeetInInches = ( Integer.parseInt(""+feetInput) - baseFeet ) * theNumberOfInchesInAFoot;
			
					additionalMaleKg = maleKilogramInchMultiplier * remaindingFeetInInches;
					additionalFemaleKg = femaleKilogramInchMultiplier * remaindingFeetInInches;
					
					double extraMaleKg = maleKilogramInchMultiplier * (Integer)inchInput;
					double extraFemaleKg = femaleKilogramInchMultiplier * (Integer)inchInput;

					if ( gender.equals ( "female" ) )
						if ( weightInput <= ( baseFemaleKg + additionalFemaleKg + extraFemaleKg ) * kilogramToPoundRateMultiplier )
						     return "@-" + feetInput + "-feet-" + inchInput + "-and-" + weightInput + "pounds--[YOU-SEEM-A-HEALTHY-FEMALE,-WITH-NORMAL-Body-Mass-Index]";
					    else
						     return "ARE-YOU-MUSCULAR?-(AF:-Yes-I-am-muscular...-OR...-No-I-am-not-muscular.)";
							 
					if ( gender.equals ( "male" ) )
						if ( weightInput <= ( baseMaleKg + additionalMaleKg + extraMaleKg ) * kilogramToPoundRateMultiplier )
						     return "@-" + feetInput + "-feet-" + inchInput + "-and-" + weightInput + "pounds--[YOU-SEEM-A-HEALTHY-FEMALE,-WITH-NORMAL-Body-Mass-Index]";
					    else
						     return "ARE-YOU-MUSCULAR?-(AF:-Yes-I-am-muscular...-OR...-No-I-am-not-muscular.)";
				}
		}
		return returnValue;
	}
	
    @Override
    public Object visitExpConscienciaDefinitionConsciousness(ExpConscienciaDefinitionConsciousness exp, Object arg)
	throws Exception {
	    return "consciousness(c)=PERCEPTION(P)*pattern(p),where-P-is-infinite-perception,-BUT-limited-by-uncertainty-ceiling,-and-p-is-a-pattern-collection.ie-brain,body,hardware.--{Jordan-Micah-Bennett}";
	}
	
    @Override
    public Object visitExpConscienciaSimulationSimpleCellularAutomata(ExpConscienciaSimulationSimpleCellularAutomata exp, Object arg)
	throws Exception {
		Environment environment = (Environment) arg; 
		String returnValue = "";
		if ( environment.CONCIENCIA_COMPUTE_PANEL != null )
		{
			int environmentWidth = ( int ) environment.CONCIENCIA_COMPUTE_WINDOW.getWidth ( ) - 40, environmentHeight = ( int ) environment.CONCIENCIA_COMPUTE_WINDOW.getHeight ( ) - 500;
			environment.CELLULAR_AUTOMATA_ENTVIRONMENT = new UNICODE_CellularAutomata_Environment ( new java.awt.Color ( environment.CONCIENCIA_COMPUTE_PANEL.getBackground ( ).getRed ( ), environment.CONCIENCIA_COMPUTE_PANEL.getBackground ( ).getGreen ( ), environment.CONCIENCIA_COMPUTE_PANEL.getBackground ( ).getBlue ( ) ), environmentWidth, environmentHeight );
			environment.CELLULAR_AUTOMATA_ENTVIRONMENT.setPreferredSize ( new java.awt.Dimension ( environmentWidth, environmentHeight ) );
			int xLocation = Integer.parseInt ( new UNICODE_ConveniencePack ( ).getRegexComponents ( "java.awt.Point(\\[)x=(\\w+)(\\,)y=(\\w+)(\\])", environment.CONCIENCIA_COMPUTE_PANEL.getToolTipText ( ), "$2" ) );
			int yLocation = Integer.parseInt ( new UNICODE_ConveniencePack ( ).getRegexComponents ( "java.awt.Point(\\[)x=(\\w+)(\\,)y=(\\w+)(\\])", environment.CONCIENCIA_COMPUTE_PANEL.getToolTipText ( ), "$4" ) );
			
		
			//very essential, validate ( ) enables one to manipulate orientation and component adding.
			//normally one does not need validate ( ), however when the operation is embeded away from
			//where the actual panel is, one definitely needs it.
			environment.CONCIENCIA_COMPUTE_PANEL.add ( environment.CELLULAR_AUTOMATA_ENTVIRONMENT );
			environment.CONCIENCIA_COMPUTE_PANEL.validate ( );
			environment.CELLULAR_AUTOMATA_ENTVIRONMENT.setLocation ( xLocation, yLocation + 50 );
			environment.CONCIENCIA_COMPUTE_PANEL.repaint ( );
			returnValue = "stop=scroll_down,go=scroll_up..[birth=3.surrounds.1.dead][survival=2or3.surround.1.alive][death=all.other.cases]...rules.by_John-Conway.code_by-Jordan-Micah-Bennett";
		}
		else	
			returnValue = "conciencia panel does not exist";
	    return returnValue;
	}
	
	
    @Override
    public Object visitExpConscienciaSimulatedOperatingSystem(ExpConscienciaSimulatedOperatingSystem exp, Object arg)
	throws Exception {
		Environment environment = (Environment) arg; 
		String returnValue = "";
		if ( environment.CONCIENCIA_COMPUTE_PANEL != null )
		{
		
			int panelWidth = ( int ) environment.CONCIENCIA_COMPUTE_WINDOW.getWidth ( ) - 40, panelHeight = ( int ) environment.CONCIENCIA_COMPUTE_WINDOW.getHeight ( ) - 100;
			environment.SIMULATED_OS_PANEL = new UNICODE_MosPanel ( );

			environment.SIMULATED_OS_PANEL.setPreferredSize ( new java.awt.Dimension ( panelWidth, panelHeight ) );
			int xLocation = Integer.parseInt ( new UNICODE_ConveniencePack ( ).getRegexComponents ( "java.awt.Point(\\[)x=(\\w+)(\\,)y=(\\w+)(\\])", environment.CONCIENCIA_COMPUTE_PANEL.getToolTipText ( ), "$2" ) );
			int yLocation = Integer.parseInt ( new UNICODE_ConveniencePack ( ).getRegexComponents ( "java.awt.Point(\\[)x=(\\w+)(\\,)y=(\\w+)(\\])", environment.CONCIENCIA_COMPUTE_PANEL.getToolTipText ( ), "$4" ) );
			
		
			//very essential, validate ( ) enables one to manipulate orientation and component adding.
			//normally one does not need validate ( ), however when the operation is embeded away from
			//where the actual panel is, one definitely needs it.
			environment.CONCIENCIA_COMPUTE_PANEL.add ( environment.SIMULATED_OS_PANEL );
			environment.CONCIENCIA_COMPUTE_PANEL.validate ( );
			environment.SIMULATED_OS_PANEL.setLocation ( xLocation, yLocation + 50 );
			environment.CONCIENCIA_COMPUTE_PANEL.repaint ( );
			returnValue = "non-computable-operating-system-simulation...";
		}
		else	
			returnValue = "conciencia panel does not exist";
	    return returnValue;
	}
/*

		*/
    @Override
    public Object visitBodyMassIndex(ExpBodyMassIndex exp, Object arg)
	throws Exception {
	/*
	Author Jordan Bennett.
	Notes?
	The ideal human body weight has been a topic of debate for a very long time. Hundreds of formulas...and...theories have been invented...and...put to the test, but the answer is still debatable. The ideal weight should be unique for everyone. The major factors that contribute to a person's ideal weight are height, gender, age, body frame, body type,...and...so on. Here is a list of the most popular "ideal weight" formulas:

	J. D. Robinson Formula (1983)

	52 kg + 1.9 kg per inch over 5 feet       (man)
	49 kg + 1.7 kg per inch over 5 feet       (woman)

	Help?
	1kg = 2.20462 pounds
	*/
	Object feetInputOnly = null, feetInput = null, inchInput = null;
	
	if ( exp.getExp() != null )
		feetInputOnly = exp.getExp().visit(this, arg);
	if ( exp.getExpL() != null )
	{
		feetInput = exp.getExpL().visit(this, arg);
		inchInput = exp.getExpR().visit(this, arg);
	}
	
	int baseMaleKg = 52;
	int baseFemaleKg = 49;
	double kilogramToPoundRateMultiplier = 2.20462; //1kg = 2.20462 pounds
	double maleKilogramInchMultiplier = 1.9;
	double femaleKilogramInchMultiplier = 1.7;
	int theNumberOfInchesInAFoot = 12;
	int baseFeet = 0;
	double additionalMaleKg = 0.0, additionalFemaleKg = 0.0, remaindingFeetInInches = 0.0;
	String outputMaleSuffix = "-- pounds (for-males)--";
	String outputFemaleSuffix = "-- pounds (for-females)--";
	String source = "J.-D.-Robinson-Formula-(1983)... HOW-TALL-ARE-YOU?--[AF:-I-am-a--<value>-feet-<value>--male; (OR female)";
    java.text.DecimalFormat decimalFormater = new java.text.DecimalFormat("##.00");
	
		//if no inches are given in input
		if(feetInputOnly != null)
		{
			if ( (Integer)feetInputOnly == 5 )
			{
				String resultComponent0 = "" + (baseMaleKg * kilogramToPoundRateMultiplier) + outputMaleSuffix;
				String resultComponent1 = "" + (baseFemaleKg * kilogramToPoundRateMultiplier) + outputFemaleSuffix;
				return resultComponent0 + "...and..." + resultComponent1 + source;
				
			}
			if ( (Integer)feetInputOnly > 5 )
			{
				//compute using base 5feet = ....
				baseFeet = 5;
				remaindingFeetInInches = ( (Integer)feetInputOnly - baseFeet ) * theNumberOfInchesInAFoot;
				additionalMaleKg = maleKilogramInchMultiplier * remaindingFeetInInches;
				additionalFemaleKg = femaleKilogramInchMultiplier * remaindingFeetInInches;
				
				String resultComponent0 = "" + ( decimalFormater.format(( baseMaleKg + additionalMaleKg ) * kilogramToPoundRateMultiplier)) + outputMaleSuffix;
				String resultComponent1 = "" + ( decimalFormater.format(( baseFemaleKg + additionalFemaleKg ) * kilogramToPoundRateMultiplier)) + outputFemaleSuffix;
				return resultComponent0 + "...and..." + resultComponent1 + source;
			}
		}
		
		else if(inchInput != null)
		{	
			if( (Integer)inchInput instanceof  Integer )
			{
				//if inches are given
				if ( ( (Integer)feetInput >= 5 ) && ( (Integer)inchInput > 0 ) )
				{
					//compute using base 5feet = ....
					baseFeet = 5;
					remaindingFeetInInches = ( (Integer)feetInput - baseFeet ) * theNumberOfInchesInAFoot;
					additionalMaleKg = maleKilogramInchMultiplier * remaindingFeetInInches;
					additionalFemaleKg = femaleKilogramInchMultiplier * remaindingFeetInInches;
					
					double extraMaleKg = maleKilogramInchMultiplier * (Integer)inchInput;
					double extraFemaleKg = femaleKilogramInchMultiplier * (Integer)inchInput;
					
					String resultComponent0 = "" + ( decimalFormater.format(( baseMaleKg + additionalMaleKg + extraMaleKg ) * kilogramToPoundRateMultiplier)) + outputMaleSuffix;
					String resultComponent1 = "" + ( decimalFormater.format(( baseFemaleKg + additionalFemaleKg + extraFemaleKg ) * kilogramToPoundRateMultiplier)) + outputFemaleSuffix;
					return resultComponent0 + "...and..." + resultComponent1 + source;
				}
			}
		}
		return "" + (Integer)feetInput + (Integer)inchInput;//Concatenation
    }
	
    @Override
    public Object visitExpAdd(ExpAdd exp, Object arg)
	throws Exception {
	Object left = exp.getExpL().visit(this, arg);
	Object right = exp.getExpR().visit(this, arg);	
        
        if( left instanceof  Double )
        {
            if( right instanceof  Double )
                return ((Double)left + (Double)right );
            if( right instanceof  Integer )
                return ((Double)left + (Integer)right);   
            if( right instanceof ExpLit )
            {
                if( ((ExpLit)right).getVal() instanceof Double )
                    return ( (Double)left + ((Double)(((ExpLit)right).getVal()))   );
                if( ((ExpLit)right).getVal() instanceof Integer )
                    return ( (Double)left + ((Integer)(((ExpLit)right).getVal()))  );
            }
        }        

        if( left instanceof  Integer )
        {
            if( right instanceof  Double )
                return ((Integer)left + (Double)right);
            if( right instanceof  Integer )
                return ((Integer)left + (Integer)right);
            if( right instanceof ExpLit )
            {
                if( ((ExpLit)right).getVal() instanceof Double )
                    return ( (Integer)left + ((Double)(((ExpLit)right).getVal()))   );
                if( ((ExpLit)right).getVal() instanceof Integer )
                    return ( (Integer)left + ((Integer)(((ExpLit)right).getVal()))  );
            }
        }

        if( left instanceof  ExpLit )
        {                
            if( ((ExpLit)left).getVal() instanceof  Double )
            {
                Double e = (Double)(((ExpLit)left).getVal());

                if( right instanceof  Double )
                    return ( e + (Double)right );
                if( right instanceof  Integer )
                    return ( e + (Integer)right );
                if( right instanceof  ExpLit )
                {
                    if( ((ExpLit)right).getVal() instanceof  Double )
                        return ( e + (Double)(((ExpLit)right).getVal()));
                    if( ((ExpLit)right).getVal() instanceof  Integer )
                        return ( e + (Integer)(((ExpLit)right).getVal()));
                }
            }
            if( ((ExpLit)left).getVal() instanceof  Integer )
            {
                Integer e = (Integer)(((ExpLit)left).getVal());

                if( right instanceof  Double )
                    return ( e + (Double)right );
                if( right instanceof  Integer )
                    return ( e + (Integer)right );
                if( right instanceof  ExpLit )
                {
                    if( ((ExpLit)right).getVal() instanceof  Double )
                        return ( e + (Double)(((ExpLit)right).getVal()));
                    if( ((ExpLit)right).getVal() instanceof  Integer )
                        return ( e + (Integer)(((ExpLit)right).getVal()) );
                }
            }                
        }
        return "" + left + right;//Concatenation
    }
    @Override
    public Object visitExpSub(ExpSub exp, Object arg)
	throws Exception {
	Object left = exp.getExpL().visit(this, arg);
	Object right = exp.getExpR().visit(this, arg);
        
        if( left instanceof  Double )
        {
            if( right instanceof  Double )
                return ((Double)left - (Double)right );
            if( right instanceof  Integer )
                return ((Double)left - (Integer)right);   
            if( right instanceof ExpLit )
            {
                if( ((ExpLit)right).getVal() instanceof Double )
                    return ( (Double)left - ((Double)(((ExpLit)right).getVal()))   );
                if( ((ExpLit)right).getVal() instanceof Integer )
                    return ( (Double)left - ((Integer)(((ExpLit)right).getVal()))  );
            }
        }        

        if( left instanceof  Integer )
        {
            if( right instanceof  Double )
                return ((Integer)left - (Double)right);
            if( right instanceof  Integer )
                return ((Integer)left - (Integer)right);
            if( right instanceof ExpLit )
            {
                if( ((ExpLit)right).getVal() instanceof Double )
                    return ( (Integer)left - ((Double)(((ExpLit)right).getVal()))   );
                if( ((ExpLit)right).getVal() instanceof Integer )
                    return ( (Integer)left - ((Integer)(((ExpLit)right).getVal()))  );
            }
        }

        if( left instanceof  ExpLit )
        {                
            if( ((ExpLit)left).getVal() instanceof  Double )
            {
                Double e = (Double)(((ExpLit)left).getVal());

                if( right instanceof  Double )
                    return ( e - (Double)right );
                if( right instanceof  Integer )
                    return ( e - (Integer)right );
                if( right instanceof  ExpLit )
                {
                    if( ((ExpLit)right).getVal() instanceof  Double )
                        return ( e - (Double)(((ExpLit)right).getVal()));
                    if( ((ExpLit)right).getVal() instanceof  Integer )
                        return ( e - (Integer)(((ExpLit)right).getVal()));
                }
            }
            if( ((ExpLit)left).getVal() instanceof  Integer )
            {
                Integer e = (Integer)(((ExpLit)left).getVal());

                if( right instanceof  Double )
                    return ( e - (Double)right );
                if( right instanceof  Integer )
                    return ( e - (Integer)right );
                if( right instanceof  ExpLit )
                {
                    if( ((ExpLit)right).getVal() instanceof  Double )
                        return ( e - (Double)(((ExpLit)right).getVal()));
                    if( ((ExpLit)right).getVal() instanceof  Integer )
                        return ( e - (Integer)(((ExpLit)right).getVal()) );
                }
            }                
        }
	//return "(- " + left + " " + right + ")";
        return new ExpLit( new Nil() );
    }

    @Override
    public Object visitExpMul(ExpMul exp, Object arg)
	throws Exception {
	Object left = exp.getExpL().visit(this, arg);
	Object right = exp.getExpR().visit(this, arg);
	
        if( left instanceof  Double )
        {
            if( right instanceof  Double )
                return ((Double)left * (Double)right );
            if( right instanceof  Integer )
                return ((Double)left * (Integer)right);   
            if( right instanceof ExpLit )
            {
                if( ((ExpLit)right).getVal() instanceof Double )
                    return ( (Double)left * ((Double)(((ExpLit)right).getVal()))   );
                if( ((ExpLit)right).getVal() instanceof Integer )
                    return ( (Double)left * ((Integer)(((ExpLit)right).getVal()))  );
            }
        }        

        if( left instanceof  Integer )
        {
            if( right instanceof  Double )
                return ((Integer)left * (Double)right);
            if( right instanceof  Integer )
                return ((Integer)left * (Integer)right);
            if( right instanceof ExpLit )
            {
                if( ((ExpLit)right).getVal() instanceof Double )
                    return ( (Integer)left * ((Double)(((ExpLit)right).getVal()))   );
                if( ((ExpLit)right).getVal() instanceof Integer )
                    return ( (Integer)left * ((Integer)(((ExpLit)right).getVal()))  );
            }
        }

        if( left instanceof  ExpLit )
        {                
            if( ((ExpLit)left).getVal() instanceof  Double )
            {
                Double e = (Double)(((ExpLit)left).getVal());

                if( right instanceof  Double )
                    return ( e * (Double)right );
                if( right instanceof  Integer )
                    return ( e * (Integer)right );
                if( right instanceof  ExpLit )
                {
                    if( ((ExpLit)right).getVal() instanceof  Double )
                        return ( e * (Double)(((ExpLit)right).getVal()));
                    if( ((ExpLit)right).getVal() instanceof  Integer )
                        return ( e * (Integer)(((ExpLit)right).getVal()));
                }
            }
            if( ((ExpLit)left).getVal() instanceof  Integer )
            {
                Integer e = (Integer)(((ExpLit)left).getVal());

                if( right instanceof  Double )
                    return ( e * (Double)right );
                if( right instanceof  Integer )
                    return ( e * (Integer)right );
                if( right instanceof  ExpLit )
                {
                    if( ((ExpLit)right).getVal() instanceof  Double )
                        return ( e * (Double)(((ExpLit)right).getVal()));
                    if( ((ExpLit)right).getVal() instanceof  Integer )
                        return ( e * (Integer)(((ExpLit)right).getVal()) );
                }
            }                
        }
	//return "(* " + left + " " + right + ")";
        return new ExpLit( new Nil() );
    }

    @Override
    public Object visitExpDiv(ExpDiv exp, Object arg)
	throws Exception {
	Object left = exp.getExpL().visit(this, arg);
	Object right = exp.getExpR().visit(this, arg);
        
        if( left instanceof  Double )
        {
            if( right instanceof  Double )
                return ((Double)left / (Double)right );
            if( right instanceof  Integer )
                return ((Double)left / (Integer)right);   
            if( right instanceof ExpLit )
            {
                if( ((ExpLit)right).getVal() instanceof Double )
                    return ( (Double)left / ((Double)(((ExpLit)right).getVal()))   );
                if( ((ExpLit)right).getVal() instanceof Integer )
                    return ( (Double)left / ((Integer)(((ExpLit)right).getVal()))  );
            }
        }        

        if( left instanceof  Integer )
        {
            if( right instanceof  Double )
                return ((Integer)left / (Double)right);
            if( right instanceof  Integer )
                return ((Integer)left / (Integer)right);
            if( right instanceof ExpLit )
            {
                if( ((ExpLit)right).getVal() instanceof Double )
                    return ( (Integer)left / ((Double)(((ExpLit)right).getVal()))   );
                if( ((ExpLit)right).getVal() instanceof Integer )
                    return ( (Integer)left / ((Integer)(((ExpLit)right).getVal()))  );
            }
        }

        if( left instanceof  ExpLit )
        {                
            if( ((ExpLit)left).getVal() instanceof  Double )
            {
                Double e = (Double)(((ExpLit)left).getVal());

                if( right instanceof  Double )
                    return ( e / (Double)right );
                if( right instanceof  Integer )
                    return ( e / (Integer)right );
                if( right instanceof  ExpLit )
                {
                    if( ((ExpLit)right).getVal() instanceof  Double )
                        return ( e / (Double)(((ExpLit)right).getVal()));
                    if( ((ExpLit)right).getVal() instanceof  Integer )
                        return ( e / (Integer)(((ExpLit)right).getVal()));
                }
            }
            if( ((ExpLit)left).getVal() instanceof  Integer )
            {
                Integer e = (Integer)(((ExpLit)left).getVal());

                if( right instanceof  Double )
                    return ( e / (Double)right );
                if( right instanceof  Integer )
                    return ( e / (Integer)right );
                if( right instanceof  ExpLit )
                {
                    if( ((ExpLit)right).getVal() instanceof  Double )
                        return ( e / (Double)(((ExpLit)right).getVal()));
                    if( ((ExpLit)right).getVal() instanceof  Integer )
                        return ( e / (Integer)(((ExpLit)right).getVal()) );
                }
            }                
        }
	//return "(/ " + left + " " + right + ")";
        return new ExpLit( new Nil() );
    }

    @Override
    public Object visitExpMod(ExpMod exp, Object arg)
	throws Exception{
	Object left = exp.getExpL().visit(this, arg);
	Object right = exp.getExpR().visit(this, arg);
	
        if( left instanceof  Double )
        {
            if( right instanceof  Double )
                return ((Double)left % (Double)right );
            if( right instanceof  Integer )
                return ((Double)left % (Integer)right);   
            if( right instanceof ExpLit )
            {
                if( ((ExpLit)right).getVal() instanceof Double )
                    return ( (Double)left % ((Double)(((ExpLit)right).getVal()))   );
                if( ((ExpLit)right).getVal() instanceof Integer )
                    return ( (Double)left % ((Integer)(((ExpLit)right).getVal()))  );
            }
        }        

        if( left instanceof  Integer )
        {
            if( right instanceof  Double )
                return ((Integer)left % (Double)right);
            if( right instanceof  Integer )
                return ((Integer)left % (Integer)right);
            if( right instanceof ExpLit )
            {
                if( ((ExpLit)right).getVal() instanceof Double )
                    return ( (Integer)left % ((Double)(((ExpLit)right).getVal()))   );
                if( ((ExpLit)right).getVal() instanceof Integer )
                    return ( (Integer)left % ((Integer)(((ExpLit)right).getVal()))  );
            }
        }

        if( left instanceof  ExpLit )
        {                
            if( ((ExpLit)left).getVal() instanceof  Double )
            {
                Double e = (Double)(((ExpLit)left).getVal());

                if( right instanceof  Double )
                    return ( e % (Double)right );
                if( right instanceof  Integer )
                    return ( e % (Integer)right );
                if( right instanceof  ExpLit )
                {
                    if( ((ExpLit)right).getVal() instanceof  Double )
                        return ( e % (Double)(((ExpLit)right).getVal()));
                    if( ((ExpLit)right).getVal() instanceof  Integer )
                        return ( e % (Integer)(((ExpLit)right).getVal()));
                }
            }
            if( ((ExpLit)left).getVal() instanceof  Integer )
            {
                Integer e = (Integer)(((ExpLit)left).getVal());

                if( right instanceof  Double )
                    return ( e % (Double)right );
                if( right instanceof  Integer )
                    return ( e % (Integer)right );
                if( right instanceof  ExpLit )
                {
                    if( ((ExpLit)right).getVal() instanceof  Double )
                        return ( e % (Double)(((ExpLit)right).getVal()));
                    if( ((ExpLit)right).getVal() instanceof  Integer )
                        return ( e % (Integer)(((ExpLit)right).getVal()) );
                }
            }                
        }
	//return "(mod " + left + " " + right + ")";
        return new ExpLit( new Nil() );
    }

    

}
