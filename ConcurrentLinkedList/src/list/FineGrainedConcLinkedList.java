package list;

public class FineGrainedConcLinkedList<T> implements IDs<T> {

	Node<T> head;
	Node<T> tail;

	public FineGrainedConcLinkedList() {
		head = new Node<T>();
		head.setIndex(head.hashCode());
		head.setItem(null);

		tail = new Node<T>();
		tail.setIndex(tail.hashCode());
		tail.setItem(null);
		tail.setNext(null);
	}

	@Override
	public boolean add(T item) {
		int key = item.hashCode();
		Node<T> pred = head;
		pred.lock.lock();
		try{
			Node<T> cur = pred.getNext();
			if(cur == null){
				Node<T> node = new Node<T>();
				node.setIndex(key);
				node.setItem(item);
				pred.setNext(node);
				return true;
			}
			cur.lock.lock();
			try{
				while(key < cur.getIndex()){
					pred.lock.unlock();
					pred=cur;
					cur = cur.getNext();
					if(cur == null)
						
					cur.lock.lock();
				}
				if(key == cur.getIndex()){
					return false;
				}
				Node<T> node = new Node<T>();
				node.setIndex(key);
				node.setItem(item);
				pred.setNext(node);
				node.setNext(cur);
				return true;
			}finally{
				if(cur != null)
				cur.lock.unlock();
			}
		
		}finally{
			pred.lock.unlock();
		}
	}

	@Override
	public boolean remove(T item) {
		// TODO Auto-generated method stub
		int key = item.hashCode();
		Node<T> pred = head;
		pred.lock.lock();
		try{
			Node<T> cur = pred.getNext();
			cur.lock.lock();
			try{
				while(key < cur.getIndex()){
					pred.lock.unlock();
					pred = cur;
					cur = cur.getNext();
					cur.lock.lock();
					
				}
				if(cur.getIndex() == key){
					pred.setNext(cur.getNext());
					return true;
				}
			}finally{
				if(cur != null)
				cur.lock.unlock();
			}
			return false;
		}finally{
			pred.lock.unlock();
		}
	}

	@Override
	public boolean contains(T item) {
		int key = item.hashCode();
		Node<T> pred = head;
		try{
			pred.lock.lock();
			Node cur = pred.getNext();
			cur.lock.lock();
			try{
				while(key < cur.getIndex()){
					pred.lock.unlock();
					pred = cur;
					cur = cur.getNext();
					cur.lock.lock();
				}
				
// Check if found the key and if found then return true				
				if(key == cur.getIndex()){
					return true;
				}
			}finally{
				cur.lock.unlock();
			}
		}finally{
			pred.lock.unlock();
		}
		
//Not able to find the key		
		return false;
	}

}
