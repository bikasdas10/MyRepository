package com.xchanging.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 5004962
 */
@Entity
@Table(name = "TBLUMRTPAEVENT")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Tblumrtpaevent.findAll", query = "SELECT t FROM TblUmrTPAEvent t"),
		@NamedQuery(name = "Tblumrtpaevent.findByEventGuid", query = "SELECT t FROM TblUmrTPAEvent t WHERE t.eventGuid = :eventGuid"),
		@NamedQuery(name = "Tblumrtpaevent.findByTpaUmr", query = "SELECT t FROM TblUmrTPAEvent t WHERE t.tpaUmr = :tpaUmr"),
		@NamedQuery(name = "Tblumrtpaevent.findByTpaUmrAndtpaGrantedTo", query = "SELECT t FROM TblUmrTPAEvent t WHERE t.tpaUmr = :tpaUmr and t.tpaGrantedTo = :tpaGrantedTo"), 
		@NamedQuery(name = "Tblumrtpaevent.findByTpaUmrEventGuidAndtpaGrantedTo", query = "SELECT t from TblUmrTPAEvent t WHERE t.tpaUmr = :tpaUmr and t.tpaGrantedTo = :tpaGrantedTo and t.grantedAccessId IN :grantedAccessId")})
public class TblUmrTPAEvent extends TblTPAEvent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "TPA_UMR")
	private String tpaUmr;

	public TblUmrTPAEvent() {
	}

	public TblUmrTPAEvent(String eventGuid) {
		this.eventGuid = eventGuid;
	}

	public String getEventGuid() {
		return eventGuid;
	}

	public void setEventGuid(String eventGuid) {
		this.eventGuid = eventGuid;
	}

	public String getTpaUmr() {
		return tpaUmr;
	}

	public void setTpaUmr(String tpaUmr) {
		this.tpaUmr = tpaUmr;
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
		result = prime * result + ((tpaUmr == null) ? 0 : tpaUmr.hashCode());
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
		TblUmrTPAEvent other = (TblUmrTPAEvent) obj;
		if (tpaUmr == null) {
			if (other.tpaUmr != null)
				return false;
		} else if (!tpaUmr.equals(other.tpaUmr))
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
		return "TblUmrTPAEvent [tpaUmr=" + tpaUmr + "]";
	}

}
