package core;

import java.util.Queue;
import java.util.Random;

public class Producer implements Runnable{
	private final Random randomNumber = new Random();
	private static int counter = 0 ;
	private Queue<Message> messageQueue;
	
	public Producer(Queue<Message> messageQueue) {
		// TODO Auto-generated constructor stub
		this.messageQueue = messageQueue;
	}
	
	public void run(){
		while(true){
			produce();
			int waitTime = randomNumber.nextInt(1000);
			/*try{
				Thread.currentThread();
				Thread.sleep(waitTime);
			}catch(InterruptedException ex){
				ex.printStackTrace();
			}*/
		}
		
	}
	
	private void produce(){
		Message msg =  new Message(counter,"Data message");
		++counter;
		this.messageQueue.offer(msg);
		synchronized(this.messageQueue){
			messageQueue.notifyAll();
		}
		System.out.println("Producer : "+msg);
	}

}
