import java.util.*;

// allows the PriorityQueue to sort
// Nodes based on their priority
class NodeComparator implements Comparator<Node> {
	public int compare(Node n1, Node n2) {
		if(n1.prio < n2.prio)
			return 1;
		else if(n1.prio > n2.prio)
			return -1;
		return 0;
	}
}