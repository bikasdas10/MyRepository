package com.xchanging.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Pankaj Kumar
 * Persistance Class for TBLEVENTTYPE
 */
@Entity
@Table(name = "TBLEVENTTYPE", catalog = "", schema = "REPOSITORY")
@NamedQueries({ @NamedQuery(name = "Tbleventtype.findAll", query = "SELECT t FROM TblEventType t"),
		@NamedQuery(name = "Tbleventtype.findByEventTypeId", query = "SELECT t FROM TblEventType t WHERE t.eventTypeId = :eventTypeId"),
		@NamedQuery(name = "Tbleventtype.findByEventTypeCode", query = "SELECT t FROM TblEventType t WHERE t.eventTypeCode = :eventTypeCode"),
		@NamedQuery(name = "Tbleventtype.findByEventTypeName", query = "SELECT t FROM TblEventType t WHERE t.eventTypeName = :eventTypeName"),
		@NamedQuery(name = "Tbleventtype.findByEventTypeDescription", query = "SELECT t FROM TblEventType t WHERE t.eventTypeDescription = :eventTypeDescription") })
public class TblEventType implements Serializable {

	private static final long serialVersionUID = -133740780362337680L;

	@Id
	@Column(name = "EVENT_TYPE_ID", nullable = false, precision = 0, scale = -127)
	private BigDecimal eventTypeId;

	@Column(name = "EVENT_TYPE_CODE", length = 50)
	private String eventTypeCode;

	@Column(name = "EVENT_TYPE_NAME", length = 255)
	private String eventTypeName;

	@Column(name = "EVENT_TYPE_DESCRIPTION", length = 255)
	private String eventTypeDescription;

	@OneToMany(mappedBy = "eventTypeId", fetch = FetchType.LAZY)
	private Collection<TblEvent> tbleventCollection;

	@OneToMany(mappedBy = "eventTypeId", fetch = FetchType.LAZY)
	private Collection<TblEventActionType> tbleventactiontypeCollection;

	@OneToMany(mappedBy = "parentEventTypeSid", fetch = FetchType.LAZY)
	private Collection<TblEventType> tbleventtypeCollection;

	@JoinColumn(name = "PARENT_EVENT_TYPE_SID", referencedColumnName = "EVENT_TYPE_ID")
	@ManyToOne(fetch = FetchType.LAZY)
	private TblEventType parentEventTypeSid;

	public BigDecimal getEventTypeId() {
		return eventTypeId;
	}

	public void setEventTypeId(BigDecimal eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	public String getEventTypeCode() {
		return eventTypeCode;
	}

	public void setEventTypeCode(String eventTypeCode) {
		this.eventTypeCode = eventTypeCode;
	}

	public String getEventTypeName() {
		return eventTypeName;
	}

	public void setEventTypeName(String eventTypeName) {
		this.eventTypeName = eventTypeName;
	}

	public String getEventTypeDescription() {
		return eventTypeDescription;
	}

	public void setEventTypeDescription(String eventTypeDescription) {
		this.eventTypeDescription = eventTypeDescription;
	}

	public Collection<TblEvent> getTbleventCollection() {
		return tbleventCollection;
	}

	public void setTbleventCollection(Collection<TblEvent> tbleventCollection) {
		this.tbleventCollection = tbleventCollection;
	}

	public Collection<TblEventActionType> getTbleventactiontypeCollection() {
		return tbleventactiontypeCollection;
	}

	public void setTbleventactiontypeCollection(Collection<TblEventActionType> tbleventactiontypeCollection) {
		this.tbleventactiontypeCollection = tbleventactiontypeCollection;
	}

	public Collection<TblEventType> getTbleventtypeCollection() {
		return tbleventtypeCollection;
	}

	public void setTbleventtypeCollection(Collection<TblEventType> tbleventtypeCollection) {
		this.tbleventtypeCollection = tbleventtypeCollection;
	}

	public TblEventType getParentEventTypeSid() {
		return parentEventTypeSid;
	}

	public void setParentEventTypeSid(TblEventType parentEventTypeSid) {
		this.parentEventTypeSid = parentEventTypeSid;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 1;
		int result = 2;
		result = prime * result + ((eventTypeId == null) ? 0 : eventTypeId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		TblEventType other = (TblEventType) obj;
		if (eventTypeId == null && other.eventTypeId != null) {
			return false;
		} else if (eventTypeId != null && !eventTypeId.equals(other.eventTypeId)){
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TblEventType [eventTypeId=" + eventTypeId + ", eventTypeCode=" + eventTypeCode + ", eventTypeName="
				+ eventTypeName + ", eventTypeDescription=" + eventTypeDescription + "]";
	}
}
