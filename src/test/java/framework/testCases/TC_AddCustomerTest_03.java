/**
 * 
 */
package framework.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import framework.pageObjects.AddCustomerPage;
import framework.pageObjects.LoginPage;

/**
 * @author manul.wickramanayaka
 *
 */
public class TC_AddCustomerTest_03 extends framework.BaseClass.BaseClass{
	
	@Test(retryAnalyzer = framework.utilities.RetryAnalyzer.class)
	public void addNewCustomer() throws InterruptedException, IOException
	{
		//--------------------------------------login------------------------------------------
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(userName);
		logger.info("User name is provided");
		lp.setPassword(password);
		logger.info("Passsword is provided");
		lp.clickLogin();
		
		Thread.sleep(3000);
		
		//--------------------------------Add new customer-------------------------------------
		AddCustomerPage addcust=new AddCustomerPage(driver);
		
		addcust.clickAddNewCustomer();
		
		logger.info("providing customer details....");
		
		addcust.custName("Manul");
		addcust.custgender("male");
		addcust.custdob("06","18","1999");
		Thread.sleep(3000);
		addcust.custaddress("SriLanka");
		addcust.custcity("Colombo");
		addcust.custstate("WesternProvince");
		//adding random pinNo
		String pinNo=randomeNum();
		addcust.custpinno(pinNo);
		addcust.custtelephoneno("94716789555");
		//adding random email
		String email=randomString()+"@gmail.com";
		addcust.custemailid(email);
		addcust.custpassword("manul@123");
		addcust.custsubmit();
		
		logger.info("customer details are added");
		
		Thread.sleep(5000);
		
		logger.info("validation started....");
		
		//Validate success message
		boolean result=driver.getPageSource().contains("Customer Registered Successfully!!!");
		
		if(result==true)
		{
			Assert.assertTrue(true);
			logger.info("Customer Registered Successfully!!!");
			logger.info("test case passed....");
			
		}
		else
		{
			logger.warn("Customer Registration Failed");
			logger.error("test case failed....");
			captureScreen(driver,"addNewCustomer");
			Assert.assertTrue(false);
		}
			
	}
	

}
