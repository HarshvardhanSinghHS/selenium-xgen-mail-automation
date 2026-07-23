package com.xgenplus.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.xgenplus.base.BaseClass;
import com.xgenplus.pages.ForgotEmailPage;
import com.xgenplus.utils.TestDataReader;

public class ForgotEmailTest extends BaseClass {

    private static final Logger log = LogManager.getLogger(ForgotEmailTest.class);

    @Test(priority = 4, description = "Verify error message when mobile number is not provided")
    public void testForgotEmailWithoutMobileNumber() throws InterruptedException {

        test = extent.createTest("Forgot Email - No Mobile Number", "Verify error when mobile number is empty");

        log.info("========== Forgot Email Test Started: No Mobile Number ==========");
        test.info("Test execution started");

        ForgotEmailPage forgotEmail = new ForgotEmailPage(driver, wait);

        // Step 1: Switch to frame
        log.info("Switching to top frame for Forgot Email page");
        test.info("Step 1: Switch to top frame");
        driver.switchTo().frame("topFrame");

        // Step 2: Open Forgot Password link
        log.info("Clicking on 'Forgot Password' link");
        test.info("Step 2: Click 'Forgot Password' link");
        forgotEmail.openForgotPasswordLink();

        // Step 3: Click Next without entering mobile
        log.info("Clicking Next without entering mobile number");
        test.info("Step 3: Click 'Next' button without mobile number");
        forgotEmail.clickNextBtn();

        // Step 4: Verify error message
        log.info("Verifying error message for empty mobile number");
        test.info("Step 4: Verify error message 'Please Provide Mobile Number!'");
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mobileError")));
        String actualError = errorElement.getText().trim();
        String expectedError = "Please Provide Mobile Number!";

        Assert.assertTrue(actualError.contains(expectedError),
                "Test failed: Expected error message not shown. Found: " + actualError);

        log.info("Test passed: Correct error message displayed for empty mobile number");
        test.pass("Step 4 Passed: Error message verified for empty mobile number");
    }

    @Test(priority = 5, description = "Verify error message for non-existent mobile number")
    public void testForgotEmailWithInvalidMobileNumber() throws InterruptedException {

        test = extent.createTest("Forgot Email - Invalid Mobile Number", "Verify error when mobile number does not exist");

        log.info("========== Forgot Email Test Started: Invalid Mobile Number ==========");
        test.info("Test execution started");

        String invalidMobile = TestDataReader.getData("invalidMobileNumber");
        ForgotEmailPage forgotEmail = new ForgotEmailPage(driver, wait);

        // Step 1: Switch to frame
        log.info("Switching to top frame for Forgot Email page");
        test.info("Step 1: Switch to top frame");
        driver.switchTo().frame("topFrame");

        // Step 2: Open Forgot Password link
        log.info("Clicking on 'Forgot Password' link");
        test.info("Step 2: Click 'Forgot Password' link");
        forgotEmail.openForgotPasswordLink();

        // Step 3: Switch back and again to frame for input
        driver.switchTo().defaultContent();
        driver.switchTo().frame("topFrame");

        // Step 4: Enter invalid mobile number
        log.info("Entering invalid mobile number: {}", invalidMobile);
        test.info("Step 3: Enter invalid mobile number");
        forgotEmail.submitMobileNumber(invalidMobile);

        // Step 5: Click Next
        log.info("Clicking Next button");
        test.info("Step 4: Click 'Next' button");
        forgotEmail.clickNextBtn();

        // Step 6: Verify error message
        log.info("Verifying error message 'Mobile Number not Exists!'");
        test.info("Step 5: Verify error message for non-existent mobile number");
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mobileError")));
        String actualError = errorElement.getText().trim();
        String expectedError = "Mobile Number not Exists!";

        Assert.assertTrue(actualError.contains(expectedError),
                "Test failed: Expected error message not shown. Found: " + actualError);

        log.info("Test passed: Correct error message displayed for non-existent mobile number");
        test.pass("Step 5 Passed: Error message verified for non-existent mobile number");
    }

    @Test(priority = 6, description = "Verify error message for mobile number exceeding 10 digits")
    public void testForgotEmailWithLongMobileNumber() throws InterruptedException {

        test = extent.createTest("Forgot Email - Mobile Number Too Long", "Verify error when mobile number exceeds 10 digits");

        log.info("========== Forgot Email Test Started: Mobile Number Too Long ==========");
        test.info("Test execution started");

        String longMobile = TestDataReader.getData("invalidMobileNumberTooLong");
        ForgotEmailPage forgotEmail = new ForgotEmailPage(driver, wait);

        driver.switchTo().frame("topFrame");
        forgotEmail.openForgotPasswordLink();
        driver.switchTo().defaultContent();
        driver.switchTo().frame("topFrame");

        log.info("Entering long mobile number: {}", longMobile);
        test.info("Step 1: Enter mobile number exceeding 10 digits");
        forgotEmail.submitMobileNumber(longMobile);

        log.info("Clicking Next button");
        test.info("Step 2: Click 'Next' button");
        forgotEmail.clickNextBtn();

        log.info("Verifying error message 'Please put 10 digit mobile number'");
        test.info("Step 3: Verify error message for long mobile number");
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mobileError")));
        String actualError = errorElement.getText().trim();
        String expectedError = "Please put 10 digit mobile number";

        Assert.assertTrue(actualError.contains(expectedError),
                "Test failed: Expected error message not shown. Found: " + actualError);

        log.info("Test passed: Correct error message displayed for mobile number exceeding 10 digits");
        test.pass("Step 3 Passed: Error message verified for long mobile number");
    }

    @Test(priority = 7, description = "Verify error message for mobile number containing alphabets")
    public void testForgotEmailWithAlphabetsInMobile() throws InterruptedException {

        test = extent.createTest("Forgot Email - Mobile Number With Alphabets", "Verify error when mobile number contains letters");

        log.info("========== Forgot Email Test Started: Mobile Number With Alphabets ==========");
        test.info("Test execution started");

        String alphaMobile = TestDataReader.getData("invalidMobileNumberAlphabets");
        ForgotEmailPage forgotEmail = new ForgotEmailPage(driver, wait);

        driver.switchTo().frame("topFrame");
        forgotEmail.openForgotPasswordLink();
        driver.switchTo().defaultContent();
        driver.switchTo().frame("topFrame");

        log.info("Entering mobile number with alphabets: {}", alphaMobile);
        test.info("Step 1: Enter mobile number containing letters");
        forgotEmail.submitMobileNumber(alphaMobile);

        log.info("Clicking Next button");
        test.info("Step 2: Click 'Next' button");
        forgotEmail.clickNextBtn();

        log.info("Verifying error message 'Not a valid number'");
        test.info("Step 3: Verify error message for mobile number with alphabets");
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mobileError")));
        String actualError = errorElement.getText().trim();
        String expectedError = "Not a valid number";

        Assert.assertTrue(actualError.contains(expectedError),
                "Test failed: Expected error message not shown. Found: " + actualError);

        log.info("Test passed: Correct error message displayed for mobile number with alphabets");
        test.pass("Step 3 Passed: Error message verified for mobile number with alphabets");
    }
}
