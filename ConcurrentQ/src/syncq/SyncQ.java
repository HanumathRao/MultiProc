package syncq;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SyncQ<T> {
	T item;
	Condition condition;
	ReentrantLock lock;
	
	public SyncQ(){
		lock = new ReentrantLock();
		condition = lock.newCondition();
	}
	
	public void enq(T item){
		boolean enqueing = false;
		lock.lock();
		try{
			while(enqueing){
				try{
				condition.await();
				}catch(InterruptedException ex){
					ex.printStackTrace();
				}
			}
			enqueing = true;
			this.item = item;
			condition.signalAll();
			while(item != null){
				try {
					condition.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			enqueing = false;
			condition.signalAll();
		}finally{
			lock.unlock();
		}
	}
	
	public T deq(){
		T removedItem;
		lock.lock();
		try{
			while(item == null){
				try {
					condition.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			removedItem = item;
			item = null;
			condition.signalAll();
			return removedItem;
		}finally{
			lock.unlock();
		}
	}
}
