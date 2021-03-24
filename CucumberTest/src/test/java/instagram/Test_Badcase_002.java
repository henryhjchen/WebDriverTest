package instagram;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

/**
 * Test if website detect wrong password
 * 
 * @author henry
 *
 */
public class Test_Badcase_002 extends CommonHelper
{
	public Test_Badcase_002() {}
	
	@Given("^BAD002 I have open the browser$") 
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
	
	@When("^BAD002 I open Instagram website$") 
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
	
	@Then("^BAD002 I Input wrong password$")
	public void doLogin()
	{
		try
		{
			inputTextToField("username",prop.getProperty("username"));
			inputTextToField("password","123456789");
	        click("//div[text()='Log In']");
	        
	        waitForSeconds(10);
	       
		}   
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Then("^BAD002 Website reject login$")
	public void rejectLogin()
	{
		try
		{
			waitForElement("//div/p[contains(text(),'Sorry, your password was incorrect.')]");
		
			System.out.println(" ----- Login rejected, match expectation");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Then("^BAD002 I close the browser$")
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
