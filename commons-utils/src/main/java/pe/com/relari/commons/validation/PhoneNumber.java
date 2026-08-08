package pe.com.relari.commons.validation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static pe.com.relari.commons.constant.Regex.REGEXP_PHONE_NUMBER;

/**
 * <b>Annotation:</b> PhoneNumber.<br>
 *
 * @author Relari.
 */

@Documented
@Constraint(validatedBy = PhoneNumber.PhoneNumberImpl.class) // Enlace con la lógica
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {

  String message() default "Value is not valid for the required category";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  class PhoneNumberImpl implements ConstraintValidator<PhoneNumber, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
      if (value == null || value.isBlank()) {
        return true;
      }
      return value.matches(REGEXP_PHONE_NUMBER);
    }
  }

}