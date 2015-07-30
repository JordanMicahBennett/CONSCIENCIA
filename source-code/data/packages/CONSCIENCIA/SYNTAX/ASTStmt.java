/*This is the AST node representing statements. */

package data.packages.CONSCIENCIA.SYNTAX;

import java.util.ArrayList;

public class ASTStmt extends ASTNode implements ASTExp
{
	String name = "statement";
	
	public ASTStmt()
	{
		super();
	}

	public ASTStmt(ArrayList<ASTNode> exps)
	{
		//super(name,exps);
	}

	/*public Object visit(Visitor v, Object arg)
	{
		return v.visitStmt(this,arg);
	}*/

}
