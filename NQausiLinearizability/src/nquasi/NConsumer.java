package nquasi;

public class NConsumer implements Runnable {
	private NQuasiLinearizableQueueDynamic nqueue;
	private static final int MAX_ITERATION = 50;

	public NConsumer(NQuasiLinearizableQueueDynamic nqueue) {
		this.nqueue = nqueue;
	}

	public void run() {
		int i = 0;
		while (i < MAX_ITERATION) {
			synchronized (nqueue) {
				if (!nqueue.isEmpty()) {
					consume();
					++i;
				}

			}

		}

		/*for (i = 0; i < MAX_ITERATION; ++i) {
			synchronized (nqueue) {
				if (nqueue.isEmpty()) {
					try {
						nqueue.wait();
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				} else {
					consume();
				}
			}
		}*/
	}

	public void consume() {

		System.out.println("Consumer : Message { id : " + nqueue.dequeue()
				+ " }");
	}

}
