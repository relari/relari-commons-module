package pe.com.relari.commons.model.api;

import static pe.com.relari.commons.constant.Constants.SUCCESS_CODE;
import static pe.com.relari.commons.constant.Constants.SUCCESS_STATUS;

/**
 * <b>Record:</b> EmployeeDetailResponse.<br>
 *
 * @author Relari.
 */

public record ApiResponse<T> (
		String code,
		Integer status,
		T data
) {

	public static <T> ApiResponse<T> success(T data) {
		return new ApiResponse<>(
				SUCCESS_CODE, SUCCESS_STATUS, data
		);
	}

}
