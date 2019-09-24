class NodeQueue {
	Node head;
	Node tail;
	int size;

	// initialize the queue
	public NodeQueue() {
		head = null;
		tail = null;
		size = 0;
	}

	// check to see if the queue contains
	// the Node
	public boolean contains(Node q) {
		Node x = head;

		// iterate through queue to see if 
		// the node has any common states
		while(x != null)
		{
			if(q.currState.equals(x.currState))
				return true;
			x = x.next;
		}

		return false;
	}

	// add a Node to the queue
	public void add(Node q) {
		// if the queue is empty, you know
		// it's not in the queue, so add it
		if(head == null) {
			head = q;
			tail = head;
			size++;
			return;
		}

		// if not already in the queue, add it
		if(!this.contains(q)) {
			tail.next = q;
			tail = tail.next;
			size++;
		}
	}

	// return the head Node
	public Node remove() {
		if(head == null)
			return null;
		Node n = head;
		head = head.next;
		n.next = null;
		if(head == null)
			tail = head;
		size--;
		return n;
	}
}