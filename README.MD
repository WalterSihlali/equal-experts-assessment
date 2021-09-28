# Equal Experts Automation Assessment
This is a Maven java project for automation of hotel booking scenarios on http://hotel-test.equalexperts.io/ using selenium and cucumber framework
## Prerequisites and installations
- Install java and maven
- Intellij or Eclipse IDE
- git clone repo to local space
- cd to pom.xml folder

## Running the automated tests
- mvn clean test
- To run test in headless mode - equal-experts.properties headless=yes

## Test Reports
- First / Latest report will be located under ./target/cucumber-reports
- Existing test reports will be moved to ./reports directory when a new one is generated
- Screenshot for fail test steps will be attached to the test report from ./target/cucumber-reports/screenshots directory
