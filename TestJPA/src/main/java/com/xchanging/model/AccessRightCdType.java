package com.xchanging.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * @author 5003453
 *
 *	enum has read and change rights
 */
@XmlType
@XmlEnum(String.class)
public enum AccessRightCdType {
	
	@XmlEnumValue("change") CHANGE,
	@XmlEnumValue("read") READ

}
