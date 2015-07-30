package data.packages.CONSCIENCIA.SYNTAX;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class Char 
{
    private char charVal;
    public Char ( char val )
    {
        this.charVal = val;
    }
    public Char ( String val )
    {
        if( val.startsWith("'"))
        {
            if( val.length() == 3 )
                this.charVal = val.charAt(1);
            else if ( val.equals("'\\n'") )
                this.charVal = '\n';
            else if ( val.equals("'\\t'") )
                this.charVal = '\t';
            else if ( val.equals("'\\f'") )
                this.charVal = '\f';
            else if ( val.equals("'\\\\'") )
                this.charVal = '\\';
            else 
            {
                val = val.replace("\\",""); 
                this.charVal = val.charAt(1);
            }
        }
        else
        {
            val = val.replace("#\\","");
            
            if( val.length() == 4 )
                this.charVal = (char)Integer.parseInt(val, 16);
            else
                this.charVal = val.charAt(0);
        }
    }
    
    @Override
    public String toString()
    {
        return "" + charVal;
    }
}
