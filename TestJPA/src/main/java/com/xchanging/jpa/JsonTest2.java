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
import com.mchange.v1.util.CollectionUtils;
import com.mchange.v2.c3p0.management.ActiveManagementCoordinator;
import com.xchanging.acord.custom.objects.ClaimMovements;
import com.xchanging.acord.extension.ClaimExtension;
import com.xchanging.acord.extension.ClaimMovementExtension;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
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
import org.acord.standards.jv_ins_reinsurance._1.ClaimMovementExtensionType;
import org.acord.standards.jv_ins_reinsurance._1.ClaimMovementType;
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
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;

public class JsonTest2 {
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
//		ClaimType claim = (ClaimType)objectUnMarshalling(xmlClaim.toString(), "application/json");
		
		/*String techXml = objectMarshallingOld(AcordUtils.getTechAccountType(),TechAccountType.class).toString();
		System.out.println("techXml--"+techXml);
		TechAccountType tech =(TechAccountType) objectUnMarshallingOld(techXml);
		String techJson = objectMarshalling(tech,"application/json").toString();
		System.out.println("techJson--"+techJson);*/
		
		String techJson = objectMarshalling(populateTechAccount(createWorkOrder()),"application/json").toString();
		System.out.println("techJson--"+techJson);
		objectUnMarshalling(techAccountJSON(),"application/json");
		
		objectUnMarshalling(techAccountJSONOther(),"application/json");
		
		String techJson1 = objectMarshalling(populateTechAccountTest(),"application/json").toString();
		System.out.println("techJson1--"+techJson1);

		
		TechAccountType tech = (TechAccountType)objectUnMarshallingOld(techAccountXml());
		String techJson2 = objectMarshalling(tech,"application/json").toString();
		System.out.println("techJson2--"+techJson2);
		TechAccountType tech1 = (TechAccountType)objectUnMarshalling(techJson2,"application/json");
		
		TechAccountType tech2 = (TechAccountType)objectUnMarshallingOld(techAccountXmlFOF());
		String techJson3 = objectMarshalling(tech2,"application/json").toString();
		System.out.println("techJson3--"+techJson3);
		TechAccountType tech3 = (TechAccountType)objectUnMarshalling(techJson3,"application/json");
		
		TechAccountType tech5 = (TechAccountType)objectUnMarshallingOld(techAccountXmlFOFError());
		String techJson5 = objectMarshalling(tech5,"application/json").toString();
		System.out.println("techJson5--"+techJson5);
		TechAccountType tech6 = (TechAccountType)objectUnMarshalling(techJson5,"application/json");
		
		TechAccountType tech7 = (TechAccountType)objectUnMarshallingOld(techAccountXmlFOFUpdate());
		String techJson7 = objectMarshalling(tech7,"application/json").toString();
		System.out.println("techJson7--"+techJson7);
		TechAccountType tech8 = (TechAccountType)objectUnMarshalling(techJson7,"application/json");
		
		ClaimMovements claimMoms = (ClaimMovements)objectUnMarshallingOld(testClaimMoms());
		ClaimMovementExtensionType claimExtn = (ClaimMovementExtensionType)getObjectFromQname(((JAXBElement<ClaimMovementType>)(claimMoms.getClaimMovementsTypes().get(0))).getValue().getContent(), "Extension");
		getClaimMovementExtension(claimExtn);
		
		processResponseFromCE(claimMoms);
/*		TechAccountType tech9 = (TechAccountType)objectUnMarshallingOld(techAccountJSONOther1());
		String techJson9 = objectMarshalling(tech9,"application/json").toString();
		System.out.println("techJson9--"+techJson9);
		TechAccountType tech10 = (TechAccountType)objectUnMarshalling(techJson7,"application/json");
		*/
		int i = 0;
		List<Object> techContents = new ArrayList<>(tech1.getContent());
		for(Object jaxType : techContents){
			
			if(jaxType instanceof ContractType){
				tech1.getContent().set(i, AcordUtils.getJvInsObjectFactory().createContract((ContractType)jaxType));
			}
			i++;
		}
		
		
		
