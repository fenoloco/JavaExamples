package com.myworkday.cditests.ws;

import com.myworkday.cditests.interceptor.ValidationInterceptor;
import com.myworkday.cditests.model.Car;
import com.myworkday.cditests.ws.exception.FaultBean;
import com.myworkday.cditests.ws.exception.MyServiceException;
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

    /**
     * Not work because the throws is missing
     *
     * @param car
     * @return
     */
    @WebMethod(operationName = "ValidateCar")
    public String validateCarMethod(@WebParam(name = "car") Car car) {
        return car.toString();
    }

    @WebMethod(operationName = "ValidateCarThrows")
    public String validateCarMethodThrows(@WebParam(name = "car") Car car) throws MyServiceException {
        FaultBean fault = new FaultBean();
        fault.setResponseCode("189");
        fault.setResponseDesc("Response error");
        throw new MyServiceException("Error message", fault);
    }

    @WebMethod(operationName = "ValidateCarIterceptorThrows")
    public String validateCarMethodIterceptorThrows(@WebParam(name = "car") Car car) throws MyServiceException {
        return car.toString();
    }
}
