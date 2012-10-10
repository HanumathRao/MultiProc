package nquasi;

public class NProducer implements Runnable {
	
	private static final int MAX_ITERATION =50;
	private NQuasiLinearizableQueue nQueue;
	
	public NProducer(NQuasiLinearizableQueue nQueue) {
		this.nQueue = nQueue;
	}
	
	public void run(){
		for(int i =0 ; i < MAX_ITERATION ; ++i){
			produce(i);
		}
	}
	
	public void produce(int i){
			nQueue.enqueue(i);
			synchronized (nQueue) {
				nQueue.notifyAll();
			}
		System.out.println("Producer : Message { id : "+i+" }");
	}

}
