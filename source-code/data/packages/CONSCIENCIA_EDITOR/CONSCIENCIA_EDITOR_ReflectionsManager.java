package data.packages.CONSCIENCIA_EDITOR;

//Written by Jordan Micah Bennett copyRight uni-code(tm) inc. 2012
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import data.packages.UNICODE.*;

public class CONSCIENCIA_EDITOR_ReflectionsManager
{
    //attributes
    private String keyWord = ""; //for keywords
    private UNICODE_ConveniencePack conveniencePack = new UNICODE_ConveniencePack ( );
    private String regexUsageAnswer = "";

    //constructor
    public CONSCIENCIA_EDITOR_ReflectionsManager ( String _keyWord, String _regexUsageAnswer )
    {
        //establish key word
        keyWord = _keyWord;
        //establish regex usage answer
        regexUsageAnswer = _regexUsageAnswer;
    }
    
    //utils
    
    //get methods based on keywords
    public String [ ] getMethods ( )
    {
        String [ ] value = null;
        try 
        {
            //determine appropriate keyword
                //standard java primitives etc
            if ( conveniencePack.getJavaStandardComponentEnquiry ( conveniencePack.getUpperCaseFirstLetterOfWord ( keyWord ), "*", " " ) )
                keyWord = conveniencePack.getJavaKeyWord ( conveniencePack.getUpperCaseFirstLetterOfWord ( keyWord ), "*", " " );
                //bushman smart gui kit stuff
            else if ( conveniencePack.stringSubsetEnquiry ( keyWord, "UNICODE" ) )
                keyWord = conveniencePack.getUNICODEKeyWord ( keyWord );
                //stuff from project directory not external package
            else 
                keyWord = keyWord;
      
            //establish class instance based on string keyWord
            Class classObject = Class.forName( keyWord );
            //establish method list with respect to class classObject
            Method [ ] methodList = classObject.getDeclaredMethods ( );
            //initialize value array with respect to method cardinality (no of methods discovered)
            value = new String [ methodList.length ];
            
            if ( regexUsageAnswer.equals ( "yes" ) )
            {
                String returnTypeInput =  "", returnTypeOutput = "";
                for (int i = 0; i < methodList.length; i++) 
                {  
                    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    //**METHOD RETURN TYPE** CORRECTLY DERIVE READABLE METHOD RETURN TYPES FROM METHOD OBJECT, VIA getReturnType ( ) call, USING REGEX UTILS : PATTERN AND MATCHER
                    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    Pattern returnTypePattern = Pattern.compile ( "(.*)(\\s)(.*)(\\.)(.*)(\\.)(.*)" );
                    
                    returnTypeInput =  methodList [ i ].getReturnType ( ).toString ( );
                    returnTypeOutput = "";
                    
                    Matcher returnTypeMatcher = returnTypePattern.matcher ( returnTypeInput );
                    
                    if ( returnTypeMatcher.find ( ) )
                    {
                        returnTypeOutput = returnTypeMatcher.replaceAll ( "$7" ); //replace all with tagged expression 7
                        returnTypeOutput = returnTypeOutput.replace ( ";", "[]" ); //finnaly tell what param types are arrays. I find that when ';' is returned, it is always array type.
                    }
                    else
                        returnTypeOutput = returnTypeInput; //else if pattern is not found be sure to return default.
                        
                    
                    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    //**METHOD NAME** DOESN't REQUIRE REGEX PROCESSING
                    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    String methodName = methodList [ i ].getName ( );
                        
                    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    //**METHOD PARAM TYPES** CORRECTLY DERIVE READABLE PARAM RETURN TYPES FROM METHOD OBJECT, VIA getParameterTypes ( ) call, USING REGEX UTILS : PATTERN AND MATCHER
                    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    Class paramReturnTypes [ ] = methodList [ i ].getParameterTypes ( );
                    String paramTypeDelimiter = "";
                    String paramTypeInput =  "", paramTypeOutput = "";
                    ArrayList paramTypeOutputs = new ArrayList ( );
                    for ( int j = 0; j < paramReturnTypes.length; j++ )
                    {
                        Pattern paramTypePattern = Pattern.compile ( "(.*)(\\s)(.*)(\\.)(.*)(\\.)(.*)" );
                        
                        String eachParamType =  paramReturnTypes [ j ].toString ( );
                        
                        if ( !conveniencePack.getIntegerIncrementEnquiry ( j, paramReturnTypes.length ) ) //FUCK YEAH...MY INTEGER INCREMENTATION VARIABLE IS WORKING FUCKING NICELEY HERE!!!
                            paramTypeDelimiter = "";
                        else
                            paramTypeDelimiter = ", ";
                        
                        paramTypeInput =  paramReturnTypes [ j ] + paramTypeDelimiter;
                        paramTypeOutput = "";
                        
                        Matcher paramTypeMatcher = paramTypePattern.matcher ( paramTypeInput );
                        
                        if ( paramTypeMatcher.find ( ) )
                        {
                            paramTypeOutput = paramTypeMatcher.replaceAll ( "$7" ); //replace all with tagged expression 7
                            paramTypeOutput = paramTypeOutput.replace ( ";", "[]" ); //finnaly tell what param types are arrays. I find that when ';' is returned, it is always array type.
                        }
                        else
                            paramTypeOutput = paramTypeInput; //else if pattern is not found be sure to return default.
                        
                        //collect param type outputs
                        paramTypeOutputs.add ( paramTypeOutput );
                        
                        //re-evaluate param type output
                        paramTypeOutput = "";
                        for ( int o = 0; o < paramTypeOutputs.size ( ); o ++ )
                            paramTypeOutput += ( String ) paramTypeOutputs.get ( o );
                    }
                      
                    value [ i ] = "public " + returnTypeOutput + " " + methodName + " (" + paramTypeOutput + ") ";
                    paramTypeOutputs.clear ( ); //reset param output aray list, prepare to derive next set of parameters
                }
            }
            //I WANT TO FUCKING SHOW PEOPLE THE WORK I HAVE JUST DONE WRITING THIS CLASS.
            //THEY WILL NOW APPRECIATE THE FACTUM THAT THE METHODS WHICH ARE SPAT OUT TO THEM,
            //ARE CONDENSED, AND ONLY REVEAL WHAT THEY WANT TO SEE......CAUSE I KNOW
            //I DON"T WANT TO SEE [Ljava.lang.String, when I could well see String[].
            else if ( regexUsageAnswer.equals ( "no" ) )
            {
                for ( int i = 0; i < methodList.length; i++ ) 
                    value [ i ] = methodList [ i ].toString ( );
            }
        }
        catch ( Throwable e ) {System.err.println(e);}

        return value;
    }
    
}
