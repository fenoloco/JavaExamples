package com.myworkday.cditests.interceptor;

import java.util.Set;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidationInterceptor {

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {

        System.out.println("ValidationInterceptor - Logging BEFORE calling method :" + context.getMethod().getName());

        for (Object param : context.getParameters()) {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Object>> constraintViolations = validator.validate(param);

            for (ConstraintViolation<Object> viol : constraintViolations) {
                System.out.println(viol.getMessage());
                System.out.println(viol.getPropertyPath());
            }
        }

        Object result = context.proceed();

        System.out.println("ValidationInterceptor - Logging AFTER calling method :" + context.getMethod().getName());

        return result;
    }
}
