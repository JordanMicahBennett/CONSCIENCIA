package data.packages.CONSCIENCIA.SYNTAX;

public class AlgebraicInt extends AlgebraicValue {

    Integer valueInt;
    Double valueDouble;

    public AlgebraicInt(int v) {
	valueInt = v;
    }
    
    public AlgebraicInt(double v) {
	valueDouble = v;
    }
    
    public boolean isInt()
    {
        return valueInt != null;
    }

    public Object getValue() 
    {        
        if ( isInt() )
            return valueInt;
	return valueDouble;
    }

    @Override
    public String toString() 
    {
        if( isInt() )
            return Integer.toString(valueInt);
        return Double.toString(valueDouble);
    }
}
