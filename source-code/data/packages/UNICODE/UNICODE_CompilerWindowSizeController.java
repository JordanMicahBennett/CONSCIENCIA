package data.packages.UNICODE; //Author(s): Jordan Micah Bennett

public class UNICODE_CompilerWindowSizeController
{
    //attributes
    private String compilerWindowSizeString = null;

    public UNICODE_CompilerWindowSizeController ( String _compilerWindowSizeString )
    {
        compilerWindowSizeString = _compilerWindowSizeString;
    }
    
    //methods
        //accessors
        public String getCompilerWindowSizeString ( )
        {
            return compilerWindowSizeString;
        }
        //mutators
        public void setCompilerWindowSizeString ( String value )
        {
            compilerWindowSizeString = value;
        }

}
