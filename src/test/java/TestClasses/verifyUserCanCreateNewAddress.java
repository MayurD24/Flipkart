package TestClasses;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import BaseClass.Base1;
import PomClasses.HomePage;
import PomClasses.ProfilePage;
import UtilClasses.Util1;

public class verifyUserCanCreateNewAddress {

	static WebDriver driver;
	
	ExtentHtmlReporter htmlReporter;
	ExtentReports reports;
	ExtentTest extentTest;
	
	HomePage hp;
	ProfilePage pp;
	
	@BeforeClass
	@Parameters("browser")
	public void beforeClass(String browser) throws IOException {
		htmlReporter = Base1.getExtentHtmlReporter();
		reports = Base1.getExtentReports();
		extentTest = Base1.getExtentTest("verifyUserCanCreateNewAddress");
		
		driver = Base1.getDriver(browser);
	}
	
	@BeforeMethod
	public void beforeMethod() {
		hp = new HomePage(driver);
		pp = new ProfilePage(driver);
	}

	@Test(priority=1)
	public void verifyUserCanOpenMyProfilePage() {
		hp.hoverToProfileName();
		hp.clickOnMyProfile();
		String profileText = pp.getProfileText();
		Assert.assertEquals(profileText, "Personal Information","ProfileText is not matching");
	}
	
	@DataProvider(name="Address")
	public Object[][] getData() {
		Object[][] address = {{"Mayur","9921220034","423601","Takli Road","Om Nagar"},{"Tejas","7020637361","423601","Shinde Nagar","Lodha Mangal Karyalaya"}};
		return address;
	}
	
	@Test(priority=2, dataProvider="Address")
	public void verifyUserCanCreateNewAddresses(String name, String phone, String pincode, String locality, String fAddress) {
		pp.clickOnManageAddress();
		
		List<String> addressDetails = Arrays.asList(name,phone,pincode,locality,fAddress);
		pp.saveNewAddress(addressDetails);
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if(result.getStatus() ==  ITestResult.SUCCESS) {
			extentTest.log(Status.PASS, "Test : "+ result.getName());
		}
		else if(result.getStatus() == ITestResult.FAILURE) {
			String path = Util1.getScreenshot(driver, result.getName());
			extentTest.log(Status.FAIL, "Test : "+ result.getName(), MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}
		else if(result.getStatus() == ITestResult.SKIP) {
			extentTest.log(Status.SKIP, "Test : "+ result.getName());
		}
	}
	
	@AfterClass
	public void afterClass() {
		reports.flush();
		Base1.unloadDriver();
	}
}
