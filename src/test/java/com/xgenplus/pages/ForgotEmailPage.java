package com.xgenplus.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotEmailPage {

	private WebDriver driver;
	private WebDriverWait wait;

	public ForgotEmailPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "foorgetEmail")
	private WebElement forgotEmailLink;

	@FindBy(id = "mobileNumber")
	private WebElement MobileTxt;

	@FindBy(id = "getNxtOtp")
	private WebElement nextBtn;

	@FindBy(id = "getNxtEmail")
	private WebElement nextBtnEmail;

	public void openForgotPasswordLink() {
		wait.until(ExpectedConditions.elementToBeClickable(forgotEmailLink)).click();
	}

	public void submitMobileNumber(String mobileNumber) {
		wait.until(ExpectedConditions.elementToBeClickable(MobileTxt));
		MobileTxt.sendKeys(mobileNumber);
	}

	public void clickNextBtn() {
		wait.until(ExpectedConditions.elementToBeClickable(nextBtn)).click();
	}

	public void clickNextBtnEmail() {
		wait.until(ExpectedConditions.elementToBeClickable(nextBtnEmail)).click();
	}

	public WebElement clicknBtn() {
		return MobileTxt;
	}
}
