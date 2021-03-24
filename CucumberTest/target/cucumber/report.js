$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri('instagram/Test_Badcase_001.feature');
formatter.feature({
  "line": 1,
  "name": "Badcsae_001",
  "description": "",
  "id": "badcsae-001",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "Test if website detect no password input",
  "description": "",
  "id": "badcsae-001;test-if-website-detect-no-password-input",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "BAD001 I have open the browser",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "BAD001 I open Instagram website",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "BAD001 I Input username only",
  "keyword": "Then "
});
formatter.step({
  "line": 7,
  "name": "BAD001 Login button cannot be click",
  "keyword": "Then "
});
formatter.step({
  "line": 8,
  "name": "BAD001 I close the browser",
  "keyword": "Then "
});
formatter.match({
  "location": "Test_Badcase_001.openBrowser()"
});
formatter.result({
  "duration": 2879293644,
  "status": "passed"
});
formatter.match({
  "location": "Test_Badcase_001.goToWeb()"
});
formatter.result({
  "duration": 1054849112,
  "status": "passed"
});
formatter.match({
  "location": "Test_Badcase_001.inputUsernameOnlyThenLogin()"
});
formatter.result({
  "duration": 295744253,
  "status": "passed"
});
formatter.match({
  "location": "Test_Badcase_001.buttonCannotClick()"
});
formatter.result({
  "duration": 24349801,
  "status": "passed"
});
formatter.match({
  "location": "Test_Badcase_001.closeBrowser()"
});
formatter.result({
  "duration": 387328755,
  "status": "passed"
});
formatter.uri('instagram/Test_Badcase_002.feature');
formatter.feature({
  "line": 1,
  "name": "Badcsae_002",
  "description": "",
  "id": "badcsae-002",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "Test if website detect wrong password",
  "description": "",
  "id": "badcsae-002;test-if-website-detect-wrong-password",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "BAD002 I have open the browser",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "BAD002 I open Instagram website",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "BAD002 I Input wrong password",
  "keyword": "Then "
});
formatter.step({
  "line": 7,
  "name": "BAD002 Website reject login",
  "keyword": "Then "
});
formatter.step({
  "line": 8,
  "name": "BAD002 I close the browser",
  "keyword": "Then "
});
formatter.match({
  "location": "Test_Badcase_002.openBrowser()"
});
formatter.result({
  "duration": 2410101373,
  "status": "passed"
});
formatter.match({
  "location": "Test_Badcase_002.goToWeb()"
});
formatter.result({
  "duration": 1105482947,
  "status": "passed"
});
formatter.match({
  "location": "Test_Badcase_002.doLogin()"
});
formatter.result({
  "duration": 575832863,
  "status": "passed"
});
formatter.match({
  "location": "Test_Badcase_002.rejectLogin()"
});
formatter.result({
  "duration": 896173357,
  "status": "passed"
});
formatter.match({
  "location": "Test_Badcase_002.closeBrowser()"
});
formatter.result({
  "duration": 379908985,
  "status": "passed"
});
formatter.uri('instagram/Test_Badcase_003.feature');
formatter.feature({
  "line": 1,
  "name": "Badcsae_003",
  "description": "",
  "id": "badcsae-003",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "Test if website detect invalid char in username",
  "description": "",
  "id": "badcsae-003;test-if-website-detect-invalid-char-in-username",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "BAD003 I have open the browser",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "BAD003 I open Instagram website",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "BAD003 I Input invalid char in username",
  "keyword": "Then "
});
formatter.step({
  "line": 7,
  "name": "BAD003 Website reject login",
  "keyword": "Then "
});
formatter.step({
  "line": 8,
  "name": "BAD003 I close the browser",
  "keyword": "Then "
});
formatter.match({
  "location": "Test_Badcase_003.openBrowser()"
});
formatter.result({
  "duration": 2401577255,
  "status": "passed"
});
formatter.match({
  "location": "Test_Badcase_003.goToWeb()"
});
formatter.result({
  "duration": 860061960,
  "status": "passed"
});
formatter.match({
  "location": "Test_Badcase_003.invalidCharInUsername()"
});
formatter.result({
  "duration": 606130117,
  "status": "passed"
});
formatter.match({
  "location": "Test_Badcase_003.rejectLogin()"
});
formatter.result({
  "duration": 21970922,
  "status": "passed"
});
formatter.match({
  "location": "Test_Badcase_003.closeBrowser()"
});
formatter.result({
  "duration": 375835405,
  "status": "passed"
});
formatter.uri('instagram/Test_Goodcase_001.feature');
formatter.feature({
  "line": 1,
  "name": "Goodcase_001",
  "description": "",
  "id": "goodcase-001",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "Check if I can login Instagram successfully",
  "description": "",
  "id": "goodcase-001;check-if-i-can-login-instagram-successfully",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "GOOD001 I have open the browser",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "GOOD001 I open Instagram website",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "GOOD001 Do login",
  "keyword": "Then "
});
formatter.step({
  "line": 7,
  "name": "GOOD001 Disable Save cookit",
  "keyword": "Then "
});
formatter.step({
  "line": 8,
  "name": "GOOD001 Disable Notification",
  "keyword": "Then "
});
formatter.step({
  "line": 9,
  "name": "GOOD001 I close the browser",
  "keyword": "Then "
});
formatter.match({
  "location": "Test_Goodcase_001.openBrowser()"
});
formatter.result({
  "duration": 2413538351,
  "status": "passed"
});
formatter.match({
  "location": "Test_Goodcase_001.goToWeb()"
});
formatter.result({
  "duration": 898713079,
  "status": "passed"
});
formatter.match({
  "location": "Test_Goodcase_001.doLogin()"
});
formatter.result({
  "duration": 561904458,
  "status": "passed"
});
formatter.match({
  "location": "Test_Goodcase_001.disableSaveDatainBrowser()"
});
formatter.result({
  "duration": 6919125917,
  "status": "passed"
});
formatter.match({
  "location": "Test_Goodcase_001.disableNotification()"
});
formatter.result({
  "duration": 248969101,
  "status": "passed"
});
formatter.match({
  "location": "Test_Goodcase_001.closeBrowser()"
});
formatter.result({
  "duration": 386075964,
  "status": "passed"
});
formatter.uri('instagram/Test_Goodcase_002.feature');
formatter.feature({
  "line": 1,
  "name": "Goodcase_002",
  "description": "",
  "id": "goodcase-002",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "Check if password field is marked",
  "description": "",
  "id": "goodcase-002;check-if-password-field-is-marked",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "GOOD002 I have open the browser",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "GOOD002 I open Instagram website",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "GOOD002 Check if password is masked",
  "keyword": "Then "
});
formatter.step({
  "line": 7,
  "name": "GOOD002 I close the browser",
  "keyword": "Then "
});
formatter.match({
  "location": "Test_Goodcase_002.openBrowser()"
});
formatter.result({
  "duration": 2320885635,
  "status": "passed"
});
formatter.match({
  "location": "Test_Goodcase_002.goToWeb()"
});
formatter.result({
  "duration": 1441391058,
  "status": "passed"
});
formatter.match({
  "location": "Test_Goodcase_002.doLogin()"
});
formatter.result({
  "duration": 289429690,
  "status": "passed"
});
formatter.match({
  "location": "Test_Goodcase_002.closeBrowser()"
});
formatter.result({
  "duration": 387525051,
  "status": "passed"
});
});