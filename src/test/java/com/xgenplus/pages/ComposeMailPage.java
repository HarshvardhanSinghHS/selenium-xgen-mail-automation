package com.xgenplus.pages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ComposeMailPage {
	private WebDriver driver;
	private WebDriverWait wait;

	// ✅ Constructor
	public ComposeMailPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(driver, this);
	}

	/* ================= LOCATORS ================= */

	@FindBy(xpath = "//span[normalize-space()='Compose Mail']")
	private WebElement ComposeMailbtn;

	@FindBy(id = "txtMailToNew0")
	private WebElement ToTxt;

	@FindBy(id = "labelSubject_0")
	private WebElement SubjectTxt;

	@FindBy(id = "lableCc_0")
	private WebElement Ccbtn;

	@FindBy(id = "lableBcc_0")
	private WebElement Bccbtn;

	@FindBy(xpath = "//div[@aria-label='Editor editing area: main. Press Alt+0 for help.']")
	private WebElement BodyTxt;

	@FindBy(id = "TxtMailToCCNew0")
	private WebElement CcTxt;

	@FindBy(id = "inputBccDiv0")
	private WebElement BccTxt;

	@FindBy(id = "butComposeSend0")
	private WebElement ComposeSendbtn;

	@FindBy(xpath = "//div[@id='ck_editor0']//*[name()='svg']")
	private WebElement FormatingOpetionbtn;

	@FindBy(id = "attachmentId_0")
	private WebElement Attachmentbtn;

	@FindBy(id = "customInlineImage_0")
	private WebElement CustomInlineImagebtn;

	@FindBy(id = "dr_report0")
	private WebElement DeliveryReportbtn;

	@FindBy(id = "readRecpt0")
	private WebElement ReadReceiptbtn;

	@FindBy(xpath = "//span[@id='svDrftbtn_0']//*[name()='svg']//*[name()='path'][5]")
	private WebElement SaveDraftbtn;

	@FindBy(id = "checkAllMailIds")
	private WebElement CheckAllMailIdsbtn;

	@FindBy(xpath = "//button[@id='ok']")
	private WebElement YesPleasesendbtn;

	@FindBy(xpath = "//button[@id='no']")
	private WebElement GoBackbtn;

	@FindBy(xpath = "//div[contains(@class,'toast-message')]")
	private WebElement toastMessage;

	@FindBy(xpath = "//div[contains(@class,'toast')]")
	private WebElement successToast;

	// Sent Mail locators newComposeClose_0

	@FindBy(xpath = "//a[@title='Sent']")
	private WebElement SentTextbtn;

	@FindBy(xpath = "//div[@id='tabTabDHTMLSuite_tabView1_1']//span[contains(text(),'Inbox')]")
	private WebElement SentInboxTextbtn;

	@FindBy(xpath = "//span[normalize-space()='Check Mail']")
	private WebElement SentInboxCheckMailbtn;

	@FindBy(id = "MailRowId1")
	private WebElement MailRowId1Text;

	@FindBy(xpath = "//div[@class='guided-tour-step-tooltip guided-tour-arrow-top']//span[@title='End tour']//*[name()='svg']")
	private WebElement GuidedTourIconbtn;

	@FindBy(xpath = "//p[normalize-space()='Automation Test Mail']")
	private WebElement SentMailSubject;

	@FindBy(xpath = "//div[@id='intExtbodycontent']//p[1]")
	private WebElement SentMailBody;

	@FindBy(xpath = "//div[@id='intExtbodycontent']//img")
	private java.util.List<WebElement> SentInlineImages;

	@FindBy(id = "newComposeClose_0")
	private WebElement ComposeMailClosebtn;

	// Draft Mail locators

	@FindBy(xpath = "//span[normalize-space()='Draft']")
	private WebElement DraftTextbtn;

	@FindBy(id = "MailRowId1")
	private WebElement DraftMailRowId1Text;

	@FindBy(xpath = "//span[contains(@class,'txtMailToSpanall-mail-to')]")
	private WebElement DraftToMail;

	@FindBy(xpath = "//span[contains(@class,'txtMailToSpanall-mail-cc_0')]")
	private WebElement DraftCcMail;

	@FindBy(xpath = "//span[contains(@class,'txtMailToSpanall-mail-bcc_0')]")
	private WebElement DraftBccMail;

	@FindBy(id = "labelSubject_0")
	private WebElement DraftMailSubject;

	@FindBy(xpath = "//div[@id='newComposeContainer']//p[1]")
	private WebElement DraftMailBody;

	@FindBy(xpath = "//img[@alt='Test-Logo.png']")
	private java.util.List<WebElement> DraftInlineImages;

	/* ================= COMMON WAIT ACTIONS ================= */

	private void waitAndClick(WebElement element) {
		try {
			// wait for visibility
			wait.until(ExpectedConditions.visibilityOf(element));

			// wait for clickable
			wait.until(ExpectedConditions.elementToBeClickable(element));

			// scroll into view
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element);

			element.click();

		} catch (Exception e) {
			// fallback JS click (handles iframe animation / overlay issues)
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		}
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

	public boolean isToFieldVisible() {
		waitForVisible(ToTxt);
		return ToTxt.isDisplayed();
	}

	public boolean isSubjectFieldVisible() {
		waitForVisible(SubjectTxt);
		return SubjectTxt.isDisplayed();
	}

	public boolean isBodyFieldVisible() {
		waitForVisible(BodyTxt);
		return BodyTxt.isDisplayed();
	}

	public boolean isCcButtonVisible() {
		waitForVisible(Ccbtn);
		return Ccbtn.isDisplayed();
	}

	public boolean isBccButtonVisible() {
		waitForVisible(Bccbtn);
		return Bccbtn.isDisplayed();
	}

	public boolean isSendButtonVisible() {
		waitForVisible(ComposeSendbtn);
		return ComposeSendbtn.isDisplayed();
	}

	public boolean isFormattingOptionVisible() {
		waitForVisible(FormatingOpetionbtn);
		return FormatingOpetionbtn.isDisplayed();
	}

	public boolean isAttachmentButtonVisible() {
		waitForVisible(Attachmentbtn);
		return Attachmentbtn.isDisplayed();
	}

	public boolean isInlineImageButtonVisible() {
		waitForVisible(CustomInlineImagebtn);
		return CustomInlineImagebtn.isDisplayed();
	}

	public boolean isDeliveryReportButtonVisible() {
		waitForVisible(DeliveryReportbtn);
		return DeliveryReportbtn.isDisplayed();
	}

	public boolean isReadReceiptButtonVisible() {
		waitForVisible(ReadReceiptbtn);
		return ReadReceiptbtn.isDisplayed();
	}

	public boolean isSaveDraftButtonVisible() {
		waitForVisible(SaveDraftbtn);
		return SaveDraftbtn.isDisplayed();
	}

	public boolean isCcFieldVisible() {
		waitForVisible(CcTxt);
		return CcTxt.isDisplayed();
	}

	public boolean isBccFieldVisible() {
		waitForVisible(BccTxt);
		return BccTxt.isDisplayed();
	}

	// Returns the WebElement of To field for further actions

	public void clickComposeMailbtn() {
		waitAndClick(ComposeMailbtn);
	}

	public void enterToEmail(String email) {
		waitAndSendKeys(ToTxt, email);
	}

	public void enterCc(String Ccemail) {
		waitAndSendKeys(CcTxt, Ccemail);
	}

	public void enterBcc(String Bccemail) {
		waitAndSendKeys(BccTxt, Bccemail);
	}

	public void enterSubjectTxt(String SubTxt) {
		waitAndSendKeys(SubjectTxt, SubTxt);
	}

	public void enterBodyText(String BodyText) {
		waitAndSendKeys(BodyTxt, BodyText);
	}

	public void clickCcButton() {
		waitAndClick(Ccbtn);
	}

	public void clickBccButton() {
		waitAndClick(Bccbtn);
	}

	public void clickAttachmentButton() {
		waitAndClick(Attachmentbtn);
	}

	public void uploadAttachment(String filePath) {
		Attachmentbtn.sendKeys(filePath);
	}

	public void clickInlineImageButton() {
		waitAndClick(CustomInlineImagebtn);
	}

	public void insertInlineImage(String imagePath) {
		// assuming inline image uses file input
		CustomInlineImagebtn.sendKeys(imagePath);
	}

	public void clickDeliveryReport() {
		waitAndClick(DeliveryReportbtn);
	}

	public void clickReadReceipt() {
		waitAndClick(ReadReceiptbtn);
	}

	public void clickSaveDraft() {
		waitAndClick(SaveDraftbtn);
	}

	public void clickComposeSendbtn() {
		waitAndClick(ComposeSendbtn);
	}

	public void clickCheckAllMailIdsbtn() {
		waitAndClick(CheckAllMailIdsbtn);
	}

	public void clickYesPleasesendbtn() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(YesPleasesendbtn)).click();
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", YesPleasesendbtn);
		}
	}

	public void clickGoBackbtn() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(GoBackbtn)).click();
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", GoBackbtn);
		}
	}

	public void clickSentText() {
		waitAndClick(SentTextbtn);
	}

	public void clickSentInboxText() {
		waitAndClick(SentInboxTextbtn);
	}

	public void clickSentInboxCheckMailText() {
		waitAndClick(SentInboxCheckMailbtn);
	}

	public void clickSentRowId1Text() {
		waitAndClick(MailRowId1Text);
	}

	public void clickGuidedTourIconbtn() {
		waitAndClick(GuidedTourIconbtn);
	}

	public void clickComposeMailClosebtn() {
		waitAndClick(ComposeMailClosebtn);
	}

	public void clickDraftText() {
		waitAndClick(DraftTextbtn);
	}

	public void clickDraftRowId1Text() {
		waitAndClick(DraftMailRowId1Text);
	}

	/* ================= CLEAR FIELD METHODS ================= */

	public void clearToField() {
		wait.until(ExpectedConditions.visibilityOf(ToTxt)).clear();
	}

	public void clearCcField() {
		wait.until(ExpectedConditions.visibilityOf(CcTxt)).clear();
	}

	public void clearBccField() {
		wait.until(ExpectedConditions.visibilityOf(BccTxt)).clear();
	}

	public String getToastMessageAndWaitToDisappear() {
		WebElement toast = wait.until(ExpectedConditions.visibilityOf(toastMessage));
		String message = toast.getText().trim();
		wait.until(ExpectedConditions.invisibilityOf(toast));
		return message;
	}

	public String getSuccessToastMessage() {
		WebElement toast = wait.until(ExpectedConditions.visibilityOf(successToast));
		String msg = toast.getText().trim();
		wait.until(ExpectedConditions.invisibilityOf(toast));
		return msg;
	}

	// ================= Sent Mail Methods =================

	public String getSentMailSubject() {
		waitForVisible(SentMailSubject);
		return SentMailSubject.getText().trim();
	}

	public String getSentMailBody() {
		waitForVisible(SentMailBody);
		return SentMailBody.getText().trim();
	}

	public boolean isInlineImagePresentInSent() {

		try {
			wait.until(driver -> !SentInlineImages.isEmpty());

			WebElement img = SentInlineImages.get(0);

			return (Boolean) ((JavascriptExecutor) driver)
					.executeScript("return arguments[0].complete && arguments[0].naturalWidth > 0;", img);

		} catch (Exception e) {
			return false;
		}
	}

	public void waitForSentMailToLoad() {
		wait.until(ExpectedConditions.visibilityOf(MailRowId1Text));
	}

	public void switchToSentMailListFrame() {
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("FB"));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("FM"));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("MC"));
	}

	public void switchToSentMailViewFrame() {
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("FB"));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("FM"));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("VC"));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ifViewMail1"));
	}

	public void switchToMailFrame() {
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("FB"));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("FM"));
	}

	// --------Draft Mail Methods --------

	private String extractEmailFromElement(WebElement element) {
		try {
			// Wait until element has some text (non-empty)
			wait.until(driver -> {
				String text = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].textContent;",
						element);
				return text != null && !text.trim().isEmpty();
			});

			String fullText = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].textContent;",
					element);

			Matcher matcher = Pattern.compile("[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}").matcher(fullText);
			if (matcher.find())
				return matcher.group().trim();
		} catch (Exception e) {
			return "";
		}
		return "";
	}

	public String getDraftToMail() {
		return extractEmailFromElement(DraftToMail);
	}

	public String getDraftCcMail() {
		return extractEmailFromElement(DraftCcMail);
	}

	public String getDraftBccMail() {
		return extractEmailFromElement(DraftBccMail);
	}

	public String getDraftMailSubject() {
		waitForVisible(DraftMailSubject);
		return DraftMailSubject.getAttribute("value").trim();
	}

	public String getDraftMailBody() {
		waitForVisible(DraftMailBody);
		return DraftMailBody.getText().trim();
	}

	// -------- Inline Image Validation --------

	public boolean isDraftInlineImagePresent() {
		wait.until(ExpectedConditions.visibilityOfAllElements(DraftInlineImages));
		return DraftInlineImages.size() > 0;
	}

}
