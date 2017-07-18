package com.xchanging.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBLTRANSACTIONDETAILS database table.
 * 
 */
@Entity
@Table(name="TBLTRANSACTIONDETAILS")
@NamedQuery(name="EAccountTransactionDetailsDTO.findAll", query="SELECT e FROM EAccountTransactionDetailsDTO e")
public class EAccountTransactionDetailsDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	EAccountTransactionDetailsCK transactionDetailsCK;
	
	@Column(name="ACTION_CODE")
	private String actionCode;

	private String assured;

	@Column(name="BKR_CLAIM_REF_1")
	private String bkrClaimRef1;

	@Column(name="BKR_NAME")
	private String bkrName;

	@Column(name="BRK_CODE")
	private String brkCode;

	@Column(name="BROKER_CONTACT")
	private String brokerContact;

	@Column(name="BUREAU_LEADER")
	private String bureauLeader;

	@Column(name="CLAIM_TYPE")
	private String claimType;

	@Column(name="CREATE_DATE")
	private String createDate;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATED_DATE_IM")
	private Date createdDateIm;

	@Column(name="DATE_ADDED")
	private String dateAdded;

	@Column(name="EXPIRY_DATE")
	private BigDecimal expiryDate;

	@Column(name="INCEPTION_DATE")
	private BigDecimal inceptionDate;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_UPDATE")
	private Date lastUpdate;

	@Column(name="LOSS_DATE_FROM")
	private String lossDateFrom;

	@Column(name="LOSS_DATE_TO")
	private String lossDateTo;

	@Column(name="LOSS_NAME")
	private String lossName;

	@Column(name="MARKET_CODE")
	private String marketCode;

	@Column(name="MARKET_LEAD_NAME")
	private String marketLeadName;

	private String moniker;

	private String reassured;

	@Column(name="REQUEST_ID")
	private BigDecimal requestId;

	@Column(name="STATUS_CODE")
	private String statusCode;

	@Column(name="TR_LABEL_RSID")
	private BigDecimal trLabelRsid;

	@Column(name="TR_STATUS")
	private String trStatus;

	@Column(name="TR_STATUS_CODE")
	private String trStatusCode;

	@Column(name="TRANS_TYPE")
	private String transType;

	@Column(name="TRANS_TYPE_SUBCODE")
	private String transTypeSubcode;

	@Column(name="UWR_YEAR")
	private BigDecimal uwrYear;

	public EAccountTransactionDetailsDTO() {
	}

	public String getActionCode() {
		return this.actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getAssured() {
		return this.assured;
	}

	public void setAssured(String assured) {
		this.assured = assured;
	}

	public String getBkrClaimRef1() {
		return this.bkrClaimRef1;
	}

	public void setBkrClaimRef1(String bkrClaimRef1) {
		this.bkrClaimRef1 = bkrClaimRef1;
	}

	public String getBkrName() {
		return this.bkrName;
	}

	public void setBkrName(String bkrName) {
		this.bkrName = bkrName;
	}

	public String getBrkCode() {
		return this.brkCode;
	}

	public void setBrkCode(String brkCode) {
		this.brkCode = brkCode;
	}

	public String getBrokerContact() {
		return this.brokerContact;
	}

	public void setBrokerContact(String brokerContact) {
		this.brokerContact = brokerContact;
	}

	public String getBureauLeader() {
		return this.bureauLeader;
	}

	public void setBureauLeader(String bureauLeader) {
		this.bureauLeader = bureauLeader;
	}

	public String getClaimType() {
		return this.claimType;
	}

	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}

	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public Date getCreatedDateIm() {
		return this.createdDateIm;
	}

	public void setCreatedDateIm(Date createdDateIm) {
		this.createdDateIm = createdDateIm;
	}

	public String getDateAdded() {
		return this.dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

	public BigDecimal getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(BigDecimal expiryDate) {
		this.expiryDate = expiryDate;
	}

	public BigDecimal getInceptionDate() {
		return this.inceptionDate;
	}

	public void setInceptionDate(BigDecimal inceptionDate) {
		this.inceptionDate = inceptionDate;
	}

	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getLossDateFrom() {
		return this.lossDateFrom;
	}

	public void setLossDateFrom(String lossDateFrom) {
		this.lossDateFrom = lossDateFrom;
	}

	public String getLossDateTo() {
		return this.lossDateTo;
	}

	public void setLossDateTo(String lossDateTo) {
		this.lossDateTo = lossDateTo;
	}

	public String getLossName() {
		return this.lossName;
	}

	public void setLossName(String lossName) {
		this.lossName = lossName;
	}

	public String getMarketCode() {
		return this.marketCode;
	}

	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
	}

	public String getMarketLeadName() {
		return this.marketLeadName;
	}

	public void setMarketLeadName(String marketLeadName) {
		this.marketLeadName = marketLeadName;
	}

	public String getMoniker() {
		return this.moniker;
	}

	public void setMoniker(String moniker) {
		this.moniker = moniker;
	}

	public String getReassured() {
		return this.reassured;
	}

	public void setReassured(String reassured) {
		this.reassured = reassured;
	}

	public BigDecimal getRequestId() {
		return this.requestId;
	}

	public void setRequestId(BigDecimal requestId) {
		this.requestId = requestId;
	}

	public String getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public BigDecimal getTrLabelRsid() {
		return this.trLabelRsid;
	}

	public void setTrLabelRsid(BigDecimal trLabelRsid) {
		this.trLabelRsid = trLabelRsid;
	}

	public String getTrStatus() {
		return this.trStatus;
	}

	public void setTrStatus(String trStatus) {
		this.trStatus = trStatus;
	}

	public String getTrStatusCode() {
		return this.trStatusCode;
	}

	public void setTrStatusCode(String trStatusCode) {
		this.trStatusCode = trStatusCode;
	}

	public String getTransType() {
		return this.transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getTransTypeSubcode() {
		return this.transTypeSubcode;
	}

	public void setTransTypeSubcode(String transTypeSubcode) {
		this.transTypeSubcode = transTypeSubcode;
	}

	public BigDecimal getUwrYear() {
		return this.uwrYear;
	}

	public void setUwrYear(BigDecimal uwrYear) {
		this.uwrYear = uwrYear;
	}

	public EAccountTransactionDetailsCK getTransactionDetailsCK() {
		return transactionDetailsCK;
	}

	public void setTransactionDetailsCK(EAccountTransactionDetailsCK transactionDetailsCK) {
		this.transactionDetailsCK = transactionDetailsCK;
	}

}