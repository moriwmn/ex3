package ex3;

public class Senior_Job extends Job {
	int _seniority;
	String _experience;

	public Senior_Job(String type, String company, String location, Languages prog_language, int seniority, String experience) {
		super(type, company, location, prog_language);
		_experience = experience;
		_seniority = seniority;

	}

	public int getSeniority(){
		return this._seniority;

	}

	public String getExperience(){
		return this._experience;
	}

	public String toString() {
		return super.toString()+ "\r\nSeniority: "+ _seniority+"\r\nExperienced with:"+_experience;
	}
}
