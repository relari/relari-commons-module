package pe.com.relari.commons.constant;

/**
 * <b>Class:</b> RegexConstant.<br/>
 * @author RLR
 * @version 1.0.0
 */

public class Regex {

    private Regex() {}

    public static final String REGEXP_ONLY_LETTERS = "[a-zA-Z]*";
    public static final String REGEXP_ONLY_NUMBER = "\\d+";
    public static final String REGEXP_GENDER = "[M|F]";
    public static final String REGEXP_PHONE_NUMBER = "\\d{9}";
    public static final String REGEXP_DOCUMENT_TYPE = "DNI|PASAPORTE|RUC";
    public static final String REGEXP_JOBS_TITLES = "SCRUM_MASTER|TEAM_LEAD|DEVELOPER|MANAGER|ARCHITECT";
    public static final String REGEXP_LETTERS_AND_NUMBERS = "[a-zA-Z0-9]";
    public static final String REGEXP_DATE = "[0-9]{2}/[0-9]{2}/[0-9]{4}";

}
