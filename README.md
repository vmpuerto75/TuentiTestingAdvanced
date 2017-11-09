**Tuenti Testing Advanaced Testsuite**

This repository contains the a Selenium test suite to execute the regression test on Tuenti website. At 09/11/2017 only contains some test for the login page in the LoginTestWD test suite. This is an advanced version that includes remote execution with Selenium grid, parallel execution, drivers downloading and screenshots. These are the implemented test and the testbook equivalence:

- loginWithMSISDNandPasswordOK: TC001	Login with registered phone number and password.
- loginWitEmailandPasswordOK: TC011	Login with registered email and password.
- loginWithMSISDNandInvalidPassword: TC002	Login with  registered phone number and invalid password.
- loginWithNotRegisteredMSISDN: TC003	Login with not registered phone number.
- loginWithMSISDNandBlankPassword: TC005	Login with registered phone number and blank password.

**EXECUTION**

Use Maven command to execute the test suite: mvn clean verify.

This will execute all the test case and the results will be located at target/failsafe-reports. Loading index.html you should see a summary of the results.

The project is configured for firefox, to change the browser, edit the pom.xml and change browser property. You can use firefox, chrome or IE.