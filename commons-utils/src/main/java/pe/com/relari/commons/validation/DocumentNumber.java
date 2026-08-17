package pe.com.relari.commons.validation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static pe.com.relari.commons.constant.Regex.REGEXP_DOCUMENT_NUMBER;

/**
 * <b>Annotation:</b> DocumentType.<br>
 *
 * @author Relari.
 */

@Constraint(validatedBy = DocumentNumber.DocumentNumberImpl.class) // Enlace con la lógica
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface DocumentNumber {

  String message() default "Value is not valid for the required category";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  class DocumentNumberImpl implements ConstraintValidator<DocumentNumber, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
      if (value == null || value.isBlank()) {
      return true;
      }
      return value.matches(REGEXP_DOCUMENT_NUMBER);
    }
  }
}
