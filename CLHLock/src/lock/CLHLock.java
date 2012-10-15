package lock;

import java.util.concurrent.atomic.AtomicReference;

public class CLHLock implements ILock{
	private AtomicReference<QNode> tail;
	private ThreadLocal<QNode> localNode;
	
	public CLHLock() {
		localNode = new ThreadLocal<QNode>();
	}
	
	
	@Override
	public void lock(){
		QNode pred = tail.getAndSet(localNode.get());
		while(pred.isLocked()){}
	}
	
	@Override
	public void unlock(){
		tail.get().setLocked(false);
	}
}
