package pe.com.relari.error.model;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * record: ValidationErrorDetail.
 * Representa un error de validación de un campo específico.
 *
 * @param field Nombre del campo validado
 * @param message Mensaje de validación
 */

public record ValidationError(

    @Schema(
        description = "Nombre del campo validado",
        name = "field",
        implementation = String.class,
        example = "email")
    String field,

    @Schema(
        description = "Mensaje de error de validación",
        name = "message",
        implementation = String.class,
        example = "El email debe ser válido")
    String message

) {}

