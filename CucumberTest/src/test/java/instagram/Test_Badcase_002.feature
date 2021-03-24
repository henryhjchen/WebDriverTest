Feature: Badcsae_002

	Scenario: Test if website detect wrong password
		Given BAD002 I have open the browser
		When BAD002 I open Instagram website
		Then BAD002 I Input wrong password
		Then BAD002 Website reject login
		Then BAD002 I close the browser