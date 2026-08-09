package pe.com.relari.commons.model.api;

import lombok.*;

import static pe.com.relari.commons.constant.Constants.SUCCESS_CODE;
import static pe.com.relari.commons.constant.Constants.SUCCESS_STATUS;

/**
 * <b>Record:</b> EmployeeDetailResponse.<br>
 *
 * @author Relari.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

  private String code;
  private Integer status;
  private T data;

  public static <T> ApiResponse<T> success(T data) {
    return new ApiResponse<>(SUCCESS_CODE, SUCCESS_STATUS, data);
  }

}
