package ex3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.lang.model.util.ElementScanner14;

public class app {

	HashMap<String, E_Profile> users_map = new HashMap<String, E_Profile>(); // user name->profile
	HashMap<String, String> passwords = new HashMap<String, String>(); // user name->password
	ArrayList<Job> jobsList = new ArrayList<Job>();
	// Scanner input = new Scanner(System.in); // Create a Scanner object
	// for printing:
	UI ui = new UI();
	// ImageIcon logo = new ImageIcon("logo.png");
	// String[] responses={null,null,null};

	public app() {
		passwords.put("maneger", "1800400400");

		// add some employees and jobs for initional DB:
		add_user_to_DB(true, "Shira", "1111");
		add_user_to_DB(true, "Moriya", "2222");
		add_user_to_DB(false, "Noa", "3333");

		add_job_to_DB(true, "FW developer", "Apple");
		add_job_to_DB(true, "Security Researcher", "Microsoft");
		add_job_to_DB(false, "QA", "Intel");

	}

	public void start_menu() {
		boolean exit = false;
		while (!exit) {
			int operation = ui.panel_welcome();
			switch (operation) {
				case 0:
					login();
					break;
				case 1:
					sign_in();
					break;
				case 2:
					// log out ->menu
					exit = true;
					break;
				default:
					exit = true;
			}
		}
		// input.close();

	}

	public void login() {
		HashMap<String, String> user = ui.login();
		if (!isExist(user.get("user_name"), user.get("password")))// check if the name exist in the system.
			ui.error_message("the user name or password are not correct");
		// check if maneger
		else if (user.get("user_name").equals("maneger") && user.get("password").equals("1800400400"))
			maneger_menu();
		else
			user_menu(user.get("user_name"));
	}

	public void sign_in() {
		String user_name;
		boolean flag = false;
		do {
			user_name = ui.free_input("SIGN-IN", "Please enter a user name:");
			flag = user_name_in_use(user_name);
			if (flag)
				ui.error_message("This name is taken. pls choose another one");
		} while (flag);
		String password = ui.free_input("SIGN-IN", "Please enter a password:");
		passwords.put(user_name, password);
		E_Profile e_p = new E_Profile();
		e_p.create_user_card();
		users_map.put(user_name, e_p);
		ui.reg_message("SIGN-IN", "Thank you for signing in! pls log-in");
		// return to main menu- to login
	}

	public void user_menu(String user_name) {

		while (true) {
			int choice = ui.two_options("Menu", "what do you want to do:", "Edit my profile", "Find my next Job");
			if (choice == 0) { // My profile
				users_map.get(user_name).menu();
			} else if (choice == 1) { // Find my next Job
				String[] find_job_options = { "Show all jobs", "Show job by lcation", "Show job by type" }; // options
																											// for
																											// printing
				int operation = ui.some_options("Job Matcher", "Find my next Job", find_job_options);
				switch (operation) {
					case 0: // print all jobs (according to the employee type- student/senior)
						print_all_jobs(user_name); // call this func with card of this user.
						break;
					case 1:
						print_jobs_by_location(user_name);
						break;
					case 2:
						// TODO:print_jobs_by_type()
						break;
					case 3://
						break;
				}
			} else // cancel
				break;

		}

	}

	private void print_jobs_by_location(String user_name) {
		String location = users_map.get(user_name).getEmployeeLocation();
		if (users_map.get(user_name).getEmployee() instanceof Student) {
			for (int i = 0; i < jobsList.size(); i++) {
				if (jobsList.get(i) instanceof Student_Job && (jobsList.get(i).getLocation().equals(location))) {
					ui.reg_message(jobsList.get(i).toString());
				}
			}
		}
		if (users_map.get(user_name).getEmployee() instanceof Senior) {
			for (int i = 0; i < jobsList.size(); i++) {
				if (jobsList.get(i) instanceof Senior_Job && (jobsList.get(i).getLocation().equals(location))) {
					ui.reg_message(jobsList.get(i).toString());
				}
			}
		}
	}

	public void maneger_menu() {
		boolean exit = false;
		while (!exit) {
			String[] manager_options = { "Add new job", "Delete a job", "Show all jobs" }; // options for printing
			int operation = ui.some_options("Job Matcher", "Maneger Menu", manager_options);
			switch (operation) {
				case 0:
					add_job();
					break;
				case 1:
					delete_job();
					break;
				case 2:
					print_all_jobs();
					break;
				case 3:
					exit = true;
					break;
				default:
					exit = true;
			}
		}

	}

	private void delete_job() {
		int delete_job_index = -1;
		// go over the job list with option to delete
		for (int i = 0; i < jobsList.size(); i++) {
			if (i == 0) {
				String[] button = { "delete", "next" };
				int retval = ui.some_options("all jobs", "Job num " + (i + 1) + jobsList.get(i).toString(), button);
				if (retval == 0) {
					delete_job_index = i;
					break;
				}
			}
			if (i < jobsList.size() - 1) {
				String[] button = { "back", "delete", "next" };
				int retval = ui.some_options("all jobs", "Job num " + (i + 1) + jobsList.get(i).toString(), button);
				if (retval == 1) {
					delete_job_index = i;
					break;
				} else if (retval == 0)
					i -= 2;
			} else { // last job in the list
				String[] button = { "back", "delete", "cancel" };
				int retval = ui.some_options("all jobs", "Job num " + (i + 1) + jobsList.get(i).toString(), button);
				if (retval == 1) {
					delete_job_index = i;
					break;
				} else if (retval == 0)
					i -= 2;
			}
		}
		if (delete_job_index != -1)
			jobsList.remove(delete_job_index);
		// TODO: add success message;
	}

