package pe.com.relari.error.model;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * record ErrorDetail.
 *
 * @param data {@link String}
 * @param description {@link String}
 */

public record ErrorDetail (

  @Schema(
      description = "Representa el nombre del componente.",
      name = "data",
      implementation = String.class,
      example = "parametro")
  String data,

  @Schema(
      description = "Representa la descripcion del error programado.",
      name = "description",
      implementation = String.class,
      example = "El parametros es requerido y no puede ser nulo.")
  String description

) {}
