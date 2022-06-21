package ex_3;

public class Student extends Employee{
	private String _university;
	private int _years_num; // how many years to graduation
	private int _gpa // avarge grades
	
	public String getUniversity(){
		return _university;
	}
	public int getYearsNum(){
		return _years_num;
	}
	public int getGpa(){
		return _gpa;
	}

	public void setUniversity(String university){
		_university=university;
	}
	public void setYearsNum(int years){
		_years_num=years;
	}
	public void setGpa(int gpa){
		_gpa=gpa;
	}



	

	

}
