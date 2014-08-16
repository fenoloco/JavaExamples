package com.myworkday.cditests.validator;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = CustomValidatorImpl.class)
@Documented
public @interface CustomValidator {

    String message() default "ErrorMessage Custom Validator";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    long value();

}
