package com.employeeapi.testCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC002_Get_Single_Employee extends TestBase{
	
	RequestSpecification httpRequest;
	Response response;
	
	@BeforeClass
	public void getEmployeeData() throws InterruptedException{
		logger.info("********** Started TC002_Get_Single_Employee ****************");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/employee/"+empID);
		
		Thread.sleep(5000);
	}
	
	@Test
	public void checkResponseBody()
	{
		String responseBody = response.getBody().asString();
		
		logger.info("Response body for Single Employe: ==> "+ responseBody);
		Assert.assertEquals(responseBody.contains(empID), true);
	}
	
	@Test
	void checkStatusCode()
	{
		logger.info("********** Check response Code **************");
		int StatusCode = response.getStatusCode();
		logger.info("Status code: ==>" + StatusCode);
		Assert.assertEquals(StatusCode,200);
	}
	
	@Test
	void checkResponseTime(){
		logger.info("******** Check Response Time *****************");
		long responseTime = response.getTime(); //get Response time
		logger.info("Response Time: ==>"+ responseTime);
		
		if(responseTime>2000){
			logger.warn("Response Time is greater than 2000");
			
			Assert.assertTrue(responseTime<10000);
		}
	}
	
	@Test
	void checkStatusLine()
	{
		logger.info("*********** Check Status line *******************");
		String statusLine = response.getStatusLine();
		logger.info("Status Line:  ==> "+statusLine );
		
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test
	void checkContentType()
	{
		logger.info("********** Check Content Type **********************");
		String contentType = response.header("Content-Type"); // Content type getting from header
		String serverType = response.header("Server"); //Server type getting from header
		String contentEncoding = response.header("Content-Encoding"); //getting the Content Encoding from the Header
		String contentLength = response.header("Content-Length");
		
		
		logger.info("Content Type: ==> "+ contentType);
		
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
	}
	@Test
	void contentLength()
	{
		String contentLength = response.header("Content-Length");
		Assert.assertTrue(Integer.parseInt(contentLength)<1500);
	}
	
	@Test
	void checkCookies()
	{
		logger.info("*********** Checking Cookies **********************");
		
		String cookie = response.getCookie("PHPSESSID");
		
	}
	
	
	@AfterClass
	void tearDown()
	{
		logger.info("*********** Finshed the test case TC002_Get_Employee **************");
	}
	
	
	
	
	

}
