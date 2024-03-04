import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Suspect {
	
	private String name;
	private String codeName;
	private String origin;
	private String cityOfActivity;
	private ArrayList<String> phoneNumbers = new ArrayList<String>();
	
	private ArrayList<Suspect> possiblePartners = new ArrayList<Suspect>(); // connection between suspects
	
	// constructor
	public Suspect(String name, String codeName,String origin, String cityOfActivity) {
		this.name = name;
		this.codeName = codeName;
		this.origin = origin;
		this.cityOfActivity = cityOfActivity;
	}

	public void addNumber(String number) {
		phoneNumbers.add(number);	
	}
	
	public void addPossiblePartner (Suspect aSuspect) { // check if list of partners has an object aSuspect type Suspect and add it to list
		
		if(possiblePartners.contains(aSuspect)==false) {
			possiblePartners.add(aSuspect);
		}
	}
	
	public ArrayList<Suspect> SuggestedPartners(){ // check for this suspect not connected suspects from his possible partners.
		
		Set<Suspect> partnersOfMyPartners = new HashSet<Suspect>(); // In set<> we don't have duplicates 
		
		for(Suspect aSuspect:possiblePartners) {
			partnersOfMyPartners.addAll(aSuspect.possiblePartners);
		}
		partnersOfMyPartners.removeAll(possiblePartners); // removes the same objects
		partnersOfMyPartners.remove(this); //removes object that calls the method, can't suggest himself for partner
		
		ArrayList<Suspect> suggestedPartners = new ArrayList<>(partnersOfMyPartners);
		
	 return suggestedPartners;
	}

	public String getName() {
		return name;
	}

	public String getCodeName() {
		return codeName;
	}

	public String getOrigin() {
		return origin;
	}

	public String getCityOfActivity() {
		return cityOfActivity;
	}

	public ArrayList<String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public ArrayList<Suspect> getPartners() {
		return possiblePartners;
	}
	
	

	public boolean isConnectedTo(Suspect aSuspect) {
		
		boolean connected = false;
		if(possiblePartners.contains(aSuspect)) { 
			connected = true;
		}
		return connected;
	}

	public ArrayList<Suspect> getCommonPartners(Suspect aSuspect) {
		
		ArrayList<Suspect> commonPartners = new ArrayList<Suspect>(possiblePartners); // project to a new list objects of possiblePartners
		
		commonPartners.retainAll(aSuspect.getPartners()); // keeps only the same objects
		
		return commonPartners;
	}
	
	public void printPosiblePartners() { //for suspect print possible partners , puts * if they are from same country
		
		System.out.println("SUSPECT: "+getName());
		System.out.println("POSSIBLE PARTNERS");
		System.out.println("=========================================");
		for(Suspect s:possiblePartners) {
		  System.out.print("NAME: " +s.getName()+" CODE_NAME: "+s.getCodeName());
		  if(this.getOrigin().equals(s.getOrigin())) {
			  System.out.print("*");
		  }
		  System.out.println("");
		}
		
	}
	

}
