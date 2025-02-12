package testCases;
/*
 * Data is valid- login success- test pass- logout
 * Data is valid- login failed - test fail 
 * Data is invalid- login success- test fail- logout
 * Data is invalid- login failed - test pass 
 */

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LogOutPage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups={"DataDriven"})
	public void verify_loginDDT(String email, String password, String exp_result) {
		try {
			logger.info("****Starting TC003_LoginDDT****");
			
			//homepage
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			//login
			LoginPage lp = new LoginPage(driver);

			lp.setEmail(email);
			lp.setPassword(password);
			lp.clickLogin();
			
			//myaccount
			MyAccountPage macc = new MyAccountPage(driver);
			boolean targetPage = macc.isMyAccountPageExists();
			
			LogOutPage  lop=  new LogOutPage(driver);
			if (exp_result.equalsIgnoreCase("valid")) {
				if (targetPage = true) {
					lop.clickLogout();
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}

			}

			if (exp_result.equalsIgnoreCase("invalid")) {
				if (targetPage = true) {
					lop.clickLogout();
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(true);
				}

			}

		} catch (Exception e) {
			Assert.fail();
		}

		logger.info("****Finished TC003_LoginDDT****");
	}

}
