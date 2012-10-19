package measurement;

import java.util.Random;

import list.FineGrainedConcLinkedList;
import list.Node;

public class FineGrainMeasurement implements Runnable {
	FineGrainedConcLinkedList<Integer> fineGrainedList;
	Thread insertThread;
	Thread deletThread;
	Random rand;

	public  FineGrainMeasurement(FineGrainedConcLinkedList<Integer> list) {
		rand = new Random();
		this.fineGrainedList = list;
		insertThread = new Thread(this);
		deletThread = new Thread(this);
		insertThread.setName("insert");
		deletThread.setName("delete");
		insertThread.start();
		deletThread.start();
	}

	public void run() {
		long i = 0;
		while (i < 1000000) {
			Integer item = new Integer(rand.nextInt(1000));
			if (Thread.currentThread().getName().equalsIgnoreCase("insert")) {
				fineGrainedList.add(item);
			} else if (Thread.currentThread().getName()
					.equalsIgnoreCase("delete")) {
				fineGrainedList.remove(item);
			} else {
				/*
				 * Do nothing
				 */
			}
			// Thread.sleep(100);
		}
	}
}
