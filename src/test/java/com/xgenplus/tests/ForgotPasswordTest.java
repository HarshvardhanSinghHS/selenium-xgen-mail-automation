package com.xgenplus.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.xgenplus.base.BaseClass;
import com.xgenplus.pages.ForgotPasswordPage;
import com.xgenplus.utils.TestDataReader;

public class ForgotPasswordTest extends BaseClass {

	private static final Logger log = LogManager.getLogger(ForgotPasswordTest.class);

	@Test(priority = 1, description = "Verify instruction message when reset password email option is selected")
	public void verifyResetPasswordViaEmail() {

		test = extent.createTest("Forgot Password | Reset Instructions",
				"Verify ResetPasswordViaEmail");

		log.info("========== TEST STARTED : Reset Password Instruction ==========");
		test.info("Test execution started");

		ForgotPasswordPage forgotPassword = new ForgotPasswordPage(driver, wait);
		log.info("ForgotPasswordPage initialized");

		log.info("Switching to top frame");
		test.info("Step 1: Switch to top frame");
		driver.switchTo().frame("topFrame");

		log.info("Entering registered email");
		test.info("Step 2: Enter registered email");
		forgotPassword.enterEmail(TestDataReader.getData("validEmail"));

		log.info("Clicking Next button");
		test.info("Step 3: Click Next");
		forgotPassword.clickNextButton();

		log.info("Clicking Forgot Password link");
		test.info("Step 4: Click Forgot Password link");
		forgotPassword.clickForgotPasswordLink();

		log.info("Proceeding with reset password option");
		test.info("Step 5: Click Reset Password Next");
		forgotPassword.clickResetPasswordNext();

		log.info("Selecting Send Password Email option");
		test.info("Step 6: Select Send Password Email option");
		forgotPassword.clickSendPasswordEmail();


		WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("labelEReset")));

		String actualMessage = messageElement.getText().trim();
		String expectedMessage = "To Reset Your Password , follow the instructions sent to your";

		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Expected instruction message not displayed. Found: " + actualMessage);

		log.info("VerifyResetPasswordViaEmail");
		test.pass("Verify Reset Password Via Email");
	}

	@Test(priority = 2, description = "Verify successful password reset with valid security answers")
	public void verifySuccessfulPasswordResetUsingSecurityQuestions() {

		test = extent.createTest("Forgot Password | Successful Reset",
				"Verify password reset using valid security questions");

		log.info("========== TEST STARTED : Successful Password Reset ==========");
		test.info("Test execution started");

		ForgotPasswordPage forgotPassword = new ForgotPasswordPage(driver, wait);
		log.info("ForgotPasswordPage initialized");

		log.info("Switching to top frame");
		test.info("Step 1: Switch to top frame");
		driver.switchTo().frame("topFrame");

		log.info("Entering registered email");
		test.info("Step 2: Enter registered email");
		forgotPassword.enterEmail(TestDataReader.getData("validEmail"));

		log.info("Clicking Next button");
		test.info("Step 3: Click Next");
		forgotPassword.clickNextButton();

		log.info("Navigating to Forgot Password flow");
		test.info("Step 4: Click Forgot Password link");
		forgotPassword.clickForgotPasswordLink();

		log.info("Proceeding to reset options");
		test.info("Step 5: Click Reset Password Next");
		forgotPassword.clickResetPasswordNext();

		log.info("Selecting security question verification");
		test.info("Step 6: Select Different Verification Option");
		forgotPassword.clickSendDifferentVerificationOption();

		log.info("Entering valid security answers");
		test.info("Step 7: Enter pet name, city and country");
		forgotPassword.enterPetName(TestDataReader.getData("validPetName"));
		forgotPassword.enterCityName(TestDataReader.getData("validCity"));
		forgotPassword.enterCountryName(TestDataReader.getData("validCountry"));

		log.info("Submitting security answers");
		test.info("Step 8: Submit security answers");
		forgotPassword.clickResetPasswordNextButton();

		log.info("Entering new password");
		test.info("Step 9: Enter new password and confirm password");
		forgotPassword.enterNewPassword("123");
		forgotPassword.enterRePassword("123");

		log.info("Submitting password change request");
		test.info("Step 10: Click Change Password");
		forgotPassword.clickChangePassword();

		log.info("Validating success message");
		test.info("Step 11: Validate password reset success message");

		WebElement successElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("labelPassChange")));

		String actualMessage = successElement.getText().trim();
		String expectedMessage = "Congratulations !! Your password has been changed successfully.";

		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Password reset success message not displayed. Found: " + actualMessage);

		log.info("Password reset completed successfully");
		test.pass("Password reset completed successfully");
	}

	@Test(priority = 3, description = "Verify error when password is not entered")
	public void verifyErrorWhenPasswordIsMissing() {

		test = extent.createTest("Forgot Password | Missing Password",
				"Verify validation error when password is not entered");

		log.info("========== TEST STARTED : Missing Password Validation ==========");
		test.info("Test execution started");

		ForgotPasswordPage forgotPassword = new ForgotPasswordPage(driver, wait);

		log.info("Switching to top frame");
		test.info("Step 1: Switch to top frame");
		driver.switchTo().frame("topFrame");

		log.info("Completing security verification");
		test.info("Step 2: Complete security verification");
		forgotPassword.enterEmail(TestDataReader.getData("validEmail"));
		forgotPassword.clickNextButton();
		forgotPassword.clickForgotPasswordLink();
		forgotPassword.clickResetPasswordNext();
		forgotPassword.clickSendDifferentVerificationOption();
		forgotPassword.enterPetName(TestDataReader.getData("validPetName"));
		forgotPassword.enterCityName(TestDataReader.getData("validCity"));
		forgotPassword.enterCountryName(TestDataReader.getData("validCountry"));
		forgotPassword.clickResetPasswordNextButton();

		log.info("Submitting empty password fields");
		test.info("Step 3: Click Change Password without entering password");
		forgotPassword.clickChangePassword();

		WebElement errorElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("changePasswordError")));

		String actualMessage = errorElement.getText().trim();
		String expectedMessage = "Password length must be greater than 2 characters and less than 21 characters";

		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Password validation error not displayed. Found: " + actualMessage);

		log.info("Password mandatory validation verified");
		test.pass("Password validation error displayed correctly");
	}

	@Test(priority = 4, description = "Verify error when password and confirm password do not match")
	public void verifyErrorForPasswordMismatch() {

		test = extent.createTest("Forgot Password | Password Mismatch",
				"Verify validation error when password and confirm password do not match");

		log.info("========== TEST STARTED : Password Mismatch Validation ==========");
		test.info("Test execution started");

		ForgotPasswordPage forgotPassword = new ForgotPasswordPage(driver, wait);

		log.info("Switching to top frame");
		test.info("Step 1: Switch to top frame");
		driver.switchTo().frame("topFrame");

		log.info("Navigating to password reset using security questions");
		test.info("Step 2: Complete security verification flow");
		forgotPassword.enterEmail(TestDataReader.getData("validEmail"));
		forgotPassword.clickNextButton();
		forgotPassword.clickForgotPasswordLink();
		forgotPassword.clickResetPasswordNext();
		forgotPassword.clickSendDifferentVerificationOption();
		forgotPassword.enterPetName(TestDataReader.getData("validPetName"));
		forgotPassword.enterCityName(TestDataReader.getData("validCity"));
		forgotPassword.enterCountryName(TestDataReader.getData("validCountry"));
		forgotPassword.clickResetPasswordNextButton();

		log.info("Entering mismatched passwords");
		test.info("Step 3: Enter mismatched password and confirm password");
		forgotPassword.enterNewPassword(TestDataReader.getData("validPassword"));
		forgotPassword.enterRePassword(TestDataReader.getData("invalidPassword"));

		forgotPassword.clickChangePassword();

		WebElement errorElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("changePasswordError")));

		String actualMessage = errorElement.getText().trim();
		String expectedMessage = "Password does not match, Please retype password.";

		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Password mismatch error not displayed. Found: " + actualMessage);

		log.info("Password mismatch validation verified successfully");
		test.pass("Password mismatch validation message displayed correctly");
	}

	@Test(priority = 5, description = "Verify error when security answers are skipped")
	public void verifyErrorWhenSecurityAnswersAreBlank() {

		test = extent.createTest("Forgot Password | Blank Security Answers",
				"Verify error message when security answers are not provided");

		log.info("========== TEST STARTED : Blank Security Answers ==========");
		test.info("Test execution started");

		ForgotPasswordPage forgotPassword = new ForgotPasswordPage(driver, wait);

		log.info("Switching to top frame");
		test.info("Step 1: Switch to top frame");
		driver.switchTo().frame("topFrame");

		log.info("Navigating to security verification without answers");
		test.info("Step 2: Proceed without answering security questions");
		forgotPassword.enterEmail(TestDataReader.getData("validEmail"));
		forgotPassword.clickNextButton();
		forgotPassword.clickForgotPasswordLink();
		forgotPassword.clickResetPasswordNext();
		forgotPassword.clickSendDifferentVerificationOption();
		forgotPassword.clickResetPasswordNextButton();

		WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("skipQueError")));

		String actualMessage = errorElement.getText().trim();
		String expectedMessage = "Hint Answer can not be Blank";

		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Security answer validation error not shown. Found: " + actualMessage);

		log.info("Security answer blank validation verified");
		test.pass("Error displayed when security answers are blank");
	}

	@Test(priority = 6, description = "Verify error when city name is not provided")
	public void verifyErrorWhenCityNameIsBlank() {

		test = extent.createTest("Forgot Password | Blank City",
				"Verify validation error when city name is not provided");

		log.info("========== TEST STARTED : Blank City Validation ==========");
		test.info("Test execution started");

		ForgotPasswordPage forgotPassword = new ForgotPasswordPage(driver, wait);

		log.info("Switching to top frame");
		test.info("Step 1: Switch to top frame");
		driver.switchTo().frame("topFrame");
		log.info("Submitting security answers with missing city");
		test.info("Step 2: Enter pet name only and submit");		
		
		forgotPassword.enterEmail(TestDataReader.getData("validEmail"));
		forgotPassword.clickNextButton();
		forgotPassword.clickForgotPasswordLink();
		forgotPassword.clickResetPasswordNext();
		forgotPassword.clickSendDifferentVerificationOption();
		forgotPassword.enterPetName(TestDataReader.getData("validPetName"));
		forgotPassword.clickResetPasswordNextButton();

		WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("skipQueError")));

		String actualMessage = errorElement.getText().trim();
		String expectedMessage = "City name can not be Blank";

		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"City validation error not displayed. Found: " + actualMessage);

		log.info("City blank validation verified");
		test.pass("Error displayed when city name is blank");
	}

	@Test(priority = 7, description = "Verify error when country name is not provided")
	public void verifyErrorWhenCountryNameIsBlank() {

		test = extent.createTest("Forgot Password | Blank Country",
				"Verify validation error when country name is not provided");

		log.info("========== TEST STARTED : Blank Country Validation ==========");
		test.info("Test execution started");

		ForgotPasswordPage forgotPassword = new ForgotPasswordPage(driver, wait);

		log.info("Switching to top frame");
		test.info("Step 1: Switch to top frame");
		driver.switchTo().frame("topFrame");

		log.info("Submitting security answers with missing country");
		test.info("Step 2: Enter pet name and city only");
		forgotPassword.enterEmail(TestDataReader.getData("validEmail"));
		forgotPassword.clickNextButton();
		forgotPassword.clickForgotPasswordLink();
		forgotPassword.clickResetPasswordNext();
		forgotPassword.clickSendDifferentVerificationOption();
		forgotPassword.enterPetName(TestDataReader.getData("validPetName"));
		forgotPassword.enterCityName(TestDataReader.getData("validCity"));
		forgotPassword.clickResetPasswordNextButton();

		WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("skipQueError")));

		String actualMessage = errorElement.getText().trim();
		String expectedMessage = "Country name can not be Blank";

		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Country validation error not displayed. Found: " + actualMessage);

		log.info("Country blank validation verified");
		test.pass("Error displayed when country name is blank");
	}

	@Test(priority = 8, description = "Verify error when incorrect security answers are provided")
	public void verifyErrorForIncorrectSecurityAnswers() {

		test = extent.createTest("Forgot Password | Incorrect Security Answers",
				"Verify error message when incorrect security answers are provided");

		log.info("========== TEST STARTED : Incorrect Security Answers ==========");
		test.info("Test execution started");

		ForgotPasswordPage forgotPassword = new ForgotPasswordPage(driver, wait);

		log.info("Switching to top frame");
		test.info("Step 1: Switch to top frame");
		driver.switchTo().frame("topFrame");

		log.info("Submitting incorrect security answers");
		test.info("Step 2: Enter incorrect security answers");
		forgotPassword.enterEmail(TestDataReader.getData("validEmail"));
		forgotPassword.clickNextButton();
		forgotPassword.clickForgotPasswordLink();
		forgotPassword.clickResetPasswordNext();
		forgotPassword.clickSendDifferentVerificationOption();
		forgotPassword.enterPetName(TestDataReader.getData("invalidPetName"));
		forgotPassword.enterCityName(TestDataReader.getData("invalidCity"));
		forgotPassword.enterCountryName(TestDataReader.getData("invalidCountry"));
		forgotPassword.clickResetPasswordNextButton();

		WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("skipQueError")));

		String actualMessage = errorElement.getText().trim();
		String expectedMessage = "Wrong User Information !";

		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Incorrect security answer error not displayed. Found: " + actualMessage);

		log.info("Incorrect security answers validation verified");
		test.pass("Error displayed for incorrect security answers");
	}

}
