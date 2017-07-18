package com.xchanging.model;


import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="TBLWORKORDERGROUP",schema="REPOSITORY")
@NamedNativeQuery(name="findWorkOrderGroups",query="SELECT WOG_GROUP_ID,WOG_GROUP_REFERENCE,WOG_NUMBER_IN_GROUP,WOG_NO_OF_WO_CREATED,WOG_CREATED_DATE,WOG_NOTIFIED,WOG_FIRST_WO_CREATED_DATE_TIME,WOG_BROKER_NO from TBLWORKORDERGROUP  where  WOG_GROUP_REFERENCE = ? and  WOG_BROKER_NO = ? and  WOG_FIRST_WO_CREATED_DATE_TIME >=   (sysdate-1) ")
public class WorkOrderGroup {
	
	@Id
	@GeneratedValue(generator="WORK_GROUP_SEQ",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="WORK_GROUP_SEQ",sequenceName="REPOSITORY.WOG_GROUP_ID_SEQ",allocationSize=1)
	@Column(name="WOG_GROUP_ID",nullable=false,unique=true)
	private Integer groupId;
	
	@Column(name="WOG_GROUP_REFERENCE",nullable=false)
	private String groupReference;
	
	@Column(name="WOG_NUMBER_IN_GROUP",nullable=false)
	private Integer noInGroup;
	
	@Column(name="WOG_NO_OF_WO_CREATED",nullable=false)
	private Integer noOfWOCreated;
	
	@Column(name="WOG_CREATED_DATE",nullable=false)
	private String woGroupCreatedDate;
	
	@Column(name="WOG_NOTIFIED",nullable=false)
	private String notified;
	
//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="WOG_FIRST_WO_CREATED_DATE_TIME",nullable=false)
	private Timestamp firstWOCreatedDatetime;

	@Column(name="WOG_BROKER_NO",nullable=false)
	private String brokerId;

	/**
	 * @return the groupId
	 */
	public Integer getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the groupReference
	 */
	public String getGroupReference() {
		return groupReference;
	}

	/**
	 * @param groupReference the groupReference to set
	 */
	public void setGroupReference(String groupReference) {
		this.groupReference = groupReference;
	}

	/**
	 * @return the noInGroup
	 */
	public Integer getNoInGroup() {
		return noInGroup;
	}

	/**
	 * @param noInGroup the noInGroup to set
	 */
	public void setNoInGroup(Integer noInGroup) {
		this.noInGroup = noInGroup;
	}

	/**
	 * @return the noOfWOCreated
	 */
	public Integer getNoOfWOCreated() {
		return noOfWOCreated;
	}

	/**
	 * @param noOfWOCreated the noOfWOCreated to set
	 */
	public void setNoOfWOCreated(Integer noOfWOCreated) {
		this.noOfWOCreated = noOfWOCreated;
	}

	/**
	 * @return the woGroupCreatedDate
	 */
	public String getWoGroupCreatedDate() {
		return woGroupCreatedDate;
	}

	/**
	 * @param woGroupCreatedDate the woGroupCreatedDate to set
	 */
	public void setWoGroupCreatedDate(String woGroupCreatedDate) {
		this.woGroupCreatedDate = woGroupCreatedDate;
	}

	/**
	 * @return the notified
	 */
	public String getNotified() {
		return notified;
	}

	/**
	 * @param notified the notified to set
	 */
	public void setNotified(String notified) {
		this.notified = notified;
	}

	/**
	 * @return the firstWOCreatedDatetime
	 */
	public Timestamp getFirstWOCreatedDatetime() {
		return firstWOCreatedDatetime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WorkOrderGroup [groupId=" + groupId + ", groupReference=" + groupReference + ", noInGroup=" + noInGroup
				+ ", noOfWOCreated=" + noOfWOCreated + ", woGroupCreatedDate=" + woGroupCreatedDate + ", notified="
				+ notified + ", firstWOCreatedDatetime=" + firstWOCreatedDatetime + ", brokerId=" + brokerId
				+ ", getGroupId()=" + getGroupId() + ", getGroupReference()=" + getGroupReference()
				+ ", getNoInGroup()=" + getNoInGroup() + ", getNoOfWOCreated()=" + getNoOfWOCreated()
				+ ", getWoGroupCreatedDate()=" + getWoGroupCreatedDate() + ", getNotified()=" + getNotified()
				+ ", getFirstWOCreatedDatetime()=" + getFirstWOCreatedDatetime() + ", getBrokerId()=" + getBrokerId()
				+ "]";
	}

	/**
	 * @param firstWOCreatedDatetime the firstWOCreatedDatetime to set
	 */
	public void setFirstWOCreatedDatetime(Timestamp firstWOCreatedDatetime) {
		this.firstWOCreatedDatetime = firstWOCreatedDatetime;
	}

	/**
	 * @return the brokerId
	 */
	public String getBrokerId() {
		return brokerId;
	}

	/**
	 * @param brokerId the brokerId to set
	 */
	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}
}
