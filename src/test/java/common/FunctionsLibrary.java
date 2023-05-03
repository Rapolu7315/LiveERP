package common;

import java.io.FileInputStream;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class FunctionsLibrary {
	public static WebDriver driver;
	public static Properties conpro;
	public static String Expected;
	public static String Actual;
	
	//method for launch browser
	public static WebDriver StartBrowser() throws Throwable
	{
		conpro = new Properties();
		conpro.load(new FileInputStream("./PropertyFile/Environment.properties"));
		if(conpro.getProperty("Browser").equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
		
		}
		else if(conpro.getProperty("Browser").equalsIgnoreCase("firfox"))
		{
			driver = new FirefoxDriver();
			driver.manage().deleteAllCookies();
		}
		else
		{
			System.out.println("browser value is not matching");
		}
		return driver;
		
		
	}
//method for launch Url
	public static void openurl(WebDriver driver)
	{
		driver.get(conpro.getProperty("Url"));
	}
	
//method for wait for element
	public static void waitForElement(WebDriver driver,String LocatorType,String LocatorValue,String WaitTime)
	{
		WebDriverWait myWait = new WebDriverWait(driver, Integer.parseInt(WaitTime));
		if(LocatorType.equalsIgnoreCase("name"))
		{
			myWait.until(ExpectedConditions.visibilityOfElementLocated(By.name(LocatorValue)));
			
		}
		else if(LocatorType.equalsIgnoreCase("xpath"))
		{
			myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocatorValue)));
		}
		else if(LocatorType.equalsIgnoreCase("id"))
		{
			myWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LocatorValue)));
		}
		}
	//method for Text boxes
	
	public static void typeAction(WebDriver driver,String LocatorType,String LocatorValue, String TextData)
	{
		if(LocatorType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(LocatorValue)).clear();
			driver.findElement(By.id(LocatorValue)).sendKeys(TextData);
		}
		else if(LocatorType.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(LocatorValue)).clear();
			driver.findElement(By.xpath(LocatorValue)).sendKeys(TextData);
		}
		else if(LocatorType.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(LocatorValue)).clear();
			driver.findElement(By.name(LocatorValue)).sendKeys(TextData);
		}
	}
	//method for button,links,radiobuttons,checkboxs and images
	
	public static void clickAction(WebDriver driver, String LocatorType,String LocatorValue)
	{
		if(LocatorType.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(LocatorValue)).click();
			
		}
		else if(LocatorType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(LocatorValue)).sendKeys(Keys.ENTER);
		}
		else if(LocatorType.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(LocatorValue)).click();
		}
	}
	
	//method for validating title
	public static void validateTitle(WebDriver driver,String Expected_Title)
	{
		String Actual_Title = driver.getTitle();
		try
		{
		Assert.assertEquals(Expected_Title, Actual_Title, "title is not matching");
		}catch(Throwable t)
		{
			System.out.println(t.getMessage());
		}
	}
	
	//method for closing browser
	public static void closeBrowser(WebDriver driver)
	{
		driver.quit();
	}
}
