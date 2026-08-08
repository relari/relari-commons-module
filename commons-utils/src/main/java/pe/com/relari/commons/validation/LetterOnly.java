package pe.com.relari.commons.validation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static pe.com.relari.commons.constant.Regex.REGEXP_ONLY_LETTERS;

/**
 * <b>Annotation:</b> LetterOnly.<br>
 *
 * @author Relari.
 */

@Documented
@Constraint(validatedBy = LetterOnly.LetterOnlyImpl.class) // Enlace con la lógica
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface LetterOnly {

  String message() default "Value is not valid for the required category";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  class LetterOnlyImpl implements ConstraintValidator<LetterOnly, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
      if (value == null || value.isBlank()) {
        return true;
      }
      return value.matches(REGEXP_ONLY_LETTERS);
    }
  }

}