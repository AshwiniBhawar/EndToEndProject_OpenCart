package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups= {"Regression", "Master"})
	public void verify_account_registration() {
		try {
			logger.info("****Starting TC001_AccountRegistrationTest****");
			HomePage hpage = new HomePage(driver);
			hpage.clickMyAccount();
			logger.info("Clicked on MyAccount link");
			hpage.clickRegister();
			logger.info("Clicked on Register link");

			logger.info("Providing customer details");
			AccountRegistrationPage accreg = new AccountRegistrationPage(driver);
			accreg.setFirstName(randomeString().toUpperCase());
			accreg.setLastName(randomeString().toUpperCase());
			String email = randomeString() + "@gmail.com";
			accreg.setEmail(email);
			accreg.setTelephone(randomeNumberic());
			String password = randomeAlphaNumeric();
			accreg.setPassword(password);
			accreg.setConfirmPassword(password);
			accreg.setSubscribe();
			accreg.setPolicy(); 
			accreg.clickContinue();

			logger.info("Validating expected message");
			String confmsg = accreg.getConfirmationMsg();

			if (confmsg.equals("Your Account Has Been Created!")) {
				Assert.assertTrue(true);
			} else {
				logger.error("Test failed..");
				logger.debug("Debug logs..");
				Assert.assertTrue(false);
			}
		} catch (Exception e) {

			Assert.fail();
		}

		logger.info("****Finished TC001_AccountRegistrationTest****");
	}

}
