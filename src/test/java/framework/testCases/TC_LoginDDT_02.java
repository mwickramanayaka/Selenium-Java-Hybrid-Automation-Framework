package framework.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import framework.pageObjects.LoginPage;
import framework.utilities.XLUtils;

public class TC_LoginDDT_02 extends framework.BaseClass.BaseClass{

	@Test(dataProvider="LoginData")
	public void loginDDT(String user,String pwd) throws InterruptedException
	{
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(user);
		logger.info("user name provided");
		lp.setPassword(pwd);
		logger.info("password provided");
		lp.clickLogin();
		
		Thread.sleep(3000);
		
		if(isAlertPresent()==true)
		{
			driver.switchTo().alert().accept();//close alert
			driver.switchTo().defaultContent();//focus on the login page
			Assert.assertTrue(false);
			logger.warn("Login failed");
			logger.error("login failed");
			
		}
		else
		{
			Assert.assertTrue(true);
			logger.info("Login passed");
			lp.clickLogout();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();//close logout alert
			driver.switchTo().defaultContent();
			
		}
		
		
	}
	
	
	public boolean isAlertPresent() //user defined method created to check alert is present or not
	{
		try {
			
		driver.switchTo().alert();
		return true;
		
		} catch(NoAlertPresentException e) {
			
			return false;
			
		}
		
	}
	
	
	@DataProvider(name="LoginData")
	String [][] getData() throws IOException
	{
		String path= ".\\TestDataFiles\\LoginData.xlsx";
		XLUtils XLUtils = new XLUtils(path);
		
		int rownum=XLUtils.getRowCount("Sheet1");
		int colcount=XLUtils.getCellCount("Sheet1",1);
		
		String logindata[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				logindata[i-1][j]=XLUtils.getCellData("Sheet1", i,j);//1 0
			}
				
		}
	return logindata;
	}
	
}
