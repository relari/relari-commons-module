package pe.com.relari.error.category;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * enum: ErrorCategory.
 *
 * @author Relari
 */

@Getter
@RequiredArgsConstructor
public enum ErrorCatalog {

  EMPLOYEE_SAVE_FAILED,
  EMPLOYEE_NOT_FOUND,
  EMPLOYEE_UPDATE_FAILED,
  EMPLOYEE_INACTIVATED,
  EMPLOYEE_ACTIVATED,
  DOCUMENT_NOT_FOUND,
  DOCUMENT_REGISTERED;

}
