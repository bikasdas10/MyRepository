package com.xchanging.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedNativeQueries;
import javax.persistence.Table;

@Entity
@Table(name = "TBLWORKORDER", schema = "REPOSITORY")
@NamedNativeQueries({
		@NamedNativeQuery(name = "selectWorkOrderQuery", query = "SELECT  SUBMISSION_ID , UMR , TRACKER_ID , APPLICATION , BROKER_CONTACT_INFO, SUBMISSION_TYPE ,"
				+ "MARKET , CLASS_OF_BUSINESS , SLIP_TYPE , PROCESSING_REQUIRED , CREATED_DATE  , WORKORDER_TAG , BROKER_CONTACT_EMAIL , "
				+ "BROKER_CONTACT_PHONE, XIS_CONTACT_INFO , NOOFDOC, TRACKER_URL , COUNT_OF_PREMIUM , COUNT_OF_AP , "
				+ "ADDITIONAL_INFORMATION , POLICY_TYPE , TRACKERSTATUS "
				+ " , PRESENTATIONDATE , WORKORDER_REF , WORK_ORDER_STATUS , WORK_PACKAGE_ID , URGENT_REFERENCE , "
				+ "UPDATED_DATE  , TECH_ACCOUNT_UUID , GROUP_ID , GROUP_REFERENCE "
				+ " , NO_IN_GROUP , UCR , TR , TREATY_FDO_STATEMENT, SIM_SIGNING_REQ , "
				+ "GROUP_STATUS_INDICATOR  , XIS_SCANNED , BROKER_CODE, XISWORKFLOW_STATUS FROM TBLWORKORDER "
				+ " where  WORKORDER_REF = ?  ", resultClass = WorkOrder.class),
		@NamedNativeQuery(name = "selectWorkOrderUUIDQuery", query = "SELECT  SUBMISSION_ID , UMR , TRACKER_ID , APPLICATION , BROKER_CONTACT_INFO, SUBMISSION_TYPE ,"
				+ "MARKET , CLASS_OF_BUSINESS , SLIP_TYPE , PROCESSING_REQUIRED , CREATED_DATE  , WORKORDER_TAG , BROKER_CONTACT_EMAIL , "
				+ "BROKER_CONTACT_PHONE, XIS_CONTACT_INFO , NOOFDOC, TRACKER_URL , COUNT_OF_PREMIUM , COUNT_OF_AP , "
				+ "ADDITIONAL_INFORMATION , POLICY_TYPE , TRACKERSTATUS "
				+ " , PRESENTATIONDATE , WORKORDER_REF , WORK_ORDER_STATUS , WORK_PACKAGE_ID , URGENT_REFERENCE , "
				+ "UPDATED_DATE  , TECH_ACCOUNT_UUID , GROUP_ID , GROUP_REFERENCE "
				+ " , NO_IN_GROUP , UCR , TR , TREATY_FDO_STATEMENT, SIM_SIGNING_REQ , "
				+ "GROUP_STATUS_INDICATOR  , XIS_SCANNED , BROKER_CODE, XISWORKFLOW_STATUS FROM TBLWORKORDER "
				+ " where  TECH_ACCOUNT_UUID = ?  ", resultClass = WorkOrder.class) 
})
public class WorkOrder implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "SUBMISSION_ID", nullable = false)
	private String submissionId;

	@Column(name = "UMR", nullable = false)
	private String umr;

	@Column(name = "TRACKER_ID", nullable = true)
	private String trackerId;

	@Column(name = "APPLICATION", nullable = true)
	private String application;

	@Column(name = "BROKER_CONTACT_INFO", nullable = true)
	private String brokerName;

	@Column(name = "SUBMISSION_TYPE", nullable = true)
	private String submissionType;

	@Column(name = "MARKET", nullable = true)
	private String market;

	@Column(name = "CLASS_OF_BUSINESS", nullable = true)
	private String classOfBusiness;

	@Column(name = "SLIP_TYPE", nullable = true)
	private String slipType;

	@Column(name = "PROCESSING_REQUIRED", nullable = true)
	private String processingRequired;

	@Column(name = "CREATED_DATE", nullable = true)
	private Date createdDate;

	@Column(name = "WORKORDER_TAG", nullable = true)
	private String workorderTag;

	@Column(name = "BROKER_CONTACT_EMAIL", nullable = true)
	private String brokerMail;

	@Column(name = "BROKER_CONTACT_PHONE", nullable = true)
	private String brokerPhone;

	@Column(name = "XIS_CONTACT_INFO", nullable = true)
	private String xisContactInfo;

	@Column(name = "NOOFDOC", nullable = true)
	private String noOfDoc;

	@Column(name = "TRACKER_URL", nullable = true)
	private String trackerURL;

	@Column(name = "COUNT_OF_PREMIUM", nullable = true)
	private String premiumCount;

	@Column(name = "COUNT_OF_AP", nullable = true)
	private String apCount;

	@Column(name = "ADDITIONAL_INFORMATION", nullable = true)
	private String additionalInformation;

	@Column(name = "POLICY_TYPE", nullable = true)
	private String policyType;

	@Column(name = "TRACKERSTATUS", nullable = true)
	private String trackerStatus;

	@Column(name = "PRESENTATIONDATE", nullable = true)
	private String presentedDate;

	@Column(name = "WORKORDER_REF", nullable = true)
	private String workOrderReference;

	@Column(name = "WORK_ORDER_STATUS", nullable = true)
	private String workOrderStatus;

	@Column(name = "WORK_PACKAGE_ID", nullable = true)
	private String workPackageId;

	@Column(name = "URGENT_REFERENCE", nullable = true)
	private String urgentReference;

	@Column(name = "UPDATED_DATE", nullable = true)
	private Date updatedDate;

	@Column(name = "TECH_ACCOUNT_UUID", nullable = true)
	private String techUUID;

	@Column(name = "GROUP_ID", nullable = true)
	private Integer groupId;

	@Column(name = "GROUP_REFERENCE", nullable = true)
	private String groupReference;

	@Column(name = "NO_IN_GROUP", nullable = true)
	private Long noinGroup;

	@Column(name = "UCR", nullable = true)
	private String ucr;

	@Column(name = "TR", nullable = true)
	private String tr;

	@Column(name = "TREATY_FDO_STATEMENT", nullable = true)
	private String treatyFDOStatement;

	@Column(name = "SIM_SIGNING_REQ", nullable = true)
	private String simSigningRequired;

	@Column(name = "GROUP_STATUS_INDICATOR", nullable = true)
	private String groupStatusIndicator;

	@Column(name = "XIS_SCANNED", nullable = true)
	private String xisScanned;

	@Column(name = "BROKER_CODE", nullable = true)
	private String brokerCode;

	@Column(name = "XISWORKFLOW_STATUS", nullable = true)
	private String xisWorkFlowStatus;

	/**
	 * @return the xisWorkFlowStatus /
	 */
	public String getXisWorkFlowStatus() {
		return xisWorkFlowStatus;
	}

	/**
	 * @param xisWorkFlowStatus
	 *            the xisWorkFlowStatus to set
	 */
	public void setXisWorkFlowStatus(String xisWorkFlowStatus) {
		this.xisWorkFlowStatus = xisWorkFlowStatus;
	}

	/**
	 * @return the submissionId
	 */
	public String getSubmissionId() {
		return submissionId;
	}

	/**
	 * @param submissionId
	 *            the submissionId to set
	 */
	public void setSubmissionId(String submissionId) {
		this.submissionId = submissionId;
	}

	/**
	 * @return the trackerId
	 */
	public String getTrackerId() {
		return trackerId;
	}

	/**
	 * @param trackerId
	 *            the trackerId to set
	 */
	public void setTrackerId(String trackerId) {
		this.trackerId = trackerId;
	}

	/**
	 * @return the umr
	 */
	public String getUmr() {
		return umr;
	}

	/**
	 * @param umr
	 *            the umr to set
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
	 * @param ucr
	 *            the ucr to set
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
	 * @param tr
	 *            the tr to set
	 */
	public void setTr(String tr) {
		this.tr = tr;
	}

	/**
	 * @return the application
	 */
	public String getApplication() {
		return application;
	}

	/**
	 * @param application
	 *            the application to set
	 */
	public void setApplication(String application) {
		this.application = application;
	}

	/**
	 * @return the brokerCode
	 */
	public String getBrokerCode() {
		return brokerCode;
	}

	/**
	 * @param brokerCode
	 *            the brokerCode to set
	 */
	public void setBrokerCode(String brokerCode) {
		this.brokerCode = brokerCode;
	}

	/**
	 * @return the brokerName
	 */
	public String getBrokerName() {
		return brokerName;
	}

	/**
	 * @param brokerName
	 *            the brokerName to set
	 */
	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}

	/**
	 * @return the brokerMail
	 */
	public String getBrokerMail() {
		return brokerMail;
	}

	/**
	 * @param brokerMail
	 *            the brokerMail to set
	 */
	public void setBrokerMail(String brokerMail) {
		this.brokerMail = brokerMail;
	}

	/**
	 * @return the brokerPhone
	 */
	public String getBrokerPhone() {
		return brokerPhone;
	}

	/**
	 * @param brokerPhone
	 *            the brokerPhone to set
	 */
	public void setBrokerPhone(String brokerPhone) {
		this.brokerPhone = brokerPhone;
	}

	/**
	 * @return the submissionType
	 */
	public String getSubmissionType() {
		return submissionType;
	}

	/**
	 * @param submissionType
	 *            the submissionType to set
	 */
	public void setSubmissionType(String submissionType) {
		this.submissionType = submissionType;
	}

	/**
	 * @return the market
	 */
	public String getMarket() {
		return market;
	}

	/**
	 * @param market
	 *            the market to set
	 */
	public void setMarket(String market) {
		this.market = market;
	}

	/**
	 * @return the classOfBusiness
	 */
	public String getClassOfBusiness() {
		return classOfBusiness;
	}

	/**
	 * @param classOfBusiness
	 *            the classOfBusiness to set
	 */
	public void setClassOfBusiness(String classOfBusiness) {
		this.classOfBusiness = classOfBusiness;
	}

	/**
	 * @return the slipType
	 */
	public String getSlipType() {
		return slipType;
	}

	/**
	 * @param slipType
	 *            the slipType to set
	 */
	public void setSlipType(String slipType) {
		this.slipType = slipType;
	}

	/**
	 * @return the processingRequired
	 */
	public String getProcessingRequired() {
		return processingRequired;
	}

	/**
	 * @param processingRequired
	 *            the processingRequired to set
	 */
	public void setProcessingRequired(String processingRequired) {
		this.processingRequired = processingRequired;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the workorderTag
	 */
	public String getWorkorderTag() {
		return workorderTag;
	}

	/**
	 * @param workorderTag
	 *            the workorderTag to set
	 */
	public void setWorkorderTag(String workorderTag) {
		this.workorderTag = workorderTag;
	}

	/**
	 * @return the xisContactInfo
	 */
	public String getXisContactInfo() {
		return xisContactInfo;
	}

	/**
	 * @param xisContactInfo
	 *            the xisContactInfo to set
	 */
	public void setXisContactInfo(String xisContactInfo) {
		this.xisContactInfo = xisContactInfo;
	}

	/**
	 * @return the noOfDoc
	 */
	public String getNoOfDoc() {
		return noOfDoc;
	}

	/**
	 * @param noOfDoc
	 *            the noOfDoc to set
	 */
	public void setNoOfDoc(String noOfDoc) {
		this.noOfDoc = noOfDoc;
	}

	/**
	 * @return the trackerURL
	 */
	public String getTrackerURL() {
		return trackerURL;
	}

	/**
	 * @param trackerURL
	 *            the trackerURL to set
	 */
	public void setTrackerURL(String trackerURL) {
		this.trackerURL = trackerURL;
	}

	/**
	 * @return the premiumCount
	 */
	public String getPremiumCount() {
		return premiumCount;
	}

	/**
	 * @param premiumCount
	 *            the premiumCount to set
	 */
	public void setPremiumCount(String premiumCount) {
		this.premiumCount = premiumCount;
	}

	/**
	 * @return the apCount
	 */
	public String getApCount() {
		return apCount;
	}

	/**
	 * @param apCount
	 *            the apCount to set
	 */
	public void setApCount(String apCount) {
		this.apCount = apCount;
	}

	/**
	 * @return the additionalInformation
	 */
	public String getAdditionalInformation() {
		return additionalInformation;
	}

	/**
	 * @param additionalInformation
	 *            the additionalInformation to set
	 */
	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	/**
	 * @return the policyType
	 */
	public String getPolicyType() {
		return policyType;
	}

	/**
	 * @param policyType
	 *            the policyType to set
	 */
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	/**
	 * @return the trackerStatus
	 */
	public String getTrackerStatus() {
		return trackerStatus;
	}

	/**
	 * @param trackerStatus
	 *            the trackerStatus to set
	 */
	public void setTrackerStatus(String trackerStatus) {
		this.trackerStatus = trackerStatus;
	}

	/**
	 * @return the presentedDate
	 */
	public String getPresentedDate() {
		return presentedDate;
	}

	/**
	 * @param presentedDate
	 *            the presentedDate to set
	 */
	public void setPresentedDate(String presentedDate) {
		this.presentedDate = presentedDate;
	}

	/**
	 * @return the workOrderReference
	 */
	public String getWorkOrderReference() {
		return workOrderReference;
	}

	/**
	 * @param workOrderReference
	 *            the workOrderReference to set
	 */
	public void setWorkOrderReference(String workOrderReference) {
		this.workOrderReference = workOrderReference;
	}

	/**
	 * @return the treatyFDOStatement
	 */
	public String getTreatyFDOStatement() {
		return treatyFDOStatement;
	}

	/**
	 * @param treatyFDOStatement
	 *            the treatyFDOStatement to set
	 */
	public void setTreatyFDOStatement(String treatyFDOStatement) {
		this.treatyFDOStatement = treatyFDOStatement;
	}

	/**
	 * @return the simSigningRequired
	 */
	public String getSimSigningRequired() {
		return simSigningRequired;
	}

	/**
	 * @param simSigningRequired
	 *            the simSigningRequired to set
	 */
	public void setSimSigningRequired(String simSigningRequired) {
		this.simSigningRequired = simSigningRequired;
	}

	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate
	 *            the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the techUUID
	 */
	public String getTechUUID() {
		return techUUID;
	}

	/**
	 * @param techUUID
	 *            the techUUID to set
	 */
	public void setTechUUID(String techUUID) {
		this.techUUID = techUUID;
	}

	/**
	 * @return the groupStatusIndicator
	 */
	public String getGroupStatusIndicator() {
		return groupStatusIndicator;
	}

	/**
	 * @param groupStatusIndicator
	 *            the groupStatusIndicator to set
	 */
	public void setGroupStatusIndicator(String groupStatusIndicator) {
		this.groupStatusIndicator = groupStatusIndicator;
	}

	/**
	 * @return the groupReference
	 */
	public String getGroupReference() {
		return groupReference;
	}

	/**
	 * @param groupReference
	 *            the groupReference to set
	 */
	public void setGroupReference(String groupReference) {
		this.groupReference = groupReference;
	}

	/**
	 * @return the xisScanned
	 */
	public String getXisScanned() {
		return xisScanned;
	}

	/**
	 * @param xisScanned
	 *            the xisScanned to set
	 */
	public void setXisScanned(String xisScanned) {
		this.xisScanned = xisScanned;
	}

	public String getWorkOrderStatus() {
		return workOrderStatus;
	}

	public void setWorkOrderStatus(String workOrderStatus) {
		this.workOrderStatus = workOrderStatus;
	}

	public String getWorkPackageId() {
		return workPackageId;
	}

	public void setWorkPackageId(String workPackageId) {
		this.workPackageId = workPackageId;
	}

	public String getUrgentReference() {
		return urgentReference;
	}

	public void setUrgentReference(String urgentReference) {
		this.urgentReference = urgentReference;
	}

	/**
	 * @return the groupId
	 */
	public Integer getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId
	 *            the groupId to set
	 */
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	/**
	 * @param noinGroup
	 *            the noinGroup to set
	 */
	public void setNoinGroup(Long noinGroup) {
		this.noinGroup = noinGroup;
	}

	/**
	 * @return the noinGroup
	 */
	public Long getNoinGroup() {
		return noinGroup;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WorkOrder [submissionId=");
		builder.append(getSubmissionId());
		builder.append(", trackerId=");
		builder.append(getTrackerId());
		builder.append(", umr=");
		builder.append(getUmr());
		builder.append(", ucr=");
		builder.append(getUcr());
		builder.append(", tr=");
		builder.append(getTr());
		builder.append(", application=");
		builder.append(application);
		builder.append(", brokerCode=");
		builder.append(getBrokerCode());
		builder.append(", brokerName=");
		builder.append(getBrokerName());
		builder.append(", brokerMail=");
		builder.append(getBrokerMail());
		builder.append(", brokerPhone=");
		builder.append(getBrokerPhone());
		builder.append(", submissionType=");
		builder.append(getSubmissionType());
		builder.append(", market=");
		builder.append(getMarket());
		builder.append(", classOfBusiness=");
		builder.append(getClassOfBusiness());
		builder.append(", slipType=");
		builder.append(getSlipType());
		builder.append(", processingRequired=");
		builder.append(getProcessingRequired());
		builder.append(", createdDate=");
		builder.append(getCreatedDate());
		builder.append(", workorderTag=");
		builder.append(getWorkorderTag());
		builder.append(", xisContactInfo=");
		builder.append(getXisContactInfo());
		builder.append(", noOfDoc=");
		builder.append(getNoOfDoc());
		builder.append(", trackerURL=");
		builder.append(getTrackerURL());
		builder.append(", premiumCount=");
		builder.append(getPremiumCount());
		builder.append(", apCount=");
		builder.append(getApCount());
		builder.append(", additionalInformation=");
		builder.append(getAdditionalInformation());
		builder.append(", policyType=");
		builder.append(getPolicyType());
		builder.append(", trackerStatus=");
		builder.append(getTrackerStatus());
		builder.append(", presentedDate=");
		builder.append(getPresentedDate());
		builder.append(", workOrderReference=");
		builder.append(getWorkOrderReference());
		builder.append(", workOrderStatus=");
		builder.append(getWorkOrderStatus());
		builder.append(", workPackageId=");
		builder.append(getWorkOrderStatus());
		builder.append(", urgentReference=");
		builder.append(urgentReference);
		builder.append(", groupId=");
		builder.append(getGroupId());
		builder.append(", groupReference=");
		builder.append(getGroupReference());
		builder.append(", noinGroup=");
		builder.append(getNoinGroup());
		builder.append(", treatyFDOStatement=");
		builder.append(getTreatyFDOStatement());
		builder.append(", simSigningRequired=");
		builder.append(getSimSigningRequired());
		builder.append(", updatedDate=");
		builder.append(getUpdatedDate());
		builder.append(", techUUID=");
		builder.append(getTechUUID());
		builder.append(", groupStatusIndicator=");
		builder.append(getGroupStatusIndicator());
		builder.append(", xisScanned=");
		builder.append(getXisScanned());
		builder.append(", xisWorkFlowStatus=");
		builder.append(getXisWorkFlowStatus());
		builder.append("]");
		return builder.toString();
	}
}
