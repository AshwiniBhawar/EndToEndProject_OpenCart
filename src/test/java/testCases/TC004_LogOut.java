package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.ChangePasswordPage;
import pageObjects.HomePage;
import pageObjects.LogOutPage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC004_LogOut extends BaseClass {

	@Test
	public void logOutTest() {
		try {
			logger.info("****Starting TC004_LogOut****");
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
			LogOutPage lop=new LogOutPage(driver);
			lop.clickLogout();
			
			logger.info("****Validate Logout Page Title****");
			String logoutSuccessTitle=driver.getTitle();
			Assert.assertEquals(logoutSuccessTitle, "Account Logout");
			lop.clickContinue();
			
			logger.info("****Validate Homepage Title****");
			String homepageTitle=driver.getTitle();
			Assert.assertEquals(homepageTitle, "Your Store");
			
			logger.info("****Finished TC004_LogOut****");
			
			}
			catch(Exception e) {
				Assert.fail();
			}
	}
}
