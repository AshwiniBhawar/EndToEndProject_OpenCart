package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogOutPage extends BasePage {

	public LogOutPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//div[@class='list-group']//a[normalize-space()='Logout']")
	WebElement lnkLogout;
	

	@FindBy(xpath="//a[normalize-space()='Continue']")
	WebElement logoutcontinue;
	
	public void clickLogout() {
		lnkLogout.click();
	}
	
	public void clickContinue() {
		logoutcontinue.click();
	}
}
