package pe.com.relari.library.error.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <b>Class:</b> ErrorResponse.<br>
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
      description = "Marca temporal del error.",
      name = "timestamp",
      implementation = String.class,
      example = "2024-01-01T12:00:00Z")
  private String timestamp;

  @Schema(
      description = "Lista de detalles del error.",
      name = "metadata",
      implementation = Map.class)
  private Map<String, String> metadata;

  @Schema(
      description = "Stack trace de la excepción (solo en desarrollo).",
      name = "throwable",
      implementation = String.class)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String throwable;

  private Object details;

  /**
   * Constructor para respuestas de error desde categoría configurada.
   *
   * @param category Categoría de error desde YAML
   */
  public ErrorResponse(ErrorCategory category) {
    this.status = category.getStatus();
    this.code = category.getCode();
    this.description = category.getMessage();
    this.timestamp = LocalDateTime.now().toString();
    this.metadata = Map.of();
  }

  /**
   * Constructor para respuestas de error genéricas.
   *
   * @param status Código HTTP
   * @param description Descripción del error
   */
  public ErrorResponse(Integer status, String description) {
    this.status = status;
    this.description = description;
    this.timestamp = LocalDateTime.now().toString();
    this.metadata = Map.of();
  }

  /**
   * Constructor para error de API con stack trace.
   * El stack trace se incluye en la respuesta según la configuración.
   *
   * @param category Categoría de error
   * @param throwable Excepción con stack trace
   * @param includeStackTrace Si debe incluir el stack trace
   */
  public ErrorResponse(ErrorCategory category, Throwable throwable, boolean includeStackTrace) {
    this(category);
    if (includeStackTrace && throwable != null) {
      this.throwable = getStackTrace(throwable);
    }
  }

  /**
   * Extrae el stack trace de una excepción en formato String.
   * Método estático para reutilización en diferentes contextos.
   *
   * @param throwable Excepción
   * @return Stack trace formateado
   */
  public static String getStackTraceStatic(Throwable throwable) {
    if (throwable == null) {
      return null;
    }

    StringBuilder sb = new StringBuilder();
    sb.append(throwable.getClass().getName()).append(": ").append(throwable.getMessage()).append("\n");

    for (StackTraceElement element : throwable.getStackTrace()) {
      sb.append("\tat ").append(element).append("\n");
    }

    if (throwable.getCause() != null) {
      sb.append("Caused by: ").append(getStackTraceStatic(throwable.getCause()));
    }

    return sb.toString();
  }

  /**
   * Extrae el stack trace de una excepción en formato String (método privado).
   *
   * @param throwable Excepción
   * @return Stack trace formateado
   */
  private static String getStackTrace(Throwable throwable) {
    return getStackTraceStatic(throwable);
  }

}
