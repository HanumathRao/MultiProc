package core;

import java.util.Queue;

public class Consumer implements Runnable {

	private Queue<Message> msgQ;
	private static long max = 50;

	public Consumer(Queue<Message> msgQ) {
		this.msgQ = msgQ;
	}

	@Override
	public void run() {
		long i = 0;
		while (i < max) {
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
