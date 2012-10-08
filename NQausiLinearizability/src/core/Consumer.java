package core;

import java.util.Queue;

public class Consumer implements Runnable{
	
	private Queue<Message> msgQ;
	private static long max = 2000000000; 
	public Consumer(Queue<Message> msgQ) {
		this.msgQ = msgQ;
	}

	@Override
	public void run(){
		long i = 0 ;
		while(i < max){
			consume();
			try{
				synchronized (msgQ) {
					msgQ.wait();
				}
			}catch(InterruptedException ex){
				ex.printStackTrace();
			}
			finally{
				++i;
			}
		}
	}

	private void consume() {
		while(!msgQ.isEmpty()){
			Message msg = msgQ.poll();
			if(msg != null){
				System.out.println("Consumer : "+msg.toString());
			}
		}
		
	}
}
