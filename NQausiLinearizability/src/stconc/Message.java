package stconc;

public class Message {
	private String msg;
	private int id;
	
	public Message(int id, String msg) {
		this.msg= msg;
		this.id = id;
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString(){
		return "Message { id : "+id+" ,mesage : "+msg+" }";
	}
}
