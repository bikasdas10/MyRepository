package com.xchanging.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



/**
 *
 * @author Pankaj Kumar Persistance Class for TBLEVENT
 */
@Entity
@Table(name = "TBLEVENT", catalog = "", schema = "REPOSITORY")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({ @NamedQuery(name = "Tblevent.findAll", query = "SELECT t FROM TblEvent t"),
		@NamedQuery(name = "Tblevent.findByEventGuid", query = "SELECT t FROM TblEvent t WHERE t.eventGuid = :eventGuid"),
		@NamedQuery(name = "Tblevent.findByEventBusinessEffectiveDate", query = "SELECT t FROM TblEvent t WHERE t.eventBusinessEffectiveDate = :eventBusinessEffectiveDate"),
		@NamedQuery(name = "Tblevent.findByEventSystemEffectiveDate", query = "SELECT t FROM TblEvent t WHERE t.eventSystemEffectiveDate = :eventSystemEffectiveDate"),
		@NamedQuery(name = "Tblevent.findByEventCreatedDate", query = "SELECT t FROM TblEvent t WHERE t.eventCreatedDate = :eventCreatedDate") })
public class TblEvent implements Serializable {

	private static final long serialVersionUID = 4169912499599373962L;

	@Id
	@Column(name = "EVENT_GUID", nullable = false, length = 32)
	public String eventGuid;

	@Column(name = "EVENT_BUSINESS_EFFECTIVE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	public Date eventBusinessEffectiveDate;

	@Column(name = "EVENT_SYSTEM_EFFECTIVE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	public Date eventSystemEffectiveDate;

	@Column(name = "EVENT_CREATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	public Date eventCreatedDate;

	@JoinColumn(name = "EVENT_SOURCE_SYSTEM_SID", referencedColumnName = "SOURCE_SYSTEM_ID")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	public TblSourceSystem eventSourceSystemSid;

	@JoinColumn(name = "EVENT_TYPE_ID", referencedColumnName = "EVENT_TYPE_ID")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	public TblEventType eventTypeId;

	@JoinColumn(name = "EVENT_ACTION_TYPE_ID", referencedColumnName = "EVENT_ACTION_TYPE_ID")
	@ManyToOne(fetch = FetchType.LAZY)
	public TblEventActionType eventActionTypeId;

	public TblEvent() {
	}

	public String getEventGuid() {
		return eventGuid;
	}

	public void setEventGuid(String eventGuid) {
		this.eventGuid = eventGuid;
	}

	public Date getEventBusinessEffectiveDate() {
		return eventBusinessEffectiveDate;
	}

	public void setEventBusinessEffectiveDate(Date eventBusinessEffectiveDate) {
		this.eventBusinessEffectiveDate = eventBusinessEffectiveDate != null ? (Date) eventBusinessEffectiveDate.clone()
				: null;
	}

	public Date getEventSystemEffectiveDate() {
		return eventSystemEffectiveDate;
	}

	public void setEventSystemEffectiveDate(Date eventSystemEffectiveDate) {
		this.eventSystemEffectiveDate = eventSystemEffectiveDate != null ? (Date) eventSystemEffectiveDate.clone()
				: null;
	}

	public Date getEventCreatedDate() {
		return eventCreatedDate;
	}

	public void setEventCreatedDate(Date eventCreatedDate) {
		this.eventCreatedDate = eventCreatedDate != null ? (Date) eventCreatedDate.clone() : null;
	}

	public TblSourceSystem getEventSourceSystemSid() {
		return eventSourceSystemSid;
	}

	public void setEventSourceSystemSid(TblSourceSystem eventSourceSystemSid) {
		this.eventSourceSystemSid = eventSourceSystemSid;
	}

	public TblEventType getEventTypeId() {
		return eventTypeId;
	}

	public void setEventTypeId(TblEventType eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	public TblEventActionType getEventActionTypeId() {
		return eventActionTypeId;
	}

	public void setEventActionTypeId(TblEventActionType eventActionTypeId) {
		this.eventActionTypeId = eventActionTypeId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eventGuid == null) ? 0 : eventGuid.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TblEvent other = (TblEvent) obj;
		if (eventGuid == null) {
			if (other.eventGuid != null)
				return false;
		} else if (!eventGuid.equals(other.eventGuid))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TblEvent [eventGuid=" + eventGuid + ", eventBusinessEffectiveDate=" + eventBusinessEffectiveDate
				+ ", eventSystemEffectiveDate=" + eventSystemEffectiveDate + ", eventCreatedDate=" + eventCreatedDate
				+ "]";
	}
}
