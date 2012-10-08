package core;

import java.util.Queue;

public class Consumer implements Runnable{
	
	private Queue<Message> msgQ;
	
	public Consumer(Queue<Message> msgQ) {
		this.msgQ = msgQ;
	}

	@Override
	public void run(){
		while(true){
			consume();
			try{
				synchronized (msgQ) {
					msgQ.wait();
				}
			}catch(InterruptedException ex){
				ex.printStackTrace();
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