		TechAccountExtension techExt = (TechAccountExtension)((JAXBElement)((List<Object>)(((TechAccountExtensionType)getObjectFromQname(tech1.getContent(),"Extension")).getAny())).get(0)).getValue();
		((List<Object>)(((TechAccountExtensionType)getObjectFromQname(tech1.getContent(),"Extension")).getAny())).set(0,AcordUtils.getExtensionObjectFactory().createTechAccountExtension(techExt));
		System.out.println(tech1);
		
		
		
		String claimMovJson = objectMarshalling(claimMovements(),"application/json").toString();
		System.out.println("claimMovJson--"+claimMovJson);
		ClaimMovements claimMovs = (ClaimMovements)objectUnMarshalling(claimMovJson,"application/json");
/*		List<Object> list= claimMovs.getClaimMovementsTypes();
          Iterator iterable = list.iterator();
		while(iterable.hasNext()){
				ClaimMovementType claimMovementType	 = (ClaimMovementType) iterable.next();
				List<JAXBElement<?>> tempList =  new ArrayList<>();
				for(Object object : claimMovementType.getContent()){
					if(object instanceof ClaimType){
						System.out.println("*****HELLO****");
							ClaimType ct = (ClaimType)object;
							
						JAXBElement claimJaxb = AcordUtils.getJvInsObjectFactory().createClaim(ct);
						tempList.add(claimJaxb);
					}else{
						tempList.add((JAXBElement)object);
					}
				}
				
				claimMovementType.getContent().clear();
				claimMovementType.getContent().addAll(tempList);
				
		}
		System.out.println(claimMovs);*/
		
/*		List<Object> tempclaimMovs = new ArrayList<>(claimMovs.getClaimMovementsTypes());
		if(org.apache.commons.collections.CollectionUtils.isNotEmpty(tempclaimMovs)){
			for(Object jaxbClaimMov : tempclaimMovs){
				if(jaxbClaimMov != null && (ClaimMovementType)(((JAXBElement)jaxbClaimMov).getValue()) != null){
				for(Object jaxbEle: ((ClaimMovementType)(((JAXBElement)jaxbClaimMov).getValue())).getContent()){
					if(jaxbEle instanceof ContractType){
						
					}
				 }
				}
			}
		}*/
		
		
		
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
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				jaxbMarshaller.setProperty(MarshallerProperties.MEDIA_TYPE, accept);
				jaxbMarshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);
