Feature: Badcsae_003

	Scenario: Test if website detect invalid char in username
		Given BAD003 I have open the browser
		When BAD003 I open Instagram website
		Then BAD003 I Input invalid char in username
		Then BAD003 Website reject login
		Then BAD003 I close the browser