package com.myworkday.cditests.ws;

import com.myworkday.cditests.interceptor.ValidationInterceptor;
import com.myworkday.cditests.model.Car;
import javax.interceptor.Interceptors;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Mauri
 */
@WebService(serviceName = "BeanValidatonWs")
@Interceptors(ValidationInterceptor.class)
public class BeanValidatonWs {


    @WebMethod(operationName = "ValidateCar")
    public String validateCarMethod(@WebParam(name = "car") Car car) {
        return car.toString();
    }
}
