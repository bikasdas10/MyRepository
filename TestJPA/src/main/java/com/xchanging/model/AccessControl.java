package com.xchanging.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author 5003453
 *
 */
@XmlRootElement(name="AccessParty")
public class AccessControl {

	private ListActionCdType listActionCdType;
	
	private AccessRightCdType accessRightCdType;
	
	private String partyId;
	
	private String partyRoleCd;
	
	private String partyName;

	/**
	 * @return the partyId
	 */
	public String getPartyId() {
		return partyId;
	}

	/**
	 * @param partyId the partyId to set
	 */
	@XmlElement(name="PartyId")
	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	/**
	 * @return the partyRoleCd
	 */
	public String getPartyRoleCd() {
		return partyRoleCd;
	}

	/**
	 * @param partyRoleCd the partyRoleCd to set
	 */
	@XmlElement(name="PartyRoleCd")
	public void setPartyRoleCd(String partyRoleCd) {
		this.partyRoleCd = partyRoleCd;
	}

	/**
	 * @return the partyName
	 */
	public String getPartyName() {
		return partyName;
	}

	/**
	 * @param partyName the partyName to set
	 */
	@XmlElement(name="PartyName")
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	/**
	 * @return the listActionCdType
	 */
	public ListActionCdType getListActionCdType() {
		return listActionCdType;
	}

	/**
	 * @param listActionCdType the listActionCdType to set
	 */
	@XmlElement(name="ListActionCd")
	public void setListActionCdType(ListActionCdType listActionCdType) {
		this.listActionCdType = listActionCdType;
	}

	/**
	 * @return the accessRightCdType
	 */
	public AccessRightCdType getAccessRightCdType() {
		return accessRightCdType;
	}

	/**
	 * @param accessRightCdType the accessRightCdType to set
	 */
	@XmlElement(name="AccessRightCd")
	public void setAccessRightCdType(AccessRightCdType accessRightCdType) {
		this.accessRightCdType = accessRightCdType;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccessControl other = (AccessControl) obj;
		if (accessRightCdType == null) {
			if (other.accessRightCdType != null)
				return false;
		} else if (!accessRightCdType.equals(other.accessRightCdType))
			return false;
		
		if (listActionCdType == null) {
			if (other.listActionCdType != null)
				return false;
		} else if (!listActionCdType.equals(other.listActionCdType))
			return false;
		
		if (partyId == null) {
			if (other.partyId != null)
				return false;
		} else if (!partyId.equals(other.partyId))
			return false;
		if (partyName == null) {
			if (other.partyName != null)
				return false;
		} else if (!partyName.equals(other.partyName))
			return false;
		if (partyRoleCd == null) {
			if (other.partyRoleCd != null)
				return false;
		} else if (!partyRoleCd.equals(other.partyRoleCd))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AccessControl [listActionCdType=" + listActionCdType + ", accessRightCdType=" + accessRightCdType
				+ ", partyId=" + partyId + ", partyRoleCd=" + partyRoleCd + ", partyName=" + partyName + "]";
	}




}
