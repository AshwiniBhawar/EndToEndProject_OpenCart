package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPersonalDetails extends BasePage {

	public EditPersonalDetails(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath ="//a[normalize-space()='Edit your account information']")
	WebElement clickEditAccInfo;
	
	@FindBy(xpath = "//h1[text()='My Account Information']")
	WebElement msgHeading;

	@FindBy(xpath = "//input[@name='firstname']")
	WebElement editFirstName;

	@FindBy(xpath = "//input[@name='lastname']")
	WebElement editLastName;

	@FindBy(xpath = "//input[@name='email']")
	WebElement editEmail;

	@FindBy(xpath = "//input[@name='telephone']")
	WebElement editPhone;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement continuebtn;
	
	@FindBy(xpath="//div[contains(@class,'alert-success')]")
	WebElement validatemsg;
	
	public void clickEditAccountInfo() {
		clickEditAccInfo.click();
	}

	public void enterFirstname(String value) {
		editFirstName.clear();
		editFirstName.sendKeys(value);
	}

	public void enterLastname(String value) {
		editLastName.sendKeys(value);
	}

	public void enterEmailname(String value) {
		editEmail.sendKeys(value);
	}

	public void enterPhonename(String value) {
		editPhone.sendKeys(value);
	}
	
	public void clickContinue() {
		continuebtn.click();
	}
	
	public String validate_accountinfo_change_msg() {
		try {
			String msg=validatemsg.getText();
			return msg;
		}
		catch(Exception e){
			return e.getMessage();
		}
	}
	
	public boolean isMyAccountPageExists() {
		try {
		return (msgHeading.isDisplayed());
		}
		catch(Exception e) {
			return false;
		}
	}

}
