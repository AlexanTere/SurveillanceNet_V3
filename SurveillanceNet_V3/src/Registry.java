import java.util.ArrayList;
import java.util.Collections;

public class Registry {
	
	private ArrayList<Communication> allComm = new ArrayList<Communication>();
	private ArrayList<Suspect> allSusp = new ArrayList<Suspect>();
	
	
	public void addSuspect(Suspect aSuspect) {
		allSusp.add(aSuspect);
	}


	public void addCommunication(Communication aComm) { // find suspects from numbers and connect them
		
		allComm.add(aComm);
		
		String num1 = aComm.getPhoneNumber1();
		String num2 = aComm.getPhoneNumber2();
		Suspect s1 = null;
		Suspect s2 = null;
		
		for(Suspect s:allSusp) {
			if(s.getPhoneNumbers().contains(num1)) {
			    s1=s;
			}
			if(s.getPhoneNumbers().contains(num2)) {
				s2=s;
			}	
		}
		s1.addPossiblePartner(s2);
		s2.addPossiblePartner(s1);
	}


	public Suspect getSuspectWithMostPartners() {
		
		ArrayList<Integer> numberOfPartners = new ArrayList<Integer>(); // we keep all a number for all suspects
		
		for(Suspect s:allSusp) {
			numberOfPartners.add(s.getPartners().size());
		}
		
		int Max = Collections.max(numberOfPartners); // find the max 
		int position = numberOfPartners.indexOf(Max); // get position of max
		
		return allSusp.get(position); // return the suspect with most partners
	}


	public PhoneCall getLongestPhoneCallBetween(String num1, String num2) {
		
		ArrayList<Integer> duration = new ArrayList<Integer>(); // we keep all durations
		PhoneCall pc = null;
		
		for(Communication comm:allComm) { // find the call with numbers 1 , 2
			if((comm.getPhoneNumber1().equals(num1) || comm.getPhoneNumber1().equals(num2)) && (comm.getPhoneNumber2().equals(num1) || comm.getPhoneNumber2().equals(num2))) {
				duration.add(comm.getDuration());
			}
		}
		
		int Max = Collections.max(duration); // get the max duration
		
		for(Communication comm:allComm) { // find the duration with max
			if(comm.getDuration() == Max) {
				pc = (PhoneCall) comm; // cast a communication to phone call
			}
		}
		return pc; // return phone call
	}


	public ArrayList<SMS> getMessagesBetween(String num1, String num2) {
		
		ArrayList<SMS> messages = new ArrayList<SMS>(); // we keep all messages
		
		for(Communication comm:allComm) { // find the communication (SMS) and if contains the key words add it to messages
			if((comm.getPhoneNumber1().equals(num1) || comm.getPhoneNumber1().equals(num2)) && (comm.getPhoneNumber2().equals(num1) || comm.getPhoneNumber2().equals(num2))) {
				if(comm.getMessage().contains("Bomb") || comm.getMessage().contains("Attack") || comm.getMessage().contains("Explosives") || comm.getMessage().contains("Gun")) {
					messages.add((SMS) comm); // cast a communication to sms
				}
			}
		}
		
		return messages;
	}


	public void printSuspectsFromCountry(String origin) { // print suspects form same country
		
		System.out.println("Suspects coming from "+origin+":");
		for(Suspect s:allSusp) {
			if(s.getOrigin().equals(origin)) {
				System.out.println(s.getName()+" ("+s.getCodeName()+") ");
			}
		}
		
	}


	public ArrayList<Communication> getAllComm() {
		return allComm;
	}


	public ArrayList<Suspect> getAllSusp() {
		return allSusp;
	}
	
	
	
	

}
