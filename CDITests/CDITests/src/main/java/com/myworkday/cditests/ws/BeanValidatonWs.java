package com.myworkday.cditests.ws;

import com.myworkday.cditests.interceptor.ValidationInterceptor;
import com.myworkday.cditests.model.Car;
import com.myworkday.cditests.ws.exception.FaultBean;
import com.myworkday.cditests.ws.exception.FaultBeanDetails;
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
        FaultBean faults = new FaultBean();
        FaultBeanDetails detail = new FaultBeanDetails();
        detail.setResponseCode("189");
        detail.setResponseDesc("Response error");
        faults.addFault(detail);
        throw new MyServiceException("Error message", faults);
    }

    @WebMethod(operationName = "ValidateCarIterceptorThrows")
    public String validateCarMethodIterceptorThrows(@WebParam(name = "car") Car car) throws MyServiceException {
        return car.toString();
    }
}
