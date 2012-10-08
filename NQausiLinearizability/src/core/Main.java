package core;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {
	/*
	 *  @Author Arijit
	 */
	public static void main(String args[]){
		
		Queue<Message> msgQ = new ConcurrentLinkedQueue<Message>();
		
		Producer producer = new Producer(msgQ);
		Consumer consumer = new Consumer(msgQ);
		
		Thread producerThread = new Thread(producer);
		Thread consumerThread = new Thread(consumer);
		
		producerThread.start();
		consumerThread.start();
		
	}
}

