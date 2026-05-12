package pe.com.relari.error.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Class: ErrorCategory.
 * Define los nombres de todas las categorías de error como constantes.
 * Sincroniza automáticamente con application.yml
 *
 * @author Relari
 */

@Getter
@Setter
public class ErrorCategory {

  private String code;
  private String message;
  private Integer status;

}

