package nquasi;

public class Node {
	private final int SIZE = 1000;
	private int[] nodeArray = new int[SIZE];
	private static int counter;
	public Node(){
		counter = 0 ;
	}
	public Node(int num) {
		this();
		addElement(num);
	}
	public int[] getNodeArray() {
		return nodeArray;
	}
	
	public void addElement(int input){
		synchronized (nodeArray) {
			nodeArray[counter]= input;
			++counter;
		}
	}
	
	public int getElement(int index){
		return nodeArray[index];
	}
	
}
