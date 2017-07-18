package com.xchanging.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author 5004962
 */
@Entity
@Table(name = "TBLTPAEVENT")
@PrimaryKeyJoinColumn(name = "EVENT_GUID")
@NamedQueries({ @NamedQuery(name = "Tbltpaevent.findAll", query = "SELECT t FROM TblTPAEvent t"),
		@NamedQuery(name = "Tbltpaevent.findByEventGuid", query = "SELECT t FROM TblTPAEvent t WHERE t.eventGuid = :eventGuid"),
		@NamedQuery(name = "Tbltpaevent.findByTpaGrantedBy", query = "SELECT t FROM TblTPAEvent t WHERE t.tpaGrantedBy = :tpaGrantedBy"),
		@NamedQuery(name = "Tbltpaevent.findByTpaGrantedTo", query = "SELECT t FROM TblTPAEvent t WHERE t.tpaGrantedTo = :tpaGrantedTo") })
public class TblTPAEvent extends TblEvent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Basic(optional = false)
	@Column(name = "TPA_GRANTED_BY")
	private String tpaGrantedBy;

	@Basic(optional = false)
	@Column(name = "TPA_GRANTED_TO")
	private String tpaGrantedTo;

	@Basic(optional = false)
	@Column(name = "GRANTED_ACCESS_ID")
	private int grantedAccessId;

	public TblTPAEvent() {
	}

	public TblTPAEvent(String eventGuid) {
		this.eventGuid = eventGuid;
	}

	public TblTPAEvent(String eventGuid, String tpaGrantedBy, String tpaGrantedTo) {
		this.eventGuid = eventGuid;
		this.tpaGrantedBy = tpaGrantedBy;
		this.tpaGrantedTo = tpaGrantedTo;
	}

	public String getEventGuid() {
		return eventGuid;
	}

	public void setEventGuid(String eventGuid) {
		this.eventGuid = eventGuid;
	}

	public String getTpaGrantedBy() {
		return tpaGrantedBy;
	}

	public void setTpaGrantedBy(String tpaGrantedBy) {
		this.tpaGrantedBy = tpaGrantedBy;
	}

	public String getTpaGrantedTo() {
		return tpaGrantedTo;
	}

	public void setTpaGrantedTo(String tpaGrantedTo) {
		this.tpaGrantedTo = tpaGrantedTo;
	}

	/**
	 * @return the grantedAccessId
	 */
	public int getGrantedAccessId() {
		return grantedAccessId;
	}

	/**
	 * @param grantedAccessId
	 *            the grantedAccessId to set
	 */
	public void setGrantedAccessId(int grantedAccessId) {
		this.grantedAccessId = grantedAccessId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + grantedAccessId;
		result = prime * result + ((tpaGrantedBy == null) ? 0 : tpaGrantedBy.hashCode());
		result = prime * result + ((tpaGrantedTo == null) ? 0 : tpaGrantedTo.hashCode());
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
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		TblTPAEvent other = (TblTPAEvent) obj;
		if (grantedAccessId != other.grantedAccessId)
			return false;
		if (tpaGrantedBy == null) {
			if (other.tpaGrantedBy != null)
				return false;
		} else if (!tpaGrantedBy.equals(other.tpaGrantedBy))
			return false;
		if (tpaGrantedTo == null) {
			if (other.tpaGrantedTo != null)
				return false;
		} else if (!tpaGrantedTo.equals(other.tpaGrantedTo))
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
		return "TblTPAEvent [tpaGrantedBy=" + tpaGrantedBy + ", tpaGrantedTo=" + tpaGrantedTo + ", grantedAccessId="
				+ grantedAccessId + "]";
	}

}
