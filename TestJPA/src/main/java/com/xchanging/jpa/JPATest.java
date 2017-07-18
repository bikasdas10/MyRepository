/**
 * 
 */
package com.xchanging.jpa;

import static com.xchanging.acord.util.AcordUtils.getAcordJAXBContext;

import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;

import com.xchanging.model.EAccountTransactionDetailsDTO;
import com.xchanging.model.Login;
import com.xchanging.model.TblUmrTPAEvent;
import com.xchanging.model.TransactionDetails;
import com.xchanging.model.WorkOrder;
import com.xchanging.model.WorkOrderGroup;
import com.xchanging.xsb.premiumact.jaxb.workorder.response.DocumentType;
import com.xchanging.xsb.premiumact.jaxb.workorder.response.Documents;
import com.xchanging.xsb.premiumact.jaxb.workorder.response.MarketType;
import com.xchanging.xsb.premiumact.jaxb.workorder.response.ObjectFactory;
import com.xchanging.xsb.premiumact.jaxb.workorder.response.SlipTypeType;

/**
 * @author 5002928
 *
 */
public class JPATest {
	static Format formatter = new SimpleDateFormat("dd/MM/yyyy");
	static SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		
		  EntityManagerFactory emf =
//				    Persistence.createEntityManagerFactory("local-XE");
				  Persistence.createEntityManagerFactory("datapump-oracle-persistence-unit");
		  
/*		  Login login1 = new Login();
		  login1.setUsername("bik1");
		  login1.setPassword("pass1");
		  Login login2 = new Login();
		  login2.setUsername("bik2");
		  login2.setPassword("pass2");
		  Login login3 = new Login();
		  login3.setUsername("bik3");
		  login3.setPassword("pass3");
		  Login login4 = new Login();
		  login4.setUsername("bik4");
		  login4.setPassword("pass4");
		  
		  ArrayList<Login> ar = new ArrayList<>();
		  ar.add(login1);
		  ar.add(login2);
		  ar.add(login3);
		  ar.add(login4);
		  
		EntityManager entityManager = emf.createEntityManager();
		
		try {
			entityManager.getTransaction().begin();
			for(Login temp : ar){
			entityManager.persist(temp);
//			entityManager.flush();
			}
			entityManager.getTransaction().rollback();
//			
		} finally{
			if(entityManager != null){
//				entityManager.getTransaction().commit();
				entityManager.close();
			}
		}*/
		  
