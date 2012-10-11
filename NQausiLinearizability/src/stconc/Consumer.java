package stconc;

import java.util.Queue;

public class Consumer implements Runnable {

	private Queue<Message> msgQ;
//	Maximum number of iterations
	private static long max = 50;
// Consumer
	public Consumer(Queue<Message> msgQ) {
		this.msgQ = msgQ;
	}

	/**
	 * Dqueue operations should be synchronized so that threads remove the data
	 * serially
	 */
	@Override
	public void run() {
		long i = 0;
		while (i < max) {
//			Remove the data serially
			synchronized (msgQ) {
				if(!msgQ.isEmpty()){
					Message msg = msgQ.poll();
					System.out.println("Consumer : "+msg.toString());
					++i;
				}
			}
		}
		System.out.println("Exiting Consumer thread");
	}
}
