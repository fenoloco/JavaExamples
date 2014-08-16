package com.myworkday.cditests.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Mauri
 */
public class CustomValidatorImpl implements ConstraintValidator<CustomValidator, Long> {

    private long min;

    @Override
    public void initialize(CustomValidator constraintAnnotation) {
        this.min = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Long object, ConstraintValidatorContext constraintContext) {

        if (object == null) {
            return false;
        }

        return object > min;
    }

}
