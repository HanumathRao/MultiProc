package bq;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedQ<T> {
	ReentrantLock enqLock, deqLock;
	Node<T> head;
	Node<T> tail;
	AtomicInteger size;
	final int CAPACITY = 15;
	Condition notFullCondition;
	Condition notEmptyCondition;

	public BoundedQ() {
		enqLock = new ReentrantLock();
		deqLock = new ReentrantLock();
		size = new AtomicInteger(0);
		notFullCondition = enqLock.newCondition();
		notEmptyCondition = deqLock.newCondition();
		head = (Node<T>) new Node<Integer>(null);
		tail = head;
	}

	public void showList() {
		Node node = head.next;
		while (node != null) {
			System.out.println("Item is : " + node.item);
			node = node.next;
		}
	}

	public void enqMethod(T item) {
		boolean alertDequer = false;
		enqLock.lock();
		try {
			while (size.get() == CAPACITY) {
				// Queue is full
				try {
					notFullCondition.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			Node n = new Node<T>(item);
			tail.next = n;
			tail = tail.next;
			if (size.getAndIncrement() == 0) {
				alertDequer = true;
			}
		} finally {
			enqLock.unlock();
		}
		if (alertDequer) {
			deqLock.lock();
			try {
				notEmptyCondition.signalAll();
			} finally {
				deqLock.unlock();
			}
		}
	}

	public T deqMethod() {
		boolean alertEnquer = false;
		T item;
		deqLock.lock();
		try {
			while (size.get() == 0) {
				try {
					notEmptyCondition.await();
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
			item = head.next.item;
			head = head.next;
			if(size.getAndDecrement() == CAPACITY){
				alertEnquer = true;
			}
		} finally {
			deqLock.unlock();
		}
		if(alertEnquer){
			enqLock.lock();
			try{
				notFullCondition.signalAll();
			}finally{
				enqLock.unlock();
			}
		}
		return item;
	}
}
