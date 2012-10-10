package core;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JOptionPane;

import nquasi.NConsumer;
import nquasi.NProducer;
import nquasi.NQuasiLinearizableQueue;
import nquasi.NQuasiLinearizableQueueDynamic;

import stconc.Consumer;
import stconc.Message;
import stconc.Producer;

public class Main {
	/*
	 * @Author Arijit
	 */

	public static int numberOfThreads = 0;

	public static void measureConLinkQ() {

		long startTime = System.nanoTime();
		Queue<Message> msgQ = new ConcurrentLinkedQueue<Message>();

		for (int i = 0; i < Main.numberOfThreads; ++i) {
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

	public static void measureNQuasiQ() {
		long startTime = System.nanoTime();
		NQuasiLinearizableQueueDynamic nqueu = new NQuasiLinearizableQueueDynamic(Main.numberOfThreads);
		for (int i = 0; i < Main.numberOfThreads; ++i) {
			NProducer producer = new NProducer(nqueu);
			NConsumer consumer = new NConsumer(nqueu);

			Thread producerThread = new Thread(producer);
			Thread consumerThread = new Thread(consumer);

			producerThread.start();
			consumerThread.start();

			try {
				producerThread.join();
				consumerThread.join();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}

		}
		
		long endTime = System.nanoTime();
		System.out.println("Total time taken in mili seconds : "+(long)((endTime-startTime)/1000000));

	}

	public static void main(String args[]) {

		Main.numberOfThreads = Integer
				.parseInt(JOptionPane
						.showInputDialog("Enter the number of producer and consumer threads"));
		measureConLinkQ();
		System.out.println("\n******************************************************\n");
		measureNQuasiQ();
	}
}