package ex3;

public class E_Profile implements Profile {

	private String _user_name;
	private Employee _employee; //student/ junior
	
	public E_Profile(String user_name) {//TODO:figure out why do we need detailes
		_user_name=_user_name;
		Personal_info personal_info=new Personal_info(user_name);
		_employee=new Employee(personal_info);//TODO:why??????????????????????????
	}
	public E_Profile() {
	}
	
	public Employee getEmployee(){
		return _employee;

	}
	//getters and setters:
	public String getEmployeeName() {
		return _employee.getDetails().getName();
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

	public void setEmployeeEmail(String email ) {
		_employee.getDetails().setEmail(email);
	}
	public String getEmployeePhone() {
		return _employee.getDetails().getPhone();
	}

	public void setEmployeePhone(String phone ) {
		_employee.getDetails().setPhone(phone);
	}





	
	public void create_account() {
		
	
		}


	public String getUser_name() {
		return _user_name;
	}


	public void setUser_name(String user_name) {
		this._user_name = user_name;
	}
	
	//func:



	@Override
	public void menu() {
		

	}
	

	
	}//end of class
	

