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

}