package ex3;

public class Senior extends Employee {

    private String _last_job;
    private int _seniority;

    // constractor:
    public Senior(Personal_info details, Languages languages, String extraInf, String last_job,
            int experience) {
        super(details, languages, extraInf);
        this._last_job = last_job;
        this._seniority = experience;
    }

    public String get_last_job() {
        return _last_job;
    }

    public void set_last_job(String _last_job) {
        this._last_job = _last_job;
    }

    public int get_seniority() {
        return _seniority;
    }

    public void set_seniority(int _seniority) {
        this._seniority = _seniority;
    }

    @Override
    public String toString() {
        return super.toString() + "\r\n last job:" + this._last_job + "\r\n experiance:" + this._seniority
                + "\r\n more info:" + this._extraInf;
    }
}// end Senior