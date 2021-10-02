package browser;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateLegalEntityWithoutMandFields {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		// Launch app

		try {
			// launch the application
			driver.get("https://login.salesforce.com/");
			// sign in
			driver.findElement(By.id("username")).sendKeys("fullstack@testleaf.com");
			driver.findElement(By.id("password")).sendKeys("SelBootcamp$123");
			driver.findElement(By.id("Login")).click();
		} catch (Exception e) {
			System.out.println("Problem while logging into the application :" + e.getMessage());
			driver.quit();
			Assert.fail();
		}

		try {
			// Click on toggle menu button from the left corner
			driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		} catch (Exception e) {
			System.out.println("Problem while clicking on the toggle menu from left corner :" + e.getMessage());
			driver.quit();
			Assert.fail();

		}
		try {
			// clicking on view all
			driver.findElement(By.xpath("//button[text()='View All']")).click();
		} catch (Exception e) {
			System.out.println("Problem while clicking on the view all button :" + e.getMessage());
			driver.quit();
			Assert.fail();
		}
		try {
			// Search for legal entities
			driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys("Legal Entities");
			// clicking on legal entities link
			driver.findElement(By.xpath("//mark[text()='Legal Entities']")).click();
		} catch (Exception e) {
			System.out.println(
					"Problem while searching for legal entities or clicking on legal entities :" + e.getMessage());
			driver.quit();
			Assert.fail();
		}
		try {
			driver.findElement(By.xpath("//a[@title='New']")).click();
		} catch (Exception e) {
			System.out.println("Problem while clicking on the new button");
			driver.quit();
			Assert.fail();
		}
		driver.findElement(By.xpath("//span[text()='Company Name']/parent::label/following-sibling::input"))
				.sendKeys("TestLeaf");
		driver.findElement(By.xpath("//span[text()='Description']/parent::label/following-sibling::textarea"))
				.sendKeys("SalesForce");
		driver.findElement(By.xpath("//div[@class='uiPopupTrigger']/div/div/a[@class='select']")).click();
		driver.findElement(By.xpath("//a[@title='Active']")).click();
		driver.findElement(By.xpath("//button[@data-aura-class='uiButton forceActionButton']/span[text()='Save']"))
				.click();
		WebElement errele = driver.findElement(By.xpath("//div[@class='desktop forcePageError']"));
		if (errele.isDisplayed()) {
			System.out.println("The following errors showing :"
					+ errele.findElement(By.xpath("//div[@class='genericNotification']/span")).getText());
			System.out.println(errele.findElement(By.xpath("//ul[@class='errorsList']/li")).getText());
			driver.quit();
		} else {
			System.out.println("Error not displayed even mandatory info missing");
			driver.quit();
			Assert.fail();
		}

	}

}
