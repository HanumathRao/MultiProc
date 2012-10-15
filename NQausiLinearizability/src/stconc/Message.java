package stconc;

public class Message {
	private int id;
	
//	Default constructor
	public Message(int id, String msg) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString(){
		return "Message { id : "+id+" }";
	}
}
