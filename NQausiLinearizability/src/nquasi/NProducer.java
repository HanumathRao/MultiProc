package nquasi;

public class NProducer implements Runnable {
	
	private static final int MAX_ITERATION =50;
	private NQuasiLinearizableQueueDynamic nQueue;

	/**
	 * 
	 * @param nQueue : Constructor
	 */
	public NProducer(NQuasiLinearizableQueueDynamic nQueue) {
		this.nQueue = nQueue;
	}
	
	@Override
	public void run(){
		for(int i =0 ; i < MAX_ITERATION ; ++i){
			produce(i);
		}
	}
	
/**
 * Enque operation is not synchronized, since in N-QuasiQueue many threads can push the data in the same node
 * @param i : data to be pushed
 */
	public void produce(int i){
			nQueue.enqueue(i);
			System.out.println("Producer : Message { id : "+i+" }");
	}

}
