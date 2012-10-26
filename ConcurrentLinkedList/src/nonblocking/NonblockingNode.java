package nonblocking;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

public class NonblockingNode<T>{
	private T item;
	private int key;
	private AtomicReference<NonblockingNode<T>> next;
	ReentrantLock lock = new ReentrantLock();
	
	public NonblockingNode(){
		this.setKey();
	}

	public T getItem() {
		return item;
	}

	public void setItem(T item) {
		this.item = item;
	}
	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
	public void setKey(){
		this.key = this.hashCode();
	}
	
}
