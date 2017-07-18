
/**
 * 
 */
package com.xchanging.jpa;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.safehaus.uuid.UUID;
import org.safehaus.uuid.UUIDGenerator;

/**
 * @author 5002928
 *
 */
public class UUIDTest {
	public final static String EWP_SUBMISSION_TYPE_RESUBMISSION = "2";	//CorrectionIndicator=resubmission
	public final static String EWP_SUBMISSION_TYPE_WITHDRAWAL = "4";	//CorrectionIndicator=Withdraw
	public final static String EWP_SUBMISSION_TYPE_QUERY = "5";			//CorrectionIndicator=resubmission_query
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		System.out.println("uuid--"+generateUUID());
		BigDecimal big = new BigDecimal(112);
		System.out.println("big to int"+Integer.parseInt(big.toString()));
		
		Date currentDate = new Date();
		System.out.println(currentDate.getTime());
//		System.out.println(new java.sql.Date(currentDate.getTime()));;
		
		String workPackageReference = "";
		String submissionType = "4";
		if (!(StringUtils.isNotBlank(submissionType)
				&& (StringUtils.equals(EWP_SUBMISSION_TYPE_RESUBMISSION, submissionType)
						|| StringUtils.equals(EWP_SUBMISSION_TYPE_WITHDRAWAL, submissionType)
						|| StringUtils.equals(EWP_SUBMISSION_TYPE_QUERY, submissionType)))) {
			System.out.println("inside");
			
		}

	}
	
	  public static synchronized String generateUUID() {

	    	UUIDGenerator uuidGen = UUIDGenerator.getInstance();
			SecureRandom rnd = new SecureRandom();
			UUID uuid = uuidGen.generateRandomBasedUUID(rnd);
			return uuid.toString();

		}

}
