package standard;

import java.util.concurrent.atomic.AtomicReference;

public class Node<T> {
	T item;
	Node<T> next;
	
	public Node(T item) {
		this.item = item;
		next = null;
	}
}
