package com.xchanging.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Pankaj Kumar
 * Persistance Class for TBLSOURCESYSTEM
 */
@Entity
@Table(name = "TBLSOURCESYSTEM", catalog = "", schema = "REPOSITORY")
@NamedQueries({ @NamedQuery(name = "Tblsourcesystem.findAll", query = "SELECT t FROM TblSourceSystem t"),
		@NamedQuery(name = "Tblsourcesystem.findBySourceSystemId", query = "SELECT t FROM TblSourceSystem t WHERE t.sourceSystemId = :sourceSystemId"),
		@NamedQuery(name = "Tblsourcesystem.findBySourceSystemCode", query = "SELECT t FROM TblSourceSystem t WHERE t.sourceSystemCode = :sourceSystemCode"),
		@NamedQuery(name = "Tblsourcesystem.findBySourceSystemName", query = "SELECT t FROM TblSourceSystem t WHERE t.sourceSystemName = :sourceSystemName"),
		@NamedQuery(name = "Tblsourcesystem.findBySourceSystemDescription", query = "SELECT t FROM TblSourceSystem t WHERE t.sourceSystemDescription = :sourceSystemDescription") })
public class TblSourceSystem implements Serializable {

	private static final long serialVersionUID = -2330613607177648651L;

	@Id
	@Column(name = "SOURCE_SYSTEM_ID", nullable = false, precision = 0, scale = -127)
	private BigDecimal sourceSystemId;

	@Column(name = "SOURCE_SYSTEM_CODE", length = 50)
	private String sourceSystemCode;

	@Column(name = "SOURCE_SYSTEM_NAME", length = 50)
	private String sourceSystemName;

	@Column(name = "SOURCE_SYSTEM_DESCRIPTION", length = 255)
	private String sourceSystemDescription;

	@OneToMany(mappedBy = "eventSourceSystemSid", fetch = FetchType.LAZY)
	private Collection<TblEvent> tbleventCollection;

	public BigDecimal getSourceSystemId() {
		return sourceSystemId;
	}

	public void setSourceSystemId(BigDecimal sourceSystemId) {
		this.sourceSystemId = sourceSystemId;
	}

	public String getSourceSystemCode() {
		return sourceSystemCode;
	}

	public void setSourceSystemCode(String sourceSystemCode) {
		this.sourceSystemCode = sourceSystemCode;
	}

	public String getSourceSystemName() {
		return sourceSystemName;
	}

	public void setSourceSystemName(String sourceSystemName) {
		this.sourceSystemName = sourceSystemName;
	}

	public String getSourceSystemDescription() {
		return sourceSystemDescription;
	}

	public void setSourceSystemDescription(String sourceSystemDescription) {
		this.sourceSystemDescription = sourceSystemDescription;
	}

	public Collection<TblEvent> getTbleventCollection() {
		return tbleventCollection;
	}

	public void setTbleventCollection(Collection<TblEvent> tbleventCollection) {
		this.tbleventCollection = tbleventCollection;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 1;
		int result = 2;
		result = prime * result + ((sourceSystemId == null) ? 0 : sourceSystemId.hashCode());
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
		TblSourceSystem other = (TblSourceSystem) obj;
		if (sourceSystemId == null) {
			if (other.sourceSystemId != null)
				return false;
		} else if (!sourceSystemId.equals(other.sourceSystemId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TblSourceSystem [sourceSystemId=" + sourceSystemId + ", sourceSystemCode=" + sourceSystemCode
				+ ", sourceSystemName=" + sourceSystemName + ", sourceSystemDescription=" + sourceSystemDescription
				+ "]";
	}
}
