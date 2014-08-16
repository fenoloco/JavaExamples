package com.myworkday.cditests.ws.exception;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Mauri
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Faults", propOrder = {
    "responseCode", "responseDesc"
})
public class FaultBeanDetails implements Serializable {

    @XmlElement(required = true)
    private String responseCode;
    @XmlElement(required = true)
    private String responseDesc;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDesc() {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }

}
