package core;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {
	/*
	 *  @Author Arijit
	 */
	public static void main(String args[]){
		long startTime = System.nanoTime();
		Queue<Message> msgQ = new ConcurrentLinkedQueue<Message>();
		
		Producer producer = new Producer(msgQ);
		Consumer consumer = new Consumer(msgQ);
		
		Thread producerThread = new Thread(producer);
		Thread consumerThread = new Thread(consumer);
		
		producerThread.start();
		consumerThread.start();
		try {
			producerThread.join();
			consumerThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long endTime = System.nanoTime();
		System.out.println("Total time taken : "+(endTime - startTime));
	}
}

