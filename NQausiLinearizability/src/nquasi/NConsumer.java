package nquasi;

public class NConsumer implements Runnable {
	private NQuasiLinearizableQueue nqueue;
	private static final int MAX_ITERATION = 50;
	
	public NConsumer(NQuasiLinearizableQueue nqueue) {
		this.nqueue = nqueue;
	}
	
	public void run(){
		for(int i =0 ;i<MAX_ITERATION; ++i){
			consume();
		}
	}
	
	public void consume(){
		nqueue.dequeue();
	}

}
