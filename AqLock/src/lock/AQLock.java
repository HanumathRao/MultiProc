package lock;

import java.util.concurrent.atomic.AtomicInteger;

import threads.IConst;

public class AQLock implements ILock,IConst{
	private boolean[] flags = new boolean[SIZE];
	private AtomicInteger next = new AtomicInteger(0);
	private ThreadLocal<Integer> mySlot;
	
	public AQLock() {
		mySlot = new ThreadLocal<Integer>();
		for(int i= 0 ; i < SIZE; ++i){
			if(i == 0 ){
				flags[i] = true;
			}else{
				flags[i] = false;
			}
		}
	}
	
	@Override
	public void lock(){
		mySlot.set(next.getAndIncrement());
		while(!flags[mySlot.get()%SIZE]){}
		flags[mySlot.get()%SIZE] = false;
	}
	
	@Override
	public void unlock(){
		flags[((mySlot.get()+1)%SIZE)] = true;
	}

}
