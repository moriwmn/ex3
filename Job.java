package ex_3;

public abstract class Job {
private String type; //job for student/junior/senior
private String Company;	
private String location;
Languages prog_language;
int num_of_hours;

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


}
