package pe.com.relari.library.support.model;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Class: EmployeeDetailResponse.
 * @author Relari
 */

public record ApiResponse<T> (
		@Schema(
				description = "Codigo HTTP.",
				name = "code",
				implementation = String.class,
				example = "OK")
		String code,
		@Schema(
				description = "Estatus HTTP.",
				name = "status",
				implementation = Integer.class,
				example = "200")
		Integer status,
		@Schema(
				description = "Data de respuesta.",
				name = "data")
		T data
) {}
