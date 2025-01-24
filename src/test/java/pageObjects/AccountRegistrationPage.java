package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegistrationPage extends BasePage{

	
	public AccountRegistrationPage(WebDriver driver){
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirmPassword;
	
	@FindBy(xpath="//input[@name='newsletter']")
	WebElement btnSubscribe;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkdPolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgAccountCreated;
	
	
	public void setFirstName(String fname) {
		txtFirstName.sendKeys(fname);
	}

	public void setLastName(String lname) {
		txtLastName.sendKeys(lname);
	}
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void setTelephone(String tel) {
		txtTelephone.sendKeys(tel);
	}
	
	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	
	public void setConfirmPassword(String pwd) {
		txtConfirmPassword.sendKeys(pwd);
	}
	
	public void setSubscribe() {
		btnSubscribe.click();
	}
	
	public void setPolicy() {
		chkdPolicy.click();
	}
	
	public void clickContinue() {
		btnContinue.click();
		
		//soln2
		//btnContinue.submit();
		
		//soln3
		//Actions act= new Actions(driver);
		//act.moveToElement(btnContinue).click().perform();
		
		//soln4
		//JavascriptExecutor js= (JavascriptExecutor)driver;
		//js.executeScript("arguments[0].click();", btnContinue);
		
		//soln5
		//btnContinue.sendKeys(Keys.RETURN);
		
		//soln6
		//WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		//wait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
		
	}
	
	public String getConfirmationMsg() {
		try {
		return(msgAccountCreated.getText());
		} 
		catch(Exception e) {
			return(e.getMessage());
		}
	}
	
}
