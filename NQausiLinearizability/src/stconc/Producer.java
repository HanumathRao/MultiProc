package stconc;

import java.lang.reflect.Constructor;
import java.util.Queue;

public class Producer implements Runnable {
	private static int counter = 0;
	private Queue<Message> messageQueue;
/**
 * {@link Constructor}
 */
	public Producer(Queue<Message> messageQueue) {
		this.messageQueue = messageQueue;
	}

	/**
	 * Produce method should add up data asynchronously
	 */
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
	}

}
