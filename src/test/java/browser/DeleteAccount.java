package browser;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteAccount {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		try {
			// launch the application
			driver.get("https://login.salesforce.com/");
			// sign in
			driver.findElement(By.id("username")).sendKeys("fullstack@testleaf.com");
			driver.findElement(By.id("password")).sendKeys("SelBootcamp$123");
			driver.findElement(By.id("Login")).click();
		} catch (Exception e) {
			System.out.println("Problem while logging into the application :" + e.getMessage());
			Assert.fail();
		}

		try {
			// Click on toggle menu button from the left corner
			driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		} catch (Exception e) {
			System.out.println("Problem while clicking on the toggle menu from left corner :" + e.getMessage());
			Assert.fail();

		}
		try {
			// clicking on view all
			driver.findElement(By.xpath("//button[text()='View All']")).click();
		} catch (Exception e) {
			System.out.println("Problem while clicking on the view all button :" + e.getMessage());
			Assert.fail();
		}
		try {
			// Search for sales
			driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys("Sales");
			// clicking on sales button
			driver.findElement(
					By.xpath("//p[@title='Manage your sales process with accounts, leads, opportunities, and more']"))
					.click();
		} catch (Exception e) {
			System.out.println("Problem while searching for sales or clicking on sales :" + e.getMessage());
			Assert.fail();
		}
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			WebElement AccEle = driver.findElement(By.xpath("//a[@title='Accounts']/span"));
			js.executeScript("arguments[0].click();", AccEle);
		} catch (Exception e) {
			System.out.println("Problem while clicking on the accounts link :" + e.getMessage());
			Assert.fail();
		}
		// Searching for account
		try {
			WebElement SearchEle = driver.findElement(By.xpath("(//input[@type='search'])[2]"));
			SearchEle.sendKeys("EditACcountByMamatha");
			SearchEle.sendKeys(Keys.ENTER);
			Thread.sleep(8000);
		} catch (Exception e) {
			System.out.println("Problem while Searching the account :" + e.getMessage());
			Assert.fail();
		}
		
		if(driver.findElement(By.xpath("//span[text()='No items to display.']")).isDisplayed()) {
			System.out.println("No account available for delete");
			driver.quit();
			Assert.fail();
			
			
		}else {
		
			// clicking on show actions dropdown for account

		try {
			WebElement ShowActionEle = driver.findElement(By.xpath("//span[text()='Show Actions']/parent::span"));
			wait.until(ExpectedConditions.visibilityOf(ShowActionEle));
			ShowActionEle.click();

		} catch (Exception e) {
			System.out.println("Problem while slecting the show actions menu :" + e.getMessage());
			Assert.fail();
		}
		// clciking on the delete button
		try {
			driver.findElement(By.xpath("//a[@title='Delete']")).click();
		} catch (Exception e) {
			System.out.println("Problem while clicking on the edit button :" + e.getMessage());
			Assert.fail();
		}
		// click on delete confirmation

		try {
			driver.findElement(By.xpath("//span[text()='Delete']")).click();
		} catch (Exception e) {
			System.out.println("Problem while clicking on the delete confirmation button :" + e.getMessage());
			Assert.fail();
		}
		// Capturing the delete successmessage
		WebElement alretElementEdit = driver.findElement(By.xpath("//span[@data-aura-class='forceActionsText']"));

		try {
			wait.until(ExpectedConditions.visibilityOf(alretElementEdit));
			String successEditMsg = alretElementEdit.getText();
			System.out.println(successEditMsg);
		} catch (Exception e) {
			System.out.println("Problem while capturing the delete success message : " + e.getMessage());
			Assert.fail();
		}
		// Searching for account
		try {

			WebElement SearchEle = driver.findElement(By.xpath("(//input[@type='search'])[2]"));
			SearchEle.clear();
			SearchEle.sendKeys("EditACcountByMamatha");
			SearchEle.sendKeys(Keys.ENTER);
			Thread.sleep(5000);
		} catch (Exception e) {
			System.out.println("Problem while Searching the account :" + e.getMessage());
			Assert.fail();
		}
		
		//delete confirmation
		try {
			if(driver.findElement(By.xpath("//span[text()='No items to display.']")).isDisplayed()) {
				System.out.println("Delete successful");
			}
		} catch (Exception e) {
			System.out.println("Problem while deleting the account or duplicate records exist :"+e.getMessage());
			driver.quit();
			Assert.fail();
		}
		}

	}

}
