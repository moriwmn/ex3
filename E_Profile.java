package ex3;

//import java.util.Scanner;

public class E_Profile implements Profile {

	private int status;
	private Employee _employee; // student/ senior

	public E_Profile() {
	}

	public E_Profile(boolean type, String name, String location) { // default
		Personal_info details = new Personal_info(location);
		details.setName(name);
		Languages languages = new Languages();
		if (type) { // true = > student / false=> senior
			this._employee = new Student(details, languages, "no_extra", "BIU", 3, 90);
			this.status = 0; 
		} else {
			this._employee = new Senior(details, languages, "no_extra", "CEO", 7);
			this.status = 1;
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
		if (this.status == 0)
			student_changes();
		else if (this.status == 1)
			senior_changes();
	}

	public void student_changes() {
		boolean exit = false;
		while (!exit) {
			String change = null;
			String[] option = { "change programming languages",
					"Change email",
					"change Phone number",
					"Change your University Year",
					"change your GPA",
					"show my user card",
			};
			int choice = UI.some_options("Menu", "Edit your user card:", option);
			switch (choice) {
				case 0:
					change_lang();
					break;
				case 1:
					change = UI.free_input("Change email", "Please enter new email:");
					if (change == null)
						break;
					_employee.setEmail(change);
					break;
				case 2:
					change = UI.free_input("Change phone number", "Please enter new phone number: (digits only)");
					if (change == null)
						break;
					this._employee.setPhone(phone_num_isValid(change));
					break;
				case 3:
					change = UI.free_input("Change Study Year ", "Please enter the new Year: (a number)");
					if (change == null)
						break;
					((Student) _employee).setYearsNum(Integer.valueOf(change));
					break;
				case 4:
					change = UI.free_input("Change your GPA ", "Please enter the new GPA:");
					if (change == null)
						break;
					((Student) _employee).setGpa(gpa_isValid(Integer.valueOf(change)));
					break;
				case 5:
					show_user_card();
					break;
				default:
					exit = true;
					break;
			}
		}
	}

	public void senior_changes() { 
		boolean exit = false;
		while (!exit) {
			String change = "null";
			String[] option = { "change programming languages", 
								"Change email", 
								"change Phone number",
								"Change num of experience",
								"add more detials", "show my user card"};
			int choice = UI.some_options("Menu", "Edit your user card:", option);
			switch (choice) {
				case 0:
					change_lang();
				case 1:
					change = UI.free_input("Change email", "Please enter new email:");
					if (change == null)
						break;
					_employee.setEmail(change);
					break;
				case 2:
					change = UI.free_input("Change phone number", "Please enter new phone number: (digits only)");
					if (change == null)
						break;
					this._employee.setPhone(phone_num_isValid(change));
					break;
				case 3://
					change = UI.free_input("Change num of experiance ", "Please enter the new num of experiance years:");
					if (change == null)
						break;
					((Senior) _employee).set_seniority(Integer.valueOf(change));
					break;
				case 4:
					change = UI.free_input("add more detials ", "Please enter more detials:");
					if (change == null)
						break;
					_employee.setExtraInf(change);
					break;
				case 5:
					show_user_card();
					break;
				case 6:
					exit = true;
					break;
				default:
					exit = true;
			}
		}
	}

	public void show_user_card() {
		UI.reg_message("my user card", _employee.toString());
	}

	public boolean create_user_card() {
		this.status = UI.two_options("SIGN-IN", "Create your user_card:\n What is your status?", "Student", "Senior");
		if (status != 0 && status != 1)
			return false;
		String name = UI.free_input("Create user card", "please enter your name");
		if (name == null)
			return false;
		String email = UI.free_input("Create user card", "please enter your email");
		if (email == null)
			return false;

		String[] locations = { "South", "Center", "North" };
		int location_c = UI.some_options("Create user card", "please enter your location:", locations);
		String location = " ";
		if (location_c == 0) {
			location = "South";
		} else if (location_c == 1) {
			location = "Center";
		} else if (location_c == 2) {
			location = "North";
		} else {
			return false;
		}

		String phone = UI.free_input("Create user card", "please enter your phone number (digits only");
		if (phone == null)
			return false;
		phone = phone_num_isValid(phone);
		if (phone.equals("error"))
			return false;
		Personal_info personal_inf = new Personal_info(name, email, location, phone);
		boolean py = false, java = false, c = false, cpp = false, j_s = false;
		String[] languages = { "python", "java", "c", "cpp", "javascript" };
		int choice = -1;
		do {
			choice = UI.some_options("Create user card", "which programming languages do you know?", languages);
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
				default:
					break; 
			}
			choice = UI.yes_no("Add job", "Do you want to add another programming language?");
		} while (choice == 0);
		Languages prog_language = new Languages(py, java, c, cpp, j_s);
		
		//separated params
		boolean valid_input = true;
		if (status == 0) { // add sudent details to user card
			String t_gpa = UI.free_input("Create user card", "please enter your GPA:");
			if (t_gpa == null)
				return false;
			int gpa = gpa_isValid(Integer.valueOf(t_gpa));
			if (gpa == -1) // isValid return error
				return false;
			String university = UI.free_input("Create user card",
					"please enter the name of the university where you are studying:");
			if (university == null)
				return false;
			String t_years =UI.free_input("Create user card", "Enter your university year (a number)");
			if (t_years == null)
				return false;
			int years =  Integer.valueOf(t_years);
			String extra_inf = UI.free_input("Create user card", "enter more information:");
			_employee = new Student(personal_inf, prog_language, extra_inf, university, years, gpa);
		} else if (status == 1) { // add senior details to user card
			String last_job = UI.free_input("Create user card", "Entar your last job:");
			if (last_job == null)
				return false;

			//seniority
			int seniority;
			do{
			valid_input = true;
			String str_seniority= UI.free_input("Add job", "Please enter the number seniority years you have");
			if (str_seniority == null)
				return false;
			seniority= Integer.valueOf(str_seniority);
			if (seniority < 0){
				valid_input = false;
				UI.reg_message("the number you entered is out of scope. pls try again");
			}
			}while (!valid_input);

			String extra_inf = UI.free_input("Create user card", "enter more information:");
			_employee = new Senior(personal_inf, prog_language, extra_inf, last_job, seniority);
		}
		return true;
	}