	private void print_all_jobs(String user_name) { //
		int j;
		if (users_map.get(user_name).getEmployee() instanceof Student) {
			// find the last index of student in the list:
			int last_student_index = -1;
			for (int i = jobsList.size() - 1; i < 0; i--) {
				if (jobsList.get(i) instanceof Student_Job) {
					last_student_index = i;
					break;
				}
			}
			// print jobs in a loop:
			for (int i = 0; i < jobsList.size(); i++) {
				if (jobsList.get(i) instanceof Student_Job) {
					j = i + 1;
					if (i == 0) {
						String[] button = { "next" };
						ui.some_options("all jobs", "Job num " + i + 1 + jobsList.get(i).toString(), button);
					}
					if (i != last_student_index) {
						String[] button = { "back", "next" };
						int retval = ui.some_options("all jobs", "Job num " + j + jobsList.get(i).toString(), button);
						if (retval == 0)
							i -= 2;
					} else { // last job in the list
						String[] button = { "back" };
						int retval = ui.some_options("all jobs", "Job num " + i + 1 + jobsList.get(i).toString(),
								button);
						if (retval == 0)
							i -= 2;
					}
				}
			}
		}
		if (users_map.get(user_name).getEmployee() instanceof Senior) {
			// find the last index of senior job in the list:
			int last_senior_index = -1;
			for (int i = jobsList.size() - 1; i < 0; i--) {
				if (jobsList.get(i) instanceof Senior_Job) {
					last_senior_index = i;
					break;
				}
			}
			for (int i = 0; i < jobsList.size(); i++) {
				if (jobsList.get(i) instanceof Senior_Job) {
					j = i + 1;
					if (i == 0) {
						String[] button = { "next" };
						ui.some_options("all jobs", "Job num " + j + jobsList.get(i).toString(), button);
					}
					if (i != last_senior_index) {
						String[] button = { "back", "next" };
						int retval = ui.some_options("all jobs", "Job num " + j + jobsList.get(i).toString(), button);
						if (retval == 0)
							i -= 2;
					} else { // last job in the list
						String[] button = { "back" };
						int retval = ui.some_options("all jobs", "Job num " + j + jobsList.get(i).toString(), button);
						if (retval == 0)
							i -= 2;
					}
				}
			}
		}
	}

	private void print_all_jobs() {// for maneger
		int j;
		for (int i = 0; i < jobsList.size(); i++) {
			j = i + 1;
			if (i == 0) {
				String[] button = { "next" };
				ui.some_options("all jobs", "Job num " + j + jobsList.get(i).toString(), button);
			} else if (i < jobsList.size() - 1) {
				String[] button = { "back", "next" };
				int retval = ui.some_options("all jobs", "Job num " + j + jobsList.get(i).toString(), button);
				if (retval == 0)
					i -= 2;
			} else { // last job in the list
				String[] button = { "back" };
				int retval = ui.some_options("all jobs", "Job num " + j + jobsList.get(i).toString(), button);
				if (retval == 0)
					i -= 2;
			}
			;
		}
	}

	public void add_job() {
		Job new_job;
		int choice = -1;
		String Job_type = " ", type = " ";
		boolean py = false, java = false, c = false, cpp = false, j_s = false;
		choice = ui.two_options("Add job", "Add new job:", "Student job", "Senior job");
		if (choice == 0)
			Job_type = "student";
		else if (choice == 1)
			Job_type = "senior";
		else
			return;

		String[] locations = { "South", "Center", "North" };
		int location_c = ui.some_options("Add job", "Please choose the job location:", locations);
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

		choice = ui.two_options("Add job", "what is the job Erea?", "Hardware", "Software");
		if (choice == 0) {
			type = "hardware";
		} else if (choice == 1) {
			type = "software";
		} else {
			return;
		}

		String[] languages = { "python", "java", "c", "cpp", "javascript" };
		do {
			choice = ui.some_options("Add job", "what programming languages is required?", languages);
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

		String company = ui.free_input("Add job", "what is the company name?");
		if (Job_type.equals("student")) { // stuednt
			int gpa_req = Integer.valueOf(ui.free_input("Add job", "Please enter a required GPA"));
			int salary = Integer.valueOf(ui.free_input("Add job", "Please enter a salary per hour"));
			int num_hours = Integer.valueOf(ui.free_input("Add job", "Please enter a num of hours in week"));
			// TODO: input validation. maybe: oone window for all 3?
			new_job = new Student_Job(type, company, location, prog_language, salary, gpa_req, num_hours);
		} else {// senior
			String experience = ui.free_input("Add job", "Please enter a required experience");
			int seniority = Integer.valueOf(ui.free_input("Add job", "Please enter a required num of seniority"));
			// TODO: input validation.
			new_job = new Senior_Job(type, company, location, prog_language, seniority, experience);
		}
		this.jobsList.add(new_job);
		ui.reg_message("The job was successfully added");
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

	public void add_user_to_DB(boolean type, String name, String password) {
		E_Profile profile = new E_Profile(type, name);
		users_map.put(name, profile);
		passwords.put(name, password);
	}

	public void add_job_to_DB(boolean type, String name, String company) {
		Languages languages = new Languages();
		if (type) { // student
			Student_Job job = new Student_Job("type", company, "North", languages, 100, 85, 20);
			jobsList.add(job);
		} else { // senior
			Senior_Job job = new Senior_Job("type", company, "South", languages, 5, "experience");
			jobsList.add(job);
		}

	}

} // end of app class
