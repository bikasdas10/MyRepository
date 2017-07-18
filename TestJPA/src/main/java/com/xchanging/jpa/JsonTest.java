package com.xchanging.jpa;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.AnnotationIntrospector.ReferenceProperty.Type;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.xchanging.acord.extension.ClaimExtension;
import com.xchanging.acord.extension.TechAccountExtension;
import com.xchanging.acord.util.AcordUtils;
import com.xchanging.xsb.premiumact.jaxb.workorder.DocumentType;
import com.xchanging.xsb.premiumact.jaxb.workorder.Documents;
import com.xchanging.xsb.premiumact.jaxb.workorder.MarketType;
import com.xchanging.xsb.premiumact.jaxb.workorder.SlipTypeType;
import com.xchanging.xsb.premiumact.jaxb.workorder.WorkOrder;

import acord_repos_1_2_2.AccessControlListType;
import acord_repos_1_2_2.AccessPartyType;
import acord_repos_1_2_2.AccessRightCdType;
import acord_repos_1_2_2.ListActionCdType;
import acord_repos_1_2_2.PartyIdType;

import static com.xchanging.acord.util.AcordUtils.getAcordJAXBContext;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.activation.DataHandler;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;

import org.acord.standards.acordmsgsvc._1.ApplicableValidatorsType;
import org.acord.standards.acordmsgsvc._1.SupportingDocumentType;
import org.acord.standards.acordmsgsvc._1.ValidatorType;
import org.acord.standards.jv_ins_reinsurance._1.AnyCountType;
import org.acord.standards.jv_ins_reinsurance._1.BrokerType;
import org.acord.standards.jv_ins_reinsurance._1.ClaimEntryType;
import org.acord.standards.jv_ins_reinsurance._1.ClaimExtensionType;
import org.acord.standards.jv_ins_reinsurance._1.ClaimType;
import org.acord.standards.jv_ins_reinsurance._1.ContactType;
import org.acord.standards.jv_ins_reinsurance._1.ContractType;
import org.acord.standards.jv_ins_reinsurance._1.EndDateType;
import org.acord.standards.jv_ins_reinsurance._1.IdType;
import org.acord.standards.jv_ins_reinsurance._1.InsurerType;
import org.acord.standards.jv_ins_reinsurance._1.LossPeriodType;
import org.acord.standards.jv_ins_reinsurance._1.ObjectFactory;
import org.acord.standards.jv_ins_reinsurance._1.PartyType;
import org.acord.standards.jv_ins_reinsurance._1.ReferredClaimMovementType;
import org.acord.standards.jv_ins_reinsurance._1.ServiceProviderType;
import org.acord.standards.jv_ins_reinsurance._1.StartDateType;
import org.acord.standards.jv_ins_reinsurance._1.SubaccountType;
import org.acord.standards.jv_ins_reinsurance._1.TechAccountExtensionType;
import org.acord.standards.jv_ins_reinsurance._1.TechAccountType;
import org.apache.commons.lang.StringUtils;
import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;

public class JsonTest {
	private static ObjectMapper objectMapper;
	static{
		objectMapper = new ObjectMapper();
		/* JaxbAnnotationModule module = new JaxbAnnotationModule();

		 objectMapper.registerModule(module);
		 objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		 objectMapper.addMixInAnnotations(JAXBElement.class, JAXBElementMixIn.class);*/
	}
	public static void main(String[] args) throws Exception {
		StringWriter xmlClaim=objectMarshalling(getClaimType(), "application/json");
		System.out.println(xmlClaim.toString());
//		System.out.println("json----"+populateFromJson(getClaimType()));
//		System.out.println("xml--"+objectMarshallingOld(objectUnMarshalling(xml.toString(),"application/xml"),ClaimType.class));
//		String contractXml = objectMarshallingOld(objectUnMarshalling(xml.toString()),ClaimType.class).toString();
//		System.out.println("object--"+objectUnMarshallingOld(contractXml));
		ClaimType claim = (ClaimType)objectUnMarshalling(xmlClaim.toString(), "application/json");
		
		/*String techXml = objectMarshallingOld(AcordUtils.getTechAccountType(),TechAccountType.class).toString();
		System.out.println("techXml--"+techXml);
		TechAccountType tech =(TechAccountType) objectUnMarshallingOld(techXml);
		String techJson = objectMarshalling(tech,"application/json").toString();
		System.out.println("techJson--"+techJson);*/
		
		String techJson = objectMarshalling(populateTechAccount(createWorkOrder()),"application/json").toString();
		System.out.println("techJson--"+techJson);
		objectUnMarshalling(techAccountJSON(),"application/json");
		
		objectUnMarshalling(techAccountJSONOther(),"application/json");
		
		
		
		
		
	}
	
