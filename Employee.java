package ex_3;

public abstract class Employee {//implements User { 
	
	Personal_info _details;
	Languages _language; // think about how do it
	String _extraInf; //extra information
	
	//getters and setters
	public String getEmail(){
		return _details.getEmail();
	}
	public String getLocation(){
		return _details.getLocation();
	}
	public String getName(){
		return _details.getName();
	}
	public String getPhone(){
		return _details.getPhone();
	}
	
	public void setName(String name) {
		this._details.getName=name;
	}
	public void setEmail(String email) {
		this._details.getEmail = email;
	}
	public void setGender(String gender) {
		this._details.getGender = gender;
	}
	public void setPhone(String phone) {
		this._details.getPhone = getPhone;
	}
	
	//getters and setters:
	public boolean isPython() {
		return _language.isPython();
	}
	public void setPython(boolean python) {
		this._language.setPython() = python;
	}
	public boolean isJava() {
		return _language.isJava();;
	}
	public void setJava(boolean java) {
		this._language.setJava() = java;
	}
	public boolean isC() {
		return _language.isC();
	}
	public void setC(boolean c) {
		this._language.setC() = c;
	}
	public boolean isCpp() {
		return _language.isCpp();
	}
	public void setCpp(boolean cpp) {
		this._language.setCpp()=cpp;
	}
	public boolean isJavascript() {
		return _language.isJavaScript();	}

	public void setJavascript(boolean javascript) {
		this._language.setJavascript() = javascript;
	}



	
	
}
