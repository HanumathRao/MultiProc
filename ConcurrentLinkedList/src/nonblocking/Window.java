package nonblocking;

public class Window<T> {
	NonblockingNode<T> pred, cur;
	
	public Window(NonblockingNode<T> pred, NonblockingNode<T> cur) {
		this.pred = pred;
		this.cur = cur;
	}
	
/*	public Window<T> find(){
		
	}*/
}
