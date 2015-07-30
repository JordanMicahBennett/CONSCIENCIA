//Author ~ Jordan Micah Bennett
package data.packages.CONSCIENCIA.SYNTAX;

public class ConscienciaTrueQueryBmiFeedback
{
	//attributes
	public double PERSON_HEIGHT_FEET_INPUT = 0.0;
	public int PERSON_HEIGHT_INCH_INPUT = 0;
	public double PERSON_WEIGHT_INPUT = 0.0;
	public String GENDER_STRING = "";
	
	//constructor
	public ConscienciaTrueQueryBmiFeedback ( double PERSON_HEIGHT_FEET_INPUT, int PERSON_HEIGHT_INCH_INPUT, String GENDER_STRING )
	{
		this.PERSON_HEIGHT_FEET_INPUT = PERSON_HEIGHT_FEET_INPUT;
		this.PERSON_HEIGHT_INCH_INPUT = PERSON_HEIGHT_INCH_INPUT;
		this.GENDER_STRING = GENDER_STRING;
	}
	
	public String toString ( )
	{
		return "_gender_" + GENDER_STRING + "---_height.feet_" + PERSON_HEIGHT_FEET_INPUT + "---_height.inch_" + PERSON_HEIGHT_INCH_INPUT;
	}
}