/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myworkday.cditests.ws.exception;

import javax.xml.ws.WebFault;

@WebFault(name = "ValidationError")
public class MyServiceException extends Exception {

    /**
     * Java type that goes as soapenv:Fault detail element.
     */
    private FaultBean faultInfo;

    public MyServiceException() {
        super();
    }

    public MyServiceException(String message, FaultBean faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    public MyServiceException(String message, FaultBean faultInfo,
            Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    public FaultBean getFaultInfo() {
        return faultInfo;
    }

    public void setFaultInfo(FaultBean faultInfo) {
        this.faultInfo = faultInfo;
    }

}
