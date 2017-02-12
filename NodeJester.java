
public class NodeJester
{
	public String data; 	// data stored at this node
	public NodeJester left;
	public NodeJester right;
	 
	public NodeJester(String data)
	{
		this(data, null, null);
		// TODO Auto-generated constructor stub
	}
	
	public NodeJester(String data, NodeJester left, NodeJester right)
	{
		this.data = data;
		this.left = left;
		this.right = right;
	}
}
