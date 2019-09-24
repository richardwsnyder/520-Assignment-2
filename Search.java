import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.*;

class Search {

	// creat the goal state for the program
	public static State createGoal(ArrayList<Tile> copy) {
		State s = new State(copy);
		int i, size = copy.size();
		for(i = 0; i < size; i++)
		{
			Tile ot = copy.get(i);
			Tile t = s.tiles.get(i);
			t.currLatt = ot.expectedLatt;
			t.currLong = ot.expectedLong;
		}

		return s;
	}

	public static void main(String args[]) throws FileNotFoundException {

		// check if enough parameters are passed in
		if(args.length < 2) {
			System.out.println("Not enough arguments");
			System.out.println("Correct format is: java Search <ALG> <FILENAME>");
			return;
		}

		ArrayList<Tile> initialState = new ArrayList<>();

		// open input file
		File f = new File(args[1]);
		Scanner scan = new Scanner(f);
		scan.nextLine();

		// read in lines from file for each 
		// individual tile
		while(scan.hasNextLine())
		{
			String line = scan.nextLine();
			// skip over closing </Marble>
			if(line.charAt(0) == '<')
				continue;

			// split line into different categories
			String delims = "[(,)]";
			String[] lineSegs = line.split(delims);

			//									currLatt			currLong					expectedLat						expectedLong					key
			Tile t = new Tile(Integer.parseInt(lineSegs[3]), Integer.parseInt(lineSegs[4]), Integer.parseInt(lineSegs[7]), Integer.parseInt(lineSegs[8]), lineSegs[1]);
			// add tile to initial state
			initialState.add(t);
		}

		State s = new State(initialState);
		String algo = args[0];
		State goal = createGoal(initialState);

		if(algo.equals("BFS"))
		{
			// create a root node with a null
			// parent and currState equal to 
			// initialState, no heuristic
			Node rootBFS = new Node(null, s);

			// create an N-ary tree with rootBFS as 
			// leading Node
			NaryTree treeBFS = new NaryTree(rootBFS);

			// try BFS
			try {
				treeBFS.BFS(rootBFS, goal);
			}
			// catch IOException
			catch(Exception e) {
				System.out.println("Error occured: " + e.toString());
			}
		}

		if(algo.equals("AStar"))
		{
			// create a root node with a null
			// parent and currState equal to 
			// initialState, with heuristic 
			// initially equal to 0
			Node rootAStar = new Node(null, s,  0);

			// create an N-ary tree with rootAStar as 
			// leading Node
			NaryTree treeAStar = new NaryTree(rootAStar);

			// try AStar
			try {
				treeAStar.AStar(rootAStar, goal);
			}
			// catch IOException
			catch(Exception e) {
				System.out.println("Error occured: " + e.toString());
			}
		}

		// I could not implement RBFS, sorry :(
		if(algo.equals("RBFS"))
			System.out.println("I did not implement RBFS. I accept whatever deductions come from that\n");

		return;
	}
}