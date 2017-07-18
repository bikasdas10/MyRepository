/**
 * 
 */
package com.xchanging.jpa;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;

import org.acord.standards.acordmsgsvc._1.GENERALPARTYType;
import org.acord.standards.acordmsgsvc._1.SupportingDocumentType;
import org.acord.standards.jv_ins_reinsurance._1.AnyCountType;
import org.acord.standards.jv_ins_reinsurance._1.BrokerType;
import org.acord.standards.jv_ins_reinsurance._1.ClaimEntryType;
import org.acord.standards.jv_ins_reinsurance._1.ContactType;
import org.acord.standards.jv_ins_reinsurance._1.ContractExtensionType;
import org.acord.standards.jv_ins_reinsurance._1.ContractType;
import org.acord.standards.jv_ins_reinsurance._1.IdType;
import org.acord.standards.jv_ins_reinsurance._1.InsurerType;
import org.acord.standards.jv_ins_reinsurance._1.ObjectFactory;
import org.acord.standards.jv_ins_reinsurance._1.PartyType;
import org.acord.standards.jv_ins_reinsurance._1.ReferredClaimMovementType;
import org.acord.standards.jv_ins_reinsurance._1.ServiceProviderType;
import org.acord.standards.jv_ins_reinsurance._1.SubaccountType;
import org.acord.standards.jv_ins_reinsurance._1.TechAccountExtensionType;
import org.acord.standards.jv_ins_reinsurance._1.TechAccountType;
//import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;

import com.xchanging.acord.custom.objects.ExtensionWorkPackageRefs;
import com.xchanging.acord.extension.ContractExtension;
import com.xchanging.acord.extension.TechAccountExtension;
import com.xchanging.acord.util.AcordUtils;
import com.xchanging.model.AccessRightCdType;
import com.xchanging.model.ListActionCdType;
import com.xchanging.model.WorkPackageRefs;
import com.xchanging.xsb.premiumact.jaxb.workorder.DocumentType;
import com.xchanging.xsb.premiumact.jaxb.workorder.Documents;
import com.xchanging.xsb.premiumact.jaxb.workorder.MarketType;
import com.xchanging.xsb.premiumact.jaxb.workorder.SlipTypeType;
import com.xchanging.xsb.premiumact.jaxb.workorder.WorkOrder;

/**
 * @author 5002928
 *
 */
public class TechAccountXML {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		System.out.println(AcordUtils.getTechAccountType());
		
		WorkOrder workOrder = new WorkOrder();
		
		com.xchanging.xsb.premiumact.jaxb.workorder.ObjectFactory of = new com.xchanging.xsb.premiumact.jaxb.workorder.ObjectFactory();
		workOrder.setDocumentsType(of.createDocuments(getDocumentType()));
		workOrder.setSlipType(SlipTypeType.B);
		workOrder.setNumberInGroup("1");
		workOrder.setMarket(MarketType.C);
		workOrder.setUMR("UMR");
		workOrder.setUCR("UCR");
		workOrder.setUCR("TR");
		workOrder.setWorkOrderReference("wprs");
		ObjectFactory objectFactory = AcordUtils.getJvInsObjectFactory();

		TechAccountType techAccountType = objectFactory.createTechAccountType();

		
		AnyCountType count = objectFactory.createAnyCountType();
		count.setCount(Long.parseLong(workOrder.getNumberInGroup()));

		
        techAccountType.getContent().add(objectFactory.createServiceProviderReference(workOrder.getWorkOrderReference()));
        techAccountType.getContent().add(objectFactory.createAccountTransactionType(workOrder.getProcessingRequired()));
        techAccountType.getContent().add(objectFactory.createGroupReference(workOrder.getGroupReference()));
        techAccountType.getContent().add(objectFactory.createSettlementGroupReference(workOrder.getGroupReference()));
        techAccountType.getContent().add(objectFactory.createItemsInSettlementGroupTotal(count));
        populateInsurer(techAccountType,objectFactory,workOrder);
        populateBroker(techAccountType,objectFactory,workOrder);
        populateServiceProvider(techAccountType,objectFactory,workOrder);
        populateContract(techAccountType,objectFactory,workOrder);        
        techAccountType.getContent().add(objectFactory.createCorrectionIndicator(workOrder.getTypeOfSubmission()));
        
