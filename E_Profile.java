package ex3;

import java.util.Scanner;

public class E_Profile implements Profile {

	private Employee _employee; // student/ junior
	Scanner input = new Scanner(System.in); // Create a Scanner object
	
	public E_Profile(String maneger) {
		_employee=null;
		 // TODO - it is ok?
	}
	public E_Profile() {
		create_user_card(); // TODO - it is ok?
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
		System.out.println("Create your user_card:");
		System.out.println("pleas enter your name");
		String name = input.nextLine();
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
			System.out.println("To add another programming language pls press 1:");
			app2 = Integer.parseInt(input.nextLine());
		} while (app2 == 1);
		Languages prog_language = new Languages(py, java, c, cpp, j_s);
		System.out.println("enter more information");
		String extra_inf = input.nextLine();
		_employee = new Employee(personal_inf, prog_language, extra_inf);
	}

}// end of class
