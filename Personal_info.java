package ex3;

public class Personal_info {
	private String name;
	private String email;
	private String location;
	private String phone;

	// default constractor:
	public Personal_info() {
		name = "no name";
		email = "no_mail@gmail.com";
		location = "location";
		phone = "050-0000000";
	}

	// constractor:
	public Personal_info(String name2, String email2, String location2, String phone2) {
		name = name2;
		email = email2;
		location = location2;
		phone = phone2;
	}

	// getters and setters:
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;

	}

}
