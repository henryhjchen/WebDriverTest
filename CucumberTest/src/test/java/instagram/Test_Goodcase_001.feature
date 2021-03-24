Feature: Goodcase_001

	Scenario: Check if I can login Instagram successfully
		Given GOOD001 I have open the browser
		When GOOD001 I open Instagram website
		Then GOOD001 Do login
		Then GOOD001 Disable Save cookit
		Then GOOD001 Disable Notification
		Then GOOD001 I close the browser