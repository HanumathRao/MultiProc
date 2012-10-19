import list.FineGrainedConcLinkedList;
import measurement.FineGrainMeasurement;


public class Main {

	public static void measureFineGrain(FineGrainedConcLinkedList<Integer> list){
		long startTime = System.nanoTime();
		FineGrainMeasurement fm = new FineGrainMeasurement(list);
		long endTime = System.nanoTime();
		System.out.println("Time taken "+(endTime-startTime)/1000000);
	}
	
	public static void main(String args[]){
		FineGrainedConcLinkedList<Integer> list = new FineGrainedConcLinkedList<Integer>();
		for(int i = 0 ; i< 100;  ++i){
			list.add(i+12);
		}
		Main.measureFineGrain(list);
	}
}
