package com.myworkday.cditests.ws.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Mauri
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Errors")
public class FaultBean implements Serializable {

    @XmlElement(required = true)
    private List<FaultBeanDetails> faults;

    public FaultBean() {
        this.faults = new ArrayList<FaultBeanDetails>();
    }
    
    

    public List<FaultBeanDetails> getFaults() {
        return faults;
    }

    public void setFaults(List<FaultBeanDetails> faults) {
        this.faults = faults;
    }

    public void addFault(FaultBeanDetails detail) {
       this.faults.add(detail);
    }

}
