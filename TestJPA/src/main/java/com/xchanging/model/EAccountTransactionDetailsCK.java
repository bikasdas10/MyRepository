package com.xchanging.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EAccountTransactionDetailsCK implements Serializable {
	
	@Column(name = "UMR")
	private String umr;
	
	@Column(name = "UCR")
	private String ucr;
	
	@Column(name = "TR")
	private String tr;
	
	@Column(name="BUREAU_ID")
	private String bureauId;
	
	public String getUmr() {
		return umr;
	}

	public void setUmr(String umr) {
		this.umr = umr;
	}

	public String getUcr() {
		return ucr;
	}

	public void setUcr(String ucr) {
		this.ucr = ucr;
	}

	public String getTr() {
		return tr;
	}

	public void setTr(String tr) {
		this.tr = tr;
	}

	public String getBureauId() {
		return bureauId;
	}

	public void setBureauId(String bureauId) {
		this.bureauId = bureauId;
	}
	
}
