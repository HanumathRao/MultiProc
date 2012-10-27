package cs;

import java.util.concurrent.atomic.AtomicReference;

public class Node <T>{
	T item;
	AtomicReference<T> next;
	
	public Node(T item){
		this.item = item;
		next = new AtomicReference<T>(null);
	}
}
