package TaskAllocation;

public class Message {
	private Messageable sender; 
	private int decisionCounter; 
	private Messageable reciever; 
	private double context; 
	private int delay;
	
	public Message(Messageable sender, int decisionCounter, Messageable reciever, double context, int delay) {
		this.sender=sender; 
		this.decisionCounter=decisionCounter; 
		this.reciever=reciever; 
		this.context=context; 
		this.delay=delay;
	}
	
	

}
