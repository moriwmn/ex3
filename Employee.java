package ex3;

public abstract class Employee {// implements User {

	Personal_info _details;
	Languages _language; // think about how do it
	String _extraInf; // extra information

	// public Employee(Personal_info _details) {
	// this._details = _details;
	// }// name constructore only

	public Employee() {
	}

	public Employee(Personal_info personal_inf, Languages prog_language, String extra_inf) {
		_details = personal_inf;
		_language = prog_language;
		_extraInf = extra_inf;
	}

	public Personal_info getDetails() {
		return _details;
	}

	public Languages getLanuage() {
		return _language;
	}

	// //getters and setters
	public String getEmail() {
		return _details.getEmail();
	}

	public String getLocation() {
		return _details.getLocation();
	}

	public String getName() {
		return _details.getName();
	}

	public String getPhone() {
		return _details.getPhone();
	}

	public void setName(String name) {
		this._details.setName(name);
	}

	public void setEmail(String email) {
		this._details.setEmail(email);
	}

	public void setLocation(String location) {
		this._details.setLocation(location);
	}

	public void setPhone(String phone) {
		this._details.setPhone(phone);
	}

	public boolean isPython() {
		return _language.isPython();
	}

	public void setPython(boolean python) {
		this._language.setPython(python);
	}

	public boolean isJava() {
		return _language.isJava();
	}

	public void setJava(boolean java) {
		this._language.setJava(java);
	}

	public boolean isC() {
		return _language.isC();
	}

	public void setC(boolean c) {
		this._language.setC(c);
	}

	public boolean isCpp() {
		return _language.isCpp();
	}

	public void setCpp(boolean cpp) {
		this._language.setCpp(cpp);
	}

	public boolean isJavascript() {
		return _language.isJavascript();
	}

	public void setJavascript(boolean javascript) {
		this._language.setJavascript(javascript);
	}

	public String getExtraInf() {
		return _extraInf;
	}

	public void setExtraInf(String extra) {
		_extraInf = extra;
	}

	@Override
	public String toString() {
		return "\r\n name:" + this._details.getName() + "\r\n email:" + this._details.getEmail() +
				"\r\n phone number:" + this._details.getPhone() + "\r\n programming languages:"
				+ this._language.toString();
	}
} // end Employee
