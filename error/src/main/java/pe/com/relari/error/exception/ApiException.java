package pe.com.relari.error.exception;

import lombok.Getter;
import pe.com.relari.error.category.ErrorCategory;

/**
 * Class: ApiException.
 *
 * @author Relari
 */

@Getter
public class ApiException extends RuntimeException {

  private final ErrorCategory catalog;
  private final Throwable throwable;

  /**
   * Constructor for ApiException with an ErrorCategory.
   *
   * @param catalog {@link ErrorCategory}
   */
  protected ApiException(ErrorCategory catalog) {
    super("");
    this.catalog = catalog;
    this.throwable = null;
  }

  /**
   * Constructor for ApiException with a Throwable cause.
   *
   * @param catalog {@link ErrorCategory}
   * @param throwable {@link Throwable}
   */
  protected ApiException(ErrorCategory catalog, Throwable throwable) {
    super("");
    this.catalog = catalog;
    this.throwable = throwable;
  }

  /**
   * Constructor for ApiException with a custom message.
   *
   * @param catalog {@link ErrorCategory}
   * @param message {@link String}
   */
  protected ApiException(ErrorCategory catalog, String message) {
    super(message);
    this.catalog = catalog;
    this.throwable = null;
  }

  /**
   * Factory method to create an ApiException with an ErrorCategory.
   *
   * @param catalog {@link ErrorCategory}
   * @return {@link ApiException}
   */
  public static ApiException of(ErrorCategory catalog) {
    return new ApiException(catalog);
  }

  /**
   * Factory method to create an ApiException with a Throwable cause.
   *
   * @param catalog {@link ErrorCategory}
   * @param throwable {@link Throwable}
   * @return {@link ApiException}
   */
  public static ApiException of(ErrorCategory catalog, Throwable throwable) {
    return new ApiException(catalog, throwable);
  }

  /**
   * Factory method to create an ApiException with a custom message.
   *
   * @param catalog {@link ErrorCategory}
   * @param message {@link String}
   * @return {@link ApiException}
   */
  public static ApiException of(ErrorCategory catalog, String message) {
    return new ApiException(catalog, message);
  }

}
