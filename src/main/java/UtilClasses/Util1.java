package UtilClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Util1 {

	public static String getConfigData(String key) throws IOException {
		
		FileInputStream file = new FileInputStream("configurations\\config.properties");
		Properties prop = new Properties();
		prop.load(file);
		return prop.getProperty(key);	
	}
	
	public static WebElement waitTillElementAppear(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static WebElement waitTillElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		return  wait.until(ExpectedConditions.visibilityOfElementLocated((By) element));
	}
	
	public static WebElement waitTillElementLocated(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		return  wait.until(ExpectedConditions.visibilityOfElementLocated((By) element));
	}
	
	public static String getScreenshot(WebDriver driver, String methodName) throws IOException {
		String path = methodName+".png";
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(path);
		FileHandler.copy(source, dest);
		return path;
	}
}
