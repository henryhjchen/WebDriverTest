package instagram;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

/**
 * Test if website detect no password input
 * 
 * @author henry
 *
 */
public class Test_Badcase_001  extends CommonHelper
{
	public Test_Badcase_001(){}
	
	@Given("^BAD001 I have open the browser$") 
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
	
	@When("^BAD001 I open Instagram website$") 
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
	
	@Then("^BAD001 I Input username only$")
	public void inputUsernameOnlyThenLogin()
	{
		try
		{
			inputTextToField("username",prop.getProperty("username"));
		}   
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Then("^BAD001 Login button cannot be click$")
	public void buttonCannotClick()
	{
		try
		{
			click("//div[text()='Log In']");
		}
		catch(Exception e)
		{
			System.out.println(" ----- Login button can't be click, match expectation");
		}
	}
	
	@Then("^BAD001 I close the browser$")
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
