package unboundedq;

import java.util.concurrent.atomic.AtomicReference;

public class UBNode<T> {
	T item;
	AtomicReference<UBNode<T>> next ;
	public UBNode(T item){
		this.item = item;
		next = new AtomicReference<UBNode<T>>(null);
	}
}
