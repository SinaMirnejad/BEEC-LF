package logic;

public class Employee {

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isValid() {
		return !(lastName == null || firstName == null || supervisor == null);
	}



	private String firstName;
	private String lastName;
	private String supervisor;
	private String phoneNumber;
	private String email;

	
	public Employee() {}
	
	@Override
	public String toString() {
		return    "firstName:" +  firstName + ", "
				+ ", lastName:"+ lastName 
				+ ", supervisor:" + supervisor 
				+ ", phoneNumber:"+  phoneNumber 
				+ ", email:" + email;
	}
	
}
