package data.packages.CONSCIENCIA.SYNTAX;

public class ExpLit extends Exp {

    private AlgebraicInt val;
    private String strVal;
    private Char chrVal; 
    private Boolean boolVal;
    private Nil nilVal;

    
    public ExpLit(Integer v) {
	val = new AlgebraicInt(v.intValue());
    }
    
    public ExpLit(Double v) {
	val = new AlgebraicInt(v.doubleValue());
    }
    
    public ExpLit(String v) {
        strVal = correctString(v);
    }
    
    public ExpLit(Char v) {
        chrVal = v;
    }
    
    public ExpLit(Boolean v) {
        boolVal = v;
    }
    
    public ExpLit(Nil v) {
        nilVal = v;
    }
    
    public static String correctString( String str )
    {
        return str.replace("\\n","\n").replace("\\t","\t").replace("\\f","\f").replace("\\\\","\\");
    }
    
    public ExpLit(Object v) 
    {
        if( v instanceof ExpLit )
            v = ((ExpLit)v).getVal();
        
        if( v instanceof Integer )
            val = new AlgebraicInt(((Integer)v).intValue());
        else if( v instanceof Double )
            val = new AlgebraicInt(((Double)v).doubleValue());
        else if( v instanceof String )
            strVal = ((String)v);
        else if( v instanceof Char )
            chrVal = ((Char)v);
        else if( v instanceof Boolean )
            boolVal = ((Boolean)v);
        else
            nilVal = new Nil();
    }

    public Object getVal() 
    {      
        if( boolVal != null)
            return boolVal;
        if( chrVal != null)
            return chrVal;
        if( strVal != null)
            return strVal;      
        if( nilVal != null)
            return nilVal;
        return val.getValue();
    }

    public <T> T visit(Visitor<T> v, Object arg) throws Exception
    {
	return v.visitExpLit(this, arg);
    }

    @Override
    public Object clone()
    {
        return this.clone();
    }
    
    public String toString() {
        if( boolVal != null)
            return boolVal.toString();
        if( chrVal != null)
            return chrVal.toString();
        if( strVal != null)
            return strVal.toString();      
        if( nilVal != null)
            return nilVal.toString();
	return val.toString();
    }
}
