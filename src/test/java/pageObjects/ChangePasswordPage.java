package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChangePasswordPage extends BasePage {

	public ChangePasswordPage(WebDriver driver){
		super(driver);
	}
	
	@FindBy(xpath="//a[normalize-space()='Change your password']")
	WebElement linkChangePwd;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement newPwd;
	
	@FindBy(xpath="//input[@name='confirm']")
	WebElement confirmPwd;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement clkSubmit;
	
	@FindBy(xpath="//div[contains(@class,'alert-success')]")
	WebElement validatemsg;
	
	
	public void clickChangePassword() {
		linkChangePwd.click();
	}
	
	public void enterNewPassword(String newPassword) {
		try {
		newPwd.sendKeys(newPassword);
		}
		catch(Exception e) {
			
		}
	}
	
	public void confirmNewPassword(String confirmPassword) {
		try {
			confirmPwd.sendKeys(confirmPassword);
		}
		catch(Exception e) {
			
		}
	}
	
	public void clickSubmit() {
		clkSubmit.click();
	}
	
	public String validate_change_pwdmsg() {
		try {
			String msg=validatemsg.getText();
			return msg;
		}
		catch(Exception e){
			return e.getMessage();
		}
	}
	
	
	
}
