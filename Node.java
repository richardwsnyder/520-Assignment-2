import java.util.*;

class Node {
	Node parent;
	State currState;
	ArrayList<Node> children;
	// hashed string
	String hString;
	// priority for priority queue
	int prio;
	// next node for NodeQueue
	Node next;
	String pathToNode;
	int depth;

	public Node(Node p, State s) {
		parent = p;
		currState = new State(s.tiles);
		children = new ArrayList<>();
		hString = "";
		if(p == null)
		{
			pathToNode = new String("Initial state -> ");
			depth = 0;
		}
		else
			pathToNode = new String(p.pathToNode);
	}

	public Node(Node p, State s, int priority) {
		parent = p;
		currState = new State(s.tiles);
		children = new ArrayList<>();
		hString = "";

		if(p != null)
		{ 		
			//     h(n)    +   g(n)
			prio = priority + p.prio;
			pathToNode = new String(p.pathToNode);
			depth = p.depth + 1;
		}

		// if this is the root node, 
		// it has a priority of 0
		else
		{
			depth = 0;
			prio = priority;
			pathToNode = new String("Initial state -> ");
		}
	}

	// HEURISTIC FUNCTION!!!
	public static int calculatePrio(State s) {
		// initialize priority of state to 0
		int prio = 0, i, size = s.tiles.size();

		// for each tile
		for(i = 0; i < size; i++)
		{
			Tile t = s.tiles.get(i);
			int rotationsFromLatt, rotationsFromLong;
			// find out how many rotations it would take to 
			// get this individual tile to its destination.
			// The coordinates are in multiples of 30, so divide by
			// 30 to get number of turns necessary.
			rotationsFromLatt = Math.abs(t.currLatt - t.expectedLatt) / 30;
			rotationsFromLong = Math.abs(t.currLong - t.expectedLong) / 30;

			prio += rotationsFromLong + rotationsFromLatt;
		}

		// average over all tiles
		prio /= size;
		return prio;
	}

	// add actions from current state to 
	// this Node's children array for AStar
	public void addChildrenAStar() {
		int i, size, prio;

		State incVert1 = new State(currState.tiles);
		incVert1.incVertOne();

		prio = calculatePrio(incVert1);
		Node n1 = new Node(this, incVert1, prio);

		size = n1.currState.tiles.size();
		for(i = 0; i < size; i++)
		{
			Tile t = n1.currState.tiles.get(i);
			n1.hString += t.key + " " + t.currLatt + " " + t.currLong + " ";
		}
		n1.hString += '\n';

		n1.pathToNode += "increment 0/180 axis by 30 -> ";
		children.add(n1);


		State decVert1 = new State(currState.tiles);
		decVert1.decVertOne();

		prio = calculatePrio(decVert1);
		Node n2 = new Node(this, decVert1, prio);

		size = n2.currState.tiles.size();
		for(i = 0; i < size; i++)
		{
			Tile t = n2.currState.tiles.get(i);
			n2.hString += t.key + " " + t.currLatt + " " + t.currLong + " ";
		}
		n2.hString += '\n';

		n2.pathToNode += "decrement 0/180 axis by 30 -> ";
		children.add(n2);


		State incVert2 = new State(currState.tiles);
		incVert2.incVertTwo();

		prio = calculatePrio(incVert2);
		Node n3 = new Node(this, incVert2, prio);

		size = n3.currState.tiles.size();
		for(i = 0; i < size; i++)
		{
			Tile t = n3.currState.tiles.get(i);
			n3.hString += t.key + " " + t.currLatt + " " + t.currLong + " ";
		}
		n3.hString += '\n';

		n3.pathToNode += "increment 90/270 axis by 30 -> ";
		children.add(n3);


		State decVert2 = new State(currState.tiles);
		decVert2.decVertTwo();

		prio = calculatePrio(decVert2);
		Node n4 = new Node(this, decVert2, prio);

		size = n4.currState.tiles.size();
		for(i = 0; i < size; i++)
		{
			Tile t = n4.currState.tiles.get(i);
			n4.hString += t.key + " " + t.currLatt + " " + t.currLong + " ";
		}
		n4.hString += '\n';

		n4.pathToNode += "decrement 90/270 axis by 30 -> ";
		children.add(n4);


		State incEq = new State(currState.tiles);
		incEq.incEquator();

		prio = calculatePrio(incEq);
		Node n5 = new Node(this, incEq, prio);

		size = n5.currState.tiles.size();
		for(i = 0; i < size; i++)
		{
			Tile t = n5.currState.tiles.get(i);
			n5.hString += t.key + " " + t.currLatt + " " + t.currLong + " ";
		}
		n5.hString += '\n';

		n5.pathToNode += "increment equator by 30 -> ";
		children.add(n5);


		State decEq = new State(currState.tiles);
		decEq.decEquator();

		prio = calculatePrio(decEq);
		Node n6 = new Node(this, decEq, prio);

		size = n6.currState.tiles.size();
		for(i = 0; i < size; i++)
		{
			Tile t = n6.currState.tiles.get(i);
			n6.hString += t.key + " " + t.currLatt + " " + t.currLong + " ";
		}
		n6.hString += '\n';

		n6.pathToNode += "decrement equator by 30 -> ";
		children.add(n6);
	}

