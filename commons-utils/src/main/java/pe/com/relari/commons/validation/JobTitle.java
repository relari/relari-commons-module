package pe.com.relari.commons.validation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static pe.com.relari.commons.constant.Regex.REGEXP_JOBS_TITLES;

/**
 * <b>Annotation:</b> JobTitle.<br>
 *
 * @author Relari.
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = JobTitle.JobTitleImpl.class)
public @interface JobTitle {

  String message() default "Value is not valid for the required category";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  class JobTitleImpl implements ConstraintValidator<JobTitle, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
      if (value == null || value.isBlank()) {
        return true;
      }
      return value.matches(REGEXP_JOBS_TITLES);
    }
  }
}
