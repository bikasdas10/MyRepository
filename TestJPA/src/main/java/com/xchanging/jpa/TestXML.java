package com.xchanging.jpa;
import static com.xchanging.acord.util.AcordUtils.getAcordJAXBContext;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.acord.standards.jv_ins_reinsurance._1.ClaimEntryType;
import org.acord.standards.jv_ins_reinsurance._1.ClaimExtensionType;
import org.acord.standards.jv_ins_reinsurance._1.ClaimMovementExtensionType;
import org.acord.standards.jv_ins_reinsurance._1.ClaimMovementType;
import org.acord.standards.jv_ins_reinsurance._1.ClaimType;
import org.acord.standards.jv_ins_reinsurance._1.ContractExtensionType;
import org.acord.standards.jv_ins_reinsurance._1.ContractType;
import org.acord.standards.jv_ins_reinsurance._1.EndDateType;
import org.acord.standards.jv_ins_reinsurance._1.LossPeriodType;
import org.acord.standards.jv_ins_reinsurance._1.ObjectFactory;
import org.acord.standards.jv_ins_reinsurance._1.StartDateType;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import com.xchanging.acord.custom.objects.ClaimAccessControl;
import com.xchanging.acord.custom.objects.ClaimMovements;
import com.xchanging.acord.custom.objects.Contracts;
import com.xchanging.acord.custom.objects.CustomAccessControlList;
import com.xchanging.acord.custom.objects.CustomAccessParty;
import com.xchanging.acord.custom.objects.DocumentPostUploadEvent;
import com.xchanging.acord.custom.objects.DocumentPostUploadEvents;
import com.xchanging.acord.custom.objects.ErrorMessage;
import com.xchanging.acord.custom.objects.GenericCOIBean;
import com.xchanging.acord.custom.objects.MessageRequest;
import com.xchanging.acord.custom.objects.Messages;
import com.xchanging.acord.custom.objects.RiskAccessControl;
import com.xchanging.acord.custom.objects.WrapperOfGenericCOIBean;
import com.xchanging.acord.extension.Audit;
import com.xchanging.acord.extension.Audits;
import com.xchanging.acord.extension.ClaimExtension;
import com.xchanging.acord.extension.ClaimMovementExtension;

import com.xchanging.acord.extension.ContractExtension;

import com.xchanging.acord.extension.DocumentExtension;
import com.xchanging.acord.util.AcordUtils;

import acord_repos_1_2_2.AccessControlListType;
import acord_repos_1_2_2.AccessPartyType;
import acord_repos_1_2_2.AccessRightCdType;
import acord_repos_1_2_2.DocumentItemType;
import acord_repos_1_2_2.DocumentListType;
import acord_repos_1_2_2.DocumentType;
import acord_repos_1_2_2.ListActionCdType;
import acord_repos_1_2_2.PartyIdType;
import jvins_2013_06_dri_slice.ReferredObjectsType;

public class TestXML {

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		
		ObjectFactory ofIns = new ObjectFactory();
		com.xchanging.acord.extension.ObjectFactory ofExt= new com.xchanging.acord.extension.ObjectFactory();
		ClaimExtension claimExtn = new ClaimExtension();
		ContractType contractType = new ContractType();
		contractType.setBrokerReference("B0001UMRSANJEEV09");
		claimExtn.setContract(contractType);

		ClaimType claimType=new ClaimType();

		claimType = ofIns.createClaimType();
		claimType.setInsurerReference("Insuredhere");
		claimType.setReinsurerReference("reinsuredhere");
		claimType.setBrokerReference("B0001UCRSANJEEV09");
		claimType.setLossOrEventName("DAVIE");
		LossPeriodType lossPeriodType=new LossPeriodType();
		StartDateType startDateType=new StartDateType();
		startDateType.setValue("01/01/2010 00:00:00:00");
		EndDateType endDateType=new EndDateType();
		endDateType.setValue("03/01/2010 00:00:00:00");
		lossPeriodType.setStartDate(startDateType);
		lossPeriodType.setEndDate(endDateType);
		claimType.setLossPeriod(lossPeriodType);
		claimType.setCatastropheNbr("DG1");
	
