package pe.com.relari.error.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Class: ErrorResponse.
 *
 * @author Relari
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

  @Schema(
      description = "Representa el codigo del error.",
      name = "code",
      implementation = String.class,
      example = "API-400")
  private String code;

  @Schema(
      description = "Estatus HTTP.",
      name = "status",
      implementation = Integer.class,
      example = "400")
  private Integer status;

  @Schema(
      description = "Representa la descripcion del error.",
      name = "description",
      implementation = String.class,
      example = "Solicitud incorrecta o inválida.")
  private String description;

  @Schema(
      description = "Representa el nombre del componente.",
      name = "component",
      implementation = String.class,
      example = "api-employee-devops")
  private String component;

  @Schema(
      description = "URL de la peticion.",
      name = "instance",
      implementation = String.class,
      example = "/api/v1/employees")
  private String instance;

  @Schema(
      description = "Marca temporal del error.",
      name = "timestamp",
      implementation = String.class,
      example = "2024-01-01T12:00:00Z")
  private String timestamp;

  @Schema(
      description = "Lista de detalles del error.",
      name = "errorDetails",
      implementation = ErrorDetail.class)
  private List<ErrorDetail> errorDetails;

}
