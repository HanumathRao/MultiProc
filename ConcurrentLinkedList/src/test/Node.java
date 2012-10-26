package test;

import java.util.concurrent.atomic.AtomicMarkableReference;

public class Node<T> {
	T item;
	int hashCode = this.hashCode;
	boolean flag = false;
	AtomicMarkableReference<Node> next = new AtomicMarkableReference<Node>(null, false);
	
}
