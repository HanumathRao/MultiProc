package unboundedq;

import java.util.concurrent.atomic.AtomicReference;

public class UnboundedLockFreeQ<T> {
	AtomicReference<UBNode<T>> head, tail;

	public void enqMethod(T item) {
		UBNode<T> node = new UBNode<T>(item);

		UBNode<T> last = tail.get();
		UBNode<T> next = last.next.get();
		while (true) {
			if (last == tail.get()) {
				if (next == null) {
					if (last.next.compareAndSet(next, node)) {
						tail.compareAndSet(last, node);
						return;
					}
				}
			} else {
				tail.compareAndSet(last, next);
			}
		}
	}
}
