package MCS;

import java.util.concurrent.atomic.AtomicBoolean;

public class MCSNode {
	private MCSNode next;
	private AtomicBoolean locked = new AtomicBoolean(true);
	
	public boolean isLocked(){
		return this.locked.get();
	}
	public void setLocked(boolean value){
		this.locked.set(value);
	}
	public MCSNode getNext() {
		return next;
	}
	public void setNext(MCSNode next) {
		this.next = next;
	}
	
	
}
