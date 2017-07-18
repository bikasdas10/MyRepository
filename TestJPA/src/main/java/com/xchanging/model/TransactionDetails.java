/*
 * Created on Dec 12, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.xchanging.model;

import java.io.Serializable;
import java.util.Date;


/**
 * @author Balakumaran Palanivel
 * 
 * Persistance Class for TBLTRANSACTIONDETAILS
 * @since 06-jun-2016
 *
 */
public class TransactionDetails implements Serializable {

	private static final long serialVersionUID = -342426472897350529L;

	private String umr;

	private String ucr;
	
	private String tr;
	
	private String bureauID;
	

	private String bureaLeader; // BUREAU_LEADER CHAR (9),

	private String lossName; // LOSS_NAME CHAR (20),

	private String lossDateFrom; // LOSS_DATE_FROM CHAR (10),

	private Long uwrYear; // UWR_YEAR NUMBER (10),

	private String brokerContact; // BROKER_CONTACT CHAR (15),

	private String brokerName; // BKR_NAME CHAR (20),

	private String assured; // ASSURED CHAR (50),

	private String reassured; // REASSURED CHAR (50),

	private String inceptionDate; // INCEPTION_DATE NUMBER (10),

	private String expiryDate; // EXPIRY_DATE NUMBER (10),

	private Long requestId; // REQUEST_ID NUMBER,

	private String createDate; // CREATE_DATE CHAR (10),

	private String actionCode; // ACTION_CODE VARCHAR2 (5),

	private String statusCode; // STATUS_CODE VARCHAR2 (5),

	private String moniker; // MONIKER VARCHAR2 (2048),

	private String brokerClaimRef1; // BKR_CLAIM_REF_1 VARCHAR2 (15),

	private Integer trLabelRsid; // TR_LABEL_RSID NUMBER

	private String trStatus; // TR_STATUS CHAR(3),

	private String datAdded; // DATE_ADDED CHAR(10),

	private Date lastUpdate; // LAST_UPDATE DATE,

	private String brokerCode; // BRK_CODE CHAR(4),

	private String lossDateTo; // LOSS_DATE_TO CHAR(10),

	private String marketLeadName; // MARKET_LEAD_NAME CHAR(20),

	private String marketCode; // MARKET_CODE CHAR(1)
	
	private Date createdDateIM  ;        //CREATED_DATE_IM   DATE     
	
	private String trStatusCode  ;  //TR_STATUS_CODE   CHAR(10)      
	
	private String transType ;    //	TRANS_TYPE    VARCHAR2(3) 
                                                                                                                                                                                                                                                                                                                                
	private String claimType;           //CLAIM_TYPE  VARCHAR2(3)      
	
	private String transTypeSubCode;     //	TRANS_TYPE_SUBCODE   VARCHAR2(3)                                                                                                                                                                                                                                                                                                                      

	/**
	 * @return the umr
	 */
	public String getUmr() {
		return umr;
	}

	/**
	 * @param umr the umr to set
	 */
	public void setUmr(String umr) {
		this.umr = umr;
	}

	/**
	 * @return the ucr
	 */
	public String getUcr() {
		return ucr;
	}

	/**
	 * @param ucr the ucr to set
	 */
	public void setUcr(String ucr) {
		this.ucr = ucr;
	}

	/**
	 * @return the tr
	 */
	public String getTr() {
		return tr;
	}

	/**
	 * @param tr the tr to set
	 */
	public void setTr(String tr) {
		this.tr = tr;
	}

	/**
	 * @return the bureauID
	 */
	public String getBureauID() {
		return bureauID;
	}

	/**
	 * @param bureauID the bureauID to set
	 */
	public void setBureauID(String bureauID) {
		this.bureauID = bureauID;
	}

	/**
	 * @return the createdDateIM
	 */
	public Date getCreatedDateIM() {
		return createdDateIM;
	}

	/**
	 * @param createdDateIM the createdDateIM to set
	 */
	public void setCreatedDateIM(Date createdDateIM) {
		this.createdDateIM = createdDateIM;
	}

	/**
	 * @return the trStatusCode
	 */
	public String getTrStatusCode() {
		return trStatusCode;
	}

	/**
	 * @param trStatusCode the trStatusCode to set
	 */
	public void setTrStatusCode(String trStatusCode) {
		this.trStatusCode = trStatusCode;
	}

