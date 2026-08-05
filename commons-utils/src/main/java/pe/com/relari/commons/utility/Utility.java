package pe.com.relari.commons.utility;

import pe.com.relari.commons.constant.Constants;

import java.util.StringJoiner;

/**
 * <b>Class:</b> Utility.</br>
 * @author Relari.
 */

public class Utility {

    private Utility() {}

    public static String buildUsername(String firstName, String lastName) {
        return new StringJoiner(Constants.POINT)
                .add(firstName)
                .add(lastName)
                .toString()
                .toLowerCase();
    }

    public static String buildPassword(
            String username, String jobTitle) {
        return username.concat(Constants.ARROBA).concat(jobTitle);
    }
}
