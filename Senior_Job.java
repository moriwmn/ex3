package ex3;

public class Senior_Job extends Job {
	int seniority;
	String experience;

	public Senior_Job(String field, String company, String location, Languages prog_language, int seniority,
			String experience) {
		super(field, company, location, prog_language);
		this.experience = experience;
		this.seniority = seniority;

	}

	public int getSeniority() {
		return this.seniority;

	}

	public String getExperience() {
		return this.experience;
	}

	public String toString() {
		return super.toString() + "\r\nSeniority: " + seniority +" years"+ "\r\nExperienced with:" + experience;
	}
}
