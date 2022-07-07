package ex3;

public class Student extends Employee {

	private String university;
	private int years_num; // how many years to graduation
	private int gpa; // avarge grades

	public Student(Personal_info details, Languages languages, String extraInf, String university, int years_num,
			int gpa) {
		super(details, languages, extraInf);
		this.university = university;
		this.years_num = years_num;
		this.gpa = gpa;
	}

	public Student(Employee _employee) {
	}

	public String getUniversity() {
		return university;
	}

	public int getYearsNum() {
		return years_num;
	}

	public int getGpa() {
		return gpa;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public void setYearsNum(int years) {
		years_num = years;
	}

	public void setGpa(int gpa) {
		this.gpa = gpa;
	}

	@Override
	public String toString() {
		return super.toString() + "\r\n university: " + this.university + "\r\n GPA : " + this.gpa
				+ "\r\n study Year: " + this.years_num + "\r\n more info: " + this.extraInf;
	}
} // end Student
