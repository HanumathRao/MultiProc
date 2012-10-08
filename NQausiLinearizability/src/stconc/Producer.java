package stconc;

import java.util.Queue;

public class Producer implements Runnable {
	private static int counter = 0;
	private Queue<Message> messageQueue;

	public Producer(Queue<Message> messageQueue) {
		// TODO Auto-generated constructor stub
		this.messageQueue = messageQueue;
	}

	public void run() {
		long i = 0L;
		// Running the code for 2 billion time
		while (i < 50) {
			produce();
			i++;
		}
		System.out.println("Exiting Producer Thread");
	}

	private void produce() {
		Message msg = new Message(counter, "Data message");
		++counter;
		this.messageQueue.offer(msg);
		synchronized (messageQueue) {
			messageQueue.notifyAll();
			System.out.println("Producer : " + msg);
		}

	}

}
