package data.packages.CONSCIENCIA.SYNTAX;

//import smpl.semantics.Visitor;
import java.util.ArrayList;

public abstract class ASTNode
{
	ArrayList<ASTNode> children = new ArrayList<ASTNode>(); //List of the AST node's child nodes
	String node; //name of AST node

	public ASTNode()
	{
		this.node = "";
	}

	public ASTNode(String name)
	{
		this.node = name;
	}

	public ASTNode(ArrayList<ASTNode> children)
	{
		this.children = children;
	}

	public ASTNode(String name, ArrayList<ASTNode> children)
	{
		this.node = name;
		this.children = children;
	}

	//public abstract <S, T> T visit(Visitor<S, T> visitor, S state) throws Exception;

	public String getNode()
	{
		return node;
	}

	public ArrayList<ASTNode> getChildren()
	{
		return children;
	}


	public void setNode(String new_name)
	{
		node = new_name;
	}

	public void setChildren(ArrayList<ASTNode> new_children)
	{
		children = new_children;
	}

	protected String showChildren() //Generates a string representation of the node's children
	{
		String child_list;
		if(children.isEmpty())
		{
			child_list = "";		
		}
		else
		{
			child_list = children.get(0).toString();
			for (int i = 1; i < children.size(); i++) 
			{
                		child_list += ", " + children.get(i);
            		}
		}

		return child_list;
	}

	@Override
	public String toString() //Returns a string representation of the node's children
	{
		String child_list = showChildren();
		String display = "("+showChildren()+")";
		return display;			
	}
}
