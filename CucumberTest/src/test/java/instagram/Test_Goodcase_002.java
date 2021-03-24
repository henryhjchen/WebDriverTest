package instagram;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

/**
 * Check if password field is marked
 * 
 * @author henry
 *
 */
public class Test_Goodcase_002 extends CommonHelper
{
	public Test_Goodcase_002() {}
	
	@Given("^GOOD002 I have open the browser$") 
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
	
	@When("^GOOD002 I open Instagram website$") 
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
	
	@Then("^GOOD002 Check if password is masked$")
	public void doLogin()
	{
		try
		{
			isPasswordFieldMasked("password");
			
			System.out.println(" ---- Password field is masked");
	       
		}   
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Then("^GOOD002 I close the browser$")
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
