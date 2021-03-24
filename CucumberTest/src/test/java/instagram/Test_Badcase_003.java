package instagram;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

/**
 * Test if website detect invalid char in username
 * 
 * @author henry
 *
 */
public class Test_Badcase_003 extends CommonHelper
{
	public Test_Badcase_003(){}
	
	@Given("^BAD003 I have open the browser$") 
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
	
	@When("^BAD003 I open Instagram website$") 
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
	
	@Then("^BAD003 I Input invalid char in username$")
	public void invalidCharInUsername()
	{
		try
		{
			inputTextToField("username","!@#$&%^$*@(@~!");
			inputTextToField("password","12345678");
			click("//div[text()='Log In']");
	        
	        waitForSeconds(10);
		}   
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Then("^BAD003 Website reject login$")
	public void rejectLogin()
	{
		try
		{
			waitForElement("//div/p[contains(text(),\"The username you entered doesn't belong to an account\")]");
		
			System.out.println(" ----- Login rejected, match expectation");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Then("^BAD003 I close the browser$")
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