//				jaxbMarshaller.setProperty(MarshallerProperties.JSON_REDUCE_ANY_ARRAYS,true);

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
		claimMovements.getClaimMovementsTypes().add(AcordUtils.getJvInsObjectFactory().createClaimMovement(claimMov));
		
		return claimMovements;
	}
	
	
	private static String techAccountJSON(){
		String techJson = "{ \"TechAccount\" : [{ \"ApplicableValidators\": { \"Validator\": { \"ValidatorURI\": \"validate\" } }, \"ServiceProviderReference\": \"wprs\", \"BrokerReference\": \"UMR\", \"AccountTransactionType\": \"true\", \"GroupReference\": \"grpRef\", \"ItemsInSettlementGroupTotal\": { \"Count\": \"1\" }, \"Broker\": { \"Party\": { \"Id\": null }, \"Contact\": null }, \"Insurer\": { \"Party\": { \"Name\": \"C\" } }, \"ServiceProvider\": null, \"CorrectionIndicator\": \"1\", \"Contract\": { \"BrokerReference\": \"UMR\" }, \"SettlementGroupReference\": \"1\", \"Subaccount\": { \"ReferredClaimMovement\": { \"BrokerReference\": \"TR\" }, \"ClaimEntry\": { \"BrokerReference\": \"UCR\" }, \"SupportingDocument\": [ { \"DocumentId\": \"!V3!MKTREPOS!C!D$104985073!\", \"DocumentReference\": \"qwer123\", \"DocumentVersion\": \"1\", \"DocumentTypeCd\": \"type\", \"Description\": \"asd\" }, { \"DocumentId\": \"!V3!MKTREPOS!C!D$104985073!\", \"DocumentReference\": \"qwer123\", \"DocumentVersion\": \"1\", \"DocumentTypeCd\": \"type\", \"Description\": \"asd\" } ] }, \"Extension\": [{ \"TechAccountExtension\": [{ \"NoOfDocs\": \"0\", \"SlipType\": \"B\" }] } ] }] }";
		
		return techJson;
	}
	
	private static String techAccountJSONOther(){
		String techJSON = "{ \"TechAccount\" : { \"ApplicableValidators\" : [ { \"Validator\" : [ { \"ValidatorURI\" : \"validate\" } ] } ], \"ServiceProviderReference\" : [ \"wprs\" ], \"BrokerReference\" : [ \"UMR\" ], \"AccountTransactionType\" : [ null ], \"GroupReference\" : [ null ], \"ItemsInSettlementGroupTotal\" : [ { \"Count\" : 1 } ], \"Broker\" : [ { \"Party\" : { \"Id\" : [ { } ] }, \"Contact\" : { } } ], \"Insurer\" : [ { \"Party\" : { \"Name\" : [ \"C\" ] } } ], \"ServiceProvider\" : [ { } ], \"CorrectionIndicator\" : [ null ], \"Contract\" :  { \"BrokerReference\" : [\"UMR\"] }, \"SettlementGroupReference\" : [ null ], \"Subaccount\" : [ { \"ReferredClaimMovement\" : { \"BrokerReference\" : [ \"TR\" ] }, \"ClaimEntry\" : { \"BrokerReference\" : [ \"UCR\" ] }, \"SupportingDocument\" : [ { \"DocumentId\" : \"!V3!MKTREPOS!C!D$104985073!\", \"DocumentReference\" : \"qwer123\", \"DocumentVersion\" : \"1\", \"DocumentTypeCd\" : \"type\", \"Description\" : \"asd\" }, { \"DocumentId\" : \"!V3!MKTREPOS!C!D$104985073!\", \"DocumentReference\" : \"qwer123\", \"DocumentVersion\" : \"1\", \"DocumentTypeCd\" : \"type\", \"Description\" : \"asd\" } ] } ], \"Extension\" : [ { \"TechAccountExtension\" : [ { \"NoOfDocs\" : 0, \"SlipType\" : \"B\" } ] } ] } } ";
	return techJSON;
	}
	
	public static TechAccountType populateTechAccountTest(){
		ObjectFactory objectFactory = AcordUtils.getJvInsObjectFactory();
		TechAccountType techAccountType = objectFactory.createTechAccountType();
		techAccountType.getContent().add(objectFactory.createUUId("f81d4fae-7dec-11d0-a765-00a0c91e6bf9"));
		
		return techAccountType;
	}
	
	public static String techAccountXml(){
		String techXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <TechAccount xmlns=\"http://www.ACORD.org/standards/Jv-Ins-Reinsurance/1\" xmlns:ns2=\"http://www.ACORD.org/Standards/AcordMsgSvc/1\" xmlns:ns3=\"http://www.xchanging.com/acord/extension\" Receiver=\"serviceprovider\" Sender=\"serviceprovider\"> <ns2:ApplicableValidators> <ns2:Validator> <ns2:ValidatorURI>http://www.ACORD.org/standards/Jv-Ins-Reinsurance/validator/RLC_EBOT_Validator_v2015-04.xsl</ns2:ValidatorURI> </ns2:Validator> </ns2:ApplicableValidators> <ServiceProviderReference></ServiceProviderReference> <UUId>1123f12-1755-11f1-b11b-fac90b590935</UUId> <BrokerReference>B0941SINGSMUCCG2</BrokerReference> <ServiceProviderGroupReference>-</ServiceProviderGroupReference> <CorrectionIndicator>1</CorrectionIndicator> <Broker> <Party> <Id>0001</Id> </Party> <Contact> <Description>sk</Description> <Telephone>01268343847</Telephone> <Email>mahesh.dwivedi@xchanging.com</Email> </Contact> </Broker> <Insurer> <Party> <Name>L</Name> </Party> </Insurer> <Contract> <BrokerReference>B0941SINGSMUCCG2</BrokerReference> </Contract> <ServiceProvider> <Contact> <Description>BALA8</Description> </Contact> </ServiceProvider> <AccountTransactionType>1</AccountTransactionType> <ItemsInSettlementGroupTotal> <Count>0</Count> </ItemsInSettlementGroupTotal> <Explanation>explanation</Explanation> <SettlementGroupReference></SettlementGroupReference> <Subaccount> <JvClassOfBusiness>M</JvClassOfBusiness> <ClaimEntry> <BrokerReference>B0941SINGSMUCCG2</BrokerReference> </ClaimEntry> <ReferredClaimMovement> <BrokerReference>TR</BrokerReference> </ReferredClaimMovement> <ns2:SupportingDocument> <ns2:DocumentId>13bc5b4a-8806-494f-85ea-d13e62e2764d</ns2:DocumentId> <ns2:DocumentVersion>1</ns2:DocumentVersion> <ns2:DocumentTypeCd>attorney_info_correspondence</ns2:DocumentTypeCd> <ns2:Description>cLAIM dOCUMENTR</ns2:Description> </ns2:SupportingDocument> </Subaccount> <Extension> <ns3:TechAccountExtension> <ns3:NoOfDocs>0</ns3:NoOfDocs> <ns3:SourceChannel>DRI</ns3:SourceChannel> <ns3:XisScanned>Y</ns3:XisScanned> <ns3:SlipType>B</ns3:SlipType> <ns3:PremiumFdoLpansCount>1</ns3:PremiumFdoLpansCount> <ns3:ApRpLpansCount>1</ns3:ApRpLpansCount> <ns3:SimSignReq>N</ns3:SimSignReq> <ns3:TreatyFDOStatement>FDO</ns3:TreatyFDOStatement> <ns3:PolicyType>Slip Policy</ns3:PolicyType> <ns3:TechAccountDocumentId>15790160-9898-aaaa-1313-9ab890aba752</ns3:TechAccountDocumentId> </ns3:TechAccountExtension> </Extension> </TechAccount> ";
		
		return techXml;
	}
	
	public static String techAccountXmlFOF(){
		String techXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><ns3:TechAccount xmlns:ns4=\"http://www.ACORD.org/Standards/AcordMsgSvc/1.4.0\" xmlns:ns3=\"http://www.ACORD.org/standards/Jv-Ins-Reinsurance/1\" xmlns:ns2=\"http://www.ACORD.org/Standards/AcordMsgSvc/1\" xmlns=\"http://www.xchanging.com/acord/extension\"><ns3:Broker><ns3:Party><ns3:Id>0001</ns3:Id><ns3:Name>L</ns3:Name></ns3:Party><ns3:Contact><ns3:Telephone>124545</ns3:Telephone><ns3:Email>bikas.das@xchanging.com</ns3:Email></ns3:Contact></ns3:Broker><ns3:Insurer><ns3:Party><ns3:Id>0001</ns3:Id><ns3:Name>L</ns3:Name></ns3:Party></ns3:Insurer><ns3:Contract><ns3:BrokerReference>B0001UMROBDRI01</ns3:BrokerReference></ns3:Contract><ns3:ServiceProvider><ns3:Contact><ns3:Telephone>124545</ns3:Telephone><ns3:Email>nisha.devi@xchanging.com</ns3:Email></ns3:Contact></ns3:ServiceProvider><ns3:AccountTransactionType>1</ns3:AccountTransactionType><ns3:Extension><TechAccountExtension><SourceChannel>DRI</SourceChannel><ApRpLpansCount>0</ApRpLpansCount><NoOfDocs>0</NoOfDocs><SimSignReq>N</SimSignReq><SlipType>E</SlipType><TechAccountDocumentId>08390160-9898-cccc-1313-9ab890aba752</TechAccountDocumentId><TechAccountDocumentReference></TechAccountDocumentReference><TechAccountDocumentVersion>1.0</TechAccountDocumentVersion></TechAccountExtension></ns3:Extension><ns3:Subaccount><ns3:JvClassOfBusiness>M</ns3:JvClassOfBusiness><ns3:ReferredClaimMovement><ns3:BrokerReference xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/></ns3:ReferredClaimMovement><ns3:ClaimEntry><ns3:BrokerReference xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/></ns3:ClaimEntry><ns2:SupportingDocument><ns2:DocumentId>886f360e-ff8c-4946-86b4-f843fe234f4e</ns2:DocumentId><ns2:DocumentVersion>1.0</ns2:DocumentVersion><ns2:DocumentVersionDtTime>2017-05-17T06:09:49.879+01:00</ns2:DocumentVersionDtTime><ns2:DocumentTypeCd>document_policy</ns2:DocumentTypeCd><ns2:Owner/></ns2:SupportingDocument></ns3:Subaccount></ns3:TechAccount>";
		
		return techXml;
	}
	
	public static String techAccountXmlFOFError(){
		String techXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><ns3:TechAccount xmlns:ns4=\"http://www.ACORD.org/Standards/AcordMsgSvc/1.4.0\" xmlns:ns3=\"http://www.ACORD.org/standards/Jv-Ins-Reinsurance/1\" xmlns:ns2=\"http://www.ACORD.org/Standards/AcordMsgSvc/1\" xmlns=\"http://www.xchanging.com/acord/extension\"><ns3:Broker><ns3:Party><ns3:Id>0001</ns3:Id><ns3:Name>L</ns3:Name></ns3:Party><ns3:Contact><ns3:Telephone>124545</ns3:Telephone><ns3:Email>bikas.das@xchanging.com</ns3:Email></ns3:Contact></ns3:Broker><ns3:Insurer><ns3:Party><ns3:Id>0001</ns3:Id><ns3:Name>L</ns3:Name></ns3:Party></ns3:Insurer><ns3:Contract><ns3:BrokerReference>B0001UMROBDRI01</ns3:BrokerReference></ns3:Contract><ns3:ServiceProvider><ns3:Contact><ns3:Telephone>124545</ns3:Telephone><ns3:Email>nisha.devi@xchanging.com</ns3:Email></ns3:Contact></ns3:ServiceProvider><ns3:AccountTransactionType>1</ns3:AccountTransactionType><ns3:Extension><TechAccountExtension><SourceChannel>DRI</SourceChannel><ApRpLpansCount>0</ApRpLpansCount><NoOfDocs>0</NoOfDocs><SimSignReq>N</SimSignReq><SlipType>E</SlipType><TechAccountDocumentId>08390160-9898-cccc-1313-9ab890aba752</TechAccountDocumentId><TechAccountDocumentReference></TechAccountDocumentReference><TechAccountDocumentVersion>1.0</TechAccountDocumentVersion></TechAccountExtension></ns3:Extension><ns3:Subaccount><ns3:JvClassOfBusiness>M</ns3:JvClassOfBusiness><ns3:ReferredClaimMovement><ns3:BrokerReference xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/></ns3:ReferredClaimMovement><ns3:ClaimEntry><ns3:BrokerReference xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/></ns3:ClaimEntry><ns2:SupportingDocument><ns2:DocumentId>886f360e-ff8c-4946-86b4-f843fe234f4e</ns2:DocumentId><ns2:DocumentVersion>1.0</ns2:DocumentVersion><ns2:DocumentVersionDtTime>2017-05-17T06:09:49.879+01:00</ns2:DocumentVersionDtTime><ns2:DocumentTypeCd>document_policy</ns2:DocumentTypeCd><ns2:Owner/></ns2:SupportingDocument><ns3:SupportingInformation><ns3:DocumentId>886f360e-ff8c-4946-86b4-f843fe234f4e</ns3:DocumentId><ns3:DocumentVersion>1.0</ns3:DocumentVersion><ns3:CreationDate></ns3:CreationDate></ns3:SupportingInformation></ns3:Subaccount></ns3:TechAccount>";
		
		return techXml;
	}
	
	public static String techAccountXmlFOFUpdate(){
		String techXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><ns3:TechAccount xmlns:ns4=\"http://www.ACORD.org/Standards/AcordMsgSvc/1.4.0\" xmlns:ns3=\"http://www.ACORD.org/standards/Jv-Ins-Reinsurance/1\" xmlns:ns2=\"http://www.ACORD.org/Standards/AcordMsgSvc/1\" xmlns=\"http://www.xchanging.com/acord/extension\"><ns3:Broker><ns3:Party><ns3:Id>0001</ns3:Id><ns3:Name>L</ns3:Name></ns3:Party><ns3:Contact><ns3:Telephone>124545</ns3:Telephone><ns3:Email>bikas.das@xchanging.com</ns3:Email></ns3:Contact></ns3:Broker><ns3:Insurer><ns3:Party><ns3:Id>0001</ns3:Id><ns3:Name>L</ns3:Name></ns3:Party></ns3:Insurer><ns3:CorrectionIndicator>2</ns3:CorrectionIndicator><ns3:ServiceProviderReference>ASSDLAZ</ns3:ServiceProviderReference><ns3:Contract><ns3:BrokerReference>B0001UMROBDRI01</ns3:BrokerReference></ns3:Contract><ns3:ServiceProvider><ns3:Contact><ns3:Telephone>124545</ns3:Telephone><ns3:Email>nisha.devi@xchanging.com</ns3:Email></ns3:Contact></ns3:ServiceProvider><ns3:AccountTransactionType>1</ns3:AccountTransactionType><ns3:Extension><TechAccountExtension><SourceChannel>DRI</SourceChannel><ApRpLpansCount>0</ApRpLpansCount><NoOfDocs>0</NoOfDocs><SimSignReq>N</SimSignReq><SlipType>E</SlipType><TechAccountDocumentId>08390160-9898-cccc-1313-9ab890aba752</TechAccountDocumentId><TechAccountDocumentReference></TechAccountDocumentReference><TechAccountDocumentVersion>1.0</TechAccountDocumentVersion></TechAccountExtension></ns3:Extension><ns3:Subaccount><ns3:JvClassOfBusiness>M</ns3:JvClassOfBusiness><ns3:ReferredClaimMovement><ns3:BrokerReference xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/></ns3:ReferredClaimMovement><ns3:ClaimEntry><ns3:BrokerReference xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/></ns3:ClaimEntry><ns2:SupportingDocument><ns2:DocumentId>886f360e-ff8c-4946-86b4-f843fe234f4e</ns2:DocumentId><ns2:DocumentVersion>1.0</ns2:DocumentVersion><ns2:DocumentVersionDtTime>2017-05-17T06:09:49.879+01:00</ns2:DocumentVersionDtTime><ns2:DocumentTypeCd>document_policy</ns2:DocumentTypeCd><ns2:Owner/></ns2:SupportingDocument><ns3:SupportingInformation><ns3:DocumentId>886f360e-ff8c-4946-86b4-f843fe234f4e</ns3:DocumentId><ns3:DocumentVersion>1.0</ns3:DocumentVersion><ns3:CreationDate></ns3:CreationDate></ns3:SupportingInformation></ns3:Subaccount></ns3:TechAccount>";
		
		return techXml;
	}
	
	private static String techAccountJSONOther1(){
		String techJson = "{\"TechAccount\": {\"Extension\": [{\"TechAccountExtension\": [{\"Status\": \"\",\"PolicyType\": \"\",\"SourceChannel\": \"ICN\",\"SimSigningRequired\": \"N\",\"TechAccountDocumentReference\": \"\",\"TreatyFdoStatement\": \"\",\"Aprplpans\": \"123\",\"Premiumfdolpans\": \"123\",\"TechAccountDocumentId\": \"\",\"SlipType\": \"Binding Authority\",\"TechAccountDocumentVersion\": \"\"}]}],\"Subaccount\": [{\"SupportingDocument\": [{\"Owner\": {\"partyName\": \"BKR/BKRDL\"},\"DocumentVersionDtTime\": \"2017-05-22T11:52:07.106+01:00\",\"DocumentId\": \"de5b3f09-98d2-4ff1-a2af-f451550b440b\",\"DocumentTypeCd\": \"Bordereau\",\"DocumentVersion\": \"1\"}],\"JvClassOfBusiness\": \"Marine\",\"ReferredClaimMovement\": {\"BrokerReference\": [\"\"]},\"ClaimEntry\": {\"BrokerReference\": [\"\"]}}],\"Broker\": [{\"Party\": {\"Id\": [{\"value\": \"\"}]},\"Contact\": {\"Description\": \"Jamie marfort\",\"Email\": \"jim@123.com\",\"Telephone\": \"4534650767850\"}}],\"AccountTransactionType\": [\"Premium Accounting\"],\"Explanation\": [\"\"],\"ServiceProvider\": [{\"Contact\": {\"Description\": \"\"}}],\"SettlementGroupReference\": [\"\"],\"CorrectionIndicator\": [\"First Submission\"],\"Contract\": [{\"BrokerReference\": \"B0001B0001NEWDATA\",\"ServiceProviderReference\": \"\"}],\"ItemsInSettlementGroupTotal\": [{\"Count\": 0}],\"Insurer\": [{\"Party\": {\"Name\": [\"\"]}}],\"ServiceProviderReference\": [\"\"]}}";
		return techJson;
	}
	
	public static String testClaimMoms(){
		String claimMomsXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><ClaimMovements xmlns:ns2=\"http://www.ACORD.org/standards/Jv-Ins-Reinsurance/1\" xmlns:ns3=\"http://www.ACORD.org/Standards/AcordMsgSvc/1\" xmlns:ns4=\"http://www.xchanging.com/acord/extension\" xmlns:ns5=\"http://www.ACORD.org/Standards/AcordMsgSvc/1.4.0\"><ns2:ClaimMovement><ns2:Contract><ns2:BrokerReference>B0001DROP3A7</ns2:BrokerReference></ns2:Contract><ns2:Claim><ns2:BrokerReference>B0001PRIGAR151</ns2:BrokerReference></ns2:Claim><ns2:BrokerReference>B0001TR01</ns2:BrokerReference><ns2:Extension><ns4:ClaimMovementExtension><ns4:BureauId>SY</ns4:BureauId><ns4:CorrelationData><ns4:RequestId>20170610183520512</ns4:RequestId></ns4:CorrelationData><ns4:Status>Success</ns4:Status></ns4:ClaimMovementExtension></ns2:Extension></ns2:ClaimMovement></ClaimMovements>";
		return claimMomsXml;
	}
	
