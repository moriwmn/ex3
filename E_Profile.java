package ex3;

import java.util.Scanner;

public class E_Profile implements Profile {

	private Employee _employee; // student/ senior
	Scanner input = new Scanner(System.in); // Create a Scanner object

	public E_Profile() {
	}
	public E_Profile(boolean type,String name) { //default
		Personal_info details = new Personal_info();
		details.setName(name);
		Languages languages = new Languages();
		if (type){ //0 = > student / 1=> senior
		this. _employee = new Student(details, languages, "no_extra", "BIU", 3, 90);	 
		}
		else{
		this. _employee = new Senior(details, languages, "no_extra", "CEO", 7);
		}
	}

	// getters and setters:
	public String getEmployeeName() {
		return _employee.getDetails().getName();
	}

	public Employee getEmployee() {
		return _employee;
	}

	public void setEmployeeName(String name) {
		_employee.getDetails().setName(name);
	}

	public String getEmployeeLocation() {
		return _employee.getDetails().getLocation();
	}

	public void setEmployeeLocation(String location) {
		_employee.getDetails().setLocation(location);
	}

	public String getEmployeeEmail() {
		return _employee.getDetails().getEmail();
	}

	public void setEmployeeEmail(String email) {
		_employee.getDetails().setEmail(email);
	}

	public String getEmployeePhone() {
		return _employee.getDetails().getPhone();
	}

	public void setEmployeePhone(String phone) {
		_employee.getDetails().setPhone(phone);
	}

	// func:
	@Override
	public void menu() {

	}

	public void create_user_card() {
		System.out.println("***********Create your user_card:***********");
		System.out.println("enter your status- \n press 1 for student \n press 2 for senior");
		int status = input.nextInt();
		while (status != 1 && status != 2) {
			System.out.println("Pls enter 1 or 2");
			status = input.nextInt();
		}
		System.out.println("please enter your name");
		String name = input.nextLine();
		name = input.nextLine();
		System.out.println("please enter your email");
		String email = input.nextLine();
		System.out.println("please enter your location: north,south,center"); // TODO לחשוב איך שומרים את זה בשביל המיון
																				// אחכ הגהה
		String location = input.nextLine();
		System.out.println("please enter your phone number");
		String phone = input.nextLine();
		Personal_info personal_inf = new Personal_info(name, email, location, phone);
		boolean py = false, java = false, c = false, cpp = false, j_s = false;
		int app = 0;
		int app2 = 0;
		do {
			System.out.println("what programming languages is required?");
			System.out.println("1)python");
			System.out.println("2)java");
			System.out.println("3)c");
			System.out.println("4)cpp");
			System.out.println("5)javascript");
			app = Integer.parseInt(input.nextLine());
			switch (app) {
				case 1:
					py = true;
					break;
				case 2:
					java = true;
					break;
				case 3:
					c = true;
					break;
				case 4:
					cpp = true;
					break;
				case 5:
					j_s = true;
					break;
			}
			System.out.println("To add another programming language pls press 1, otherwise press on any other key:");
			app2 = Integer.parseInt(input.nextLine());
		} while (app2 == 1);
		Languages prog_language = new Languages(py, java, c, cpp, j_s);
		System.out.println("enter more information");
		String extra_inf = input.nextLine();

		if (status == 1) { // add sudent details to user card
			System.out.println("please enter your GPA:");
			int gpa = input.nextInt();
			System.out.println("please enter the name of the university where you are studying:");
			String univesity = input.nextLine();
			univesity = input.nextLine();
			System.out.println("Enter num of years left");
			int years = input.nextInt();
			_employee = new Student(personal_inf, prog_language, extra_inf, univesity, years, gpa);
		}
		if (status == 2) { // add senior details to user card
			System.out.println("entar your last job");
			String last_job = input.nextLine();
			System.out.println("enter num years of experience ");
			int experience = input.nextInt();
			_employee = new Senior(personal_inf, prog_language, extra_inf, last_job, experience);
		}
	}

}// end of class
