package list;

import java.util.concurrent.locks.ReentrantLock;


public class Node<T> {
	private int index;
	private T item;
	private Node<T> next;
	ReentrantLock lock = new ReentrantLock();

	public Node<T> getNext() {
		return next;
	}
	public void setNext(Node<T> next) {
		this.next = next;
	}
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public T getItem() {
		return item;
	}
	public void setItem(T item) {
		this.item = item;
	}
	
	
}