		claimType
				.setLossStatus("Yes");
		claimType.setLossRiskDetails("XXXXXX");
		ClaimExtension claimExtension = ofExt
				.createClaimExtension();
		claimExtension.setAssociatedUcr("B0001UCR");
		claimExtension.setBrokerCode("B0001");
		claimExtension.setBkrClaimRef("B0001");
		claimExtension.setClaimant("JGFGH");
		claimExtension.setContract(contractType);
		
		AccessControlListType accessControlListType=new AccessControlListType();
		
		List<AccessPartyType> accessControlList=new ArrayList<AccessPartyType>();
		AccessPartyType accesspartyType=new AccessPartyType();
		accesspartyType.setAccessRightCd(AccessRightCdType.CHANGE);
		accesspartyType.setListActionCd(ListActionCdType.ADD);
		PartyIdType partyIdtype=new PartyIdType();
		partyIdtype.setValue("BR0001");
		accesspartyType.setPartyId(partyIdtype);
		accesspartyType.setPartyName("Name :imrp8admin ; AccessLevel :Unknown");
		accesspartyType.setPartyRoleCd("INSURER");
		
		accessControlList.add(accesspartyType);
		
		accessControlListType.getAccessParty().add(accesspartyType);
	    claimExtension.setAccessControls(accessControlListType);
	    
		ClaimExtensionType claimExtensionType = ofIns
				.createClaimExtensionType();
		claimExtensionType.getAny().add(
				ofExt.createClaimExtension(
						claimExtension));
		

/*		SupportingDocumentType support=new SupportingDocumentType();
		support.setDocumentId("72364782634786237846");
		GENERALPARTYType genral = new GENERALPARTYType();
		org.acord.standards.acordmsgsvc._1.PartyIdType partyID=new org.acord.standards.acordmsgsvc._1.PartyIdType();
		partyID.setValue("BR0001");
		genral.setPartyId(partyID);
		support.setOwner(genral);*/
	/*	
		List<SupportingDocumentType> suuportList=new ArrayList<SupportingDocumentType>();
		suuportList.add(support);
		claimExtension.setDocuments(suuportList);*/
		
		DocumentListType documentListType=new DocumentListType();
		DocumentItemType documentItemType=new DocumentItemType();
		DocumentType docuementType=new DocumentType();
		docuementType.setDocumentId("5245");
		docuementType.setDocumentReference("hhgfg");
		documentItemType.setDocument(docuementType);
		documentItemType.setAccessControlList(accessControlListType);
		documentListType.getDocumentItem().add(documentItemType);
		
//		claimExtension.setDocuments(documentListType);
		claimType.setExtension(claimExtensionType);
		StringWriter xml=objectMarshalling(claimType, ClaimType.class);
		System.out.println(xml.toString());
		
		StringWriter xmlDocExt=objectMarshalling(documentExtension(), DocumentExtension.class);
		System.out.println(xmlDocExt.toString());
		
		DocumentExtension docExt = (DocumentExtension)objectUnMarshalling(xmlDocExt.toString());
		
		StringWriter xmlClaimMovs=objectMarshalling(claimMovements(), ClaimMovements.class);
		System.out.println(xmlClaimMovs.toString());
		
//		StringWriter xmlPostUpoladEvents=objectMarshalling(documentPostUploadEvents(), DocumentPostUploadEvents.class);
//		System.out.println(xmlPostUpoladEvents.toString());
		
		StringWriter xmlRiskAccessControls=objectMarshalling(RiskAccessControls(), com.xchanging.acord.custom.objects.RiskAccessControls.class);
		System.out.println(xmlRiskAccessControls.toString());
		
		StringWriter xmlClaimAccessControls=objectMarshalling(ClaimAccessControls(), com.xchanging.acord.custom.objects.ClaimAccessControls.class);
		System.out.println(xmlClaimAccessControls.toString());
		
		StringWriter xmlWrapperOfGenericCOIBean=objectMarshalling(wrapperOfGenericCOIBean(), WrapperOfGenericCOIBean.class);
		System.out.println(xmlWrapperOfGenericCOIBean.toString());
		
