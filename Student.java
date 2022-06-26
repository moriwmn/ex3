package ex3;

public class Student extends Employee {

	private String _university;
	private int _years_num; // how many years to graduation
	private int _gpa; // avarge grades

	// public Student(Personal_info _details) {
	// super(_details);
	// // TODO Auto-generated constructor stub
	// }

	public Student(Personal_info _details, Languages _languages, String _extraInf, String _university, int _years_num,
			int _gpa) {
		super(_details, _languages, _extraInf);
		this._university = _university;
		this._years_num = _years_num;
		this._gpa = _gpa;
	}

	public Student(Employee _employee) {
	}

	public String getUniversity() {
		return _university;
	}

	public int getYearsNum() {
		return _years_num;
	}

	public int getGpa() {
		return _gpa;
	}

	public void setUniversity(String university) {
		_university = university;
	}

	public void setYearsNum(int years) {
		_years_num = years;
	}

	public void setGpa(int gpa) {
		_gpa = gpa;
	}

}
