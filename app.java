package ex3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class app {
	
    HashMap<String, E_Profile> users_map = new HashMap<String, E_Profile>(); //user name->profile
	HashMap<String, String> passwords = new HashMap<String, String>(); //user name->password
    ArrayList jobsList= new ArrayList<Job>();
	Scanner input = new Scanner(System.in);  // Create a Scanner object
	public app() {
	}
  

    public void start_menu() {
		int exit = 0;
		while (exit == 0) {
			System.out.println(" ********************Welcome to Job Matcher! ********************");
			System.out.println("1.LOG-IN ");
			System.out.println("2.SIGN-IN ");
			System.out.println("3.Exit ");
			int app = Integer.parseInt(input.nextLine());
			switch (app) {
			case 1:
				login();
				break; 
			case 2:
				sign_in();
				break;
			case 3: 
			//log out ->menu
				exit=1; 
				break;	
			default: System.out.println("Not a valid choice!"); break;
			}
		}
		input.close();

    }
    public void login() {
		System.out.println("Please enter your user name:");
		String user_name = input.nextLine();
		System.out.println("Please enter your password:");
		String password = input.nextLine();
		if(!isExist(user_name))//check if the name exist in the system.
			System.out.println("the user name or password are not correct.");
		//check if maneger
		else if(user_name.equals("maneger") && password.equals("1800400400"))
			maneger_menu();
			else
				user_menu(user_name);
    }

    public void sign_in() {
		String user_name;
		boolean flag=false;
		do{
			System.out.println("Please enter a user name:");
			user_name = input.nextLine();
			flag=isExist(user_name);
			if(flag)
				System.out.println("This name is taken. pls choose another one");
		}while(flag);
		System.out.println("Please enter a password:");
		String password = input.nextLine(); //TODO: what to do with the password?
    	E_Profile e_p=new E_Profile(user_name);
    	users_map.put(user_name,e_p); 
		System.out.println("Thank you for signing in! pls log-in");
    	 //return to main menu- to login
    }
    
    public void user_menu(String user_name) {
		int exit = 0;
		while (exit == 0) {
			System.out.println("********************Menu********************");
			System.out.println("1. Edit your profile ");
			System.out.println("2. show all jobs");
			System.out.println("3. Show job by lcation ");
			System.out.println("3. Show job by type ");
			System.out.println("3. Show job by type ");
			System.out.println("4. Exit");
			System.out.println("********************************************");
			System.out.println("Enter app number:");
			int app = Integer.parseInt(input.nextLine());
			switch (app) {
			case 1:
				//TODO:Edit_profile_menu()-> this.users[i].menu(); 
				break; 
			case 2:
				//TODO:print_all_jobs()
				break;
			case 3: 
				//TODO:print_jobs_by_location()
				break;
			case 4: 
				//TODO:print_jobs_by_type()
				break;
			case 5: 
			//TODO:log out ->menu
				exit=1; 
				break;	
			default: System.out.println("Not a valid choice!"); break;
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
			int app = Integer.parseInt(input.nextLine());
			switch (app) {
			case 1:
				add_job();
				break; 
			case 2:
				//TODO:delete_job();
				break;
			case 3: 
				//TODO:print_all_jobs()
				break;
			case 4: 
				exit=1; 
				break;
			default: System.out.println("Not a valid choice!"); break;
			}
		}
		
	}
	public boolean isExist(String user_name){
		return passwords.containsValue(user_name);
	}
	private void add_job() {
		int app2=0;
		boolean py, java, c, cpp, j_s;
		System.out.println("what is the job type? 1)student, 2)Senior");
		String type=input.nextLine();
		System.out.println("what is the job location");
		String location=input.nextLine();
		do{
		System.out.println("what programming languages is required?");
		System.out.println("1)python");
		System.out.println("2)java");
		System.out.println("3)c");
		System.out.println("4)cpp");
		System.out.println("5)javascript");
		int app = Integer.parseInt(input.nextLine());
		switch (app) {
		case 1:
			 py=true;
			break; 
		case 2:
			 java=true;
			break;
		case 3: 
			 c=true;
			break;
		case 4: 
			 cpp=true;
			break;
		case 5: 
			 j_s=true;
			break;
		}
		System.out.println("To add another programming language pls press 1:");	
		app2 = Integer.parseInt(input.nextLine());
		} while(app2==1);
		Languages prog_language= new Languages(py, java, c, cpp, j_s);
		System.out.println("what is the company name?");
		String company=input.nextLine();
		Job new_job= new Job( type,  company,  location,  prog_language);//TODO:wht cannot get job
		this.jobsList.add(new_job);
	}
	


}