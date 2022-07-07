package ex3;

public class Student_Job extends Job {
	int salery_per_hour;
	int gpa_req;
	int num_of_hours;

	public Student_Job(String field, String company, String location, Languages prog_language, int salery_per_hour,
			int gpa_req, int num_of_hours) {
		super(field, company, location, prog_language);
		this.salery_per_hour = salery_per_hour;
		this.gpa_req = gpa_req;
		this.num_of_hours = num_of_hours;
	}

	public int getGpa() {
		return this.gpa_req;
	}

	public int getNumOfHours() {
		return this.num_of_hours;
	}

	public int getSaleryPerHour() {
		return this.salery_per_hour;
	}

	@Override
	public String toString() {
		return super.toString() + "\r\nwith salery per hour: " + salery_per_hour + "\r\nGPA requaired: " + gpa_req
				+ "\r\nNum of hours per weeks: " + num_of_hours;
	}

}