	/**
	 * @return the transType
	 */
	public String getTransType() {
		return transType;
	}

	/**
	 * @param transType the transType to set
	 */
	public void setTransType(String transType) {
		this.transType = transType;
	}

	/**
	 * @return the claimType
	 */
	public String getClaimType() {
		return claimType;
	}

	/**
	 * @param claimType the claimType to set
	 */
	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}

	/**
	 * @return the transTypeSubCode
	 */
	public String getTransTypeSubCode() {
		return transTypeSubCode;
	}

	/**
	 * @param transTypeSubCode the transTypeSubCode to set
	 */
	public void setTransTypeSubCode(String transTypeSubCode) {
		this.transTypeSubCode = transTypeSubCode;
	}

	/**
	 * @return Returns the actionCode.
	 */
	public String getActionCode() {
		return actionCode;
	}

	/**
	 * @param actionCode
	 *            The actionCode to set.
	 */
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	/**
	 * @return Returns the assured.
	 */
	public String getAssured() {
		return assured;
	}

	/**
	 * @param assured
	 *            The assured to set.
	 */
	public void setAssured(String assured) {
		this.assured = assured;
	}

	/**
	 * @return Returns the brokerClaimRef1.
	 */
	public String getBrokerClaimRef1() {
		return brokerClaimRef1;
	}

	/**
	 * @param brokerClaimRef1
	 *            The brokerClaimRef1 to set.
	 */
	public void setBrokerClaimRef1(String brokerClaimRef1) {
		this.brokerClaimRef1 = brokerClaimRef1;
	}

	/**
	 * @return Returns the brokerCode.
	 */
	public String getBrokerCode() {
		return brokerCode;
	}

	/**
	 * @param brokerCode
	 *            The brokerCode to set.
	 */
	public void setBrokerCode(String brokerCode) {
		this.brokerCode = brokerCode;
	}

	/**
	 * @return Returns the brokerContact.
	 */
	public String getBrokerContact() {
		return brokerContact;
	}

	/**
	 * @param brokerContact
	 *            The brokerContact to set.
	 */
	public void setBrokerContact(String brokerContact) {
		this.brokerContact = brokerContact;
	}

	/**
	 * @return Returns the brokerName.
	 */
	public String getBrokerName() {
		return brokerName;
	}

	/**
	 * @param brokerName
	 *            The brokerName to set.
	 */
	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}

	/**
	 * @return Returns the bureaLeader.
	 */
	public String getBureaLeader() {
		return bureaLeader;
	}

	/**
	 * @param bureaLeader
	 *            The bureaLeader to set.
	 */
	public void setBureaLeader(String bureaLeader) {
		this.bureaLeader = bureaLeader;
	}

	/**
	 * @return Returns the createDate.
	 */
	public String getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            The createDate to set.
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return Returns the datAdded.
	 */
	public String getDatAdded() {
		return datAdded;
	}

	/**
	 * @param datAdded
	 *            The datAdded to set.
	 */
	public void setDatAdded(String datAdded) {
		this.datAdded = datAdded;
	}

	/**
	 * @return Returns the expiryDate.
	 */
	public String getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate
	 *            The expiryDate to set.
	 */
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * @return Returns the inceptionDate.
	 */
	public String getInceptionDate() {
		return inceptionDate;
	}

	/**
	 * @param inceptionDate
	 *            The inceptionDate to set.
	 */
	public void setInceptionDate(String inceptionDate) {
		this.inceptionDate = inceptionDate;
	}

	/**
	 * @return Returns the lastUpdate.
	 */
	public Date getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * @param lastUpdate
	 *            The lastUpdate to set.
	 */
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	/**
	 * @return Returns the lossDateFrom.
	 */
	public String getLossDateFrom() {
		return lossDateFrom;
	}

	/**
	 * @param lossDateFrom
	 *            The lossDateFrom to set.
	 */
	public void setLossDateFrom(String lossDateFrom) {
		this.lossDateFrom = lossDateFrom;
	}

	/**
	 * @return Returns the lossDateTo.
	 */
	public String getLossDateTo() {
		return lossDateTo;
	}

	/**
	 * @param lossDateTo
	 *            The lossDateTo to set.
	 */
	public void setLossDateTo(String lossDateTo) {
		this.lossDateTo = lossDateTo;
	}

	/**
	 * @return Returns the lossName.
	 */
	public String getLossName() {
		return lossName;
	}

	/**
	 * @param lossName
	 *            The lossName to set.
	 */
	public void setLossName(String lossName) {
		this.lossName = lossName;
	}

	/**
	 * @return Returns the marketCode.
	 */
	public String getMarketCode() {
		return marketCode;
	}

	/**
	 * @param marketCode
	 *            The marketCode to set.
	 */
	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
	}

	/**
	 * @return Returns the marketLeadName.
	 */
	public String getMarketLeadName() {
		return marketLeadName;
	}

	/**
	 * @param marketLeadName
	 *            The marketLeadName to set.
	 */
	public void setMarketLeadName(String marketLeadName) {
		this.marketLeadName = marketLeadName;
	}

	/**
	 * @return Returns the moniker.
	 */
	public String getMoniker() {
		return moniker;
	}

	/**
	 * @param moniker
	 *            The moniker to set.
	 */
	public void setMoniker(String moniker) {
		this.moniker = moniker;
	}

	/**
	 * @return Returns the reassured.
	 */
	public String getReassured() {
		return reassured;
	}

	/**
	 * @param reassured
	 *            The reassured to set.
	 */
	public void setReassured(String reassured) {
		this.reassured = reassured;
	}

	/**
	 * @return Returns the requestId.
	 */
	public Long getRequestId() {
		return requestId;
	}

	/**
	 * @param requestId
	 *            The requestId to set.
	 */
	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	/**
	 * @return Returns the statusCode.
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode
	 *            The statusCode to set.
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return Returns the trLabelRsid.
	 */
	public Integer getTrLabelRsid() {
		return trLabelRsid;
	}

	/**
	 * @param trLabelRsid
	 *            The trLabelRsid to set.
	 */
	public void setTrLabelRsid(Integer trLabelRsid) {
		this.trLabelRsid = trLabelRsid;
	}

	/**
	 * @return Returns the trStatus.
	 */
	public String getTrStatus() {
		return trStatus;
	}

	/**
	 * @param trStatus
	 *            The trStatus to set.
	 */
	public void setTrStatus(String trStatus) {
		this.trStatus = trStatus;
	}

	/**
	 * @return Returns the uwrYear.
	 */
	public Long getUwrYear() {
		return uwrYear;
	}

	/**
	 * @param uwrYear
	 *            The uwrYear to set.
	 */
	public void setUwrYear(Long uwrYear) {
		this.uwrYear = uwrYear;
	}

	@Override
	public String toString() {
		return "TransactionDetails [umr=" + getUmr() + ", ucr=" + getUcr() + ", tr=" + getTr()
				+ ", bureauID=" + getBureauID() + ", bureaLeader=" + getBureaLeader()
				+ ", lossName=" + getLossName() + ", lossDateFrom=" + getLossDateFrom()
				+ ", uwrYear=" + getUwrYear() + ", brokerContact=" + getBrokerContact()
				+ ", brokerName=" + getBrokerName() + ", assured=" + getAssured()
				+ ", reassured=" + getReassured() + ", inceptionDate="
				+ getInceptionDate() + ", expiryDate=" + getExpiryDate() + ", requestId="
				+ getRequestId() + ", createDate=" + getCreateDate() + ", actionCode="
				+ getActionCode() + ", statusCode=" + getStatusCode() + ", moniker="
				+ getMoniker() + ", brokerClaimRef1=" + getBrokerClaimRef1()
				+ ", trLabelRsid=" + getTrLabelRsid() + ", trStatus=" + getTrStatus()
				+ ", datAdded=" + getDatAdded() + ", lastUpdate=" + getLastUpdate()
				+ ", brokerCode=" + getBrokerCode() + ", lossDateTo=" + getLossDateTo()
				+ ", marketLeadName=" + getMarketLeadName() + ", marketCode="
				+ getMarketCode() + ", createdDateIM=" + getCreatedDateIM()
				+ ", trStatusCode=" + getTrStatusCode() + ", transType=" + getTransType()
				+ ", claimType=" + getClaimType() + ", transTypeSubCode="
				+ getTransTypeSubCode() + "]";
	}
	
	
}
