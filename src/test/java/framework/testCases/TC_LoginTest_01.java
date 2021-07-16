/**
 * 
 */
package framework.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import framework.pageObjects.LoginPage;

/**
 * @author manul.wickramanayaka
 *
 */
public class TC_LoginTest_01 extends framework.BaseClass.BaseClass {

	@Test
	public void loginTest() throws IOException {

		LoginPage lp = new LoginPage(driver);

		lp.setUserName(userName);
		logger.info("username entered");
		lp.setPassword(password);
		logger.info("password entered ");
		lp.clickLogin();
		
		if (driver.getTitle().equals("Guru99 Bank Manager HomePage")) {
			Assert.assertTrue(true);
			logger.info("title matched");
			logger.info("login test passed");
			
		}else {
			logger.warn("expected title not matched with the actual title");
			logger.error("login test failed");
			Assert.assertTrue(false);
			
		}

	}
}