		  EntityManager entityManager = emf.createEntityManager();
			try {
				List<Integer> list = new ArrayList<>();
				list.add(0);
				list.add(2);
				entityManager.getTransaction().begin();
//				Query que =  entityManager.createQuery("FROM TblUmrTPAEvent where tpaUmr='B0001UMRSANJEEV09' and grantedAccessId IN (0,2)");
				Query que =  entityManager.createQuery("SELECT t FROM TblUmrTPAEvent t WHERE t.tpaUmr = '"+"B0001UMRSANJEEV09"+"' and t.tpaGrantedTo = '"+"0002"+"' and t.grantedAccessId IN (0,2)");
//				que.setParameter("tpaUmr", "B0001UMRSANJEEV09");
//				que.setParameter("tpaGrantedTo","0002");
//				que.setParameter("grantedAccessId",list);
//				Query que =  entityManager.createQuery("SELECT t FROM WorkOrderGroup t WHERE t.groupId = :id");
//				que.setParameter("id", 1);
				
				System.out.println(que.getResultList());
//				
			} finally{
				if(entityManager != null){
					entityManager.getTransaction().commit();
					entityManager.close();
				}
			}
/*		Query workOrderTransactionQuery = em
				.createQuery("SELECT t FROM TransactionDetails t WHERE trim(t.umr) = :umr and trim(t.ucr) = :ucr and trim(t.tr) = :tr")
				.setParameter("umr", "B0370JS9900182")
				.setParameter("ucr", "B0370JZ9900182002")
				.setParameter("tr", "B0370001");
		
		
		System.out.println("---BureaLeader--"+((TransactionDetails)(workOrderTransactionQuery.getResultList().get(0))).getBureaLeader());

	Query workOrderTransactionQuery1 = em
			.createQuery("SELECT t FROM EAccountTransactionDetailsDTO t WHERE trim(t.transactionDetailsCK.umr) = :umr and trim(t.transactionDetailsCK.ucr) = :ucr and trim(t.transactionDetailsCK.tr) = :tr")
			.setParameter("umr", "B0370JS9900182")
			.setParameter("ucr", "B0370JZ9900182002")
			.setParameter("tr", "B0370001");*/
		

	
//	em.close();
	
/*	try{
	System.out.println("xml----"+objectMarshalling(populateWorkOrderResponse(), WorkOrder.class).toString());
	}catch(Exception e)
	{
		e.printStackTrace();
	}*/
	}
	
	public static com.xchanging.xsb.premiumact.jaxb.workorder.response.WorkOrder populateWorkOrderResponse() throws Exception{

		ObjectFactory workOrderResponseObjectFactory = new ObjectFactory();
		com.xchanging.xsb.premiumact.jaxb.workorder.response.WorkOrder workOrderResponse = workOrderResponseObjectFactory.createWorkOrder();
		workOrderResponse.setTechAccountUUId("");
		workOrderResponse.setApplication("EACCOU");
		workOrderResponse.setWorkOrderUniqueId("20160915-122609");
		workOrderResponse.setUrgentReference("");
		workOrderResponse.setBrokerContactName("Christian Watson");
		workOrderResponse.setBrokerContactPhone("020 7933 2048");
		workOrderResponse.setBrokerContactEmail("Christian.Watson@uk.lockton.com");
		workOrderResponse.setBroker("0713");
		workOrderResponse.setUMR("B0713AVNLS1600829");
		workOrderResponse.setTypeOfSubmission("5");
		workOrderResponse.setMarket(MarketType.C);
		workOrderResponse.setClassOfBusiness("A");
		workOrderResponse.setSlipType(SlipTypeType.D);
		workOrderResponse.setTypeOfPolicy("");
		workOrderResponse.setProcessingRequired("1");
		workOrderResponse.setPremiumFDOLPANS("2");
		workOrderResponse.setAPRPLPANS("6");
		workOrderResponse.setXISContactName("");
		workOrderResponse.setAdditionalDetails("");
		workOrderResponse.setPresentationDate("2016-09-15T12:22:32.000Z");
		workOrderResponse.setWorkOrderReference("EBLNUJY");
		workOrderResponse.setGroupReference("");
		workOrderResponse.setNumberInGroup("0");
		workOrderResponse.setUCR("");
		workOrderResponse.setTR("");
		workOrderResponse.setSimSigningRequired("N");
		workOrderResponse.setTreatyFdoStatement("");
		workOrderResponse.setXisScanned("");
		
		workOrderResponseObjectFactory.createDocumentsType(getDocumentType());
		
		Documents documents = new Documents();
		documents.getDocument().add(getDocumentType());

		workOrderResponse.setDocumentsType(workOrderResponseObjectFactory.createDocuments(documents));
		
		workOrderResponse.setTrackerBarcode("REP01666655");
		workOrderResponse.setLogisticsInView("https://insuranceportal.xchanging.com/doctrack/DataCapture.jsp?barCode=REP01666655&amp;sourcepage=XisWorkNotPrintedTableTag");
		workOrderResponse.setLogisticsOutView("https://insuranceportal.xchanging.com/doctrack/Departures?barCode=REP01666655");
		workOrderResponse.setTechniciansView("https://insuranceportal.xchanging.com/doctrack/TransactionStatusArrivals?barCode=REP01666655");
		workOrderResponse.setWorkPackageView("https://repository.xchanging.com/worksitemp/dispatch?operation=package&amp;datasource=workspace&amp;objectId=!V3!MKTREPOS!C!F$84930!W$2463654!&amp;packageId=5692671");
		workOrderResponse.setPegaWO("EA-56365");
		return workOrderResponse;
	}
	
	public static DocumentType getDocumentType() throws Exception{
		DocumentType documentType = new DocumentType();
		documentType.setType("");
		documentType.setID("!V3!MKTREPOS!C!D$104985073!");
		documentType.setReference("");
		documentType.setVersion("");
		documentType.setDescription("");
		
		return documentType;
	}
	public static WorkOrder populateWorkOrder(){
		WorkOrder wo = new WorkOrder();
		wo.setUmr("B0001UMR123");
		wo.setGroupReference("B0001TESTCOM991");
		wo.setNoinGroup(2L);
		return wo;
	}
	public static WorkOrderGroup populateWorkOrderGroup(WorkOrder workOrder) throws ParseException{
		
		WorkOrderGroup workOrderGroup = null;
		if(StringUtils.isNotBlank(workOrder.getGroupReference())){
			workOrderGroup = new WorkOrderGroup();
			workOrderGroup.setGroupReference(workOrder.getGroupReference());
			workOrderGroup.setBrokerId(StringUtils.defaultIfBlank(workOrder.getBrokerCode(), StringUtils.substring(workOrder.getUmr(), 0, 5)));
			workOrderGroup.setNoInGroup(workOrder.getNoinGroup().intValue());
			workOrderGroup.setNoOfWOCreated(Integer.valueOf(1));
			Timestamp stamp = new Timestamp(System.currentTimeMillis());
			Date currentDate = new Date();
			workOrderGroup.setWoGroupCreatedDate(formatter.format(currentDate));
			workOrderGroup.setFirstWOCreatedDatetime(stamp);
			System.out.println(stamp);
			workOrderGroup.setNotified("P");
			if(StringUtils.equalsIgnoreCase(workOrder.getTrackerStatus(), "E")){
				workOrderGroup.setNotified("E");
			}
		}
		return workOrderGroup;
	}
	public static StringWriter objectMarshalling(final Object obj, final Class className) {
		StringWriter writer = new StringWriter();
		try {
			if (obj != null) {
				JAXBContext jaxbContext = JAXBContext.newInstance(className);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				jaxbMarshaller.marshal(obj, writer);
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return writer;
	}
	
	public  Object objectUnMarshalling(final String inputStream) {
        Object obj = null;
		try {
			Unmarshaller unmarshaller = getAcordJAXBContext().createUnmarshaller();
			obj = JAXBIntrospector.getValue(unmarshaller.unmarshal(new StringReader(inputStream)));
		} catch (JAXBException exception) {
			exception.printStackTrace();
		}
        
        return obj;
 }
    @SuppressWarnings("rawtypes")
    public Object objectUnMarshalling(final String strResponse, final Class className) throws JAXBException {
        JAXBContext jaxbContext = getAcordJAXBContext();
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return JAXBIntrospector.getValue(unmarshaller.unmarshal(new StringReader(strResponse)));
    }
}
