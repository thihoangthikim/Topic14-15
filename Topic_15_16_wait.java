package webdriver;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_16_wait {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	String aName = "a.jpg";
	String bName = "b.jpg";
	String cName = "c.jpg";
	
	String aPath = projectPath + "\\UploadFiles\\" + aName;
	String bPath = projectPath + "\\UploadFiles\\" + bName;
	String cPath = projectPath + "\\UploadFiles\\" + cName;
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();

		
	}
	
	//@Test
	public void TC_02_ImplicitlyWait() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.cssSelector("div#start button")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);	
		Assert.assertTrue(driver.findElement(By.cssSelector("div#finish h4")).isDisplayed());
	}
	
	//@Test
	public void TC_04_ExplicitlyWait() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.cssSelector("div#start button")).click();
		explicitWait = new WebDriverWait(driver,1);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#loading img")));
		Assert.assertTrue(driver.findElement(By.cssSelector("div#finish h4")).isDisplayed());
	}
	
	//@Test
	public void TC_05_ExplicitlyWait() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.cssSelector("div#start button")).click();
		explicitWait = new WebDriverWait(driver,3);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish h4")));
	}
	
	//@Test
	public void TC_06_ExplicitlyWait() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		explicitWait = new WebDriverWait(driver,1);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1Panel']")));
	    Assert.assertEquals(driver.findElement(By.cssSelector("div[id*='Label1Panel'] span")).getText(), "No Selected Dates to display.");
	    explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("td[title='Wednesday, July 19, 2023']"))).click();
	    explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.raDiv")));
	    explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected']//a[text()='19']")));

	    Assert.assertEquals(driver.findElement(By.cssSelector("div[id*='Label1Panel'] span")).getText(), "Wednesday, July 19, 2023");
	    
	}
	//@Test
	public void TC_07_ExplicitlyWait() {
		driver.get("https://gofile.io/?t=uploadFiles");
		explicitWait = new WebDriverWait(driver,10);
		driver.findElement(By.cssSelector("button.btn-lg")).click();
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("//input[@type='file']"))).sendKeys(aPath + "\n" + bPath + "\n" + cPath);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress-bar"))));
		
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}



