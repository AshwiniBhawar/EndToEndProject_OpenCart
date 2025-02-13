package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.EditPersonalDetails;
import pageObjects.HomePage;
import pageObjects.LogOutPage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC006_EditPersonalDetails extends BaseClass{

	
		@Test
		public void editAccountInformation() {
			try {
				logger.info("****Starting TC006_EditPersonalDetails****");
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
				
				logger.info("****Validate MyAccount Information Page Header****");
				EditPersonalDetails ed= new EditPersonalDetails(driver);
				ed.clickEditAccountInfo();
				boolean targetPage2=ed.isMyAccountPageExists();
				Assert.assertEquals(targetPage2,true, "Login failed");
				
				logger.info("****Update Account Information****");
				ed.enterFirstname(prop.getProperty("FirstName"));
				//ed.enterFirstname(prop.getProperty("LastName"));
				//ed.enterFirstname(prop.getProperty("E-Mail"));
				//ed.enterFirstname(prop.getProperty("Telephone"));
				ed.clickContinue();
				
				logger.info("****Validate Message****");
				String msg=ed.validate_accountinfo_change_msg();
				Assert.assertEquals(msg, "Success: Your account has been successfully updated.");
				
				logger.info("****Logout****");
				LogOutPage lop=new LogOutPage(driver);
				lop.clickLogout();
				logger.info("****Finished TC006_EditPersonalDetails****");
				
				}
				catch(Exception e) {
					Assert.fail();
				}
				
	}

}
