package com.xchanging.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.acord.standards.jv_ins_reinsurance._1.TechAccountType;




@XmlRootElement(name = "WorkPackageRefs")
@XmlSeeAlso(TechAccountType.class)
@XmlAccessorType(XmlAccessType.FIELD)
public class WorkPackageRefs {
	
	@XmlElementWrapper(name="techWrapper",namespace="//abc")
	@XmlAnyElement(lax = true)
	List<Object> techAccountTypes ;

	
	public List<Object> getTechAccountTypes() {
        if (techAccountTypes == null) {
        	techAccountTypes = new ArrayList<Object>();
        }
        return this.techAccountTypes;
    }
	
	@XmlElement(name="Count")
	int count;

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
}
