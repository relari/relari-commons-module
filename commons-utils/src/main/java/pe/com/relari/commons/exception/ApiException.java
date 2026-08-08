package pe.com.relari.commons.exception;

/**
 * <b>Class:</b> ApiException.<br>
 *
 * @author Relari.
 */

public class ApiException extends RuntimeException {

  public ApiException(String code) {
    super(code);
  }

  public ApiException(String code, Throwable cause) {
    super(code, cause);
  }

}
