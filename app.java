package ex3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.*; 

public class app {

	HashMap<String, E_Profile> users_map = new HashMap<String, E_Profile>(); // user name->profile
	HashMap<String, String> passwords = new HashMap<String, String>(); // user name->password
	ArrayList<Job> jobsList = new ArrayList<Job>();
	Scanner input = new Scanner(System.in); // Create a Scanner object
	//for printing:
	UI ui = new UI();
	// ImageIcon logo = new ImageIcon("logo.png");
	// String[] responses={null,null,null}; 

	public app() {
		passwords.put("maneger", "1800400400");
		E_Profile e_p1 = new E_Profile();
		users_map.put("shira", e_p1);
		passwords.put("shira", "1111");
	}

	public void start_menu() {
		int exit = 0;
		while (exit == 0) {
			int operation = ui.panel_welcome();
			// responses[0] = "LOG-IN";
			// responses[1] = "SIGN-IN";
			// responses[2] = "Exit";
			// //{"LOG-IN", "SIGN-IN", "Exit" };
			// int operation = JOptionPane.showOptionDialog(null,
			// "Welcome to Job Matcher!", 
			// "welcome", 
			// JOptionPane.YES_NO_CANCEL_OPTION, 
			// JOptionPane.INFORMATION_MESSAGE, 
			// logo, 
			// responses, 
			// null);
			//System.out.println(" ********************Welcome to Job Matcher! ********************");
			//System.out.println("1.LOG-IN ");
			//System.out.println("2.SIGN-IN ");
			//System.out.println("3.Exit ");
			//int app = input.nextInt();
			switch (operation) {
				case 0:
					login();
					break;
				case 1:
					sign_in();
					break;
				case 2:
					// log out ->menu
					exit = 1;
					break;
				default:
					//System.out.println("Not a valid choice!");
					break;
			}
		}
		input.close();

	}

	public void login() {
		HashMap<String, String> user = ui.login();  
		 if (!isExist(user.get("user_name"), user.get("user_name")))// check if the name exist in the system.
		 	ui.error_message("the user name or password are not correct");
		// check if maneger
		else if (user.get("user_name").equals("maneger") && user.get("user_name").equals("1800400400"))
			maneger_menu();
		else
			user_menu(user.get("user_name"));
	}

	public void sign_in() {
		String user_name;
		boolean flag = false;
		do {
			System.out.println("Please enter a user name:");
			user_name = input.nextLine();
			user_name = input.nextLine();
			flag = user_name_in_use(user_name);
			if (flag)
				System.out.println("This name is taken. pls choose another one");
		} while (flag);
		System.out.println("Please enter a password:");
		String password = input.nextLine();
		// password = input.nextLine();
		passwords.put(user_name, password);
		E_Profile e_p = new E_Profile();
		e_p.create_user_card();
		users_map.put(user_name, e_p);
		System.out.println("Thank you for signing in! pls log-in");
		// return to main menu- to login
	}

	public void user_menu(String user_name) {

		while (true) {
			System.out.println("********************Menu********************");
			System.out.println("1. Edit your profile ");
			System.out.println("2. show all jobs");
			System.out.println("3. Show job by lcation ");
			System.out.println("4. Show job by type ");
			System.out.println("5. Exit");
			System.out.println("********************************************");
			System.out.println("Enter app number:");
			int app = input.nextInt();
			switch (app) {
				case 1:
					users_map.get(user_name).menu();
					break;
				case 2: // הדפסת כל המשרות בהתאמה לסוג מחפש- סטודנט או עובד
					print_all_jobs(user_name); // call this func with card of this user.
					break;
				case 3:
					// TODO:print_jobs_by_location()
					break;
				case 4:
					// TODO:print_jobs_by_type()
					break;
				case 5:// log out ->start menu
					start_menu();
					break;
				default:
					System.out.println("Not a valid choice!");
					break;
			}
		}

	}

