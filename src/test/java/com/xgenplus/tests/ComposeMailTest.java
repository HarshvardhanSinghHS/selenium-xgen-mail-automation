package com.xgenplus.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.xgenplus.base.BaseClass;
import com.xgenplus.pages.ComposeMailPage;
import com.xgenplus.utils.TestDataReader;

public class ComposeMailTest extends BaseClass {

	private static final Logger log = LogManager.getLogger(ComposeMailTest.class);

	@Test(priority = 1, description = "Verify Compose Mail window opens with all mandatory fields and actions")
	public void verifyComposeWindowOpens() {

		test = extent.createTest("Compose Mail | UI Validation",
				"Validate mandatory fields, controls and actions in Compose window");

		log.info("========== TEST STARTED : Compose Window UI Validation ==========");
		test.info("Precondition → User logged in");

		loginToMail();

		log.info("Initializing page object");
		test.info("Step 1 → Initialize ComposeMailPage");
		ComposeMailPage composePage = new ComposeMailPage(driver, wait);

		log.info("Opening compose window");
		test.info("Step 2 → Click Compose button");
		composePage.clickComposeMailbtn();

		log.info("Validating UI elements visibility");
		test.info("Step 3 → Validate all fields and controls");

		SoftAssert softAssert = new SoftAssert();

		checkField(composePage.isToFieldVisible(), "To Field", softAssert);
		checkField(composePage.isSubjectFieldVisible(), "Subject Field", softAssert);
		checkField(composePage.isBodyFieldVisible(), "Body Field", softAssert);
		checkField(composePage.isCcButtonVisible(), "CC Button", softAssert);
		checkField(composePage.isBccButtonVisible(), "BCC Button", softAssert);
		checkField(composePage.isSendButtonVisible(), "Send Button", softAssert);
		checkField(composePage.isFormattingOptionVisible(), "Formatting Option", softAssert);
		checkField(composePage.isAttachmentButtonVisible(), "Attachment Button", softAssert);
		checkField(composePage.isInlineImageButtonVisible(), "Inline Image Button", softAssert);
		checkField(composePage.isDeliveryReportButtonVisible(), "Delivery Report", softAssert);
		checkField(composePage.isReadReceiptButtonVisible(), "Read Receipt", softAssert);
		checkField(composePage.isSaveDraftButtonVisible(), "Save Draft Button", softAssert);

		log.info("All UI elements validated");
		test.pass("Compose UI validation successful");

		softAssert.assertAll();
	}

	private void checkField(boolean isVisible, String fieldName, SoftAssert softAssert) {

		log.info("Validating visibility → {}", fieldName);
		test.info("Checking → " + fieldName);

		if (isVisible) {
			log.info("{} visible", fieldName);
			test.pass(fieldName + " visible");
		} else {
			log.error("{} not visible", fieldName);
			test.fail(fieldName + " not visible");
		}
		softAssert.assertTrue(isVisible, fieldName + " not visible");
	}

