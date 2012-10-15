package lock;

import java.util.concurrent.atomic.AtomicReference;

public class CLHLock implements ILock{
	private AtomicReference<QNode> tail;
	private ThreadLocal<QNode> myNode;
	private ThreadLocal<QNode> myPred;
	public CLHLock() {
		myNode = new ThreadLocal<QNode>(){
			@Override
			protected QNode initialValue(){
				return new QNode();
			}
		};
	}
	
	
	@Override
	public void lock(){
		QNode node = myNode.get();
		node.setLocked(true);
		QNode pred = tail.getAndSet(myNode.get());
		myPred.set(pred);
		while(pred.isLocked()){}
	}
	
	@Override
	public void unlock(){
		QNode node = myNode.get();
		node.setLocked(false);
		myNode.set(myPred.get());
	}
}
