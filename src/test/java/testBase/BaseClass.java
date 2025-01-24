package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public Properties prop;
	public FileInputStream fis;

	@BeforeClass(groups = { "Sanity", "Regression", "Master", "DataDriven" })
	@Parameters({ "os", "browser" })
	public void setUp(String os, String br) throws IOException {

		fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
		prop = new Properties();
		prop.load(fis);

		logger = LogManager.getLogger(this.getClass());

		if (prop.getProperty("execution_env").equalsIgnoreCase("remote")) 
		{
			DesiredCapabilities cap = new DesiredCapabilities();

			// os
			if (os.equalsIgnoreCase("windows")) 
			{
				cap.setPlatform(Platform.WIN10);
			} 
			else if (os.equalsIgnoreCase("mac")) 
			{
				cap.setPlatform(Platform.MAC);
			} 
			else if (os.equalsIgnoreCase("linux")) 
			{
				cap.setPlatform(Platform.LINUX);
			} 
			else
			{
				System.out.println("No matching os");
				return;
			}

			// browser
			switch (br.toLowerCase()) {
			case "chrome": cap.setBrowserName("chrome"); break;
			case "edge": cap.setBrowserName("MicrosoftEdge"); break;	
			case "firefox": cap.setBrowserName("firefox"); break;	
			default: System.out.println("Invalid browser name"); return;
			
			}

			driver = new RemoteWebDriver(new URL(prop.getProperty("hub_url")), cap);
		}

		if(prop.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch (br.toLowerCase()) 
			{
			case "chrome": driver = new ChromeDriver(); break;	
			case "edge": driver = new EdgeDriver(); break;
			case "firefox": driver = new FirefoxDriver(); break;
			default:System.out.println("Invalid browser name"); return;
			}
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();

	}

	@AfterClass(groups = { "Sanity", "Regression", "Master", "DataDriven" })
	public void tearDown() {
		driver.quit();
	}

	public String randomeString() {

		String generatedstring = RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}

	public String randomeNumberic() {

		String generatednumeric = RandomStringUtils.randomNumeric(10);
		return generatednumeric;
	}

	public String randomeAlphaNumeric() {

		String generatedalphanumeric = RandomStringUtils.randomAlphanumeric(8);
		return generatedalphanumeric;

		/*
		 * String generatednumeric=RandomStringUtils.randomNumeric(3); String
		 * generatedstring=RandomStringUtils.randomAlphabetic(4); return
		 * (generatedstring+"@"+generatednumeric);
		 */
	}

	public String captureScreen(String tname) {

		String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takescreenshot = (TakesScreenshot) driver;
		File sourceFile = takescreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timestamp + ".png";
		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);
		return targetFilePath;

	}

}
