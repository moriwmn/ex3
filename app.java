package ex_3;

import java.util.HashMap;

public class app {
	
    HashMap<String, E_Profile> users_map = new HashMap<String, E_Profile>(); //user name->profile
    HashMap<String, String> passwords = new HashMap<String, String>(); //user name->password
    All_Jobs jobsList;
    
    public void start_menu() {
		int exit = 0;
		while (exit == 0) {
			System.out.println("********************Job Matcher********************");
			System.out.println("1.LOG-IN ");
			System.out.println("2.SIGN-IN ");
			int app = Integer.parseInt(input.nextLine());
			switch (app) {
			case 1:
				login();
				break; 
			case 2:
				signin();
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
		
		isExist(user_name, password);
		//check if maneger
		if(user_name.isEqual("maneger") && password.isEqual("1800400400"))
			maneger_menu();
		else:
			user_menu(user_name);
    }

    public void sign_in() {
		System.out.println("Please enter a user name:");
		String user_name = input.nextLine();
		System.out.println("Please enter a password:");
		String password = input.nextLine();
    	E_Profile e_p=new E_Profile(user_name);
    	users_map.put(user_name,e_p); 
    	 //return to main menu- to login
    }
    
    public void user_menu(String user_name) {
		int exit = 0;
		while (exit == 0) {
			System.out.println("********************Maneger Menu********************");
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
				//Edit_profile_menu()-> this.users[i].menu();
				break; 
			case 2:
				//print_all_jobs()
				break;
			case 3: 
				//print_jobs_by_location()
				break;
			case 4: 
				//print_jobs_by_type()
				break;
			case 5: 
			//log out ->menu
				exit=1; 
				break;	
			default: System.out.println("Not a valid choice!"); break;
			}
		}
    	
    }
	public void maneger_menu(String user_name) {
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
				//add_job();
				break; 
			case 2:
				//delete_job();
				break;
			case 3: 
				//print_all_jobs()
				break;
			case 4: 
				exit=1; 
				break;
			default: System.out.println("Not a valid choice!"); break;
			}
		}
		
	}
    public static void main(String[] args){
		start_menu(); 
	}
 
}

