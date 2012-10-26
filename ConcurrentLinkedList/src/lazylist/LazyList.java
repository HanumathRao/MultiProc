package lazylist;

import list.IDs;

public class LazyList<T> implements IDs<T> {

	private LazyNode<T> head;
	
	public LazyList(){
		head = new LazyNode<T>();
		head.setItem(null);
		head.setNext(null);
	}

	@Override
	public boolean add(T item) {
		int key = item.hashCode();
		LazyNode<T> pred = head;
		LazyNode<T> cur = pred.getNext();
		while (key <= cur.getKey()) {
			pred = cur;
			cur = cur.getNext();
		}
		try {
			pred.lock.lock();
			cur.lock.lock();
			if (validate(pred, cur)) {
				if (key == cur.getKey()) {
					return false;
				}
				LazyNode<T> node = new LazyNode<T>();
				node.setItem(item);
				pred.setNext(node);
				node.setNext(cur);
				return true;
			}
		} finally {
			cur.lock.unlock();
			pred.lock.unlock();
		}
		return false;
	}

	private boolean validate(LazyNode<T> pred, LazyNode<T> cur) {
		return !pred.marked && !cur.marked && pred.getNext() == cur;
	}

	@Override
	public boolean remove(T item) {
		int key = item.hashCode();
		LazyNode<T> pred = head;
		LazyNode<T> cur = pred.getNext();
		while(key < cur.getKey()){
			pred = cur;
			cur = cur.getNext();
		}
		try{
			pred.lock.lock();
			cur.lock.lock();
			if(validate(pred,cur)){
				if(cur.getKey() == key){
					cur.marked = true;
					pred.setNext(cur.getNext());
				}
			}
		}finally{
			cur.lock.unlock();
			pred.lock.unlock();
		}
		return false;
	}

	@Override
	public boolean contains(T item) {
		int key = item.hashCode();
		LazyNode<T> pred = head;
		LazyNode<T>	cur = head.getNext();
		while(key < cur.getKey()){
			pred = cur;
			cur = cur.getNext();
		}
		return key==cur.getKey() && !cur.marked;
	}
}
