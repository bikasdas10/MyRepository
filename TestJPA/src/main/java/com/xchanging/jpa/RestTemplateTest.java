package com.xchanging.jpa;


import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_XML;

import java.util.Arrays;

import org.acord.standards.jv_ins_reinsurance._1.ContractType;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;


public class RestTemplateTest {

	public static void main(String[] args) {
		
		System.out.println(getContractDetailsService(null,null));
	}
	
	
	public static ContractType getContractDetailsService(final String userId, final String accountCode) {


		try {
			RestTemplate restTemplate = new RestTemplate();
			/*
			 * Appends umr to IMRCE Service URL
			 */
			final String url = "http://imrcetsb.xchanging.com/api/1/riskrefs/B0001M0101SN014";

			/*
			 * Gets the HttpHeader with "Accept" and "Content-Type" headers with
			 * MIME Type "application/xml" already added.
			 * 
			 * Adds headers "UserId" and "AccountCode"
			 */
			 HttpHeaders headers = createBasicHttpHeader();
			headers.set("UserId", userId);
			headers.set("AccountCode", accountCode);
			
			if(StringUtils.isBlank(userId)){
				headers.set("AccountType", "System");
			}
			
			//Remove after testing
//			headers.set("UserId", StringUtils.EMPTY);
//			headers.set("AccountCode", StringUtils.EMPTY);
//			headers.set("AccountType", "System");
//			headers.add("AccountType", "abc");
			
			System.out.println("headers:"+ headers.toString());
			final HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
			/*
			 * Invokes the Rest Service with specific url, Request Method GET,
			 * Headers and Response Class type
			 */
			final ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

			int responseCode = res.getStatusCode().value();
			System.out.println("getContractDetailsService ResponseCode:"+ responseCode);
			if (OK.value() == responseCode) {
				// In case of 200 response code
				System.out.println("response:"+res.getBody());
			}

		} catch (HttpClientErrorException | HttpServerErrorException httpErrorEx) {
			httpErrorEx.printStackTrace();
		} catch (ResourceAccessException resourceAccessException) {
			// when services are not available
			// In case of Exception it send a mail to support team
			resourceAccessException.printStackTrace();

		}

		System.out.println("[END: method :  IMRContentEngineServiceImpl.getContractDetailsService ]");

		return null;

	}
	
	private static HttpHeaders createBasicHttpHeader() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Arrays.asList(APPLICATION_XML));
		httpHeaders.setContentType(APPLICATION_XML);
		return httpHeaders;
	}
}
