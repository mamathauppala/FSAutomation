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

public class EditLegalEntity {

	public static void main(String[] args) throws InterruptedException {
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
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println(
					"Problem while searching for legal entities or clicking on legal entities :" + e.getMessage());
			driver.quit();
			Assert.fail();
		}
		// Searching for legal entity
		try {
			WebElement SearchEle = driver.findElement(By.xpath("(//input[@type='search'])[2]"));
			SearchEle.sendKeys("Salesforce Automation by Mamatha");
			SearchEle.sendKeys(Keys.ENTER);
			Thread.sleep(5000);
		} catch (Exception e) {
			System.out.println("Problem while Searching the account :" + e.getMessage());
			Assert.fail();
		}
		try {
			WebElement ShowActionEle = driver
					.findElement(By.xpath("//span[text()='Show Actions']/preceding-sibling::span"));
			wait.until(ExpectedConditions.visibilityOf(ShowActionEle));
			ShowActionEle.click();
		} catch (Exception e) {
			System.out.println("Problem while slecting the show actions menu :"+e.getMessage());
			Assert.fail();
		}
		//Clicking on the edit button
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//Thread.sleep(3000);
		try {
			WebElement EditEle=driver.findElement(By.xpath("//a[@title='Edit']"));
			//wait.until(ExpectedConditions.visibilityOf(EditEle));
			js.executeScript("arguments[0].click();",EditEle );
			//driver.findElement(By.xpath("(//a[@title='Edit']")).click();
		} catch (Exception e) {
			System.out.println("Problem while clicking on the edit button :"+e.getMessage());
			Assert.fail();
		}
		driver.findElement(By.xpath("//span[text()='Company Name']/parent::label/following-sibling::input")).clear();
		driver.findElement(By.xpath("//span[text()='Company Name']/parent::label/following-sibling::input")).sendKeys("TestLeaf");
		driver.findElement(By.xpath("//span[text()='Description']/parent::label/following-sibling::textarea")).clear();		
		driver.findElement(By.xpath("//span[text()='Description']/parent::label/following-sibling::textarea")).sendKeys("Sales");
		driver.findElement(By.xpath("//span[text()='Street']/parent::label/following-sibling::textarea")).clear();		
		driver.findElement(By.xpath("//span[text()='Street']/parent::label/following-sibling::textarea")).sendKeys("Nethaji");
		driver.findElement(By.xpath("//div[@class='uiPopupTrigger']/div/div/a[@class='select']")).click();
		driver.findElement(By.xpath("//a[@title='Active']")).click();
		driver.findElement(By.xpath("//button[@data-aura-class='uiButton forceActionButton']/span[text()='Save']")).click();
		//Capturing the edit successmessage
				WebElement alretElementEdit = driver.findElement(By.xpath("//span[@data-aura-class='forceActionsText']"));

				try {
					wait.until(ExpectedConditions.visibilityOf(alretElementEdit));
					String successEditMsg = alretElementEdit.getText();
					System.out.println(successEditMsg);
				} catch (Exception e) {
					System.out.println("Problem while capturing the edit success message : "+ e.getMessage());
					Assert.fail();
				}
				Thread.sleep(3000);
				try {
					WebElement ShowActionEle = driver
							.findElement(By.xpath("//span[text()='Show Actions']/preceding-sibling::span"));
					wait.until(ExpectedConditions.visibilityOf(ShowActionEle));
					ShowActionEle.click();
				} catch (Exception e) {
					System.out.println("Problem while slecting the show actions menu :"+e.getMessage());
					Assert.fail();
				}
				try {
					WebElement ShowActionEle = driver
							.findElement(By.xpath("//span[text()='Show Actions']/preceding-sibling::span"));
					wait.until(ExpectedConditions.visibilityOf(ShowActionEle));
					ShowActionEle.click();
				} catch (Exception e) {
					System.out.println("Problem while slecting the show actions menu :"+e.getMessage());
					Assert.fail();
				}
				//Clicking on the edit button
				
				//Thread.sleep(3000);
				try {
					WebElement EditEle=driver.findElement(By.xpath("//a[@title='Edit']"));
					//wait.until(ExpectedConditions.visibilityOf(EditEle));
					js.executeScript("arguments[0].click();",EditEle );
					//driver.findElement(By.xpath("(//a[@title='Edit']")).click();
				} catch (Exception e) {
					System.out.println("Problem while clicking on the edit button :"+e.getMessage());
					Assert.fail();
				}
				
				if(driver.findElement(By.xpath("//a[text()='Active']")).isDisplayed()) {
					System.out.println("Status is Active .Passed");
				}else {
					System.out.println("Status not updated to Active .Test case failed");
					driver.quit();
					Assert.fail();
				}
			
	}

}
