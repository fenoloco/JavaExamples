package com.myworkday.cditests.interceptor;

import com.myworkday.cditests.ws.exception.FaultBean;
import com.myworkday.cditests.ws.exception.FaultBeanDetails;
import com.myworkday.cditests.ws.exception.MyServiceException;
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
        FaultBean faults = new FaultBean();
        for (Object param : context.getParameters()) {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Object>> constraintViolations = validator.validate(param);
            for (ConstraintViolation<Object> viol : constraintViolations) {
                System.out.println("ERROR::::" + viol.getMessage() + " property:" + viol.getPropertyPath());
                FaultBeanDetails fault = new FaultBeanDetails();
                fault.setResponseCode("189");
                fault.setResponseDesc("Error on field::" + viol.getPropertyPath());
                faults.addFault(fault);
            }
        }
        if (faults.getFaults().size() > 0) {
            throw new MyServiceException("Error from exception interceptor", faults);
        }

        Object result = context.proceed();

        System.out.println("ValidationInterceptor - Logging AFTER calling method :" + context.getMethod().getName());

        return result;
    }
}
