package nquasi;

import java.util.Random;

public class NQuasiLinearizableQueue {
	private static final int SIZE = 1000;
	private Node[] nodes = new Node[SIZE];
	private int head = 0;
	private int tail = 0;
	private Random rand;

	public NQuasiLinearizableQueue() {
		rand = new Random();
		for (int i = 0; i < SIZE; ++i) {
			Node n = new Node();
			nodes[i] = n;
		}
	}

	public boolean isEmpty(){
		if(head == tail){
			return true;
		}
		return false;
	}
	
	public void enqueue(int number) {
		if (tail == SIZE) {
			System.out.println("Queue is full");
			return;
		}

		Node n = nodes[tail % SIZE];
		n.addElement(number);
		++tail;
	}

	public int dequeue() {
		int retval = -999;
		if (head == tail) {
			System.out.println("Queue is empty");
			return -999 ;
		}
		synchronized (nodes) {
			Node n = nodes[head % SIZE];
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
