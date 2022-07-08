package ex3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;


public class app {

	HashMap<String, E_Profile> users_map = new HashMap<String, E_Profile>(); // user name->profile
	HashMap<String, String> passwords = new HashMap<String, String>(); // user name->password
	ArrayList<Job> jobsList = new ArrayList<Job>();

	public app() {
		passwords.put("maneger", "1800400400");

		// add some employees and jobs for initional DB:
		add_user_to_DB(true, "Shira", "1111", "Center");
		add_user_to_DB(true, "Moriya", "2222", "North");
		add_user_to_DB(false, "Noa", "3333", "South");

		// create DB

		// student jobs:
		add_job_to_DB(true, "Software", "Sony", true, false, false, false, false, "Center", 85, 100);
		add_job_to_DB(true, "Firmware", "Apple", true, false, true, false, false, "Center", 90, 92);
		add_job_to_DB(true, "Security", "Microsoft", true, false, true, true, false, "Center", 90, 110);
		add_job_to_DB(true, "Software", "Sony", true, false, false, false, false, "North", 85, 100);
		add_job_to_DB(true, "Firmware", "Apple", true, false, true, false, false, "North", 90, 95);
		add_job_to_DB(true, "Security", "Microsoft", true, false, true, true, false, "North", 90, 110);
		add_job_to_DB(true, "DevOps", "Elta", true, false, true, false, true, "North", 80, 80);
		add_job_to_DB(true, "Software", "Apple", false, false, true, true, false, "Center", 85, 90);
		add_job_to_DB(true, "Firmware", "Intel", false, true, true, false, false, "North", 90, 92);
		add_job_to_DB(true, "DevOps", "Microsoft", true, false, true, true, false, "Center", 85, 88);
		add_job_to_DB(true, "Security", "Elta", true, false, true, false, true, "Center", 85, 80);

		// Senior jobs:
		add_job_to_DB(false, "QA", "Intel", true, false, false, false, true, "Center", 5, 0); //salary isn't a param for Senior job
		add_job_to_DB(false, "Firmware", "Intel", true, false, false, false, true, "Center", 1, 0);
		add_job_to_DB(false, "Software", "Intel", true, false, false, false, true, "Center", 5, 0);
		add_job_to_DB(false, "DevOps", "Intel", true, false, false, false, true, "Center", 8, 0);
		add_job_to_DB(false, "Security", "Intel", true, false, false, false, true, "Center", 3, 0 );
		add_job_to_DB(false, "Security", "Microsoft", true, true, true, false, true, "Center", 5, 0);


	}
	
	public void start_menu() {
		boolean exit = false;
		while (!exit) {
			int operation = UI.panel_welcome();
			switch (operation) {
				case 0:
					login();
					break;
				case 1:
					sign_in();
					break;
				case 2:
					exit = true;
					break;
				default:
					exit = true;
			}
		}

	}

	public void login() {
		HashMap<String, String> user = UI.login();
		if (!isExist(user.get("user_name"), user.get("password")))// check if the name exist in the system.
			UI.error_message("the user name or password are not correct");
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
			user_name = UI.free_input("SIGN-IN", "Please enter a user name:");
			if (user_name == null)
				return;
			flag = user_name_in_use(user_name);
			if (flag)
				UI.error_message("This name is taken. pls choose another one");
		} while (flag);
		final String password = UI.free_input("SIGN-IN", "Please enter a password:");
		if (password == null)
			return;
		
