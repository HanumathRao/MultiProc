package core;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JOptionPane;

import nquasi.NConsumer;
import nquasi.NProducer;
import nquasi.NQuasiLinearizableQueueDynamic;

import stconc.Consumer;
import stconc.Message;
import stconc.Producer;

/*
 *  This class is used to measure the performance of the Java std Concurrent Linked Queue
 *  and N-Quasi Queue. Since the Paper proposes about the implementation in Server, where tasks
 *  are arranged in task queue, so in this project performance is measured with a Standard Producer-Consumer problem
 *  This program measures the performance wiht varying number of threads. So user need to run this in gui terminal as it 
 *  pops up the JOptionPane Input dialog.
 */
public class Main {
	/*
	 * @Author Arijit
	 */
//Stores the number of threads
	public static int numberOfThreads = 0;
	

	/**
	 * Time measurement for ConcurrentLinkedQueue
	 * @return : Time taken for 50 enq and 50 deq operations on a ConcurrentlinkedQueue
	 */
	public static long measureConLinkQ() {

		long startTime = System.nanoTime();
		Queue<Message> msgQ = new ConcurrentLinkedQueue<Message>();

		//Cretes as many threads as mentioned by the user
		for (int i = 0; i < Main.numberOfThreads; ++i) {
			Producer producer = new Producer(msgQ);
			Consumer consumer = new Consumer(msgQ);

//			Creates the Producer and consumer threads
			Thread producerThread = new Thread(producer);
			Thread consumerThread = new Thread(consumer);

			producerThread.start();
			consumerThread.start();
//			Main thread should wait until both of them are finished.
			try {
				producerThread.join();
				consumerThread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		long endTime = System.nanoTime();
		return (long) ((endTime - startTime) / 1000000);
	}

	/**
	 * 
	 * @return : Measures the time taken by N-Quasi Queue for 50 enq and 50 deq operations in milisecs
	 */
	public static long measureNQuasiQ() {
		long startTime = System.nanoTime();
		NQuasiLinearizableQueueDynamic nqueu = new NQuasiLinearizableQueueDynamic(
				Main.numberOfThreads);
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
		return  (long) ((endTime - startTime) / 1000000);

	}

	public static void main(String args[]) {

		Main.numberOfThreads = Integer
				.parseInt(JOptionPane
						.showInputDialog("Enter the number of producer and consumer threads"));
		
		long stdPerformance = measureConLinkQ();;
		long myPerformance = measureNQuasiQ();

		System.out.println("Std Performance " + stdPerformance
				+ " My Performance : " + myPerformance);
	}
}