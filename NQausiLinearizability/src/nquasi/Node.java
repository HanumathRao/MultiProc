package nquasi;
/**
 * Single node consists of an array of size 1000, so that at a time if many threads 
 * tries to enque to a location , all the values will be inserted in the same node but 
 * in the differrent location of th array inside the node. 
 * @author Arijit
 *
 */
public class Node {
//	Size of the cells inside the node
	private final int SIZE = 1000;
//	Array of the node
	private int[] nodeArray = new int[SIZE];
//	Index to control the array
	private static int counter;
//	Default constructor
	public Node(){
		counter = 0 ;
	}
	/**
	 * @deprecated
	 * @param num : Number to be added
	 */
	public Node(int num) {
		this();
		addElement(num);
	}
	
	/**
	 * 
	 * @return : returns the node array
	 */
	public int[] getNodeArray() {
		return nodeArray;
	}
	
	/**
	 * 
	 * @param input: adds up element to the node
	 */
	
	public void addElement(int input){
		synchronized (nodeArray) {
			nodeArray[counter]= input;
			++counter;
		}
	}
	
	/**
	 * 
	 * @param index: index of a perticular element inside the node
	 * @return : the element which is in that perticular location
	 */
	public int getElement(int index){
		return nodeArray[index];
	}
	
}