	// add actions from current state to 
	// this Node's children array for BFS
	public void addChildren() {
		int i, size;

		State incVert1 = new State(currState.tiles);
		incVert1.incVertOne();
		Node n1 = new Node(this, incVert1);
		size = n1.currState.tiles.size();
		for(i = 0; i < size; i++)
		{
			Tile t = n1.currState.tiles.get(i);
			n1.hString += t.key + " " + t.currLatt + " " + t.currLong + " ";
		}
		n1.hString += '\n';
		n1.pathToNode += "increment 0/180 axis by 30 -> ";
		children.add(n1);

		State decVert1 = new State(currState.tiles);
		decVert1.decVertOne();
		Node n2 = new Node(this, decVert1);
		size = n2.currState.tiles.size();
		for(i = 0; i < size; i++)
		{
			Tile t = n2.currState.tiles.get(i);
			n2.hString += t.key + " " + t.currLatt + " " + t.currLong + " ";
		}
		n2.hString += '\n';
		n2.pathToNode += "decrement 0/180 axis by 30 -> ";
		children.add(n2);

		State incVert2 = new State(currState.tiles);
		incVert2.incVertTwo();
		Node n3 = new Node(this, incVert2);
		size = n3.currState.tiles.size();
		for(i = 0; i < size; i++)
		{
			Tile t = n3.currState.tiles.get(i);
			n3.hString += t.key + " " + t.currLatt + " " + t.currLong + " ";
		}
		n3.hString += '\n';
		n3.pathToNode += "increment 90/270 axis by 30 -> ";
		children.add(n3);

		State decVert2 = new State(currState.tiles);
		decVert2.decVertTwo();
		Node n4 = new Node(this, decVert2);
		size = n4.currState.tiles.size();
		for(i = 0; i < size; i++)
		{
			Tile t = n4.currState.tiles.get(i);
			n4.hString += t.key + " " + t.currLatt + " " + t.currLong + " ";
		}
		n4.hString += '\n';
		n4.pathToNode += "decrement 90/270 axis by 30 -> ";
		children.add(n4);

		State incEq = new State(currState.tiles);
		incEq.incEquator();
		Node n5 = new Node(this, incEq);
		size = n5.currState.tiles.size();
		for(i = 0; i < size; i++)
		{
			Tile t = n5.currState.tiles.get(i);
			n5.hString += t.key + " " + t.currLatt + " " + t.currLong + " ";
		}
		n5.hString += '\n';
		n5.pathToNode += "increment equator by 30 -> ";
		children.add(n5);

		State decEq = new State(currState.tiles);
		decEq.decEquator();
		Node n6 = new Node(this, decEq);
		size = n6.currState.tiles.size();
		for(i = 0; i < size; i++)
		{
			Tile t = n6.currState.tiles.get(i);
			n6.hString += t.key + " " + t.currLatt + " " + t.currLong + " ";
		}
		n6.hString += '\n';
		n6.pathToNode += "decrement equator by 30 -> ";
		children.add(n6);
	}
}