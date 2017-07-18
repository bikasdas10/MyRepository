package com.xchanging.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author 5004962
 *
 */
@Entity
@Table(name = "TBLUCRTPAEVENT")
@PrimaryKeyJoinColumn(name = "EVENT_GUID")
@NamedQueries({ @NamedQuery(name = "Tblucrtpaevent.findAll", query = "SELECT t FROM TblUcrTPAEvent t"),
		@NamedQuery(name = "Tblucrtpaevent.findByEventGuid", query = "SELECT t FROM TblUcrTPAEvent t WHERE t.eventGuid = :eventGuid"),
		@NamedQuery(name = "Tblucrtpaevent.findByTpaUcr", query = "SELECT t FROM TblUcrTPAEvent t WHERE t.tpaUcr = :tpaUcr"),
		@NamedQuery(name = "Tblucrtpaevent.findByTpaUcrAndtpaGrantedTo", query = "SELECT t FROM TblUcrTPAEvent t WHERE t.tpaUcr = :tpaUcr and t.tpaGrantedTo = :tpaGrantedTo") })
public class TblUcrTPAEvent extends TblTPAEvent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "TPA_UCR")
	private String tpaUcr;

	public TblUcrTPAEvent() {
	}

	public String getTpaUcr() {
		return tpaUcr;
	}

	public void setTpaUcr(String tpaUcr) {
		this.tpaUcr = tpaUcr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((tpaUcr == null) ? 0 : tpaUcr.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		TblUcrTPAEvent other = (TblUcrTPAEvent) obj;
		if (tpaUcr == null) {
			if (other.tpaUcr != null)
				return false;
		} else if (!tpaUcr.equals(other.tpaUcr))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TblUcrTPAEvent [tpaUcr=" + tpaUcr + "]";
	}

}
