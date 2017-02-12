import java.io.PrintStream;
import java.util.Scanner;


public class JesterTree
{
	
	
	private NodeJester overallRoot;
	
	
	private UserInterface ui;
	
	
	private int numGames;
	
	
	private int gamesWon;


	public JesterTree(UserInterface ui)
	{
		this.ui = ui;
		overallRoot = new NodeJester("God");
		this.numGames = 0;
		this.gamesWon = 0;
	}

	
	public void play() 
	{
		numGames++;
		overallRoot = play(overallRoot);
	}

	
	private NodeJester play(NodeJester root) 
	{
		String response;

		if (root.left != null && root.right != null)
		{
			ui.println(root.data); 

			// question
			response = ui.nextLine();
			if (response.equals("yes") || response.equals("y"))	
			{
				root.left = play(root.left);
			}
			else if (response.equals("no") || response.equals("n"))
			{
				root.right = play(root.right);				
			}
		}
		else
		{
			ui.println("Would your object happen to be "+ root.data.substring(0) + "?");
			response = ui.nextLine();

			if (response.equals("yes") || response.equals("y"))
			{
				ui.println("I win!");
				gamesWon++;
			}
			else if (response.equals("no") || response.equals("n"))
			{
				ui.println("I lose.  What is your object?");
				String newObjectText = ui.nextLine();

				ui.println("Type a yes/no question to distinguish your item "
						+ "from " + root.data.substring(0) + ":");
				String questionText = ui.nextLine();				
				ui.println("And what is the answer for your object?");
				boolean answerText = ui.nextBoolean();

				NodeJester newQuestion = new NodeJester(questionText);
				NodeJester newAnswer = new NodeJester(newObjectText);

				NodeJester oldRoot = root;

				// yes response
				if (answerText)
				{
					newQuestion.left = newAnswer;
					newQuestion.right = oldRoot;
				}

				// no response
				else 
				{
					newQuestion.left = oldRoot;
					newQuestion.right = newAnswer;		
				}

				root = newQuestion;
			}
		}
		return root;
	}


	
	public void save(PrintStream output)
	{
		save(output, overallRoot);
	}

	
	private void save(PrintStream output, NodeJester root)
	{
		if (root != null)
		{
			if (root.left != null && root.right != null)
			{
				
				//ui.println(root.data);
				output.println("Q:" + root.data);
				save(output, root.left);
				save(output, root.right);	
			}
			else
			{
				//ui.println(root.data);
				output.println(root.data);
			}	
		}
	}
	
	
	public void load(Scanner input)
	{
		overallRoot = load(input, overallRoot);
	}
	
	
	private NodeJester load(Scanner input, NodeJester current)
	{
		if (input.hasNextLine())
		{
			String line = input.nextLine();
			
			current = new NodeJester(line);
			
			// check for a Q or A 
			char responseType = line.charAt(0);
			
			if (current != null)
			{
				if (responseType == 'Q')
				{
					line = line.substring(2, line.length());
					current.left = load(input, current);
					current.right = load(input, current);
				}
			}
		}
		return current;
	}
	
	
	public int totalGames()
	{
		return numGames;
	}

	
	public int gamesWon()
	{
		return gamesWon;
	}
}
