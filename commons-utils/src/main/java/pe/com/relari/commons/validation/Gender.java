package pe.com.relari.commons.validation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static pe.com.relari.commons.constant.Regex.REGEXP_GENDER;

/**
 * <b>Annotation:</b> Gender.<br>
 *
 * @author Relari.
 */

@Documented
@Constraint(validatedBy = Gender.GenderImpl.class) // Enlace con la lógica
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Gender {

  String message() default "Value is not valid for the required category";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  class GenderImpl implements ConstraintValidator<Gender, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
      if (value == null || value.isBlank()) {
        return true;
      }
      return value.matches(REGEXP_GENDER);
    }
  }

}
