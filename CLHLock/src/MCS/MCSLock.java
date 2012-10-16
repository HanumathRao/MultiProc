package MCS;

import java.util.concurrent.atomic.AtomicReference;

import lock.ILock;

public class MCSLock implements ILock {

	private AtomicReference<MCSNode> tail;
	private ThreadLocal<MCSNode> myPred;
	private ThreadLocal<MCSNode> myNode;

	public MCSLock() {
		myNode = new ThreadLocal<MCSNode>() {
			@Override
			protected MCSNode initialValue() {
				return new MCSNode();
			}
		};
	}

	@Override
	public void lock() {
		MCSNode node = myNode.get();
		node.setLocked(true);
		MCSNode myPred = tail.getAndSet(node);
		if (myPred != null) {
			myPred.setNext(node);
			while (node.isLocked()) {
			}
		}
	}

//Unlocking MCS Locking
	@Override
	public void unlock() {
		MCSNode node = myNode.get();
		if(node.getNext() == null){
			if(tail.compareAndSet(node, null))
				return;
			while(node.getNext() == null){}
		}
		node.getNext().setLocked(false);
		node.getNext().setNext(null);
	}

}
