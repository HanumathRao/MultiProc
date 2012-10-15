package nquasi;
/**
 * N-QuasiQueue Consume operation
 * @author Arijit
 *
 */
public class NConsumer implements Runnable {
	private NQuasiLinearizableQueueDynamic nqueue;
	private static final int MAX_ITERATION = 50;

	/**
	 * Constructor
	 * @param nqueue
	 */
	public NConsumer(NQuasiLinearizableQueueDynamic nqueue) {
		this.nqueue = nqueue;
	}

	/**
	 * In the deque operation  Consume thread should consume only when the queue is not empty
	 */
	@Override
	public void run() {
		int i = 0;
		while (i < MAX_ITERATION) {
//			Dequeing should be synchronized because otherwise many threads can dequeue can remove node from the same location which
//			will voilate the property of the N_quasi Queue.
			synchronized (nqueue) {
				if (!nqueue.isEmpty()) {
					consume();
					++i;
				}

			}

		}
	}

	/**
	 * Consume the node from the head
	 */
	public void consume() {
		System.out.println("Consumer : Message { id : " + nqueue.dequeue()
				+ " }");
	}

}
