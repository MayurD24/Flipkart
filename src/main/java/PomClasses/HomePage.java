package PomClasses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UtilClasses.Util1;

public class HomePage extends Util1 {

	WebDriver driver;
	
	@FindBy(xpath="//div[text()='MAYUR']")
	private WebElement profileName;
	
	@FindBy(xpath="//div[text()='My Profile']")
	private WebElement myProfile;
	
	@FindBy(xpath="//input[@name='q']")
	private WebElement searchField;
	
	@FindBy(xpath="//div[@class='_1AtVbE']")
	private WebElement textOnProductList;
	
	@FindBy(xpath="//div[@class='_2kHMtA']")
	private WebElement productList;
	
	@FindBy(xpath="//div[@class='_30jeq3 _1_WHN1']")
	private List <WebElement> productPriceList;

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	public String getProfileName() throws InterruptedException {
		Thread.sleep(2000);
		waitTillElementAppear(driver,profileName);
		String pName = profileName.getText();
		return pName;
	}
	
	public void searchProduct() {
		waitTillElementAppear(driver,searchField);
		searchField.sendKeys("realme");
		searchField.sendKeys(Keys.ENTER);	
	}
	
	public String getValidText() {
		waitTillElementAppear(driver,productList);
		String actualWholeText = textOnProductList.getText();
		return actualWholeText;
	}
	
	public String getLowestPriceOfProduct() {
		List <Integer> priceList = new ArrayList<>();
		
		for(WebElement price : productPriceList) {
			priceList.add(Integer.parseInt(price.getText().replace("₹","").replace(",", "")));
		}
		System.out.println(priceList);
		Collections.sort(priceList);
		return priceList.get(0).toString();		
	}

	public void switchToPage(int x) {
		driver.findElement(By.xpath("//a[text()='"+ x +"']")).click();
		waitTillElementAppear(driver,driver.findElement(By.xpath("//a[@class='ge-49M _2Kfbh8' and text()='"+ x +"']")));
	}
	
	public void hoverToProfileName() {
		Actions act = new Actions(driver);
		act.moveToElement(profileName).perform();;
		waitTillElementAppear(driver,myProfile);
	}
	
	public void clickOnMyProfile() {
		myProfile.click();
	}
}
