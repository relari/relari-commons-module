package pe.com.relari.commons.validation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static pe.com.relari.commons.constant.Regex.REGEXP_ONLY_NUMBER;

/**
 * <b>Annotation:</b> NumericOnly.<br>
 *
 * @author Relari.
 */

@Documented
@Constraint(validatedBy = NumericOnly.NumericOnlyImpl.class) // Enlace con la lógica
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NumericOnly {

  String message() default "Value is not valid for the required category";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  class NumericOnlyImpl implements ConstraintValidator<NumericOnly, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
      if (value == null || value.isBlank()) {
        return true;
      }
      return value.matches(REGEXP_ONLY_NUMBER);
    }
  }

}