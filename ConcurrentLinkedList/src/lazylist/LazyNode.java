package lazylist;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import list.Node;

public class LazyNode<T> {
	private T item;
	private int key;
	private LazyNode<T> next;
	boolean marked = false;
	ReentrantLock lock;
	public LazyNode() {
		// TODO Auto-generated constructor stub
		this.setKey();
	}
	

	public LazyNode<T> getNext() {
		return next;
	}
	public void setNext(LazyNode<T> next) {
		this.next = next;
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
