//Author ~ Jordan Micah Bennett
//Establishes consciencia unique object tracking.
package data.packages.CONSCIENCIA.SYNTAX;

import java.util.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;

import data.packages.UNICODE.*;

/**
 * An instance of class <code>Environment</code> maintains a
 * collection of bindings from valid identifiers to integers.
 * It supports storing and retrieving bindings, just as would
 * be expected in any dictionary.
 *
 * @author <a href="mailto:dcoore@uwimona.edu.jm">Daniel Coore</a>
 * @version 1.0
 */
public class Environment {

    HashMap<String, Exp> dictionary;
    Environment parent;
	public ConscienciaTrueQueryBmiFeedback CONCIENCIA_TRUE_QUERY_BMI_FEEDBACK = null;
	public JPanel CONCIENCIA_COMPUTE_PANEL = null;
	
	public UNICODE_CellularAutomata_Environment CELLULAR_AUTOMATA_ENTVIRONMENT = null;
	public UNICODE_MosPanel SIMULATED_OS_PANEL = null;
	public JFrame CONCIENCIA_COMPUTE_WINDOW = null;
	
	public ArrayList <UNICODE_MessageBoxWindow> CONSCIENCIA_MESSAGE_BOX_VECTOR = new ArrayList <UNICODE_MessageBoxWindow> ( );

	
    /**
     * Create a new (empty) top level Environment.
     *
     */
    public Environment() {
	this(null);
    }

	public void establishEditorSynchronization ( JFrame APPLICATION_FRAME, JPanel APPLICATION_PANEL )
	{
		CONCIENCIA_COMPUTE_WINDOW = APPLICATION_FRAME;
		CONCIENCIA_COMPUTE_PANEL = APPLICATION_PANEL; 
	}
	
    public Environment(Environment p) {
	parent = p;
	dictionary = new HashMap<String, Exp>();
    }

    /**
     * Creates a new <code>Environment</code> instance that is
     * initialised with the given collection of bindings
     * (presented as separate arrays of names and values).
     *
     * @param ids the collection of identifiers to be bound.
     * @param values the corresponding collection of values
     * for the identifiers.  Note that the two arrays must
     * have the same length.
     */
    public Environment(Environment p, String[] ids, Exp[] values) {
	parent = p;
	dictionary = new HashMap();
	for (int i = 0; i < ids.length; i++) {
	    put(ids[i], values[i]);
	}
    }

    /**
     * Creates a new <code>Environment</code> instance that is
     * initialised with the given collection of bindings
     * (presented as separate arrays of names and values).
     *
     * @param ids the collection of identifiers to be bound.
     * @param values the corresponding collection of values
     * for the identifiers.  Note that the two arrays must
     * have the same length.
     */
    public Environment(String[] ids, Exp[] values) {
	this(null, ids, values);
    }

    public Environment extend(String[] ids, Exp[] values) {
	return new Environment(this, ids, values);
    }

    /**
     * Create an instance of a global environment suitable for
     * evaluating an program.
     *
     * @return the <code>Environment</code> created.
     */
    public static Environment makeGlobalEnv() {
	Environment result =  new Environment();
	// add definitions for any primitive procedures or
	// constants here
	return result;
    }

    /**
     * Store a binding for the given identifier to the given
     * value within this environment.
     *
     * @param id the name to be bound
     * @param value the value to which the name is bound.
     */
    /*public void put(String id, Exp value) {
	dictionary.put(id, value);
    }*/

    /**
     * Store a binding for the given identifier to the given
     * int within this environment.
     *
     * @param id the name to be bound
     * @param value the value to which the name is bound.
     */
    public void put(String id, Object value)
    {
        if( value instanceof ExpList )
            dictionary.put(id, (ExpList)value );
        else if( value instanceof ExpPair )
            dictionary.put(id, (ExpPair)value );
        else
            dictionary.put(id, new ExpLit(value) );
    }

    /**
     * Return the int associated with the given identifier.
     *
     * @param id the identifier.
     * @return the int associated with the identifier in
     * this environment.
     * @exception Exception if <code>id</code> is unbound
     */
    public Exp get(String id) throws Exception {
	Exp result = (Exp) dictionary.get(id);
	if (result == null)
	    if (parent == null)
		throw new Exception("Unbound variable " + id);
	    else
		return parent.get(id);
	else
	    return result;
    }

	
	public void enableTrueQueryBmiFeeback ( double HEIGHT_FEET_DATA, int HEIGHT_INCH_DATA, String GENDER_STRING )
	{
		CONCIENCIA_TRUE_QUERY_BMI_FEEDBACK = new ConscienciaTrueQueryBmiFeedback (HEIGHT_FEET_DATA,HEIGHT_INCH_DATA,GENDER_STRING);
	}
    /**
     * Create a string representation of this environment.
     *
     * @return a string of all the names bound in this
     *         environment.
     */
    public String toString() {
	StringBuffer result = new StringBuffer();

	Iterator iter = dictionary.keySet().iterator();
	while (iter.hasNext()) {
	    result = result.append(iter.next().toString());
	}
	return result.toString();
    }

}