	public void maneger_menu() {
		int exit = 0;
		while (exit == 0) {
			System.out.println("********************Maneger Menu********************");
			System.out.println("1. add new job ");
			System.out.println("2. delete a job");
			System.out.println("3. show all jobs");
			System.out.println("4. Exit");
			System.out.println("********************************************");
			System.out.println("Enter app number:");
			int app = input.nextInt();
			switch (app) {
				case 1:
					add_job();
					break;
				case 2:
					// TODO:delete_job();
					break;
				case 3:
					print_all_jobs();
					break;
				case 4:
					exit = 1;
					break;
				default:
					System.out.println("Not a valid choice!");
					break;
			}
		}

	}

	private void print_all_jobs(String user_name) { //
		if (users_map.get(user_name).getEmployee() instanceof Student) {
			for (int i = 0; i < jobsList.size(); i++) {
				if (jobsList.get(i) instanceof Student_Job) {
					System.out.print(jobsList.get(i));
				}
			}
		}
		if (users_map.get(user_name).getEmployee() instanceof Senior) {
			for (int i = 0; i < jobsList.size(); i++) {
				if (jobsList.get(i) instanceof Senior_Job) {
					System.out.print(jobsList.get(i));
				}
			}
		}
	}

	private void print_all_jobs() {
		for (int i = 0; i < jobsList.size(); i++) {
			System.out.print(jobsList.get(i));
		}
	}

	public void add_job() {
		Job new_job;
		int app2, app = 0;
		String Job_type = " ", type = " ";
		boolean py = false, java = false, c = false, cpp = false, j_s = false;
		System.out.println("what is the position type? 1)Student, 2)Senior");// Job type
		app = input.nextInt();
		while (app != 1 && app != 2) {
			System.out.println("Pls enter 1 or 2");
			app = input.nextInt();
		}
		if (app == 1) {
			Job_type = "student";
		} else if (app == 2) {
			Job_type = "senior";
		}
		System.out.println("what is the job location?");// Job location
		String location = input.nextLine();
		location = input.nextLine();
		System.out.println("what is the job Erea? 1)Hardware, 2)Software"); // Job field
		app = input.nextInt();
		while (app != 1 && app != 2) {
			System.out.println("Pls enter 1 or 2");
			app = input.nextInt();
		}
		if (app == 1) {
			type = "hardware";
		} else if (app == 2) {
			type = "software";
		}
		do {
			System.out.println("what programming languages is required?");// programming langueges
			System.out.println("1)python");
			System.out.println("2)java");
			System.out.println("3)c");
			System.out.println("4)cpp");
			System.out.println("5)javascript");
			app = input.nextInt();
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
			app2 = input.nextInt();
		} while (app2 == 1);
		Languages prog_language = new Languages(py, java, c, cpp, j_s);
		System.out.println("what is the company name?"); // company name
		String company = input.nextLine();
		company = input.nextLine();
		if (Job_type.equals("student")) { // stuednt
			System.out.println("Please enter a required GPA");
			int gpa_req = input.nextInt();
			System.out.println("Please enter a salary per hour");
			int salary = input.nextInt();
			System.out.println("Please enter a num of hours in week");
			int num_hours = input.nextInt();
			new_job = new Student_Job(type, company, location, prog_language, salary, gpa_req, num_hours);
		} else {// senior
			System.out.println("Please enter a required experience");
			String experience = input.nextLine();
			System.out.println("Please enter a required num of seniority");
			int seniority = input.nextInt();
			new_job = new Senior_Job(type, company, location, prog_language, seniority, experience);
		}
		this.jobsList.add(new_job);
	}

	public boolean isExist(String user_name, String password) {
		if (passwords.containsKey(user_name)) {
			String x = passwords.get(user_name);
			if (x.equals(password)) {
				return true;
			}

		}
		return false;
	}

	private boolean user_name_in_use(String user_name) {
		if (user_name.equals("maneger")) { // dont allow choose maneger name
			return true;
		}
		return passwords.containsKey(user_name);
	}

} // end of app class
