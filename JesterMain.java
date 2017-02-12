

import java.io.*;
import java.util.Scanner;
import java.util.*;
import java.lang.*;
public class JesterMain implements UserInterface {
    public static int maxnum = 0; 
	public static int goalCount = 0; 
	public static int Sum = 0; 
	public static int i = 0; 
	public static int c=0;
    public static int k=0;
    public static void main(String[] args) {
       Scanner Input= new Scanner(System.in);
	    
		System.out.println("Hello! my name is Jester");
		System.out.println("Which game would you like to play?");
		System.out.println("1. Counting Game");
		System.out.println("2. Guessing Game");
		c=Input.nextInt();
		switch(c)
		{
		    case 1:
		    {   
		        System.out.println("It is a two player game. The idea is simple, you want to count up to a number by adding certain numbers. The one who reaches the number first, wins.");
		        accept();
		          break;
		      }
		      case 2:
		      {   
		          JesterMain tq = new JesterMain();
                  tq.run();
		          break;
		      }
		      
		      default:
		      {
		          System.out.println("Invalid Choice");
		          
		
		  }
        
    }
}
   public static void accept()
    {
        Scanner Input= new Scanner(System.in);
    while ((goalCount<=0)||(goalCount>= 101)) { 
		    System.out.println("Enter the Goal count of the game(1-100)");
			goalCount = Input.nextInt();
		} 
		while ((maxnum > 10) || (maxnum < 2)) { 
		    System.out.println("What is the maximum number that can be added?(between 2 and 10");
		    
			maxnum = Input.nextInt();
		} 

		while (Sum < goalCount) { 
		    Sum = Computer(Sum, goalCount, maxnum); 
			Sum = User(Sum, maxnum); 
			if(k<5)
			{k++;}
			else
			{
			    k=0;
			 }
			if(k==(int)(Math.random()*(3-1))+1)
			{  
			   System.out.println("Do you want to change the range?");
			   char c1=(char)Input.next().charAt(0);
			   if(c1=='Y'||c1=='y')
			   {
			       System.out.println("Enter the new maximum number that can be added?");
			       maxnum=Input.nextInt();
			     }
			     
			 }
		} 
}
public static int Computer(int From, int CountTo, int MaxAdd) { 
		int Ret = 0; 
		int ModVal = (CountTo % (MaxAdd + 1)); 
		for (i=1; i <= MaxAdd; i++) { 
			if ((Sum + i) % (maxnum + 1) == ModVal) { 
				Ret = From + i; 
				System.out.println("I added " + i + " to get " + Ret); 
				if (Ret == goalCount) { 
					System.out.println("I win!"); 
					System.exit(0); 
				} 
				i = MaxAdd + 10; 
			} 
		} 
		return Ret; 
	}
	public static int User(int From, int MaxAdd) {
	    Scanner Input= new Scanner(System.in);
		int Ret = 0; 
		int Adder = 0; 
		while (Adder > MaxAdd || Adder <= 0) { 
			System.out.println("Add?(1 to" + " "+ MaxAdd + ")");
			Adder=Input.nextInt();
		    
		} 
		Ret = From + Adder; 
		System.out.println("You added " + Adder + " to get " + Ret); 
		return Ret; 
	} 
    
    // fields
    private Scanner console;
    private JesterTree tree;
    
    public JesterMain() {
        console = new Scanner(System.in);
        tree = new JesterTree(this);
       
    }
    
    
    public String nextLine() {
        return console.nextLine();
    }

    public void print(String message) {
        System.out.print(message);
        System.out.print(" ");
    }
    public void println(String message) {
        System.out.println(message);
    }
    public void println() {
        System.out.println();
    }

   
    public boolean nextBoolean() {
        String answer = console.nextLine();
        return answer.trim().toLowerCase().startsWith("y");
    }
    
    // private helper for overall game(s) loop
    private void run() {
        println("Welcome to the guessing game");
        load();
        println("\n" + BANNER_MESSAGE);
            
        do {
           
            println();      
            tree.play();
            print(PLAY_AGAIN_MESSAGE);
        } while (nextBoolean());   
        
        
        println("\n" + String.format(STATUS_MESSAGE,
                tree.totalGames(), tree.gamesWon()));

        save();
    }
    
   
    private void load() {
        //print(LOAD_MESSAGE);
        //if (nextBoolean()) {
           //print(SAVE_LOAD_FILENAME_MESSAGE);
           // String filename = nextLine();
            try {
                Scanner in = new Scanner(new File("jestercp"));
                tree.load(in);
            } catch (FileNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
       //}
    }
    
    private void save() {
        //print(SAVE_MESSAGE);
        //if (nextBoolean()) {
            //print(SAVE_LOAD_FILENAME_MESSAGE);
            //String filename = nextLine();
            try {
                PrintStream out = new PrintStream(new File("jestercp"));
                tree.save(out);
                out.close();
            } catch (FileNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
        //}
    }
}