package pe.com.relari.commons.model.error;

/**
 * <b>Record:</b> ValidationError.<br>
 *
 * @param field Nombre del campo validado
 * @param message Mensaje de validación
 */

public record ValidationError(
    String field,
    String message
) {}

