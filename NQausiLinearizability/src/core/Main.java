package core;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JOptionPane;

public class Main {
	/*
	 * @Author Arijit
	 */
	public static void main(String args[]) {
		long startTime = System.nanoTime();
		Queue<Message> msgQ = new ConcurrentLinkedQueue<Message>();

		int noOfThreads = Integer
				.parseInt(JOptionPane
						.showInputDialog("Enter the number of producer and consumer threads"));
		for (int i = 0; i < noOfThreads; ++i) {
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

		}

		long endTime = System.nanoTime();
		System.out.println("Total time taken in mili seconds : "
				+ (long) ((endTime - startTime) / 1000000));
	}
}
