package com.xchanging.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * @author 5003453
 *
 */
@XmlType
@XmlEnum(String.class)
public enum ListActionCdType {

	@XmlEnumValue("add") ADD, 
    @XmlEnumValue("remove") REMOVE;
}