    private static String populateFromJson(ClaimType claim)
            throws IOException, JsonParseException, JsonMappingException {
/*     Gson gson = new Gson();
     objectMapper.writeValueAsString(claim);
      if(claim != null){
      
      ClaimExtension extension=null;
      for(Object object : claim.getExtension().getAny()){
            extension = objectMapper.readValue(gson.toJson((JAXBElement)object), ClaimExtension.class);
//    	  extension = objectMapper.readValue(objectMarshalling(object,JAXBElement.class).toString(), ClaimExtension.class);
      }
     
      claim.getExtension().getAny().clear();
      claim.getExtension().getAny().add(extension);
      
      return objectMapper.writeValueAsString(claim);
     
      
      }*/
    	
    	Gson gson = new Gson();
//    	System.out.println(gson.toJson(claim));
      return objectMapper.writeValueAsString(claim);
}
    
    @JsonIgnoreProperties(value = {"globalScope", "typeSubstituted", "nil"})
    static class JAXBElementMixIn<T> {
  
      @JsonCreator
      public JAXBElementMixIn(@JsonProperty("name") QName name,
              @JsonProperty("declaredType") Class<T> declaredType,
              @JsonProperty("scope") Class scope,
              @JsonProperty("value") T value) {
      }
  }
    
	public static StringWriter objectMarshalling(final Object obj, final String accept) {
		StringWriter writer = null;
		try {
			if (obj != null) {
				writer = new StringWriter();
//				JAXBContext jaxbContext = JAXBContext.newInstance(className);
//				JAXBContext jaxbContext = AcordUtils.getAcordJAXBContext();
				JAXBContext jaxbContext = JAXBContextFactory.createContext(new Class[]{com.xchanging.acord.custom.objects.ObjectFactory.class,org.acord.standards.acordmsgsvc._1.ObjectFactory.class,
					com.xchanging.acord.extension.ObjectFactory.class,
					org.acord.standards.jv_ins_reinsurance._1.ObjectFactory.class}, new HashMap<>());
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				jaxbMarshaller.setProperty(MarshallerProperties.MEDIA_TYPE, accept);
				jaxbMarshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);

				jaxbMarshaller.marshal(obj, writer);

			}
		} catch (JAXBException jaxbException) {
			jaxbException.printStackTrace();
		}
		return writer;
	}
	
	public static Object objectUnMarshalling(final String inputStream,final String contentType) {
        Object obj = null;
		try {
//			Unmarshaller unmarshaller = getAcordJAXBContext().createUnmarshaller();.
			JAXBContext jaxbContext = JAXBContextFactory.createContext(new Class[]{com.xchanging.acord.custom.objects.ObjectFactory.class,org.acord.standards.acordmsgsvc._1.ObjectFactory.class,
					com.xchanging.acord.extension.ObjectFactory.class,
					org.acord.standards.jv_ins_reinsurance._1.ObjectFactory.class}, new HashMap<>());
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, contentType);
			unmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, true);
			obj = JAXBIntrospector.getValue(unmarshaller.unmarshal(new StringReader(inputStream)));
		} catch (JAXBException exception) {
			exception.printStackTrace();
		}
        
        return obj;
 }
	public static StringWriter objectMarshallingOld(final Object obj, final Class className) {
		StringWriter writer = null;
		try {
			if (obj != null) {
				writer = new StringWriter();
				JAXBContext jaxbContext = JAXBContext.newInstance(className);
//				JAXBContext jaxbContext = AcordUtils.getAcordJAXBContext();
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
	
	public static Object objectUnMarshallingOld(final String inputStream) {
        Object obj = null;
		try {
			Unmarshaller unmarshaller = getAcordJAXBContext().createUnmarshaller();
			obj = JAXBIntrospector.getValue(unmarshaller.unmarshal(new StringReader(inputStream)));
		} catch (JAXBException exception) {
			exception.printStackTrace();
		}
        
        return obj;
 }
    private static ClaimType getClaimType(){
    	
    	ClaimExtension claimExtn = new ClaimExtension();
		ContractType contractType = new ContractType();
		contractType.setBrokerReference("B0001UMRSANJEEV09");
		claimExtn.setContract(contractType);
    	
		ClaimType claimType = AcordUtils.getJvInsObjectFactory().createClaimType();
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
		ClaimExtension claimExtension = AcordUtils.getExtensionObjectFactory()
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
	    
		ClaimExtensionType claimExtensionType = AcordUtils.getJvInsObjectFactory()
				.createClaimExtensionType();
		claimExtensionType.getAny().add(
				AcordUtils.getExtensionObjectFactory().createClaimExtension(
						claimExtension));
		
		claimType.setExtension(claimExtensionType);
    	return claimType;
    }
    
    
    //Create WOrkOrder
    public static WorkOrder createWorkOrder() throws Exception{
    	WorkOrder workOrder = new WorkOrder();
    	com.xchanging.xsb.premiumact.jaxb.workorder.ObjectFactory of = new com.xchanging.xsb.premiumact.jaxb.workorder.ObjectFactory();
		workOrder.setDocumentsType(of.createDocuments(getDocumentType()));
		workOrder.setSlipType(SlipTypeType.B);
		workOrder.setNumberInGroup("1");
		workOrder.setMarket(MarketType.C);
		workOrder.setUMR("UMR");
		workOrder.setUCR("UCR");
		workOrder.setTR("TR");
		workOrder.setWorkOrderReference("wprs");
		workOrder.setProcessingRequired("true");
		
		return workOrder;
    	
    }
    
	public static Documents getDocumentType() throws Exception{
		Documents documents = new Documents();
		
		DocumentType documentType1 = new DocumentType();
		documentType1.setType("type");
		documentType1.setID("!V3!MKTREPOS!C!D$104985073!");
		documentType1.setReference("qwer123");
		documentType1.setVersion("1");
		documentType1.setDescription("asd");
		
		DocumentType documentType2 = new DocumentType();
		documentType2.setType("type");
		documentType2.setID("!V3!MKTREPOS!C!D$104985073!");
		documentType2.setReference("qwer123");
		documentType2.setVersion("1");
		documentType2.setDescription("asd");
		
		documents.getDocument().add(documentType1);
		documents.getDocument().add(documentType2);
		return documents;
	}
    
    
    //TechAccount from workOrder
	public static TechAccountType populateTechAccount(WorkOrder workOrder){
		ObjectFactory objectFactory = AcordUtils.getJvInsObjectFactory();
		org.acord.standards.acordmsgsvc._1.ObjectFactory accordObjectFactory = new org.acord.standards.acordmsgsvc._1.ObjectFactory();
		TechAccountType techAccountType = objectFactory.createTechAccountType();
	
		AnyCountType count = objectFactory.createAnyCountType();
		if(StringUtils.isNotBlank(workOrder.getNumberInGroup())){
		count.setCount(Long.parseLong(workOrder.getNumberInGroup()));
		}
		techAccountType.getContent().add(accordObjectFactory.createApplicableValidators(populateApplicableValidatorsType()));
		if (StringUtils.isNotBlank(workOrder.getWorkOrderReference())) {
			techAccountType.getContent()
					.add(objectFactory.createServiceProviderReference(workOrder.getWorkOrderReference()));
		} else {
			techAccountType.getContent().add(objectFactory.createServiceProviderReference(StringUtils.EMPTY));
		}
		techAccountType.getContent().add(objectFactory.createBrokerReference(workOrder.getUMR()));
        techAccountType.getContent().add(objectFactory.createAccountTransactionType(workOrder.getProcessingRequired()));
        techAccountType.getContent().add(objectFactory.createGroupReference(workOrder.getGroupReference()));
        techAccountType.getContent().add(objectFactory.createItemsInSettlementGroupTotal(count));
        populateBroker(techAccountType,objectFactory,workOrder);
        populateInsurer(techAccountType,objectFactory,workOrder);
        populateServiceProvider(techAccountType,objectFactory,workOrder);
        techAccountType.getContent().add(objectFactory.createCorrectionIndicator(workOrder.getTypeOfSubmission()));
        populateContract(techAccountType,objectFactory,workOrder);
        techAccountType.getContent().add(objectFactory.createSettlementGroupReference(workOrder.getGroupReference()));
        populateSubAccount(techAccountType,objectFactory,workOrder);
        populateTechAccountExtension(techAccountType,objectFactory,workOrder);
		return techAccountType;
	}
	
	private static ApplicableValidatorsType populateApplicableValidatorsType() {
		ApplicableValidatorsType applicableValidatorsType = new ApplicableValidatorsType();
		ValidatorType validatorType = new ValidatorType();
		validatorType.setValidatorURI("validate");
		applicableValidatorsType.getValidator().add(validatorType);
		return applicableValidatorsType;
	}
	
	private static void populateContract(TechAccountType techAccountType,ObjectFactory objectFactory,WorkOrder workOrder){
		ContractType contract =  objectFactory.createContractType();
		contract.setServiceProviderReference(workOrder.getUrgentReference());
		contract.setBrokerReference(workOrder.getUMR());
		
		techAccountType.getContent().add(objectFactory.createContract(contract));
	}
	
	private static void populateBroker(TechAccountType techAccountType,ObjectFactory objectFactory,WorkOrder workOrder){
		BrokerType broker = objectFactory.createBrokerType();
		
		ContactType contactBroker = objectFactory.createContactType();
		contactBroker.setDescription(workOrder.getBrokerContactName());
		contactBroker.setTelephone(workOrder.getBrokerContactPhone());
		contactBroker.setEmail(workOrder.getBrokerContactEmail());
		broker.setContact(contactBroker);
		
		PartyType partyBroker = objectFactory.createPartyType();
		IdType id = objectFactory.createIdType();
		id.setValue(workOrder.getBroker());
		partyBroker.getContent().add(objectFactory.createId(id));
		broker.setParty(partyBroker);
		
		techAccountType.getContent().add(objectFactory.createBroker(broker));
	}
	
	private static void populateInsurer(TechAccountType techAccountType,ObjectFactory objectFactory,WorkOrder workOrder){
		PartyType partyInsurer = objectFactory.createPartyType();
		InsurerType insurer = objectFactory.createInsurerType();
		if(workOrder.getMarket() != null){
		partyInsurer.getContent().add(objectFactory.createName(workOrder.getMarket().value()));
		}
		insurer.setParty(partyInsurer);
		
		techAccountType.getContent().add(objectFactory.createInsurer(insurer));
	}
	
	private static void populateServiceProvider(TechAccountType techAccountType,ObjectFactory objectFactory,WorkOrder workOrder){
		ServiceProviderType serviceProvider = objectFactory.createServiceProviderType();
		ContactType contactServiceProvider = objectFactory.createContactType();
		contactServiceProvider.setDescription(workOrder.getXISContactName());
		
		techAccountType.getContent().add(objectFactory.createServiceProvider(serviceProvider));
	}
	
	private static void populateClaimEntryType(ObjectFactory objectFactory, SubaccountType subAccount,WorkOrder workOrder) {
		ClaimEntryType claim = objectFactory.createClaimEntryType();
		System.out.println("ucr---"+workOrder.getUCR());
		claim.getContent().add(objectFactory.createBrokerReference(workOrder.getUCR()));	
		subAccount.setClaimEntry(claim);
	}
	
	private static void populateReferredClaimMovement(ObjectFactory objectFactory, SubaccountType subAccount,WorkOrder workOrder) {
		ReferredClaimMovementType claimMovement = objectFactory.createReferredClaimMovementType();
		System.out.println("tr---"+workOrder.getTR());
		claimMovement.getContent().add(objectFactory.createBrokerReference(workOrder.getTR()));
		subAccount.setReferredClaimMovement(claimMovement);
	}
	
	
	private static void populateSubAccount(TechAccountType techAccountType,ObjectFactory objectFactory,WorkOrder workOrder){
		SubaccountType subAccount = objectFactory.createSubaccountType();
		subAccount.setJvClassOfBusiness(workOrder.getClassOfBusiness());
		
		populateClaimEntryType(objectFactory,subAccount,workOrder);
		populateReferredClaimMovement(objectFactory,subAccount,workOrder);
		
		if(workOrder.getDocumentsType() != null){
		Documents docs = ((JAXBElement<Documents>)workOrder.getDocumentsType()).getValue();
		for(DocumentType doc : docs.getDocument()){
			SupportingDocumentType supportingDocumentType = new SupportingDocumentType();
			supportingDocumentType.setDocumentId(doc.getID());
			supportingDocumentType.setDocumentReference(doc.getReference());
			supportingDocumentType.setDocumentTypeCd(doc.getType());
			supportingDocumentType.setDocumentVersion(doc.getVersion());
			supportingDocumentType.setDescription(doc.getDescription());
			
			subAccount.getSupportingInformationOrSupportingDocument().add(supportingDocumentType);
		 }
		}
		techAccountType.getContent().add(objectFactory.createSubaccount(subAccount));
	}
	
	private static void populateTechAccountExtension(TechAccountType techAccountType,ObjectFactory objectFactory,WorkOrder workOrder){
		TechAccountExtension extension = new TechAccountExtension();		
		extension.setApplication(workOrder.getApplication());
		if (workOrder.getSlipType() != null) {
			extension.setSlipType(workOrder.getSlipType().value());
		}
		extension.setPremiumFdoLpansCount(workOrder.getPremiumFDOLPANS());
		extension.setApRpLpansCount(workOrder.getAPRPLPANS());
		extension.setSimSignReq(workOrder.getSimSigningRequired());
		extension.setTreatyFDOStatement(workOrder.getTreatyFdoStatement());
		extension.setXisScanned(workOrder.getXisScanned());
		extension.setPolicyType(workOrder.getTypeOfPolicy());
		extension.setPegaWO(workOrder.getPegaWO());
		TechAccountExtensionType techAccountExtensionType = objectFactory.createTechAccountExtensionType();
		com.xchanging.acord.extension.ObjectFactory extensionOF = new com.xchanging.acord.extension.ObjectFactory();
		techAccountExtensionType.getAny().add(extensionOF.createTechAccountExtension(extension));
		
		
		techAccountType.getContent().add(objectFactory.createTechAccountTypeExtension(techAccountExtensionType));
	}
	
	private static String techAccountJSON(){
		String techJson = "{ \"TechAccount\" : { \"ApplicableValidators\": { \"Validator\": { \"ValidatorURI\": \"validate\" } }, \"ServiceProviderReference\": \"wprs\", \"BrokerReference\": \"UMR\", \"AccountTransactionType\": \"true\", \"GroupReference\": \"grpRef\", \"ItemsInSettlementGroupTotal\": { \"Count\": \"1\" }, \"Broker\": { \"Party\": { \"Id\": null }, \"Contact\": null }, \"Insurer\": { \"Party\": { \"Name\": \"C\" } }, \"ServiceProvider\": null, \"CorrectionIndicator\": \"1\", \"Contract\": { \"BrokerReference\": \"UMR\" }, \"SettlementGroupReference\": \"1\", \"Subaccount\": { \"ReferredClaimMovement\": { \"BrokerReference\": \"TR\" }, \"ClaimEntry\": { \"BrokerReference\": \"UCR\" }, \"SupportingDocument\": [ { \"DocumentId\": \"!V3!MKTREPOS!C!D$104985073!\", \"DocumentReference\": \"qwer123\", \"DocumentVersion\": \"1\", \"DocumentTypeCd\": \"type\", \"Description\": \"asd\" }, { \"DocumentId\": \"!V3!MKTREPOS!C!D$104985073!\", \"DocumentReference\": \"qwer123\", \"DocumentVersion\": \"1\", \"DocumentTypeCd\": \"type\", \"Description\": \"asd\" } ] }, \"Extension\": [{ \"TechAccountExtension\": [{ \"NoOfDocs\": \"0\", \"SlipType\": \"B\" }] } ] } } ";
		
		return techJson;
	}
	
	private static String techAccountJSONOther(){
		String techJSON = "{ \"TechAccount\" : { \"ApplicableValidators\" : [ { \"Validator\" : [ { \"ValidatorURI\" : \"validate\" } ] } ], \"ServiceProviderReference\" : [ \"wprs\" ], \"BrokerReference\" : [ \"UMR\" ], \"AccountTransactionType\" : [ null ], \"GroupReference\" : [ null ], \"ItemsInSettlementGroupTotal\" : [ { \"Count\" : 1 } ], \"Broker\" : [ { \"Party\" : { \"Id\" : [ { } ] }, \"Contact\" : { } } ], \"Insurer\" : [ { \"Party\" : { \"Name\" : [ \"C\" ] } } ], \"ServiceProvider\" : [ { } ], \"CorrectionIndicator\" : [ null ], \"Contract\" : [ { \"BrokerReference\" : \"UMR\" } ], \"SettlementGroupReference\" : [ null ], \"Subaccount\" : [ { \"ReferredClaimMovement\" : { \"BrokerReference\" : [ \"TR\" ] }, \"ClaimEntry\" : { \"BrokerReference\" : [ \"UCR\" ] }, \"SupportingDocument\" : [ { \"DocumentId\" : \"!V3!MKTREPOS!C!D$104985073!\", \"DocumentReference\" : \"qwer123\", \"DocumentVersion\" : \"1\", \"DocumentTypeCd\" : \"type\", \"Description\" : \"asd\" }, { \"DocumentId\" : \"!V3!MKTREPOS!C!D$104985073!\", \"DocumentReference\" : \"qwer123\", \"DocumentVersion\" : \"1\", \"DocumentTypeCd\" : \"type\", \"Description\" : \"asd\" } ] } ], \"Extension\" : [ { \"TechAccountExtension\" : [ { \"NoOfDocs\" : 0, \"SlipType\" : \"B\" } ] } ] } } ";
	return techJSON;
	}
	
	
	

}
