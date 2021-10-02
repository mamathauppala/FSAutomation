package browser;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Opportunity {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		// download browser
		WebDriverManager.chromedriver().setup();
		// Handle notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		// launch the browser

		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		try {
			// launch the application
			driver.get("https://login.salesforce.com/");
			// sign in
			driver.findElement(By.id("username")).sendKeys("fullstack@testleaf.com");
			driver.findElement(By.id("password")).sendKeys("SelBootcamp$123");
			driver.findElement(By.id("Login")).click();
		} catch (Exception e) {
			System.out.println("Problem while logging into the application :"+ e.getMessage());
			Assert.fail();
		}
		

		try {
			// Click on toggle menu button from the left corner
			driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		} catch (Exception e) {
			System.out.println("Problem while clicking on the toggle menu from left corner :"+e.getMessage());
			Assert.fail();
			
		}
		try {
			// clicking on view all
			driver.findElement(By.xpath("//button[text()='View All']")).click();
		} catch (Exception e) {
			System.out.println("Problem while clicking on the view all button :"+e.getMessage());
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
			System.out.println("Problem while searching for sales or clicking on sales :"+e.getMessage());
			Assert.fail();
		}

		try {
			driver.findElement(By.xpath("//a[@title='Opportunities']/following-sibling::one-app-nav-bar-item-dropdown/div")).click();
		} catch (Exception e) {
			System.out.println("Problem while clicking on the opportunities dropdown :"+e.getMessage());
			Assert.fail();
		}
		try {
			WebElement ele=driver.findElement(By.xpath("//span[text()='New Opportunity']"));
			JavascriptExecutor js = (JavascriptExecutor) driver;  
			js.executeScript("arguments[0].click();",ele);
		} catch (Exception e) {
			System.out.println("Problem while clicking on the New opportunity button");
			Assert.fail();
		}
		
		try {
			driver.findElement(By.xpath("//input[@name='Name']")).sendKeys("Salesforce Automation by Mamatha");
			String Text =driver.findElement(By.xpath("//input[@name='Name']")).getText();
			Date tdate=new Date();
			SimpleDateFormat formatter=new SimpleDateFormat("M/dd/yyy");
			String today=formatter.format(tdate);
			driver.findElement(By.xpath("//input[@name='CloseDate']")).sendKeys(today);
			driver.findElement(By.xpath("//label[text()='Stage']/following::div")).click();
			driver.findElement(By.xpath("//div/lightning-base-combobox-item[@data-value='Needs Analysis']")).click();
					
			driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
		} catch (Exception e) {
			System.out.println("Problem while entering data for  the opportunity or saving the opportunity:"+e.getMessage());
			Assert.fail();
		}
		String successAlertMessage="";
		try {
			WebElement alretElement=driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div/div/span"));
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(30));		
			wait.until(ExpectedConditions.visibilityOf(alretElement));
			successAlertMessage = alretElement.getText();
			System.out.println(successAlertMessage);
		} catch (Exception e) {
			System.out.println("Problem while showing success alert :"+e.getMessage());
			Assert.fail();
		}
		String actualMessage="Opportunity \"Salesforce Automation by Mamatha\" was created.";
		System.out.println(actualMessage);
		//Assert.assertEquals(successAlertMessage, "Opportunity \"Salesforce Automation by Mamatha\" was created.");
		
		if(successAlertMessage.equals("Opportunity \"Salesforce Automation by Mamatha\" was created.")) {
			System.out.println("Opportunity created successfully");
			driver.quit();
		}else {
			System.out.println("Probem in showing the success alert.Expected : "+actualMessage +"  Actual: "+successAlertMessage);
			driver.quit();
			Assert.fail();
			
		}
		
		
		
	}

}
