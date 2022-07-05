package ex3;

public abstract class Job {
	
private String type; //job for student/senior
private String Company;	
private String location;
Languages prog_language;


public Job(String type, String company, String location, Languages prog_language) {
	this.type = type;
	this.Company = company;
	this.location = location;
	this.prog_language = prog_language;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}
public String getCompany() {
	return Company;
}
public void setCompany(String company) {
	Company = company;
}

public Languages getLanguages() {
	return prog_language;
}
public void setLanguages(Languages languages) {
	prog_language = languages;
}

@Override
public String toString() {
	return "\r\nJob type:"+type+ "\r\nCompany name:" + Company + "\r\nLocation:"+location; //TODO: how to print languages
}
}

