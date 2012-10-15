package threads;

import java.util.Random;

import lock.AQLock;
import lock.IConst;

public class MyThread implements Runnable, IConst {
	final int shrmSize = 50;
	private int[] sharedMem = new int[shrmSize];
	private Thread[] threads = new Thread[SIZE];
	private AQLock lock = new AQLock();
	private Random rand;

	public MyThread() throws InterruptedException {
		rand = new Random();
		for (int i = 0; i < SIZE; ++i) {
			Thread t = new Thread(this);
			threads[i] = t;
			threads[i].setName("" + i);
			threads[i].join();
		}

		for (int i = 0; i < shrmSize; ++i) {
			sharedMem[i] = rand.nextInt(1000);
		}
	}

	public void runAllThreads() {
		for (int i = 0; i < SIZE; ++i) {
			threads[i].start();
		}
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; ++i) {
			try {
				lock.lock();
//				System.out.println(Thread.currentThread().getName());
				int position = rand.nextInt(49);
				sharedMem[position] = 0;
				Thread.currentThread().sleep(rand.nextInt(500));
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			} finally {
				lock.unlock();
			}
		}

	}

	public void showSharedmem() {
		for (int i = 0; i < 50; ++i) {
			System.out.println("The value is : " + sharedMem[i]);
		}
	}

}
