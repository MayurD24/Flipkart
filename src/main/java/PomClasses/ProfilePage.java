package PomClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UtilClasses.Util1;

public class ProfilePage extends Util1{

	WebDriver driver;
	
	@FindBy(xpath="//span[text()='Personal Information']")
	private WebElement profilePageText;
	
	@FindBy(xpath="//div[text()='Manage Addresses']")
	private WebElement address;
	
	@FindBy(xpath="//div[@class='_1QhEVk']")
	private WebElement addNewAddress;
	
	@FindBy(xpath="//textarea[@name='addressLine1']")
	private WebElement fullAddress;
	
	@FindBy(xpath="//button[text()='Save']")
	private WebElement saveBtn;
	
	
	public ProfilePage(WebDriver driver) {
		PageFactory.initElements(driver,this);
		this.driver=driver;
	}
	
	public String getProfileText() {
		waitTillElementAppear(driver,profilePageText);
		String pText = profilePageText.getText();
		return pText;
	}
	
	public void clickOnManageAddress() {
		waitTillElementAppear(driver,address);
		address.click();
	}
	
	public void saveNewAddress(List<String> addressDetails) {
		addNewAddress.click();
		
		for(int i=1; i<=4; i++) {
			driver.findElement(By.xpath("//input[@tabindex='"+i+"']")).sendKeys(addressDetails.get(i-1));
		}
		fullAddress.sendKeys(addressDetails.get(4));
		waitTillElementAppear(driver,saveBtn);
		saveBtn.click();
	}
}
