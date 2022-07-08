package ex3;

public class Senior extends Employee {

    private String last_job;
    private int seniority; //years

    // constractor:
    public Senior(Personal_info details, Languages languages, String extraInf, String n_last_job, int n_seniority) {
        super(details, languages, extraInf);
        this.last_job = n_last_job;
        this.seniority = n_seniority;
    }

    public String get_last_job() {
        return last_job;
    }

    public void set_last_job(String last_job) {
        this.last_job = last_job;
    }

    public int get_seniority() {
        return seniority;
    }

    public void set_seniority(int seniority) {
        this.seniority = seniority;
    }

    @Override
    public String toString() {
        return super.toString() + "\r\n last job: " + this.last_job + "\r\n experiance: " + this.seniority + " years"
                + "\r\n more info:" + this.extraInf;
    }
}// end Senior