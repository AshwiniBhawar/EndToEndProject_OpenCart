package testCases;

import java.util.RandomAccess;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups={"Sanity","Master"})
	public void verify_login() {
		try {
		logger.info("****Starting TC002_LoginTest****");
		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		logger.info("****Enter Login Details****");
		LoginPage lp= new LoginPage(driver);
		lp.setEmail(prop.getProperty("email_address"));
		lp.setPassword(prop.getProperty("password"));
		lp.clickLogin();
		
		logger.info("****Validate Header****");
		MyAccountPage macc= new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExists();
		Assert.assertEquals(targetPage,true, "Login failed");
		
		logger.info("****Logout****");
		macc.clickLogout();
		logger.info("****Finished TC002_LoginTest****");
		
		}
		catch(Exception e) {
			Assert.fail();
		}
		
		
	}
	

}
