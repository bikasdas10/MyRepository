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
 * Persistance Class for TBLEVENTACTIONTYPE
 */
@Entity
@Table(name = "TBLEVENTACTIONTYPE", catalog = "", schema = "REPOSITORY")
@NamedQueries({ @NamedQuery(name = "Tbleventactiontype.findAll", query = "SELECT t FROM TblEventActionType t"),
		@NamedQuery(name = "Tbleventactiontype.findByEventActionTypeId", query = "SELECT t FROM TblEventActionType t WHERE t.eventActionTypeId = :eventActionTypeId"),
		@NamedQuery(name = "Tbleventactiontype.findByEventActionTypeCode", query = "SELECT t FROM TblEventActionType t WHERE t.eventActionTypeCode = :eventActionTypeCode"),
		@NamedQuery(name = "Tbleventactiontype.findByEventActionTypeName", query = "SELECT t FROM TblEventActionType t WHERE t.eventActionTypeName = :eventActionTypeName"),
		@NamedQuery(name = "Tbleventactiontype.findByEventActionTypeDescription", query = "SELECT t FROM TblEventActionType t WHERE t.eventActionTypeDescription = :eventActionTypeDescription") })
public class TblEventActionType implements Serializable {

	private static final long serialVersionUID = 68664627420408879L;

	@Id
	@Column(name = "EVENT_ACTION_TYPE_ID", nullable = false, precision = 0, scale = -127)
	private BigDecimal eventActionTypeId;

	@Column(name = "EVENT_ACTION_TYPE_CODE", length = 50)
	private String eventActionTypeCode;

	@Column(name = "EVENT_ACTION_TYPE_NAME", length = 255)
	private String eventActionTypeName;

	@Column(name = "EVENT_ACTION_TYPE_DESCRIPTION", length = 255)
	private String eventActionTypeDescription;

	@OneToMany(mappedBy = "eventActionTypeId", fetch = FetchType.LAZY)
	private Collection<TblEvent> tbleventCollection;

	@JoinColumn(name = "EVENT_TYPE_ID", referencedColumnName = "EVENT_TYPE_ID")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private TblEventType eventTypeId;

	public BigDecimal getEventActionTypeId() {
		return eventActionTypeId;
	}

	public void setEventActionTypeId(BigDecimal eventActionTypeId) {
		this.eventActionTypeId = eventActionTypeId;
	}

	public String getEventActionTypeCode() {
		return eventActionTypeCode;
	}

	public void setEventActionTypeCode(String eventActionTypeCode) {
		this.eventActionTypeCode = eventActionTypeCode;
	}

	public String getEventActionTypeName() {
		return eventActionTypeName;
	}

	public void setEventActionTypeName(String eventActionTypeName) {
		this.eventActionTypeName = eventActionTypeName;
	}

	public String getEventActionTypeDescription() {
		return eventActionTypeDescription;
	}

	public void setEventActionTypeDescription(String eventActionTypeDescription) {
		this.eventActionTypeDescription = eventActionTypeDescription;
	}

	public Collection<TblEvent> getTbleventCollection() {
		return tbleventCollection;
	}

	public void setTbleventCollection(Collection<TblEvent> tbleventCollection) {
		this.tbleventCollection = tbleventCollection;
	}

	public TblEventType getEventTypeId() {
		return eventTypeId;
	}

	public void setEventTypeId(TblEventType eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eventActionTypeId == null) ? 0 : eventActionTypeId.hashCode());
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TblEventActionType other = (TblEventActionType) obj;
		if (eventActionTypeId == null) {
			if (other.eventActionTypeId != null)
				return false;
		} else if (!eventActionTypeId.equals(other.eventActionTypeId))
			return false;
		if (eventTypeId == null) {
			if (other.eventTypeId != null)
				return false;
		} else if (!eventTypeId.equals(other.eventTypeId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TblEventActionType [eventActionTypeId=" + eventActionTypeId + ", eventActionTypeCode="
				+ eventActionTypeCode + ", eventActionTypeName=" + eventActionTypeName + ", eventActionTypeDescription="
				+ eventActionTypeDescription + "]";
	}
}
