import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

// where all the magic happens
class NaryTree {
	Node root;

	// initialize the tree to root r
	public NaryTree(Node r) {
		root = r;
	}

	// if a solution is found, print it to the file
	public void printSolution(Node ans, FileWriter f, int counter) throws IOException {
		f.write(ans.pathToNode + "solution\n");
		f.write("The solution took " + ans.depth + " rotations of 30 degrees");
		f.write("The solution expanded upon " + counter + " nodes.");
		f.write("Solution takes the form of key (currentLattitude, currentLongitude): \n");
		f.write(ans.currState.printState());
		f.close();
	}

	// Breadth first search implementation
	public void BFS(Node root, State goal) throws IOException {
		FileWriter fileWriter = new FileWriter("./BFSoutput.txt");
		int counter = 0;
		Node n = root;

		// check to see if the goal 
		// was passed in
		if(n.currState.equals(goal))
		{
			printSolution(n, fileWriter, counter);
			return;
		}

		// create a hash string for root
		int i, size = n.currState.tiles.size();
		for(i = 0; i < size; i++)
		{
			Tile t = n.currState.tiles.get(i);
			n.hString += t.key + " " + t.currLatt + " " + t.currLong + " ";
		}
		n.hString += '\n';
		
		// initialize the frontier and explored set
		NodeQueue frontier = new NodeQueue();
		frontier.add(n);
		HashSet<String> explored = new HashSet<>();

		// while the frontier isn't empty
		while(frontier.size > 0)
		{
			n = frontier.remove();
			// increment the number of explored 
			// nodes
			counter++;
			explored.add(n.hString);

			// generate actions from current state
			n.addChildren();
			for(Node x : n.children)
			{
				if(!explored.contains(x.hString) && !frontier.contains(x))
				{
					// check to see if child is goal
					if(x.currState.equals(goal))
					{
						printSolution(x, fileWriter, counter);
						return;
					}
					frontier.add(x);
				}
			}
			if(counter % 500 == 0)
				fileWriter.write("There's another 500 expanded nodes " + counter);
		}

		// if you reach an empty queue and a solution hasn't been found, then there is none
		fileWriter.write("No solution!");
		return;
	}

	// AStar implementation
	public void AStar(Node root, State goal) throws IOException {
		FileWriter fileWriter = new FileWriter("./AStaroutput.txt");
		int counter = 0;

		Node n = root;

		// initialize priority queue
		PriorityQueue<Node> frontier = new PriorityQueue<>(new NodeComparator());
		frontier.add(n);

		HashSet<String> explored = new HashSet<>();
		while(frontier.size() > 0)
		{
			Node z = frontier.poll();

			// increment how many nodes have been expanded
			counter++;

			// check if found a solution
			if(z.currState.equals(goal))
			{
				printSolution(n, fileWriter, counter);
				return;
			}
			explored.add(z.hString);

			// generate actions from current state
			z.addChildrenAStar();
			for(Node x : z.children)
			{
				if(!explored.contains(x.hString) && !frontier.contains(x))
					frontier.add(x);
				
				// if there exists a node with same state but higher path cost, 
				// remove it and replace it with lower prio value
				Iterator<Node> iter = frontier.iterator();
				while(iter.hasNext())
				{
					Node zn = iter.next();
					if(zn.currState.equals(x.currState) && x.prio < zn.prio)
					{
						frontier.remove(zn);
						frontier.add(x);
						break;
					}
				}
			}

			if(counter % 500 == 0)
				fileWriter.write("There's another 500 expanded nodes " + counter);
		}
		fileWriter.write("No solution!");
		fileWriter.close();

		return;
	}
}