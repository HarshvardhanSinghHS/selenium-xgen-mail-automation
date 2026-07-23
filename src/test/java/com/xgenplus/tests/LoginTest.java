package com.xgenplus.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.xgenplus.base.BaseClass;
import com.xgenplus.pages.LoginPage;
import com.xgenplus.utils.TestDataReader;

public class LoginTest extends BaseClass {

	private static final Logger log = LogManager.getLogger(LoginTest.class);

	@Test(priority = 1, description = "Verify login with valid credentials using URL validation")
	public void validLoginTest() throws InterruptedException {

		test = extent.createTest("Valid Login Test", "Verify login functionality using valid credentials");

		log.info("========== Login Test Started ==========");
		test.info("Login test execution started");

		String email = TestDataReader.getData("validEmail");
		String password = TestDataReader.getData("validPassword");

		// Step 1: Initialize Login Page
		log.debug("Initializing LoginPage object");
		LoginPage loginPage = new LoginPage(driver, wait);
		test.info("Login page initialized");

		// Step 2: Switch to Login Frame
		log.info("Switching to login frame");
		test.info("Switching to login frame");
		driver.switchTo().frame("topFrame");

		// Step 3: Perform Login
		log.info("Performing login using valid credentials");
		test.info("Entering valid email and password");
		loginPage.login(email, password);

		// Step 4: Handle post-login frames and popups
		log.info("Handling post-login frames and guided tour if present");
		test.info("Handling post-login popups");
		loginPage.switchToMailFrame();
		loginPage.closeGuidedTourIfVisible();

		// Step 5: Verify successful login using URL
		log.info("Waiting for inbox URL to be loaded");
		test.info("Verifying user is redirected to Inbox page");

		wait.until(ExpectedConditions.urlContains("TyHtmMain.jsp"));
		String actualUrl = driver.getCurrentUrl();

		Assert.assertTrue(actualUrl.contains("TyHtmMain.jsp"), "Login failed: Inbox URL mismatch. Found: " + actualUrl);

		log.info("Login successful. User redirected to: {}", actualUrl);
		test.pass("Login successful. User redirected to Inbox page");
	}

	@Test(priority = 2, description = "Verify login with invalid email")
	public void invalidEmailLoginTest() {

		test = extent.createTest("Invalid Email Login Test", "Verify login functionality using invalid email");

		log.info("========== Invalid Email Login Test Started ==========");
		test.info("Login test execution started with invalid email");

		String email = TestDataReader.getData("invalidEmail");

		// Step 1: Initialize Login Page
		log.debug("Initializing LoginPage object");
		LoginPage loginPage = new LoginPage(driver, wait);
		test.info("Login page initialized");

		// Step 2: Switch to Login Frame
		log.info("Switching to login frame");
		test.info("Switching to login frame");
		driver.switchTo().frame("topFrame");

		// Step 3: Enter invalid email and click Next
		log.info("Entering invalid email and clicking Next");
		test.info("Entering invalid email and clicking Next");
		loginPage.enterEmail().sendKeys(email);
		loginPage.clickNextBtn().click();

		// Step 4: Verify error message
		log.info("Verifying error message for invalid email");
		test.info("Checking for 'User does not Exist' message");

		WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailError")));

		String actualMessage = messageElement.getText().trim();
		String expectedMessage = "User does not Exist";

		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Expected instruction message not displayed. Found: " + actualMessage);

		log.info("Invalid email login test passed");
		test.pass("Invalid email login properly blocked by the application");
	}

	@Test(priority = 3, description = "Verify login with valid email with invaild password")
	public void invalidPasswordLoginTest() {

		test = extent.createTest("Invalid Password Login Test", "Verify login functionality using invalid Password");

		log.info("========== Invalid Password Login Test Started ==========");
		test.info("Login test execution started with InvaildPassword email");

		String email = TestDataReader.getData("validEmail");
		String password = TestDataReader.getData("invalidPassword");

		// Step 1: Initialize Login Page
		log.debug("Initializing LoginPage object");
		LoginPage loginPage = new LoginPage(driver, wait);
		test.info("Login page initialized");

		// Step 2: Switch to Login Frame
		log.info("Switching to login frame");
		test.info("Switching to login frame");
		driver.switchTo().frame("topFrame");

		// Step 3: Enter invalid email and click Next
		log.info("Entering valid email and clicking Next");
		test.info("Entering valid email and clicking Next");
		loginPage.enterEmail().sendKeys(email);
		loginPage.clickNextBtn().click();
		loginPage.clickPassword().sendKeys(password);
		loginPage.clickloginBtn().click();

		WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("spLoginErrmsg")));
		String actualError = errorElement.getText().trim();

		String expectedError = "Invalid Username/Mobile or Password";

		Assert.assertTrue(actualError.contains(expectedError),
				"Test failed: Expected error message not shown. Found: " + actualError);

		log.info("Invalid password login test passed");
		test.pass("Invalid password login properly blocked by the application");

	}

}