		StringWriter xmlContracts=objectMarshalling(contracts(), Contracts.class);
		System.out.println(xmlContracts.toString());
		
//		StringWriter xmlMessages=objectMarshalling(messages(), Messages.class);
//		System.out.println(xmlMessages.toString());
		
		StringWriter xmlError=objectMarshalling(error(), ErrorMessage.class);
		System.out.println(xmlError.toString());
		
	}

	/**
	 * @param obj
	 * @param className
	 * @return
	 */
	public static StringWriter objectMarshalling(final Object obj, final Class className) {
		StringWriter writer = null;
		try {
			if (obj != null) {
				writer = new StringWriter();
//				JAXBContext jaxbContext = JAXBContext.newInstance(className);
				JAXBContext jaxbContext = AcordUtils.getAcordJAXBContext();
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
						true);
				jaxbMarshaller.marshal(obj, writer);
				//jaxbMarshaller.marshal(obj, System.out);

			}
		} catch (JAXBException jaxbException) {
			jaxbException.printStackTrace();
		}
		return writer;
	}
	
	public static DocumentExtension documentExtension(){
		DocumentExtension  docExt = AcordUtils.getExtensionObjectFactory().createDocumentExtension();
		
		
		Audit audit = new Audit();
		audit.setCreationTime("asda");
		audit.setUserId("asdasd");
		audit.setUserName("asdad");
		
		Audit audit1 = new Audit();
		audit1.setCreationTime("asda1");
		audit1.setUserId("asdasd1");
		audit1.setUserName("asdad1");
		Audits audits = new Audits();
		audits.getAudit().add(audit);
		audits.getAudit().add(audit1);
		
		docExt.setAudits(audits);
		
		return docExt;
	}
	
	public static Object objectUnMarshalling(final String inputStream) {
        Object obj = null;
		try {
			Unmarshaller unmarshaller = getAcordJAXBContext().createUnmarshaller();
			obj = JAXBIntrospector.getValue(unmarshaller.unmarshal(new StringReader(inputStream)));
		} catch (JAXBException exception) {
			exception.printStackTrace();
		}
        
        return obj;
 }
	public static ClaimMovements claimMovements(){
		
		ClaimMovements claimMovements = new ClaimMovements();
		claimMovements.setCount(Integer.valueOf(2));
		ClaimMovementType claimMov = AcordUtils.getJvInsObjectFactory().createClaimMovementType();
		claimMov.getContent().add(AcordUtils.getJvInsObjectFactory().createUUId("347474UUid"));
		claimMov.getContent().add(AcordUtils.getJvInsObjectFactory().createBrokerReference("brkref"));
		ContractType contract =  AcordUtils.getJvInsObjectFactory().createContractType();
		contract.setBrokerReference("B0001UMROBDRI01");
		claimMov.getContent().add(AcordUtils.getJvInsObjectFactory().createContract(contract));
		
		ClaimType claim =  AcordUtils.getJvInsObjectFactory().createClaimType();
		claim.setBrokerReference("B0001UMROBDRI02");
		claimMov.getContent().add(AcordUtils.getJvInsObjectFactory().createClaim(claim));
		
		ClaimEntryType claimEntry =  AcordUtils.getJvInsObjectFactory().createClaimEntryType();
		claimEntry.getContent().add(AcordUtils.getJvInsObjectFactory().createBrokerReference("B0001TR01"));
		claimMov.getContent().add(AcordUtils.getJvInsObjectFactory().createClaimEntry(claimEntry));
		
		ClaimMovementExtensionType claimExt =  AcordUtils.getJvInsObjectFactory().createClaimMovementExtensionType();
		claimMov.getContent().add(AcordUtils.getJvInsObjectFactory().createClaimMovementTypeExtension(claimExt));
		
		ClaimMovementExtension claimExension =  AcordUtils.getExtensionObjectFactory().createClaimMovementExtension();
		claimExension.setNoAccessFlag(false);
		claimExension.setLirmaDelFlag(false);
		claimExension.setLloydsDelFlag(false);
		claimExension.setILUDelFlag(false);
		
		claimExt.getAny().add(AcordUtils.getExtensionObjectFactory().createClaimMovementExtension(claimExension));
		
		
		
		claimMovements.getClaimMovementsTypes().add(AcordUtils.getJvInsObjectFactory().createClaimMovement(claimMov));
		
		return claimMovements;
	}
	
	public static DocumentPostUploadEvents  documentPostUploadEvents(){
		DocumentPostUploadEvents docUploadEvents = new DocumentPostUploadEvents();
		List<DocumentPostUploadEvent> listDocUploadEvent = new ArrayList<>();
		DocumentPostUploadEvent docUploadEvent = new DocumentPostUploadEvent();
		
		DocumentItemType docItem = new DocumentItemType();
		DocumentType docType = new DocumentType();
		docType.setDocumentId("0dc463543-47a7-b5f5-202827123132");
		docType.setDocumentReference("ref");
		docType.setDocumentVersion("1.0");
		docItem.setDocument(docType);
		
		ReferredObjectsType refObj = new ReferredObjectsType();
		jvins_2013_06_dri_slice.ContractType contract = new jvins_2013_06_dri_slice.ContractType();
		contract.setBrokerReference("B0001UMROBDRI01");
		refObj.setContract(contract);
		
		jvins_2013_06_dri_slice.ClaimType claim = new jvins_2013_06_dri_slice.ClaimType();
		claim.setBrokerReference("B0001UMROBDRI02");
		refObj.setClaim(claim);
		
		docItem.setReferredObjects(refObj);
		docItem.setDocument(docType);
		
		docUploadEvent.setDocumentItemType(docItem);
		listDocUploadEvent.add(docUploadEvent);
		docUploadEvents.setDocumentPostUploadEvents(listDocUploadEvent);
		
		return docUploadEvents;
	}
	
	public static com.xchanging.acord.custom.objects.RiskAccessControls  RiskAccessControls(){
		com.xchanging.acord.custom.objects.RiskAccessControls riskAccessControls = new com.xchanging.acord.custom.objects.RiskAccessControls();
		RiskAccessControl riskAccessControl = new RiskAccessControl();
		List<RiskAccessControl> listRiskAccessControl = new ArrayList();
		listRiskAccessControl.add(riskAccessControl);
		AccessControlListType acCtlList = new AccessControlListType();
		AccessPartyType accessParty = new AccessPartyType();
		accessParty.setAccessRightCd(AccessRightCdType.CHANGE);
		accessParty.setListActionCd(ListActionCdType.ADD);
		PartyIdType party = new PartyIdType();
//		party.setTokenId("1234");
		party.setValue("tokVal");
		accessParty.setPartyId(party);
		accessParty.setPartyName("PartyName");
		accessParty.setPartyRoleCd("PartyRoleCd");
		acCtlList.getAccessParty().add(accessParty);
		riskAccessControl.setAccessControlList(acCtlList);
		
		CustomAccessControlList customAccessControlList = new CustomAccessControlList();
		CustomAccessParty customAccessParty = new CustomAccessParty();
		List<CustomAccessParty> listCustomAccessParty = new ArrayList();
		customAccessParty.setPartyId("partId");
		customAccessParty.setReason("reason");
		customAccessParty.setStatus("status");
		listCustomAccessParty.add(customAccessParty);
		customAccessControlList.setListCustomAccessParty(listCustomAccessParty);
		riskAccessControl.setCustomAccessControlList(customAccessControlList);
		
		riskAccessControls.setRiskAccessControls(listRiskAccessControl);
		
		return riskAccessControls;
		
	}
	
	public static com.xchanging.acord.custom.objects.ClaimAccessControls  ClaimAccessControls(){
		com.xchanging.acord.custom.objects.ClaimAccessControls riskAccessControls = new com.xchanging.acord.custom.objects.ClaimAccessControls();
		ClaimAccessControl riskAccessControl = new ClaimAccessControl();
		List<ClaimAccessControl> listRiskAccessControl = new ArrayList();
		listRiskAccessControl.add(riskAccessControl);
		AccessControlListType acCtlList = new AccessControlListType();
		AccessPartyType accessParty = new AccessPartyType();
		accessParty.setAccessRightCd(AccessRightCdType.CHANGE);
		accessParty.setListActionCd(ListActionCdType.ADD);
		PartyIdType party = new PartyIdType();
//		party.setTokenId("1234");
		party.setValue("tokVal");
		accessParty.setPartyId(party);
		accessParty.setPartyName("PartyName");
		accessParty.setPartyRoleCd("PartyRoleCd");
		acCtlList.getAccessParty().add(accessParty);
		riskAccessControl.setAccessControlList(acCtlList);
		
		CustomAccessControlList customAccessControlList = new CustomAccessControlList();
		CustomAccessParty customAccessParty = new CustomAccessParty();
		List<CustomAccessParty> listCustomAccessParty = new ArrayList();
		customAccessParty.setPartyId("partId");
		customAccessParty.setReason("reason");
		customAccessParty.setStatus("status");
		listCustomAccessParty.add(customAccessParty);
		customAccessControlList.setListCustomAccessParty(listCustomAccessParty);
		riskAccessControl.setCustomAccessControlList(customAccessControlList);
		
		riskAccessControls.setClaimAccessControls(listRiskAccessControl);
		
		return riskAccessControls;
		
	}
	
	public static WrapperOfGenericCOIBean  wrapperOfGenericCOIBean(){
		WrapperOfGenericCOIBean wrapOfGenericCOIBean = new WrapperOfGenericCOIBean();
		GenericCOIBean genericCOIBean = new GenericCOIBean();
		genericCOIBean.setAction("action");
		genericCOIBean.setBureauId("bid");
		genericCOIBean.setUserId("UserId");
		genericCOIBean.setReason("reason");
		genericCOIBean.setStatus("status");
		
		GenericCOIBean genericCOIBean1 = new GenericCOIBean();
		genericCOIBean1.setAction("action");
		genericCOIBean1.setBureauId("bid");
		genericCOIBean1.setUserId("UserId");
		genericCOIBean1.setReason("reason");
		genericCOIBean1.setStatus("status");
		wrapOfGenericCOIBean.getGenericCOIBeanList().add(genericCOIBean);
		wrapOfGenericCOIBean.getGenericCOIBeanList().add(genericCOIBean1);
		return wrapOfGenericCOIBean;
	}
	public static Contracts  contracts(){
		Contracts contracts = new Contracts();
		ContractType contractType = AcordUtils.getJvInsObjectFactory().createContractType();
		contractType.setInsurerReference("ins");
        contractType.setReinsurerRiskReference("reIns");
        contractType.setBrokerReference("B0001TEST123");
        
        ContractExtensionType contractExtensionType = AcordUtils.getJvInsObjectFactory().createContractExtensionType();
        ContractExtension contractExtension = AcordUtils.getExtensionObjectFactory().createContractExtension();
        
        contractExtension.setPolicyInceptionDateFrom("2016-11-11");
        contractExtension.setPolicyInceptionDateTo("2017-11-11");

  contractExtensionType.getAny().add(AcordUtils.getExtensionObjectFactory().createContractExtension(contractExtension));
  contractType.setExtension(contractExtensionType);
  contracts.getContractTypes().add(AcordUtils.getJvInsObjectFactory().createContract(contractType));

  return contracts;
	}
	
	public static ErrorMessage error(){
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setCode("200");
		errorMessage.setDescription("desc");
		errorMessage.setMessage("message");
		return errorMessage;
	}
	
	public static Messages  messages() throws JsonGenerationException, JsonMappingException, IOException{
		Messages mesgs = new Messages();
		MessageRequest mesReq = new MessageRequest();
		mesReq.setBodyText("body");
		mesReq.setSubject("sub");
		mesReq.setMimeType("mime");
		
		List<MessageRequest> mesgReqList = new ArrayList<>();
		mesgReqList.add(mesReq);
		mesgs.setMessageRequestList(mesgReqList);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JaxbAnnotationModule());
		mapper.writeValue(System.out, mesgs);
		
		return mesgs;
		
		
	}
}