	@Test(priority = 2, description = "Verify sending email with all fields")
	public void verifySendMailWithAllFields() throws InterruptedException {

		test = extent.createTest("Compose Mail | Send Mail Flow",
				"Validate validations, attachments, inline image, draft and send functionality");

		log.info("========== TEST STARTED : Send Mail Flow ==========");
		test.info("Precondition → Login");

		loginToMail();

		log.info("Fetching test data");
		test.info("Step 1 → Load test data");

		String composeToMail = TestDataReader.getData("composeToMail");
		String composeCcMail = TestDataReader.getData("composeCcMail");
		String composeBccMail = TestDataReader.getData("composeBccMail");
		String composeSubject = TestDataReader.getData("composeSubject");
		String composeBodyText = TestDataReader.getData("composeBodyText");
		String attachment = TestDataReader.getData("composeAttachment");
		String inlineImage = TestDataReader.getData("composeInlineImage");
		String invalidComposeToMail = TestDataReader.getData("invalidComposeToMail");
		String invalidComposeCcMail = TestDataReader.getData("invalidComposeCcMail");
		String invalidComposeBccMail = TestDataReader.getData("invalidComposeBccMail");

		log.info("Initializing compose page");
		test.info("Step 2 → Initialize ComposeMailPage");
		ComposeMailPage composePage = new ComposeMailPage(driver, wait);

		log.info("Opening compose window");
		test.info("Step 3 → Click Compose");
		composePage.clickComposeMailbtn();

		log.info("Validating blank To field");
		test.info("Step 4 → Send without To");
		composePage.clickComposeSendbtn();

		String toast = composePage.getToastMessageAndWaitToDisappear();
		log.info("Toast captured → {}", toast);
		Assert.assertTrue(toast.contains("To Field Cannot be left blank"));
		test.pass("Blank To validation verified");

		log.info("Entering invalid To email");
		test.info("Step 5 → Enter invalid To");
		composePage.enterToEmail(invalidComposeToMail);
		composePage.clickComposeSendbtn();

		String toast1 = composePage.getToastMessageAndWaitToDisappear();
		log.info("Toast captured → {}", toast1);
		Assert.assertTrue(toast1.contains("not recognized"));
		test.pass("Invalid To validation verified");

		log.info("Entering valid To email");
		test.info("Step 6 → Enter valid To");
		composePage.clearToField();
		composePage.enterToEmail(composeToMail);
		composePage.clickComposeSendbtn();

		log.info("Handling subject alert");
		test.info("Step 7 → Handle blank subject alert");
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		log.info("Alert text → {}", alert.getText());
		Assert.assertTrue(alert.getText().contains("Subject is blank"));
		alert.dismiss();
		test.pass("Subject alert validated");

		log.info("Validating invalid CC");
		test.info("Step 8 → Enter invalid CC");
		composePage.clickCcButton();
		composePage.enterCc(invalidComposeCcMail);
		composePage.clickComposeSendbtn();

		String toast2 = composePage.getToastMessageAndWaitToDisappear();
		log.info("Toast captured → {}", toast2);
		Assert.assertTrue(toast2.contains("not recognized"));
		test.pass("Invalid CC validation verified");

		log.info("Entering valid CC");
		test.info("Step 9 → Enter valid CC");
		composePage.clearCcField();
		composePage.enterCc(composeCcMail);

		log.info("Validating invalid BCC");
		test.info("Step 10 → Enter invalid BCC");
		composePage.clickBccButton();
		composePage.enterBcc(invalidComposeBccMail);
		composePage.clickComposeSendbtn();

		String toast3 = composePage.getToastMessageAndWaitToDisappear();
		log.info("Toast captured → {}", toast3);
		Assert.assertTrue(toast3.contains("not recognized"));
		test.pass("Invalid BCC validation verified");

		log.info("Entering valid BCC");
		test.info("Step 11 → Enter valid BCC");
		composePage.clearBccField();
		composePage.enterBcc(composeBccMail);

		log.info("Entering subject");
		test.info("Step 12 → Enter Subject");
		composePage.enterSubjectTxt(composeSubject);

		log.info("Entering body");
		test.info("Step 13 → Enter Body");
		composePage.enterBodyText(composeBodyText);

		log.info("Uploading attachment");
		test.info("Step 14 → Upload Attachment");
		composePage.uploadAttachment(attachment);

		log.info("Adding inline image");
		test.info("Step 15 → Insert Inline Image");
		composePage.insertInlineImage(inlineImage);

		log.info("Selecting delivery report");
		test.info("Step 16 → Select Delivery Report");
		composePage.clickDeliveryReport();

		log.info("Selecting read receipt");
		test.info("Step 17 → Select Read Receipt");
		composePage.clickReadReceipt();

		log.info("Saving draft");
		test.info("Step 18 → Save Draft");
		composePage.clickSaveDraft();

		WebElement draftMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("saveDrftTime_0")));
		log.info("Draft message → {}", draftMsg.getText());
		Assert.assertTrue(draftMsg.getText().contains("Draft"));
		test.pass("Draft saved");

		log.info("Sending mail");
		test.info("Step 19 → Click Send");
		composePage.clickComposeSendbtn();
		composePage.clickYesPleasesendbtn();

		WebElement failMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//b[normalize-space()='Email will not be sent to the following']")));

		log.info("Validation message → {}", failMsg.getText());
		Assert.assertTrue(failMsg.getText().contains("Email will not be sent"));
		test.pass("Validation popup verified");

		log.info("Confirming send");
		test.info("Step 20 → Confirm Send");
		composePage.clickGoBackbtn();
		composePage.clickCheckAllMailIdsbtn();
		composePage.clickYesPleasesendbtn();

		String successMsg = composePage.getSuccessToastMessage();
		log.info("Success toast → {}", successMsg);
		Assert.assertTrue(successMsg.contains("Message sent successfully"));
		test.pass("Mail sent successfully");

		log.info("========== TEST COMPLETED : Send Mail Flow ==========");
	}

	@Test(priority = 3, description = "Verify email present in Sent folder")
	public void verifyComposeEmailInSentFolderWithCorrectContent() {

		test = extent.createTest("Compose Mail | Sent Folder Verification",
				"Validate sent mail subject, body and inline image");

		log.info("========== TEST STARTED : Sent Folder Verification ==========");
		test.info("Precondition → Login");

		loginToMail();

		log.info("Loading test data");
		test.info("Step 1 → Load test data");

		String composeToMail = TestDataReader.getData("composeToMail");
		String composeCcMail = TestDataReader.getData("composeCcMail");
		String composeBccMail = TestDataReader.getData("composeBccMail");
		String composeSubject = TestDataReader.getData("composeSubject");
		String composeBodyText = TestDataReader.getData("composeBodyText");
		String attachment = TestDataReader.getData("composeAttachment");
		String inlineImage = TestDataReader.getData("composeInlineImage");

		log.info("Initializing page");
		test.info("Step 2 → Initialize Page Object");
		ComposeMailPage composePage = new ComposeMailPage(driver, wait);

		log.info("Composing mail");
		test.info("Step 3 → Compose Mail");

		composePage.clickComposeMailbtn();
		composePage.enterToEmail(composeToMail);
		composePage.clickCcButton();
		composePage.enterCc(composeCcMail);
		composePage.clickBccButton();
		composePage.enterBcc(composeBccMail);
		composePage.enterSubjectTxt(composeSubject);
		composePage.enterBodyText(composeBodyText);
		composePage.uploadAttachment(attachment);
		composePage.insertInlineImage(inlineImage);
		composePage.clickDeliveryReport();
		composePage.clickReadReceipt();
		composePage.clickSaveDraft();

		log.info("Sending mail");
		test.info("Step 4 → Send Mail");

		composePage.clickComposeSendbtn();
		composePage.clickCheckAllMailIdsbtn();
		composePage.clickYesPleasesendbtn();

		String success = composePage.getSuccessToastMessage();
		log.info("Success message → {}", success);
		Assert.assertTrue(success.contains("Message sent successfully"));
		test.pass("Mail sent");

		log.info("Opening Sent folder");
		test.info("Step 5 → Open Sent Folder");

		composePage.clickSentText();
		composePage.switchToSentMailListFrame();
		composePage.waitForSentMailToLoad();
		composePage.clickSentRowId1Text();
		composePage.switchToSentMailViewFrame();
		composePage.clickGuidedTourIconbtn();

		log.info("Validating subject");
		test.info("Step 6 → Validate Subject");
		Assert.assertTrue(composePage.getSentMailSubject().contains(composeSubject));

		log.info("Validating body");
		test.info("Step 7 → Validate Body");
		Assert.assertTrue(composePage.getSentMailBody().contains(composeBodyText));

		log.info("Validating inline image");
		test.info("Step 8 → Validate Inline Image");
		Assert.assertTrue(composePage.isInlineImagePresentInSent());

		test.pass("Sent mail verified successfully");
		log.info("========== TEST COMPLETED : Sent Folder Verification ==========");
	}

	@Test(priority = 4, description = "Verify draft mail details")
	public void verifyComposedEmailSavedInDraftFolder() {

		test = extent.createTest("Compose Mail | Draft Verification", "Validate draft mail content");

		log.info("========== TEST STARTED : Draft Verification ==========");
		test.info("Precondition → Login");

		loginToMail();

		log.info("Loading test data");
		test.info("Step 1 → Load test data");

		String composeToMail = TestDataReader.getData("composeToMail");
		String composeCcMail = TestDataReader.getData("composeCcMail");
		String composeBccMail = TestDataReader.getData("composeBccMail");
		String composeSubject = TestDataReader.getData("composeSubject");
		String composeBodyText = TestDataReader.getData("composeBodyText");
		String attachment = TestDataReader.getData("composeAttachment");
		String inlineImage = TestDataReader.getData("composeInlineImage");

		log.info("Initializing page");
		test.info("Step 2 → Initialize Page Object");
		ComposeMailPage composePage = new ComposeMailPage(driver, wait);

		log.info("Composing draft mail");
		test.info("Step 3 → Compose Mail");

		composePage.clickComposeMailbtn();
		composePage.enterToEmail(composeToMail);
		composePage.clickCcButton();
		composePage.enterCc(composeCcMail);
		composePage.clickBccButton();
		composePage.enterBcc(composeBccMail);
		composePage.enterSubjectTxt(composeSubject);
		composePage.enterBodyText(composeBodyText);
		composePage.uploadAttachment(attachment);
		composePage.insertInlineImage(inlineImage);
		composePage.clickDeliveryReport();
		composePage.clickReadReceipt();

		log.info("Saving draft");
		test.info("Step 4 → Save Draft");
		composePage.clickSaveDraft();

		WebElement draftMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("saveDrftTime_0")));
		log.info("Draft message → {}", draftMsg.getText());
		Assert.assertTrue(draftMsg.getText().contains("Draft"));
		test.pass("Draft saved");

		log.info("Opening Draft folder");
		test.info("Step 5 → Open Draft Folder");

		composePage.clickComposeMailClosebtn();
		composePage.clickDraftText();
		composePage.switchToSentMailListFrame();
		composePage.clickDraftRowId1Text();
		composePage.switchToMailFrame();

		log.info("Validating draft To");
		test.info("Step 6 → Validate To");
		Assert.assertEquals(composePage.getDraftToMail(), composeToMail);

		log.info("Validating draft CC");
		test.info("Step 7 → Validate CC");
		Assert.assertEquals(composePage.getDraftCcMail(), composeCcMail);

		log.info("Validating draft BCC");
		test.info("Step 8 → Validate BCC");
		Assert.assertEquals(composePage.getDraftBccMail(), composeBccMail);

		log.info("Validating subject");
		test.info("Step 9 → Validate Subject");
		Assert.assertEquals(composePage.getDraftMailSubject(), composeSubject);

		log.info("Validating body");
		test.info("Step 10 → Validate Body");
		Assert.assertEquals(composePage.getDraftMailBody(), composeBodyText);

		log.info("Validating inline image");
		test.info("Step 11 → Validate Inline Image");
		Assert.assertTrue(composePage.isDraftInlineImagePresent());

		test.pass("Draft verified successfully");
		log.info("========== TEST COMPLETED : Draft Verification ==========");
	}
}
