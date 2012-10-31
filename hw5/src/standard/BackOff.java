package standard;

public class BackOff {
	private final int MIN_DELAY = 10;
	private final int MAX_DELAY = 100;
	private static int count	= 0;
	
	public void backOff(){
		try{
			Thread.currentThread().sleep(MIN_DELAY+(count++));
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
	}
}
