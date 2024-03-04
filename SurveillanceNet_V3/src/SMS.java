
public class SMS extends Communication {
	
	private String message;

    public SMS(String phoneNumber1, String phoneNumber2, int day, int month, int year, String message) {
		
		super(phoneNumber1,phoneNumber2,day,month,year); // super class attributes
		this.message = message;
	}

	@Override
	public int getDuration() { // does nothing. Is method of phone call class
		return 0;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void printInfo() {
		System.out.println("This SMS has the following info");
		super.printInfo(); // calls printInfo from super class
		System.out.println("Text: "+message);
	}

}
