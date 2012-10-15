package lock;

import java.util.concurrent.atomic.AtomicBoolean;

public class QNode {
	private AtomicBoolean locked = new AtomicBoolean(true);

	public boolean isLocked() {
		return locked.get();
	}
	
	public void setLocked(boolean lockValue){
		locked.set(lockValue);
	}

}
