Feature: Badcsae_001

	Scenario: Test if website detect no password input
		Given BAD001 I have open the browser
		When BAD001 I open Instagram website
		Then BAD001 I Input username only
		Then BAD001 Login button cannot be click
		Then BAD001 I close the browser