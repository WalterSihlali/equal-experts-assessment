package hotelBooking;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import freemarker.log.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;

public class HotelBookingSteps extends BaseUtilities {

    private static Logger logger = Logger.getLogger(HotelBookingSteps.class.getName());


    @Given("^user launches the hotel booking website$")
    public void user_launches_the_hotel_booking_website() {
        try {
            driver.get(appURL);
        } catch (WebDriverException ex) {
            logger.info("Element not found", ex);
        }
    }

    @When("^user enter new booking \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
    public void user_enter_new_booking_something_something_and_something(String name, String surname, String price) {
        try {
            WebElement nameTextField = driver.findElement(By.id(PageObjects.FIRST_NAME_TEXTFIELD));
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView();", nameTextField);

            waitForElement(By.id(PageObjects.FIRST_NAME_TEXTFIELD));
            driver.findElement(By.id(PageObjects.FIRST_NAME_TEXTFIELD)).sendKeys(name);
            driver.findElement(By.id(PageObjects.SURNAME_TEXT_FIELD)).sendKeys(surname);
            driver.findElement(By.id(PageObjects.PRICE_TEXTFIELD)).sendKeys(price);

        } catch (NoSuchElementException ex) {
            logger.info("Element not found", ex);
        }
    }

    @Then("^user is on hotel booking landing page$")
    public void the_user_can_see_hotel_booking_form_title() {
        try {
            String landingPageTitle = driver.getTitle();
            Assert.assertEquals(getConfigPropertyValue(propertyFile, "landing_page_title"), landingPageTitle);
        } catch (NoSuchElementException ex) {
            logger.info("Element not found", ex);
        }
    }

    @Then("^new booking details \"([^\"]*)\" are captured successfully$")
    public void new_booking_details_are_captured_successfully(String name) {
        try {
            String latestName = driver.findElement(By.cssSelector(PageObjects.NAME_ENTRY)).getText();
            Assert.assertEquals(name, latestName);

        } catch (NoSuchElementException ex) {
            logger.info("Element not found", ex);
        }
    }

    @And("^user can see details header for the booking$")
    public void user_can_see_details_header_for_the_booking() {
        try {
            int firstNameLabels = driver.findElements(By.cssSelector(PageObjects.FIRST_NAME_LABEL)).size();
            int surnameLabels = driver.findElements(By.cssSelector(PageObjects.SURNAME_LABEL)).size();
            int priceLabels = driver.findElements(By.cssSelector(PageObjects.PRICE_LABEL)).size();
            int depositLabels = driver.findElements(By.cssSelector(PageObjects.DEPOSIT_LABEL)).size();
            int checkinLabels = driver.findElements(By.cssSelector(PageObjects.CHECK_IN_DATE_LABEL)).size();
            int checkoutLabels = driver.findElements(By.cssSelector(PageObjects.CHECK_OUT_DATE_LABEL)).size();

            Assert.assertEquals(1, firstNameLabels);
            Assert.assertEquals(1, surnameLabels);
            Assert.assertEquals(1, priceLabels);
            Assert.assertEquals(1, depositLabels);
            Assert.assertEquals(1, checkinLabels);
            Assert.assertEquals(1, checkoutLabels);

        } catch (NoSuchElementException ex) {
            logger.info("Element not found", ex);
        }
    }

    @And("^user select deposit option \"([^\"]*)\"$")
    public void user_select_deposit_option_something(String deposit) {
        try {

            if (deposit.equalsIgnoreCase("false")) {
                driver.findElement(By.id(PageObjects.DEPOSIT_TEXTFIELD)).click();
                driver.findElement(By.cssSelector(PageObjects.DEPOSIT_FALSE)).click();
            }

        } catch (NoSuchElementException ex) {
            logger.info("Element not found", ex);
        }
    }

    @And("^user select checkin and checkout date$")
    public void user_select_checkin_something_and_checkout_something_date() {
        try {
            driver.findElement(By.id(PageObjects.CHECKIN_TEXTFIELD)).click();
            driver.findElement(By.cssSelector(PageObjects.CHECK_IN_NEXT_MONTH)).click();
            driver.findElement(By.cssSelector(PageObjects.CHECK_IN_DAY)).click();

            driver.findElement(By.id(PageObjects.CHECKOUT_TEXTFIELLD)).click();
            driver.findElement(By.cssSelector(PageObjects.CHECK_OUT_NEXT_MONTH)).click();
            driver.findElement(By.cssSelector(PageObjects.CHECK_OUT_DAY)).click();

        } catch (NoSuchElementException ex) {
            logger.info("Element not found", ex);
        }
    }

    @And("^user save booking details$")
    public void user_save_booking_details() {
        try {

            driver.findElement(By.cssSelector(PageObjects.SAVE_BUTTON)).click();

        } catch (NoSuchElementException ex) {
            logger.info("Element not found", ex);
        }
    }


    @When("^user delete the new booking \"([^\"]*)\"$")
    public void user_delete_the_new_booking(String name) {
        try {

            driver.navigate().refresh();

            String latestName = driver.findElement(By.cssSelector(PageObjects.NAME_ENTRY)).getText();
            if (latestName.equalsIgnoreCase(name)) {
                driver.findElement(By.cssSelector(PageObjects.NEW_ENTRY_DELETE)).click();
            }
        } catch (NoSuchElementException ex) {
            logger.info("Element not found", ex);
        }
    }

    @Then("^booking is deleted successfully \"([^\"]*)\"$")
    public void booking_is_deleted_successfully(String name) {
        try {
            String latestName = driver.findElement(By.cssSelector(PageObjects.NAME_ENTRY)).getText();
            Assert.assertNotEquals(latestName, name);

        } catch (NoSuchElementException ex) {
            logger.info("Element not found", ex);
        }
    }

}
