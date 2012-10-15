package nquasi;

import java.util.ArrayList;
import java.util.Random;

public class NQuasiLinearizableQueueDynamic {
	int SIZE = 0;
	// Each node consists of the array, and the queue consists of the arraylist
	// of the node. Arraylist makes the Q more dynamic
	ArrayList<Node> nodes = new ArrayList<Node>();
	private int head = 0;
	private int tail = 0;
	// Radom number required with uniform distribution so that we treat each
	// cell equally while dequeing.
	private Random rand;

	/**
	 * Constructor
	 * 
	 * @param size
	 *            : Size of the arraylist
	 */
	public NQuasiLinearizableQueueDynamic(int size) {
		this.SIZE = size * 50;
		rand = new Random();
		for (int i = 0; i < SIZE; ++i) {
			Node n = new Node();
			nodes.add(n);
		}
	}

	/**
	 * Checks whether the q is empty or nor
	 * 
	 * @return : false if non empty and vice versa
	 */
	public boolean isEmpty() {
		if (head == tail) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param number
	 *            : number to be inserted into the que
	 */
	public void enqueue(int number) {
		if (tail == SIZE) {
			System.out.println("Queue is full");
			return;
		}
		// Enq operation is not sequential so that it will allow many threads to
		// enq at same location
		Node n = nodes.get(tail % SIZE);
		n.addElement(number);
		++tail;
	}

	/**
	 * Deque operation should be synchronized since otherwise two threads can deque from the same location which is not intended.
	 * @return : Return the dqueue value from all possible values of equally distributed values
	 */
	public int dequeue() {
		int retval = -999;
		if (head == tail) {
			System.out.println("Queue is empty");
			return -999;
		}
		synchronized (nodes) {
			Node n = nodes.get(head % SIZE);
			if (n != null) {
				int[] cells = n.getNodeArray();
				int r = rand.nextInt(cells.length - 1);
				retval = cells[r];
				++head;
			}
		}
		return retval;
	}
}
