package logic;

import java.util.Comparator;

import org.json.*;

public class Manager {

	private String lastName;
	private String id;
	private String phone;
	private String jurisdiction;
	private String identificationNumber;
	private String firstName;
	
	
	public Manager() {};
	
	public Manager(JSONObject  inJ) {
		try {
			firstName = (String) inJ.get("firstName");
			lastName = (String) inJ.get("lastName");
			phone = (String) inJ.get("phone");
			jurisdiction = (String) inJ.get("jurisdiction");
			identificationNumber = (String) inJ.get("identificationNumber");
			id = (String) inJ.get("identificationNumber");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
			
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getID() {
		return id;
	}

	public void setID(String iD) {
		id = iD;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getJurisdiction() {
		return jurisdiction;
	}

	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	

	//checks if Jurisdiction is Alphabetic
	public boolean isJurisdictionAlphabetic() {
		for (int i = 0; i < jurisdiction.length(); i++) {
			if(!Character.isAlphabetic(jurisdiction.charAt(i) )){
				return false;
			}
		}
		return true;
	}
	
	//checks if Jurisdiction is numeric
	public boolean isJurisdictionNumeric() {
		for (int i = 0; i < jurisdiction.length(); i++) {
			if(!Character.isDigit(jurisdiction.charAt(i) )){
				return false;
			}
		}
		return true;
	}
	
	
	//Prints the manager info  
	public String print() {
		return this.jurisdiction + " - " + this.lastName + ", " + this.firstName;
	}
	
	//returns the requested comparator or null if there is no such comparator
	public static Comparator<Object> getComparator(String type) {
		if(type.equals("JLF")) { //jurisdiction - last - first
			return new JurNameComparator();
		}
		return null;
	}
	

	// Jurisdiction and Name Comparator
	private static class JurNameComparator implements Comparator<Object>{

		@Override
		public int compare(Object o1, Object o2) {
			if(o1.getClass() != Manager.class) {
				System.err.print("JurNameComparator use for none Manager comparing");
				
				return 0;
				
			}
			Manager M1 = (Manager)o1;
			Manager M2 = (Manager)o2;
			
			if(M1.jurisdiction.compareTo(M2.jurisdiction) != 0 ) {	
				return M1.jurisdiction.compareTo(M2.jurisdiction);
			}else if(M1.lastName.compareTo(M2.lastName) != 0 ) {
				return M1.lastName.compareTo(M2.lastName);
			}else {
				return M1.firstName.compareTo(M2.firstName);
			}
		}
		
	}
	
}