/*	public static ClaimMovementExtension getClaimMovementExtension(ClaimMovementExtensionType claimMovementExtensionType) {

		List<Object> objects = claimMovementExtensionType.getAny();

			return ((JAXBElement<ClaimMovementExtension>) objects.get(0)).getValue();

	}*/
	
    public static Object getObjectFromQname(List<JAXBElement<?>> contents, String field) {
        if (org.apache.commons.collections.CollectionUtils.isEmpty(contents)) {
            return null;
        }

        for (JAXBElement<?> jaxbElement : contents) {
            QName qName = jaxbElement.getName();
            if (StringUtils.equalsIgnoreCase(field, qName.getLocalPart())) {
                return jaxbElement.getValue();
            }
        }
        return null;

    }
    
    
	@SuppressWarnings("unchecked")
	public static void processResponseFromCE(ClaimMovements claimMovements) {
		ContractType contractType = getContractType(claimMovements);
		List<Object> objects = claimMovements.getClaimMovementsTypes();
		String status;

			for (Object object : objects) {
				ClaimMovementType claimMovementType = ((JAXBElement<ClaimMovementType>) object).getValue();
				ClaimType claimType = getClaimType(claimMovementType);
				ClaimMovementExtensionType claimMovementExtensionType = (ClaimMovementExtensionType) getObjectFromQname(claimMovementType.getContent(), "Extension");
				if (claimMovementExtensionType != null) {
					ClaimMovementExtension claimMovementExtension = getClaimMovementExtension(claimMovementExtensionType);
				}
				
			}
		
	}
	
