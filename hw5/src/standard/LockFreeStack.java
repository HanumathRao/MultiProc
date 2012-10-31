package standard;

import java.util.concurrent.atomic.AtomicReference;

public class LockFreeStack<T> {
	AtomicReference<Node<T>> top = new AtomicReference<Node<T>>(null);
	
	
}
