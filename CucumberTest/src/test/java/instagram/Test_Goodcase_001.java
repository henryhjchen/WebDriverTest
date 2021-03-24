package instagram;

import cucumber.annotation.en.Given; 
import cucumber.annotation.en.Then; 
import cucumber.annotation.en.When; 

/**
 * Check if I can login Instagram successfully
 * 
 * @author henry
 *
 */
public class Test_Goodcase_001 extends CommonHelper
{
	
	public Test_Goodcase_001(){}
	
	@Given("^GOOD001 I have open the browser$") 
	public void openBrowser()
	{
		try
		{
			initSeleniumInstance();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@When("^GOOD001 I open Instagram website$") 
	public void goToWeb()
	{
		try
		{
			openWebPage("https://www.instagram.com/accounts/login/?hl=en&source=auth_switcher", 10);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Then("^GOOD001 Do login$")
	public void doLogin()
	{
		try
		{
			
	        
			inputTextToField("username",prop.getProperty("username"));
			inputTextToField("password",prop.getProperty("password"));
	        click("//div[text()='Log In']");
	        
	        waitForSeconds(10);
	       
		}   
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Then("^GOOD001 Disable Save cookit$")
	public void disableSaveDatainBrowser()
	{
		try
		{
			if(waitForElement("//div[(text()='Save Your Login Info?')]",5))
			{
				click("//div/button[(text()='Not Now')]");
			}
		}
		catch(Exception e)
		{
			System.out.println(" -- Unable to find \"Disable save cookit\" button");
			System.out.println(" --   Skip verify this step");
		}
	}
	
	@Then("^GOOD001 Disable Notification$")
	public void disableNotification()
	{
		try
		{
			if(waitForElement("//div/button[(text()='Not Now')]",5))
	        {
	        	click("//div/button[(text()='Not Now')]");
	        }
			
		}
		catch(Exception e)
		{
			System.out.println(" -- Unable to find \"Disable Notification\" button");
			System.out.println(" --   Skip verify this step");
		}
	}
	
	@Then("^GOOD001 I close the browser$")
	public void closeBrowser()
	{
		try
		{
			selenium.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
}