/*	public static ClaimType getClaimType(ClaimMovementType claimMovementType) {
		return (ClaimType) getObjectFromQname(claimMovementType.getContent(), "Claim");
	}
	
	public static ContractType getContractType(ClaimMovements claimMovements) {
		List<Object> objects = claimMovements.getClaimMovementsTypes();

			ClaimMovementType claimMovementType = ((JAXBElement<ClaimMovementType>) objects.get(0)).getValue();
			return (ContractType) getObjectFromQname(claimMovementType.getContent(), "Contract");

	}*/
	
	
	 @SuppressWarnings("unchecked")
	    public static ClaimMovementExtension getClaimMovementExtension(
	            ClaimMovementExtensionType claimMovementExtensionType) {
	        /*
	         * List<Object> objects = claimMovementExtensionType.getAny(); List<JAXBElement<?>> listExtension1 = new
	         * ArrayList<JAXBElement<?>>(); if (CollectionUtils.isNotEmpty(objects)) { for (Object extension : objects) { if
	         * (extension instanceof JAXBElement<?>) { listExtension1.add((JAXBElement<?>) extension); } }
	         * ClaimMovementExtension claimMovementExtension = (ClaimMovementExtension) getObjectFromQname(listExtension1,
	         * "ClaimMovementExtension"); // LOGGER.info(claimMovementExtension.toString()); LOGGER.info(
	         * "Object instance of ClaimMovementExtension {} ",objects.get(0).getValue() instanceof ClaimMovementExtension);
	         * LOGGER.info("object instance of : {}",objects.get(0).getValue().getclass()); // return
	         * ((JAXBElement<ClaimMovementExtension>) objects.get(0)).getValue(); return claimMovementExtension; } return
	         * null;
	         */
	        ClaimMovementExtension claimMovementExtension = null;
	        if (null != claimMovementExtensionType.getAny() && null != claimMovementExtensionType.getAny().get(0)) {
	            claimMovementExtension = ((JAXBElement<ClaimMovementExtension>) (claimMovementExtensionType.getAny()
	                    .get(0))).getValue();
	            System.out.println(claimMovementExtension);
	        }
	        return claimMovementExtension;
	    }

	    public static ClaimType getClaimType(ClaimMovementType claimMovementType) {
	        return (ClaimType) getObjectFromQname(claimMovementType.getContent(), "Claim");
	    }

	    private static void populateValue(String objectFromQname) {
	        System.out.println(objectFromQname);

	    }

	    @SuppressWarnings("unchecked")
	    private static ContractType getContractType(ClaimMovements claimMovements) {
	        List<Object> objects = claimMovements.getClaimMovementsTypes();

	            ClaimMovementType claimMovementType = ((JAXBElement<ClaimMovementType>) objects.get(0)).getValue();
	            return (ContractType) getObjectFromQname(claimMovementType.getContent(), "Contract");

	    }

	
}
