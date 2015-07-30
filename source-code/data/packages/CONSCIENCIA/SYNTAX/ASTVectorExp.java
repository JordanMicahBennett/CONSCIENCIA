package data.packages.CONSCIENCIA.SYNTAX;

public class ASTVectorExp extends ASTNode //implements ASTExp
{
	String name = "vector";
	double x, y;
	
	public ASTVectorExp(double x, double y)
	{
		//super(name);
		this.x = x;
		this.y = y;
	}

	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}

	/*public Object visit(Visitor v, Object arg)
	{
		return v.visitVectorExp(this,arg);
	}*/
}
