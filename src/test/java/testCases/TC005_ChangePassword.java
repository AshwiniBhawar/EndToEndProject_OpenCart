package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.ChangePasswordPage;
import pageObjects.HomePage;
import pageObjects.LogOutPage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC005_ChangePassword extends BaseClass{

	
	@Test
	public void changePassword() {
		try {
		logger.info("****Starting TC005_ChangePassword****");
		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		logger.info("****Enter Login Details****");
		LoginPage lp= new LoginPage(driver);
		lp.setEmail(prop.getProperty("email_address"));
		lp.setPassword(prop.getProperty("password"));
		lp.clickLogin();
		
		logger.info("****Validate MyAccount Page Header****");
		MyAccountPage macc= new MyAccountPage(driver);
		boolean targetPage1=macc.isMyAccountPageExists();
		Assert.assertEquals(targetPage1,true, "Login failed");
		
		logger.info("****Validate Change Password Page Header****");
		ChangePasswordPage cp= new ChangePasswordPage(driver);
		cp.clickChangePassword();
		boolean targetPage2=macc.isMyAccountPageExists();
		Assert.assertEquals(targetPage2,true, "Login failed");
		
		logger.info("****Change Password****");
		cp.enterNewPassword(prop.getProperty("new_password"));
		cp.confirmNewPassword(prop.getProperty("confirm_password"));
		cp.clickSubmit();
		
		logger.info("****Change Password Success Message****");
		String actualmsg=cp.validate_change_pwdmsg();
		Assert.assertEquals(actualmsg, "Success: Your password has been successfully updated.");
		
		logger.info("****Logout****");
		LogOutPage lop=new LogOutPage(driver);
		lop.clickLogout();
		logger.info("****Finished TC005_ChangePassword****");
		
		}
		catch(Exception e) {
			Assert.fail();
		}
		
	}
}
