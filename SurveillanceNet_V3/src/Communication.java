
public abstract class Communication { // abstract class that extends sms and phone call
	
	private String phoneNumber1;
	private String phoneNumber2;
	private int day;
	private int month;
	private int year;
	
	public Communication(String phoneNumber1, String phoneNumber2, int day, int month, int year) {
		
		this.phoneNumber1 = phoneNumber1;
		this.phoneNumber2 = phoneNumber2;
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public String getPhoneNumber1() {
		return phoneNumber1;
	}

	public String getPhoneNumber2() {
		return phoneNumber2;
	}

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}
	
	public void printInfo() { // super printInfo();
		System.out.println("Between "+phoneNumber1+" --- "+phoneNumber2);
		System.out.println("on "+day+"/"+month+"/"+year);
	}
    
	// abstract methods that are implemented in the subclasses
	public abstract int getDuration();
	public abstract String getMessage();
}
