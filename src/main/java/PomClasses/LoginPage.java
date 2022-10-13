package PomClasses;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UtilClasses.Util1;

public class LoginPage extends Util1 {

	WebDriver driver;
	
	@FindBy(xpath="//input[@class='_2IX_2- VJZDxU']")
	private WebElement emailID;
	
	@FindBy(xpath="//input[@type='password']")
	private WebElement password;
	
	@FindBy(xpath="(//button[@type='submit'])[2]")
	private WebElement loginBtn;
	
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
		this.driver=driver;
	}
	
	public void enterEmailID() throws IOException {
		emailID.sendKeys(Util1.getConfigData("username"));
	}
	public void enterPassword() throws IOException {
		password.sendKeys(Util1.getConfigData("password"));
	}
	public void clickLoginBtn() {
		loginBtn.click();
	}
}
