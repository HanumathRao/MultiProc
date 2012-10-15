package core;

import threads.MyThread;

public class Main {

	public static void main(String args[]) {
		MyThread m;
		try {
			m = new MyThread();
			m.runAllThreads();
			m.showSharedmem();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
