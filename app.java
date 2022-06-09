package ex_3;

import java.util.HashMap;

public class app {
	
    HashMap<String, E_Profile> users_map = new HashMap<String, E_Profile>(); //user name->profile
    HashMap<String, String> passwords = new HashMap<String, String>(); //user name->password
    All_Jobs jobsList;
    
    public void start_menu() {
    	//1) call login->call user_menue;
    	//2) call sign->call user_menu
    	//3) call jobList.add_job->call add jobs
    }
    public void login() {
    	//get user name and password and check if exist and return  the current user_name;
    	//call user_menue
    }

    public void sign_in() {
    	//authentication
    	String user_name="no name";
    	E_Profile e_p=new E_Profile(user_name);
    	users_map.put(user_name,e_p);
    	user_menu(user_name); 
    	 
    }
    
    public void user_menu(String user_name) {
    	//1) 1)profile.menu (users_map.get(user_name).menu())
    	//2) find job : jobsList.user_job_menu();
    	//3)log out ->menu
    	//switch case (func)
    }
 
}