        populateSubAccount(techAccountType,objectFactory,workOrder);
        populateTechAccountExtension(techAccountType,objectFactory,workOrder);
        
        System.out.println(new JPATest().objectMarshalling(techAccountType,TechAccountType.class).toString());
        System.out.println(objectMarshalling(techAccountType,"application/json").toString());
        WorkPackageRefs listTa = new WorkPackageRefs();
        listTa.getTechAccountTypes().add(techAccountType);
        listTa.getTechAccountTypes().add(AcordUtils.getTechAccountType());
        System.out.println(new JPATest().objectMarshalling(listTa,WorkPackageRefs.class).toString());
        
        System.out.println("search xml:"+serachWorkpackageRes());
//        System.out.println(new JPATest().objectUnMarshalling(serachWorkpackageRes(),WorkPackageRefs.class));
        
/*		List<com.xchanging.model.AccessControl> listAcc = new ArrayList<>();
		
		com.xchanging.model.AccessControl acl = new com.xchanging.model.AccessControl();
		acl.setPartyId("1");
		acl.setPartyName("1abc");
		acl.setPartyRoleCd("1role");
		acl.setAccessRightCdType(AccessRightCdType.CHANGE );
		acl.setListActionCdType(ListActionCdType.ADD );
		
		com.xchanging.model.AccessControl acl1 = new com.xchanging.model.AccessControl();
		acl1.setPartyId("2");
		acl1.setPartyName("2abc");
		acl1.setPartyRoleCd("2role");
		acl1.setAccessRightCdType(AccessRightCdType.CHANGE );
		acl1.setListActionCdType(ListActionCdType.ADD );
		
		com.xchanging.model.AccessControl acl2 = new com.xchanging.model.AccessControl();
		acl2.setPartyId("3");
		acl2.setPartyName("3abc");
		acl2.setPartyRoleCd("3role");
		acl2.setAccessRightCdType(AccessRightCdType.CHANGE );
		acl2.setListActionCdType(ListActionCdType.ADD );
		listAcc.add(acl);
		listAcc.add(acl1);
		listAcc.add(acl2);
		
		System.out.println("acl list before:"+listAcc);
		
		com.xchanging.model.AccessControl acl3 = new com.xchanging.model.AccessControl();
		acl3.setPartyId("3");
		acl3.setPartyName("3abc");
		acl3.setPartyRoleCd("3role");
		acl3.setAccessRightCdType(AccessRightCdType.CHANGE );
		acl3.setListActionCdType(ListActionCdType.REMOVE );
		
		System.out.println(acl2.equals(acl3));
		System.out.println(acl2==acl3);
		
		listAcc.remove(acl2);
		
		System.out.println("acl list before:"+listAcc);
		

		JPATest jpa = new JPATest();
        System.out.println(jpa.objectUnMarshalling(getContract()));
        getContractExtension((ContractType)jpa.objectUnMarshalling(getContract1()));*/
        
