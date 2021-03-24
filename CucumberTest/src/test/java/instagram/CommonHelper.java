package instagram;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonHelper 
{
	protected WebDriver selenium = null;
	protected Properties prop = null;
	
	public CommonHelper()
	{
		//Load account information
		loadProperties("src/test/java/instagram/account.txt");
	}
	
	/**
	 * Initial webdriver instance with given options, then start the browser
	 * 
	 * @throws Exception
	 */
	protected void initSeleniumInstance() throws Exception
	{
		System.setProperty("webdriver.gecko.driver", "lib/geckodriver");
		
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("intl.accept_languages", "en-us");
		
		FirefoxOptions options = new FirefoxOptions();
		options.setCapability("marionette", true);
		options.setProfile(profile);
		
		selenium = new FirefoxDriver(options);
		
		
	}
	
	/**
	 * Open given url in browser
	 * 
	 * 
	 * @param url website URL
	 * @param waitSeconds waiting time
	 * @throws Exception
	 */
	protected void openWebPage(String url, int waitSeconds) throws Exception
	{
		selenium.get("https://www.instagram.com/accounts/login/?hl=en&source=auth_switcher");
		waitForSeconds(waitSeconds);
	}
	
	/**
	 * Click given web element 
	 * 
	 * @param locatorPath XPath of target element 
	 * @throws Exception
	 */
	protected void click(String locatorPath) throws Exception
	{
		selenium.findElement(By.xpath(locatorPath)).click();
	}
	
	/**
	 * Input given text to target element field
	 * 
	 * @param locatorPath XPath of target element 
	 * @param text Value you want to input
	 * @throws Exception
	 */
	protected void inputTextToField(String locatorPath, String text) throws Exception 
	{
		selenium.findElement(By.name(locatorPath)).sendKeys(text);
	}
	
	/**
	 * Check if element is set to "password" type
	 * 
	 * @param locatorPath XPath of target element 
	 * @return True if given element is "pasword" type
	 * @throws Exception
	 */
	protected boolean isPasswordFieldMasked(String locatorPath) throws Exception
	{
		WebElement element = selenium.findElement(By.name(locatorPath));
		return element.getAttribute("type") == "password";
	}
	
	/**
	 * Wait given seconds for element presented
	 * 
	 * @param second waiting time
	 * @throws Exception
	 */
	protected void waitForSeconds(int second) throws Exception
	{
		selenium.manage().timeouts().implicitlyWait(second, TimeUnit.SECONDS);
	}
	
	/**
	 * Waiting to see if given element is presented in 30 seconds
	 * 
	 * @param locatorPath XPath of target element 
	 * @return
	 */
	protected boolean waitForElement(String locatorPath)
	{
		return waitForElement(locatorPath, 30);
	}
	
	/**
	 * Waiting to see if given element is presented in given seconds
	 * 
	 * @param locatorPath XPath of target element 
	 * @aitSeconds waiting time
	 * @return
	 */
	protected boolean waitForElement(String locatorPath, int waitSeconds)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(selenium,waitSeconds);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorPath)));
			
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Load properties from given filePath
	 * 
	 * @param filePath path of file
	 */
	protected void loadProperties(String filePath)
	{
		try (InputStream input = new FileInputStream(filePath)) 
		{

            prop = new Properties();

            // load a properties file
            prop.load(input);

        } catch (Exception e) 
		{
            e.printStackTrace();
        }
	}
}