		E_Profile e_p = new E_Profile();
		boolean create_successful =  e_p.create_user_card();
		if (!create_successful){
				UI.reg_message("create user card is failed or canceled. pls try again");
				return;
		}	
		users_map.put(user_name, e_p);
		passwords.put(user_name, password);
		UI.reg_message("SIGN-IN", "Thank you for signing in! pls log-in");
	}

	public void user_menu(String user_name) {

		while (true) {
			String[] menu_options = { "My profile", "Find my next Job","LOG-OUT"};
			int choice = UI.some_options("Menu", "what do you want to do:",menu_options);
			if (choice == 0) { // My profile
				users_map.get(user_name).menu();
			} else if (choice == 1) { // Find my next Job
				String[] find_job_options = { "Show all jobs",
						"Show job by lcation",
						"Show job by field",
						"find my dream job" }; // options for printing
				int operation = UI.some_options("Job Matcher", "Find my next Job", find_job_options);
				switch (operation) {
					case 0: // print all jobs (according to the employee type- student/senior)
						print_all_jobs(user_name); // call this func with card of this user.
						break;
					case 1:
						print_jobs_by_location(user_name);
						break;
					case 2:
						print_jobs_by_field(user_name);
						break;	
					case 3:
						find_the_dream_job(user_name);
						break;
				}
			} else if (choice == 2 || choice == -1 )// logout
				return;
		}
	}

	private void print_jobs_by_field(String user_name) {
		String[] field = { "Software", "Firmware" , "Security" , "DevOps" , "QA"};
		int choice1 = -1;
		do {
			choice1 = UI.some_options("Find job by field",
					"In which field of job would you like to look?", field);
			switch (choice1) {
				case 0:
					show_job_field(user_name, "Software");
					break;
				case 1:
					show_job_field(user_name,"Firmware");
					break;
				case 2:
					show_job_field(user_name,"Security");
					break;
				case 3:
					show_job_field(user_name,"DevOps");
					break;
				case 4:
					show_job_field(user_name,"QA");
					break;
				default:	
					break;
			}
			choice1 = UI.yes_no("Find job by field", "Do you want to see another jobs?");
		} while (choice1 == 0);
	
	}

	private void show_job_field(String user_name,String job_field) {
		//helper func to print jobs by field
		boolean flag=true;
		int retval = 0;
		String[] button = {"next","exit"};
		if (users_map.get(user_name).getEmployee() instanceof Student) {
			for (int i = 0; i < jobsList.size(); i++) {
				if (jobsList.get(i) instanceof Student_Job && (jobsList.get(i).getJobField().equals(job_field))) {
					flag=false;
					retval = UI.some_options(job_field , jobsList.get(i).toString() , button);
					if (retval == -1 || retval == 1)
						return;
				}
			}
		}
		if (users_map.get(user_name).getEmployee() instanceof Senior) {
			for (int i = 0; i < jobsList.size(); i++) {
				if (jobsList.get(i) instanceof Senior_Job && (jobsList.get(i).getJobField().equals(job_field))) {
					flag=false;
					retval = UI.some_options(job_field, jobsList.get(i).toString(), button);
					if (retval == -1 || retval == 1)
						return;
				}
			}
		}
		if(flag){
			UI.reg_message("There are no jobs in "+ job_field);
			return;
		}
	}

	private void print_jobs_by_location(String user_name) {
		boolean flag=true;
		int retval = 0;
		String[] button = {"next","exit"};
		String location = users_map.get(user_name).getEmployeeLocation();
		if (users_map.get(user_name).getEmployee() instanceof Student) {
			for (int i = 0; i < jobsList.size(); i++) {
				if (jobsList.get(i) instanceof Student_Job && (jobsList.get(i).getLocation().equals(location))) {
					flag=false;
					retval = UI.some_options("jobs in "+location, jobsList.get(i).toString(), button);
					if (retval == -1 || retval == 1)
						return;
				}
			}
		}
		if (users_map.get(user_name).getEmployee() instanceof Senior) {
			for (int i = 0; i < jobsList.size(); i++) {
				if (jobsList.get(i) instanceof Senior_Job && (jobsList.get(i).getLocation().equals(location))) {
					flag=false;
					retval = UI.some_options("jobs in "+location, jobsList.get(i).toString(), button);
					if (retval == -1 || retval == 1)
						return;
				}
			}
		}
		if(flag){
			UI.reg_message("there are no jobs in yor location..");
			return;
		}

	}

	private void find_the_dream_job(String user_name) {
		// this function gives a csore to each job in the job-list, for specific
		// parameters
		// the parameters are fit to the employee's status
		// and are calculeted according to the employee's profile,

		HashMap<Integer, Integer> scores = new HashMap<Integer, Integer>();
		int score;

		if (users_map.get(user_name).getEmployee() instanceof Student) {
			for (int i = 0; i < jobsList.size(); i++) { // for each job
				if (jobsList.get(i) instanceof Student_Job) {
					score = 0;
					score += score_lang(user_name, i);
					score += score_location(user_name, i);
					score += score_gpa(user_name, i);
					scores.put(i, score);
				} else {
					scores.put(i, -1);
				}
			}
		}
		if (users_map.get(user_name).getEmployee() instanceof Senior) {
			for (int i = 0; i < jobsList.size(); i++) {
				if (jobsList.get(i) instanceof Senior_Job) {
					score = 0;
					score += score_lang(user_name, i);
					score += score_location(user_name, i);
					score += score_experience(user_name, i);
					scores.put(i, score);
				} else {
					scores.put(i, -1);
				}
			}
		}

		// sort the hash by the value
		// in order to print the matcher jobs

		LinkedHashMap<Integer, Integer> sorted_jobs = sortHashMapByValues(scores);

		// print the top 3 jobs with the best score (score must be at least 30)
	
		List<Integer> keys =new ArrayList<Integer>(sorted_jobs.keySet());
		Collections.reverse(keys); //reverse the keys order (ordered by the score's value)- for starting from the high score to the low one
		int key_counter = 0;
		int prints_counter = 0;
		int retval = 0;
		String[] button = {"next","exit"};

		for (Integer key : keys) {

			score = sorted_jobs.get(key);
			// for the first job only:
			if (key_counter == 0 && score < 10) { // there is't a fit job
				UI.reg_message("Unfortunately there are no jobs that fit you perfectly.. \n Try again later");
				return;
			}
			// check if the job's score is high enough. abort if not 
			// (the jobs aresorted by them score)
			if (score < 10 || prints_counter == 3) {
				UI.reg_message("That's all :)\n Hurry up! your new employers are waiting for you");
				return;
			} else {
				if (prints_counter == 0){
					String[] first_m_button = {"show me"};
					retval = UI.some_options("your dream-job", "we found jobs special for you! click here to see! :)", first_m_button);
					if (retval == -1)
						return;
				}
				retval = UI.some_options("your dream-job", jobsList.get(key).toString(), button);
				if (retval == 1 || retval == -1)
					return;
				prints_counter++;
			}
			key_counter++;
		}

	}

	private int score_lang(String user_name, int job_index) {
		//the function gets user name and job index and check if the user have the job's required languages
		int score = 0;
		// python
		if (jobsList.get(job_index).getLanguages().isPython()) {
			if (users_map.get(user_name).getEmployee().getLanuage().isPython())
				score += 10;
			else
				score -= 5;
		}
		// C
		if (jobsList.get(job_index).getLanguages().isC()) {
			if (users_map.get(user_name).getEmployee().getLanuage().isC())
				score += 10;
			else
				score -= 5;
		}
		// cpp
		if (jobsList.get(job_index).getLanguages().isCpp()) {
			if (users_map.get(user_name).getEmployee().getLanuage().isCpp())
				score += 10;
			else
				score -= 5;
		}
		// Java
		if (jobsList.get(job_index).getLanguages().isJava()) {
			if (users_map.get(user_name).getEmployee().getLanuage().isJava())
				score += 10;
			else
				score -= 5;
		}

		// Javascript
		if (jobsList.get(job_index).getLanguages().isJavascript()) {
			if (users_map.get(user_name).getEmployee().getLanuage().isJavascript())
				score += 10;
			else
				score += 5;
		}

		// Bonus- if the employee knows all languages that required
		int How_many_lang = jobsList.get(job_index).getLanguages().How_many_lang();
		if (score / 10 == How_many_lang)
			score += 10;

		return score;
	}

	private int score_location(String user_name, int job_index) {
		//the function gets user name and job index and check if the user and the job in the same location 
		int score = 0;
		String user_location = users_map.get(user_name).getEmployeeLocation();
		String job_location = jobsList.get(job_index).getLocation();

		if (user_location == job_location) { // max score if both have the same location
			score += 10;
		} else if (user_location.equals("Center") || job_location.equals("Center")) { // half of the score if the
																						// employee or the job in the
																						// center.
			score += 5;
		} else { // the job is in the North and the Employee is from the South or vice versa
			score -= 5;
		}
		return score;
	}

	private int score_experience(String user_name, int job_index) {
		int score = 0;
		int job_request = ((Senior_Job) jobsList.get(job_index)).getSeniority();
		int employee_experience = ((Senior) users_map.get(user_name).getEmployee()).get_seniority();

		int gap = employee_experience - job_request;
		if (gap > 0)
			score += 15;
		else if (gap == 0)
			score += 10;
		else if (gap == -1)
			score += 5;
		else if (gap < -3)
			score -= 10;

		return score;
	}

	private int score_gpa(String user_name, int job_index) {
		//the function gets user name and job index and check if the user have the job's required gpa
		int score = 0;
		int job_request = ((Student_Job) jobsList.get(job_index)).getGpa();
		int employee_gpa = ((Student) users_map.get(user_name).getEmployee()).getGpa();

		int gap = employee_gpa - job_request;
		if (gap > 10)
			score += 15;
		else if (gap > 0)
			score += 12;
		else if (gap == 0)
			score += 10;
		else if (gap > -5)
			score += 5;
		else if (gap < -10)
			score -= 15;

		return score;
	}

	public LinkedHashMap<Integer, Integer> sortHashMapByValues(
			HashMap<Integer, Integer> passedMap) {
		List<Integer> mapKeys = new ArrayList<>(passedMap.keySet());
		List<Integer> mapValues = new ArrayList<>(passedMap.values());
		Collections.sort(mapValues);
		Collections.sort(mapKeys);

		LinkedHashMap<Integer, Integer> sortedMap = new LinkedHashMap<>();

		Iterator<Integer> valueIt = mapValues.iterator();
		while (valueIt.hasNext()) {
			Integer val = valueIt.next();
			Iterator<Integer> keyIt = mapKeys.iterator();

			while (keyIt.hasNext()) {
				Integer key = keyIt.next();
				Integer comp1 = passedMap.get(key);
				Integer comp2 = val;

				if (comp1.equals(comp2)) {
					keyIt.remove();
					sortedMap.put(key, val);
					break;
				}
			}
		}
		return sortedMap;
	}

	public void maneger_menu() {
		boolean exit = false;
		while (!exit) {
			String[] manager_options = { "Add new job", "Delete a job", "Show all jobs", "LOG-OUT" }; // options for printing
			int operation = UI.some_options("Job Matcher", "Maneger Menu", manager_options);
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
				int retval = UI.some_options("all jobs", "Job num " + (i + 1) + jobsList.get(i).toString(), button);
				if (retval == 0) {
					delete_job_index = i;
					break;
				}
				if(retval== -1){return;}
			}
			 else if (i < jobsList.size() - 1) {
				String[] button = { "back", "delete", "next" };
				int retval = UI.some_options("all jobs", "Job num " + (i + 1) + jobsList.get(i).toString(), button);
				if (retval == 1)
				{
					delete_job_index = i;
					break;
				}
				else if (retval == 0){i -= 2;}
				else if(retval== -1){return;}
			} else { // last job in the list
				String[] button = { "back", "delete", "cancel" };
				int retval = UI.some_options("all jobs", "Job num " + (i + 1) + jobsList.get(i).toString(), button);
				if (retval == 1) {
					delete_job_index = i;
					break;
				} 
				else if (retval == 0){i -= 2;}
				else if(retval== -1){return;}
			}
		}
		if (delete_job_index != -1){
			jobsList.remove(delete_job_index);
			UI.reg_message("the job was successfully removed");
		}
		
	}

	private void print_all_jobs(String user_name) { //user
		int retval = -1;
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
					if (i == 0) {
						String[] buttons = {"next", "exit"};
						retval = UI.some_options("all jobs", "Job num " + (i + 1) + jobsList.get(i).toString(), buttons);
						if (retval == 1 || retval == -1)
							return;
					} else if (i != last_student_index) {
						String[] buttons = { "back", "next", "exit" };
						retval = UI.some_options("all jobs", "Job num " + (i + 1) + jobsList.get(i).toString(),buttons);
						if (retval == 0)
							i -= 2;
						if (retval == 2 || retval == -1)
							return;
					} else { // last job in the list
						String[] buttons = { "back", "exit"};
						retval = UI.some_options("all jobs", "Job num " + (i + 1) + jobsList.get(i).toString(),buttons);
						if (retval == 0)
							i -= 2;
						if (retval == 1 || retval == -1)
							return;
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
					if (i == 0) {
						String[] button = { "next" ,"exit"};
						retval = UI.some_options("all jobs", "Job num " + (i + 1) + jobsList.get(i).toString(), button);
						if (retval == 1 || retval == -1)
							return;
					}
					if (i != last_senior_index) {
						String[] button = { "back", "next", "exit" };
						retval = UI.some_options("all jobs", "Job num " + (i + 1) + jobsList.get(i).toString(),
								button);
						if (retval == 0)
							i -= 2;
						if (retval == 2 || retval == -1)
							return;
					} else { // last job in the list
						String[] button = { "back", "exit" };
						retval = UI.some_options("all jobs", "Job num " + (i + 1) + jobsList.get(i).toString(),
								button);
						if (retval == 0)
							i -= 2;
						if (retval == 1 || retval == -1)
							return;
					}
				}
			}
		}
	}

	private void print_all_jobs() {// for maneger
		int retval = -1;
		for (int i = 0; i < jobsList.size(); i++) {
			if (i == 0) {
				String[] button = { "next", "exit"};
				retval = UI.some_options("all jobs", "Job num " + (i + 1) + jobsList.get(i).toString(), button);
				if (retval == 1 || retval == -1)
					return;
			} else if (i < jobsList.size() - 1) {
				String[] button = { "back", "next", "exit"};
				retval = UI.some_options("all jobs", "Job num " + (i + 1) + jobsList.get(i).toString(), button);
				if (retval == 0)
					i -= 2;
				if (retval == 2 || retval == -1)
					return;
			} else { // last job in the list
				String[] button = { "back" ,"exit"};
				retval = UI.some_options("all jobs", "Job num " + (i + 1) + jobsList.get(i).toString(), button);
				if (retval == 0)
					i -= 2;
				if (retval == 1 || retval == -1)
					return;
			}
			;
		}
	}

	public void add_job() {
		int choice = -1;
		String Job_type = " ", job_field = " ";
		boolean py = false, java = false, c = false, cpp = false, j_s = false;

		//get the params tha relevant to both student and Senior:

		//job status type
		choice = UI.two_options("Add job", "Add new job:", "Student job", "Senior job");
		if (choice == 0)
			Job_type = "student";
		else if (choice == 1)
			Job_type = "senior";
		else
			return;

		//company
		String company = UI.free_input("Add job", "what is the company name?");
		if (company == null)
			return;
		//location
		String[] locations = { "South", "Center", "North" };
		int location_c = UI.some_options("Add job", "Please choose the job location:", locations);
		String location = " ";
		if (location_c == 0) {
			location = "South";
		} else if (location_c == 1) {
			location = "Center";
		} else if (location_c == 2) {
			location = "North";
		} else {
			return;
		}

		//field
		String[] fields = { "Software", "Firmware" , "Security" , "DevOps" , "QA"};
		choice = UI.some_options("Add job", "what is the job Erea?", fields);
		switch (choice) {
			case 0:
				job_field= "Software";
				break;
			case 1:
				job_field="Firmware";
				break;
			case 2:
				job_field="Security";
				break;
			case 3:
				job_field="DevOps";
				break;
			case 4:
				job_field="QA";
				break;
			default:
			 	return;	
		}
		
		//required languages
		String[] languages = { "python", "java", "c", "cpp", "javascript" };
		do {
			choice = UI.some_options("Add job", "what programming languages is required?", languages);
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
					return;
			}
			choice = UI.yes_no("Add job", "Do you want to add another programming language?");
		} while (choice == 0);
		Languages prog_language = new Languages(py, java, c, cpp, j_s);

		//get the separated params:

		boolean valid_input;
		if (Job_type.equals("student")) { // stuednt
			int gpa_req, salary, num_hours;

			//GPA
			do {
			valid_input = true;
			String str_gpa = UI.free_input("Add job", "Please enter a required GPA");
			if (str_gpa == null)
				return;
			gpa_req = Integer.valueOf(str_gpa);
			if (gpa_req < 60 || gpa_req > 100){
				valid_input = false;
				UI.reg_message("the number you entered is out of scope. pls try again");
			}
			}while (!valid_input);
			
			//salary
			do {
			valid_input = true;
			String str_salary = UI.free_input("Add job", "Please enter a salary- NIS per hour");
			if (str_salary == null)
				return;
			salary = Integer.valueOf(str_salary);
			if (salary < 0){
				valid_input = false;
				UI.reg_message("the number you entered is out of scope. pls try again");
			}
			}while (!valid_input);

			//hours per week
			do {
			valid_input = true;
			String str_num_hours = UI.free_input("Add job", "Please enter a num of hours in week");
			if (str_num_hours == null)
				return;
			num_hours = Integer.valueOf(str_num_hours);
			if (num_hours < 0 || num_hours > 50){
				valid_input = false;
				UI.reg_message("the number you entered is out of scope. pls try again");
			}
			}while (!valid_input);

			//create the new Student job and add it to the jobs list
			Job new_job = new Student_Job(job_field, company, location, prog_language, salary, gpa_req, num_hours);
			this.jobsList.add(new_job);
			UI.reg_message("The job was successfully added");
		} 
		else if (Job_type.equals("senior")) {// senior
			//required experience
			String experience = UI.free_input("Add job", "Please enter a required experience (text)");
			if (experience == null)
				return;

			//seniority
			int seniority;
			do{
			valid_input = true;
			String str_seniority= UI.free_input("Add job", "Please enter the number of required seniority years");
			if (str_seniority == null)
				return;
			seniority= Integer.valueOf(str_seniority);
			if (seniority < 0){
				valid_input = false;
				UI.reg_message("the number you entered is out of scope. pls try again");
			}
			}while (!valid_input);
			//create the new Senior job and add it to the jobs list
			Job new_job = new Senior_Job(job_field, company, location, prog_language, seniority, experience);
			this.jobsList.add(new_job);
			UI.reg_message("The job was successfully added");
		}
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

	public void add_user_to_DB(boolean type, String name, String password, String location) {
		E_Profile profile = new E_Profile(type, name, location);
		users_map.put(name, profile);
		passwords.put(name, password);
	}

	public void add_job_to_DB(boolean type, String field, String company, boolean python, boolean java, boolean c,
			boolean cpp, boolean javascript, String location, int gpa_or_seniority, int salery) {
		Languages languages = new Languages(python, java, c, cpp, javascript);
		if (type) { // student
			Student_Job job = new Student_Job(field, company, location, languages, salery, gpa_or_seniority, 20);
			jobsList.add(job);
		} else { // senior
			Senior_Job job = new Senior_Job(field, company, location, languages, gpa_or_seniority, " ");
			jobsList.add(job);
		}

	}

} // end of app class
