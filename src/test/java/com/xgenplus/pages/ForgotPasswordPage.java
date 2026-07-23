package com.xgenplus.pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPasswordPage {

	private WebDriver driver;
	private WebDriverWait wait;

	// ✅ Constructor
	public ForgotPasswordPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		PageFactory.initElements(driver, this);
	}

	/* ================= LOCATORS ================= */

	@FindBy(id = "username")
	private WebElement emailTxt;

	@FindBy(id = "signinNxt")
	private WebElement nextBtn;

	@FindBy(id = "frgtpass")
	private WebElement forgotPasswordLink;

	@FindBy(xpath = "//a[@id='btnNextStp1']//input[@name='Next']")
	private WebElement nextBtnResetpw;

	@FindBy(id = "labelSendPasswordEmail")
	private WebElement sendPasswordEmailBtn;

	@FindBy(id = "emailSuccessFinishButton")
	private WebElement emailSuccessFinishBtn;

	@FindBy(id = "labelUseADiffrentVerificationOption")
	private WebElement sendDifferentVerificationOption;

	@FindBy(id = "hnt_answer")
	private WebElement petsNameTxt;

	@FindBy(id = "city")
	private WebElement cityNameTxt;

	@FindBy(id = "country")
	private WebElement countryNameTxt;

	@FindBy(id = "btnNextStp3")
	private WebElement resetPasswordNextBtn;

	@FindBy(id = "frpwd")
	private WebElement passwordTxt;

	@FindBy(id = "frRepwd")
	private WebElement reEnterPasswordTxt;

	@FindBy(xpath = "//input[@value='Change Password']")
	private WebElement changePasswordBtn;

	/* ================= COMMON WAIT ACTIONS ================= */

	private void waitAndClick(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	private void waitAndSendKeys(WebElement element, String value) {
		WebElement el = wait.until(ExpectedConditions.visibilityOf(element));
		el.clear();
		el.sendKeys(value);
	}

	private void waitForVisible(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	private void jsClick(WebElement element) {
		WebElement el = wait.until(ExpectedConditions.visibilityOf(element));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
	}

	/* ================= PAGE ACTION METHODS ================= */

	public void enterEmail(String email) {
		waitAndSendKeys(emailTxt, email);
	}

	public void clickNextButton() {
		waitAndClick(nextBtn);
	}

	public void clickForgotPasswordLink() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink)).click();
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", forgotPasswordLink);
		}
	}

	public void clickResetPasswordNext() {
		waitAndClick(nextBtnResetpw);
	}

	public void clickSendPasswordEmail() {
		waitAndClick(sendPasswordEmailBtn);
	}

	public void clickSendDifferentVerificationOption() {
		waitAndClick(sendDifferentVerificationOption);
	}

	public void clickEmailSuccessFinish() {
		waitAndClick(emailSuccessFinishBtn);
	}

	public void enterPetName(String petName) {
		waitAndSendKeys(petsNameTxt, petName);
	}

	public void enterCityName(String city) {
		waitAndSendKeys(cityNameTxt, city);
	}

	public void enterCountryName(String country) {
		waitAndSendKeys(countryNameTxt, country);
	}

	public void clickResetPasswordNextButton() {
		waitAndClick(resetPasswordNextBtn);
	}

	public void enterNewPassword(String password) {
		waitAndSendKeys(passwordTxt, password);
	}

	public void enterRePassword(String password) {
		waitAndSendKeys(reEnterPasswordTxt, password);
	}

	public void clickChangePassword() {
		waitAndClick(changePasswordBtn);
	}
}
