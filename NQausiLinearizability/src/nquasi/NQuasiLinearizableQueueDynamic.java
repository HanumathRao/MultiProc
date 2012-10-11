package nquasi;

import java.util.ArrayList;
import java.util.Random;

public class NQuasiLinearizableQueueDynamic {
	int SIZE = 0 ;
	ArrayList<Node> nodes = new ArrayList<Node>();
	private int head = 0;
	private int tail = 0;
	private Random rand;

	public NQuasiLinearizableQueueDynamic(int size) {
		this.SIZE = size*50;
		rand = new Random();
		for (int i = 0; i < SIZE; ++i) {
			Node n = new Node();
			nodes.add(n);
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

		Node n = nodes.get(tail%SIZE);
		n.addElement(number);
		synchronized (nodes) {
			++tail;	
		}
	}

	public int dequeue() {
		int retval = -999;
		if (head == tail) {
			System.out.println("Queue is empty");
			return -999 ;
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
