package ex3;

//import java.util.Scanner;

public class E_Profile implements Profile {

	private int status;
	private Employee _employee; // student/ senior
	// Scanner input = new Scanner(System.in); // Create a Scanner object
	UI ui = new UI();

	public E_Profile() {
	}

	public E_Profile(boolean type, String name) { // default
		Personal_info details = new Personal_info();
		details.setName(name);
		Languages languages = new Languages();
		if (type) { // 0 = > student / 1=> senior
			this._employee = new Student(details, languages, "no_extra", "BIU", 3, 90);
		} else {
			this._employee = new Senior(details, languages, "no_extra", "CEO", 7);
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
		boolean exit = false;
		while (!exit) {
			String change = "null";
			if (status == 0) {
				student_changes();
			} else if (status == 1) {
				senior_changes();
			} else {
				exit = true;
			}

		}
	}

	public void student_changes() {// TODO how exit from this window
		boolean exit = false;
		while (!exit) {
			String change = "null";
			String[] option = { "Change name", "Change email", "change Phone number", "Change your Year",
					"change your GPA", "show my user card" };
			int choice = ui.some_options("Menu", "Edit your user card:", option);
			switch (choice) {
				case 0:
					change = ui.free_input("Change Name", "Please enter new name:");
					_employee.setName(change);
					break;
				case 1:
					change = ui.free_input("Change email", "Please enter new email:");
					_employee.setEmail(change);
					break;
				case 2:
					change = ui.free_input("Change phone number", "Please enter new phone number:");
					_employee.setPhone(change);
					break;
				case 3://
					change = ui.free_input("Change Year ", "Please enter the new Year:");
					// _employee.setYearsNum(change); //TODO how get the student parameters
					break;
				case 4:
					change = ui.free_input("Change your GPA ", "Please enter the new GPA:");
					// _employee.setYearsNum(change); //TODO how get the student parameters
					break;
				case 5:
					show_senior_card();
					break;
				case 6:
					exit = true;
					break;
				default:
					exit = true;
			}
		}
	}

	public void senior_changes() { // TODO how exit from this window
		boolean exit = false;
		while (!exit) {
			String change = "null";
			String[] option = { "Change name", "Change email", "change Phone number", "Change num of experience",
					"add more detials", "show my user card" };
			int choice = ui.some_options("Menu", "Edit your user card:", option);
			switch (choice) {
				case 0:
					change = ui.free_input("Change Name", "Please enter new name:");
					_employee.setName(change);
					break;
				case 1:
					change = ui.free_input("Change email", "Please enter new email:");
					_employee.setEmail(change);
					break;
				case 2:
					change = ui.free_input("Change phone number", "Please enter new phone number:");
					_employee.setPhone(change);
					break;
				case 3://
					change = ui.free_input("Change num of experiance ", "Please enter the new num of experiance:");
					// _employee.setNumOfExperience(change); //TODO how get the senior parameters
					break;
				case 4:
					change = ui.free_input("add more detials ", "Please enter more detials:");
					// _employee.setYearsNum(change); //TODO how get the senior parameters
					break;
				case 5:
					show_senior_card();
					break;
				case 6:
					exit = true;
					break;
				default:
					exit = true;
			}
		}
	}

	public void show_senior_card() {
		// TODO print to screen the user card;
	}

	public void show_student_card() {
		// TODO print to screen the user card;
	}

	public void create_user_card() {
		do {
			status = ui.two_options("SIGN-IN", "Create your user_card:\n What is your status?", "Student", "Senior");
		} while (status != 0 && status != 1);
		String name = ui.free_input("Create user card", "please enter your name");
		String email = ui.free_input("Create user card", "please enter your email");

		String[] locations = { "South", "Center", "North" };
		int location_c = ui.some_options("Create user card", "please enter your location::", locations);
		String location = " ";
		if (location_c == 0) {
			location = "South";
		} else if (location_c == 1) {
			location = "Center";
		} else if (location_c == 2) {
			location = "North";
		} else {
			return;
		} // TODO: check. free new_job?

		String phone = ui.free_input("Create user card", "please enter your phone number");
		Personal_info personal_inf = new Personal_info(name, email, location, phone);

		boolean py = false, java = false, c = false, cpp = false, j_s = false;
		String[] languages = { "python", "java", "c", "cpp", "javascript" };
		int choice = -1;
		do {
			choice = ui.some_options("Create user card", "which programming languages do you know?", languages);
			switch (choice) {
				case 0:
					py = true;
					break;
				case 1:
					java = true;
					break;
				case 2:
					c = true;
					break;
				case 3:
					cpp = true;
					break;
				case 4:
					j_s = true;
					break;
			}
			choice = ui.yes_no("Add job", "Do you want to add another programming language?");
		} while (choice == 0);
		Languages prog_language = new Languages(py, java, c, cpp, j_s);

		if (status == 0) { // add sudent details to user card
			int gpa = Integer.valueOf(ui.free_input("Create user card", "please enter your GPA:"));
			String univesity = ui.free_input("Create user card",
					"please enter the name of the university where you are studying:");
			int years = Integer.valueOf(ui.free_input("Create user card", "Enter num of years left till graduation"));
			String extra_inf = ui.free_input("Create user card", "enter more information:");

			_employee = new Student(personal_inf, prog_language, extra_inf, univesity, years, gpa);
		} else if (status == 1) { // add senior details to user card
			String last_job = ui.free_input("Create user card", "Entar your last job:");
			int experience = Integer.valueOf(ui.free_input("Create user card", "enter num years of experience"));
			String extra_inf = ui.free_input("Create user card", "enter more information:");
			_employee = new Senior(personal_inf, prog_language, extra_inf, last_job, experience);
		}
	}

}// end of class