        System.out.println(new JPATest().objectMarshalling(serachWorkpackageRefs(),ExtensionWorkPackageRefs.class).toString());
        System.out.println("search--"+serachWorkpackageResNew());
        System.out.println(new JPATest().objectUnMarshalling(serachWorkpackageResNew(),ExtensionWorkPackageRefs.class));
        
        
	}
	
	private static void getContractExtension(ContractType contract) {
		ContractExtension conExt = AcordUtils.getExtensionObjectFactory().createContractExtension();
		conExt.setIsSigningGenerated("Y");
		JAXBElement<ContractExtension>  jaxbConExt = AcordUtils.getExtensionObjectFactory().createContractExtension(conExt);
		ContractExtensionType  conExtType = AcordUtils.getJvInsObjectFactory().createContractExtensionType();
		conExtType.getAny().add(jaxbConExt);
		contract.setExtension(conExtType);
		
		System.out.println(new JPATest().objectMarshalling(contract, ContractType.class));
		
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
		partyInsurer.getContent().add(objectFactory.createName(workOrder.getMarket().value()));
		insurer.setParty(partyInsurer);
		
		techAccountType.getContent().add(objectFactory.createInsurer(insurer));
	}
	
	private static void populateServiceProvider(TechAccountType techAccountType,ObjectFactory objectFactory,WorkOrder workOrder){
		ServiceProviderType serviceProvider = objectFactory.createServiceProviderType();
		ContactType contactServiceProvider = objectFactory.createContactType();
		contactServiceProvider.setDescription(workOrder.getXISContactName());
		
		techAccountType.getContent().add(objectFactory.createServiceProvider(serviceProvider));
	}
	
	/**
	 * @param objectFactory
	 * @param techAccountType
	 */
	private static void populateClaimEntryType(ObjectFactory objectFactory, SubaccountType subAccount,WorkOrder workOrder) {
		ClaimEntryType claim = objectFactory.createClaimEntryType();
		claim.getContent().add(objectFactory.createBrokerReference(workOrder.getUCR()));
		
		subAccount.setClaimEntry(claim);
	}
	
	private static void populateReferredClaimMovement(ObjectFactory objectFactory, SubaccountType subAccount,WorkOrder workOrder) {
		ReferredClaimMovementType claimMovement = objectFactory.createReferredClaimMovementType();
		claimMovement.getContent().add(objectFactory.createBrokerReference(workOrder.getTR()));
		
		subAccount.setReferredClaimMovement(claimMovement);
	}
	
	private static void populateSubAccount(TechAccountType techAccountType,ObjectFactory objectFactory,WorkOrder workOrder){
		SubaccountType subAccount = objectFactory.createSubaccountType();
		subAccount.setJvClassOfBusiness(workOrder.getClassOfBusiness());
		populateClaimEntryType(objectFactory,subAccount,workOrder);
		populateReferredClaimMovement(objectFactory,subAccount,workOrder);
		 		
		Documents dcos = ((JAXBElement<Documents>)workOrder.getDocumentsType()).getValue();
		for(DocumentType doc : dcos.getDocument()){
			SupportingDocumentType supportingDocumentType = new SupportingDocumentType();
			supportingDocumentType.setDocumentId(doc.getID());
			supportingDocumentType.setDocumentReference(doc.getReference());
			supportingDocumentType.setDocumentTypeCd(doc.getType());
			supportingDocumentType.setDocumentVersion(doc.getVersion());
			supportingDocumentType.setDescription(doc.getDescription());
			supportingDocumentType.setOriginator(new GENERALPARTYType());
			
			subAccount.getSupportingInformationOrSupportingDocument().add(supportingDocumentType);
		}
			
		
		techAccountType.getContent().add(objectFactory.createSubaccount(subAccount));
	}
	
	private static void populateTechAccountExtension(TechAccountType techAccountType,ObjectFactory objectFactory,WorkOrder workOrder){
		TechAccountExtension extension = new TechAccountExtension();
		extension.setApplication(workOrder.getApplication());		
		extension.setSlipType(workOrder.getSlipType().value());
		extension.setPremiumFdoLpansCount(workOrder.getPremiumFDOLPANS());
		extension.setApRpLpansCount(workOrder.getAPRPLPANS());
		extension.setPresentationDate(workOrder.getPresentationDate());
		extension.setSimSignReq(workOrder.getSimSigningRequired());
		extension.setTreatyFDOStatement(workOrder.getTreatyFdoStatement());
		extension.setXisScanned(workOrder.getXisScanned());
		TechAccountExtensionType techAccountExtensionType = objectFactory.createTechAccountExtensionType();
		com.xchanging.acord.extension.ObjectFactory extensionOF = new com.xchanging.acord.extension.ObjectFactory();
		techAccountExtensionType.getAny().add(extensionOF.createTechAccountExtension(extension));
		
		
		techAccountType.getContent().add(objectFactory.createTechAccountTypeExtension(techAccountExtensionType));
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
	
	public static String getContract(){
		return  "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
		+"<Contract xmlns=\"http://www.ACORD.org/standards/Jv-Ins-Reinsurance/1\" xmlns:ns2=\"http://www.ACORD.org/Standards/AcordMsgSvc/1\" xmlns:ns3=\"http://www.xchanging.com/acord/extension\">"
		   +" <BrokerReference>B0001UMRSANJEEV09</BrokerReference>"
		   +" <ReinsurerRiskReference></ReinsurerRiskReference>"
		    +"<InsurerReference>TEST</InsurerReference>"
		    +"<Extension>"
		      +"<ns3:ContractExtension>"
		            +"<ns3:PolicyInceptionDateFrom>2016-01-01</ns3:PolicyInceptionDateFrom>"
		            +"<ns3:PolicyInceptionDateTo>2016-04-01</ns3:PolicyInceptionDateTo>"
		            +"<ns3:BrokerCode>0001</ns3:BrokerCode>"
		        +"</ns3:ContractExtension>"
		    +"</Extension>"
		+"</Contract>";
	}
	
	public static String getContract1(){
		return  "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
		+"<Contract xmlns=\"http://www.ACORD.org/standards/Jv-Ins-Reinsurance/1\" xmlns:ns2=\"http://www.ACORD.org/Standards/AcordMsgSvc/1\" xmlns:ns3=\"http://www.xchanging.com/acord/extension\">"
		   +" <BrokerReference>B0001UMRSANJEEV09</BrokerReference>"
		   +" <ReinsurerRiskReference></ReinsurerRiskReference>"
		    +"<InsurerReference>TEST</InsurerReference>"
		+"</Contract>";
	}
	
    public static Object getObjectFromQname(List<JAXBElement<?>> contents, String field) {
//        if (CollectionUtils.isEmpty(contents)) {
//            return null;
//        }

        for (JAXBElement<?> jaxbElement : contents) {
            QName qName = jaxbElement.getName();
            if (StringUtils.equalsIgnoreCase(field, qName.getLocalPart())) {
                return jaxbElement.getValue();
            }
        }
        return null;

    }
    
    private static String serachWorkpackageRes(){
    	String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"+
"<ns4:WorkPackageRefs xmlns:ns2=\"http://www.ACORD.org/standards/Jv-Ins-Reinsurance/1\" xmlns:ns4=\"http://www.xchanging.com/acord/extension\" xmlns:ns3=\"http://www.ACORD.org/Standards/AcordMsgSvc/1\">"+
	"<ns2:TechAccount>"+
		"<ns2:ServiceProviderReference>AADRMZL</ns2:ServiceProviderReference>"+
		"<ns2:Contract>"+
			"<ns2:BrokerReference>B0001M0101SN014</ns2:BrokerReference>"+
		"</ns2:Contract>"+
		"<ns2:CreationDate>2010-12-20</ns2:CreationDate>"+
		"<ns2:Extension>"+
		"<ns4:TechAccountExtension>"+
			"<ns4:LastUpdatedDate>2010-12-20</ns4:LastUpdatedDate>"+
		"</ns4:TechAccountExtension>"+
		"</ns2:Extension>"+
	"</ns2:TechAccount>"+
	"<ns2:TechAccount>"+
		"<ns2:ServiceProviderReference>AADRMZL</ns2:ServiceProviderReference>"+
		"<ns2:Contract>"+
			"<ns2:BrokerReference>B0001M0101SN014</ns2:BrokerReference>"+
		"</ns2:Contract>"+
		"<ns2:CreationDate>2010-12-20</ns2:CreationDate>"+
		"<ns2:Extension>"+
		"<ns4:TechAccountExtension>"+
			"<ns4:LastUpdatedDate>2010-12-20</ns4:LastUpdatedDate>"+
		"</ns4:TechAccountExtension>"+
		"</ns2:Extension>"+
	"</ns2:TechAccount>"+
	"</ns4:WorkPackageRefs>";
		
	return xml;
    }
    
    private static ExtensionWorkPackageRefs serachWorkpackageRefs(){
    	ExtensionWorkPackageRefs wprRefs = new ExtensionWorkPackageRefs();
    	wprRefs.setCount(0);
    	
    	wprRefs.getTechAccountTypes().add(AcordUtils.getJvInsObjectFactory().createTechAccount(AcordUtils.getTechAccountType()));
    	return wprRefs;
    }
    
    
    private static String serachWorkpackageResNew(){
    	return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><WorkPackageRefs xmlns:ns2=\"http://www.ACORD.org/Standards/AcordMsgSvc/1.4.0\" xmlns:ns3=\"http://www.ACORD.org/standards/Jv-Ins-Reinsurance/1\" xmlns:ns4=\"http://www.ACORD.org/Standards/AcordMsgSvc/1\" xmlns:ns5=\"http://www.xchanging.com/acord/extension\"><ns3:TechAccount><ns3:ServiceProviderReference>AGPUOLT</ns3:ServiceProviderReference><ns3:Contract><ns3:BrokerReference>B0001M0101SN014</ns3:BrokerReference></ns3:Contract><ns3:CreationDate>2010-12-20</ns3:CreationDate><ns3:Extension><ns5:TechAccountExtension><ns5:LastUpdatedDate>2010-12-20</ns5:LastUpdatedDate></ns5:TechAccountExtension></ns3:Extension></ns3:TechAccount><ns3:TechAccount><ns3:ServiceProviderReference>AGPUOLT</ns3:ServiceProviderReference><ns3:Contract><ns3:BrokerReference>B0001M0101SN014</ns3:BrokerReference></ns3:Contract><ns3:CreationDate>2010-12-20</ns3:CreationDate><ns3:Extension><ns5:TechAccountExtension><ns5:LastUpdatedDate>2010-12-20</ns5:LastUpdatedDate></ns5:TechAccountExtension></ns3:Extension></ns3:TechAccount><ns3:TechAccount><ns3:ServiceProviderReference>AADRMZL</ns3:ServiceProviderReference><ns3:Contract><ns3:BrokerReference>B0001M0101SN014</ns3:BrokerReference></ns3:Contract><ns3:CreationDate>2010-12-20</ns3:CreationDate><ns3:Extension><ns5:TechAccountExtension><ns5:LastUpdatedDate>2010-12-20</ns5:LastUpdatedDate></ns5:TechAccountExtension></ns3:Extension></ns3:TechAccount><ns3:TechAccount><ns3:ServiceProviderReference>AADRMZL</ns3:ServiceProviderReference><ns3:Contract><ns3:BrokerReference>B0001M0101SN014</ns3:BrokerReference></ns3:Contract><ns3:CreationDate>2010-12-20</ns3:CreationDate><ns3:Extension><ns5:TechAccountExtension><ns5:LastUpdatedDate>2010-12-20</ns5:LastUpdatedDate></ns5:TechAccountExtension></ns3:Extension></ns3:TechAccount><ns3:TechAccount><ns3:ServiceProviderReference>AADRMZL</ns3:ServiceProviderReference><ns3:Contract><ns3:BrokerReference>B0001M0101SN014</ns3:BrokerReference></ns3:Contract><ns3:CreationDate>2010-12-20</ns3:CreationDate><ns3:Extension><ns5:TechAccountExtension><ns5:LastUpdatedDate>2010-12-20</ns5:LastUpdatedDate></ns5:TechAccountExtension></ns3:Extension></ns3:TechAccount><Count>5</Count></WorkPackageRefs>";
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
}
