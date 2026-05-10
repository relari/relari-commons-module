package pe.com.relari.error.handler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import pe.com.relari.error.config.ErrorProperties;
import pe.com.relari.error.exception.ApiException;
import pe.com.relari.error.model.ErrorDetail;
import pe.com.relari.error.model.ErrorResponse;

import java.time.LocalDateTime;
import java.util.List;

/**
 * class: GlobalExceptionHandler.
 *
 * @author Relari
 */

@Slf4j
@Getter
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

  @Value("${spring.application.name}")
  private String applicationName;

  private final ErrorProperties errorProperties;

  /**
   * Maneja las excepciones personalizadas de negocio (ApiException).
   * Se lanza cuando ocurre un error controlado en la lógica de negocio,
   * mapeado en el catálogo de errores.
   *
   * @param apiException La excepción de negocio capturada
   * @param request  La solicitud HTTP actual
   * @return ResponseEntity con los detalles del error en formato JSON
   */
  @ExceptionHandler(ApiException.class)
  public ResponseEntity<ErrorResponse> apiException(
      ApiException apiException, HttpServletRequest request) {

    var errorDetail = errorProperties.getCategories().getValue(apiException.getCatalog());

    log.error("[ApiException]: {}", errorDetail.description(), apiException);

    var error = new ErrorResponse();
    error.setCode(apiException.getCatalog().name());
    error.setStatus(apiException.getCatalog().getStatus().value());
    error.setDescription(errorDetail.description());
    error.setComponent(applicationName);
    error.setInstance(request.getRequestURI());
    error.setTimestamp(LocalDateTime.now().toString());

    return ResponseEntity.status(apiException.getCatalog().getStatus()).body(error);
  }

  /**
   * Maneja cualquier excepción no controlada (Exception).
   * Se lanza ante errores inesperados del sistema (NullPointerException, fallos
   * de BD no controlados, etc.).
   * Actúa como un "catch-all" para evitar que el cliente reciba un stacktrace.
   *
   * @param exception La excepción inesperada
   * @param request   La solicitud HTTP actual
   * @return ResponseEntity con error 500 Internal Server Error
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(
      Exception exception, HttpServletRequest request) {

    log.error("Unexpected error occurred", exception);

    var status = HttpStatus.INTERNAL_SERVER_ERROR;

    var error = new ErrorResponse();
    error.setStatus(status.value());
    error.setCode("API-" + status.value());
    error.setDescription("Error interno del servidor.");
    error.setComponent(applicationName);
    error.setInstance(request.getRequestURI());
    error.setTimestamp(LocalDateTime.now().toString());

    return ResponseEntity.status(status).body(error);
  }

  /**
   * Maneja excepciones de validación de argumentos (@Valid).
   * Se lanza cuando el cuerpo de la solicitud (Body) falla las validaciones de
   * las anotaciones
   * (ej: @NotNull, @Email, @Size) en el DTO de entrada.
   *
   * @param exception La excepción con los resultados de la validación
   * @param request   La solicitud HTTP actual
   * @return ResponseEntity con error 400 y la lista detallada de campos inválidos
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> methodArgumentNotValidException(
      MethodArgumentNotValidException exception, HttpServletRequest request) {

    log.error("MethodArgumentNotValidException occurred", exception);

    var status = HttpStatus.BAD_REQUEST;

    List<ErrorDetail> errorDetails = exception.getBindingResult().getFieldErrors().stream()
        .map(fieldError -> new ErrorDetail(
            fieldError.getField(),
            fieldError.getDefaultMessage()))
        .toList();

    var error = new ErrorResponse();
    error.setStatus(status.value());
    error.setCode("API-" + status.value());
    error.setDescription("Error de validación de parámetros.");
    error.setComponent(applicationName);
    error.setInstance(request.getRequestURI());
    error.setTimestamp(LocalDateTime.now().toString());
    error.setErrorDetails(errorDetails);

    return ResponseEntity.status(status).body(error);
  }

  /**
   * Maneja la ausencia de parámetros requeridos (@RequestParam).
   * Se lanza cuando un endpoint espera un parámetro obligatorio en la URL (Query
   * Param)
   * y este no es proporcionado por el cliente.
   *
   * @param exception La excepción que indica qué parámetro falta
   * @param request   La solicitud HTTP actual
   * @return ResponseEntity con error 400 indicando el parámetro faltante
   */
  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<ErrorResponse> missingServletRequestParameterException(
      MissingServletRequestParameterException exception, HttpServletRequest request) {

    log.error("MissingServletRequestParameterException occurred", exception);

    var status = HttpStatus.BAD_REQUEST;

    var error = new ErrorResponse();
    error.setStatus(status.value());
    error.setCode("API-" + status.value());
    error.setDescription("Parámetro requerido faltante: " + exception.getParameterName());
    error.setComponent(applicationName);
    error.setInstance(request.getRequestURI());
    error.setTimestamp(LocalDateTime.now().toString());

    return ResponseEntity.status(status).body(error);
  }

  /**
   * Maneja errores de tipo de dato en argumentos (Type Mismatch).
   * Se lanza cuando se intenta convertir un valor de la URL (Path o Query
   * Variable)
   * a un tipo de Java incompatible (ej: enviar texto "abc" donde se espera un
   * Integer).
   *
   * @param exception La excepción de desajuste de tipos
   * @param request   La solicitud HTTP actual
   * @return ResponseEntity con error 400 indicando qué parámetro tiene el tipo
   *   incorrecto
   */
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ErrorResponse> methodArgumentTypeMismatchException(
      MethodArgumentTypeMismatchException exception, HttpServletRequest request) {

    log.error("MethodArgumentTypeMismatchException occurred", exception);

    var status = HttpStatus.BAD_REQUEST;

    var error = new ErrorResponse();
    error.setStatus(status.value());
    error.setCode("API-" + status.value());
    error.setDescription("Tipo de argumento incorrecto para el parámetro: " + exception.getName());
    error.setComponent(applicationName);
    error.setInstance(request.getRequestURI());
    error.setTimestamp(LocalDateTime.now().toString());

    return ResponseEntity.status(status).body(error);
  }

  /**
   * Maneja errores de lectura del cuerpo de la solicitud (JSON mal formado).
   * Se lanza cuando Jackson no puede parsear el JSON de entrada (sintaxis
   * inválida,
   * comas faltantes, tipos de datos incompatibles en el JSON, etc.).
   *
   * @param exception La excepción de mensaje no legible
   * @param request   La solicitud HTTP actual
   * @return ResponseEntity con error 400 indicando JSON mal formado
   */
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorResponse> httpMessageNotReadableException(
      HttpMessageNotReadableException exception, HttpServletRequest request) {

    log.error("HttpMessageNotReadableException occurred", exception);

    var status = HttpStatus.BAD_REQUEST;

    var error = new ErrorResponse();
    error.setStatus(status.value());
    error.setCode("API-" + status.value());
    error.setDescription("Cuerpo de la solicitud mal formado o inválido.");
    error.setComponent(applicationName);
    error.setInstance(request.getRequestURI());
    error.setTimestamp(LocalDateTime.now().toString());

    return ResponseEntity.status(status).body(error);
  }

}
