
public class PhoneCall extends Communication {
	
	private int duration;

	public PhoneCall(String phoneNumber1, String phoneNumber2, int day, int month, int year, int duration) {
		
		super(phoneNumber1,phoneNumber2,day,month,year); // super class attributes
		this.duration = duration;
	}
	
	public void printInfo() {
		super.printInfo(); // calls printInfo from super class
		System.out.println("Duration: "+duration);
	}

	public int getDuration() {
		return duration;
	}
	
	@Override
	public String getMessage() { // does nothing . Is method of sms class
		return "";
	}

}