	// validtion:

	public String phone_num_isValid(String phone) {
		
		while (phone.length() != 9 && phone.length() != 10) {
			UI.reg_message("this phone number invaild pls enter valid value");
			phone = UI.free_input("Create user card", "please enter your phone number");
			if (phone == null)
				return "error";
		}
		return phone;
	}

	public int gpa_isValid(int gpa) {
		String str_gpa = "";
		while (gpa < 60 || gpa > 100) {
			UI.reg_message("this GPA is invaild. pls enter num in range 60-100");
			str_gpa = UI.free_input("Create user card", "please enter your GPA");
			if (str_gpa == null)
				return -1;
			gpa = Integer.valueOf(str_gpa);
		}
		return Integer.valueOf(gpa);
	}

	public void change_lang() {
		boolean exit = false;
		while (!exit) {
			int choice = UI.two_options("Menu", "change programming languages:", "add programming language",
					"delete programming language");
			switch (choice) {
				case 0:
					add_lang();
					break;
				case 1:
					delete_lang();
					break;
				default:
					exit = true;
			}

		}
	}

	public void add_lang() {
		String[] languages = { "python", "java", "c", "cpp", "javascript" };
		int choice1 = -1;
		do {
			choice1 = UI.some_options("add a programming language",
					"which programming languages do you want add?", languages);
			switch (choice1) {
				case 0:
					_employee.setPython(true);
					break;
				case 1:
					_employee.setJava(true);
					break;
				case 2:
					_employee.setC(true);
					break;
				case 3:
					_employee.setCpp(true);
					break;
				case 4:
					_employee.setJavascript(true);
					break;
			}
			choice1 = UI.yes_no("add a programming language",
					"Do you want to add another programming language?");
		} while (choice1 == 0);
	}

	public void delete_lang() {
		String[] languages = { "python", "java", "c", "cpp", "javascript" };
		int choice1 = -1;
		do {
			choice1 = UI.some_options("delete a programming language",
					"which programming languages do you want delete?", languages);
			switch (choice1) {
				case 0:
					_employee.setPython(false);
					break;
				case 1:
					_employee.setJava(false);
					break;
				case 2:
					_employee.setC(false);
					break;
				case 3:
					_employee.setCpp(false);
					break;
				case 4:
					_employee.setJavascript(false);
					break;
			}
			choice1 = UI.yes_no("add a programming language",
					"Do you want to add another programming language?");
		} while (choice1 == 0);
	}
}// end E_profile
