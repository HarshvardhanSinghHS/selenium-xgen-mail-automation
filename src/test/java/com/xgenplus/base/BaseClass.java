package com.xgenplus.base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.xgenplus.pages.LoginPage;
import com.xgenplus.utils.ConfigReader;
import com.xgenplus.utils.TestDataReader;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;

public class BaseClass {
	public WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static String browserName;
	protected WebDriverWait wait;

	// Browser setup
	@Parameters("browser")
	@BeforeMethod
	public void setUp(@Optional("chrome") String browser) {

		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();

	/*		options.addArguments("--headless=new");
			options.addArguments("--window-size=1920,1080");
			options.addArguments("--disable-gpu");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-dev-shm-usage");       */

			driver = new ChromeDriver(options);
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.get(ConfigReader.get("url"));
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	}

	// ExtentReports setup

	@BeforeSuite
	public void setupExtent() {

		ConfigReader.loadConfig(); //
		String reportPath = System.getProperty("user.dir") + "/" + ConfigReader.get("reportPath");// fixed name
		ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
		spark.config().setDocumentTitle("Automation Report");
		spark.config().setReportName(ConfigReader.get("reportName"));
		spark.config().setOfflineMode(true); // ✅ Critical for Jenkins

		extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("Project Name", "XgenPlus");
		extent.setSystemInfo("Executed By", ConfigReader.get("testerName"));
		extent.setSystemInfo("Operating System (OS)", System.getProperty("os.name"));
		extent.setSystemInfo("Browser Name", ConfigReader.get("browserName"));
		extent.setSystemInfo("Java Version", System.getProperty("java.version"));

	}

	// Screenshot capture on failure + report flush
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + result.getName() + ".png";
			FileUtils.copyFile(src, new File(screenshotPath));
			test.fail(result.getThrowable());
			test.addScreenCaptureFromPath(screenshotPath);
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.pass("Test passed");
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.skip("Test skipped");
		}

		if (driver != null) {
			driver.quit();

		}

	}

	@AfterSuite
	public void closeReport() {
		extent.flush();
	}

	protected void loginToMail() {

		String email = TestDataReader.getData("validEmail");
		String password = TestDataReader.getData("validPassword");

		// top frame for login
		driver.switchTo().defaultContent();
		driver.switchTo().frame("topFrame");

		LoginPage loginPage = new LoginPage(driver, wait);
		loginPage.login(email, password);

		// switch to mail frame
		loginPage.switchToMailFrame();
		loginPage.closeGuidedTourIfVisible();
	}

}
