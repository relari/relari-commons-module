package pe.com.relari.library.error.category;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * <b>Enum:</b> ErrorCategory.<br>
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
