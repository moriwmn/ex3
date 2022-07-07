package ex3;

public abstract class Employee {

	Personal_info details;
	Languages language;
	String extraInf; // extra information

	public Employee() {
	}

	public Employee(Personal_info personal_inf, Languages prog_language, String extra_inf) {
		details = personal_inf;
		language = prog_language;
		extraInf = extra_inf;
	}

	public Personal_info getDetails() {
		return details;
	}

	public Languages getLanuage() {
		return language;
	}

	// //getters and setters
	public String getEmail() {
		return details.getEmail();
	}

	public String getLocation() {
		return details.getLocation();
	}

	public String getName() {
		return details.getName();
	}

	public String getPhone() {
		return details.getPhone();
	}

	public void setName(String name) {
		this.details.setName(name);
	}

	public void setEmail(String email) {
		this.details.setEmail(email);
	}

	public void setLocation(String location) {
		this.details.setLocation(location);
	}

	public void setPhone(String phone) {
		this.details.setPhone(phone);
	}

	public boolean isPython() {
		return language.isPython();
	}

	public void setPython(boolean python) {
		this.language.setPython(python);
	}

	public boolean isJava() {
		return language.isJava();
	}

	public void setJava(boolean java) {
		this.language.setJava(java);
	}

	public boolean isC() {
		return language.isC();
	}

	public void setC(boolean c) {
		this.language.setC(c);
	}

	public boolean isCpp() {
		return language.isCpp();
	}

	public void setCpp(boolean cpp) {
		this.language.setCpp(cpp);
	}

	public boolean isJavascript() {
		return language.isJavascript();
	}

	public void setJavascript(boolean javascript) {
		this.language.setJavascript(javascript);
	}

	public String getExtraInf() {
		return extraInf;
	}

	public void setExtraInf(String extra) {
		extraInf = extra;
	}

	@Override
	public String toString() {
		return "\r\n name: " + this.details.getName() + "\r\n email: " + this.details.getEmail() +
				"\r\n phone number: " + this.details.getPhone() + "\r\n programming languages: "
				+ this.language.toString();
	}
} // end Employee
