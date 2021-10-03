package browser;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateTaskWithoutMand {

	public static void main(String[] args) {
		
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			ChromeDriver driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

			try {
				// launch the application sales
				driver.get("https://login.salesforce.com/");
				// sign in
				driver.findElement(By.id("username")).sendKeys("fullstack@testleaf.com");
				driver.findElement(By.id("password")).sendKeys("SelBootcamp$123");
				driver.findElement(By.id("Login")).click();
			} catch (Exception e) {
				System.out.println("Problem while logging into the application :"+ e.getMessage());
				Assert.fail();
			}
			//click on global actions
			try {
				driver.findElement(By.xpath("//div[@data-aura-class='oneGlobalCreate']/div/div/div/div/a")).click();
			} catch (Exception e) {
				System.out.println("problem while clicking on the Global Actions : "+e.getMessage());
				driver.quit();
				Assert.fail();
			}
			//click on the new task
			try {
				driver.findElement(By.xpath("//span[text()='New Task']")).click();
			} catch (Exception e) {
				System.out.println("problem while clicking on the New Task button : "+e.getMessage());
				driver.quit();
				Assert.fail();
			}
			
			try {
				//enter Data for task
				driver.findElement(By.xpath("//a[@aria-label='Name—Current Selection: Contacts, Pick an object']")).click();
				driver.findElement(By.xpath("//span[@title='Contacts']")).click();
				driver.findElement(By.xpath("//input[@title='Search Contacts']")).click();
				driver.findElement(By.xpath("//a[@role='option']/div[2]/div[1]")).click();
				/*WebElement maxele=driver.findElement(By.xpath("//svg[@data-key='expand_alt']"));
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();",maxele );*/
				driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();
			} catch (Exception e) {
				System.out.println("problem while clicking on the entering data or click on save button : "+e.getMessage());
				driver.quit();
				Assert.fail();
			}
			//checking successalert message
			String successAlertMessage="";
			try {
				WebElement alretElement=driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div/div/span"));
				WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(30));		
				wait.until(ExpectedConditions.visibilityOf(alretElement));
				successAlertMessage = alretElement.getText();
				System.out.println(successAlertMessage);
				driver.quit();
			} catch (Exception e) {
				System.out.println("Problem while showing success alert :"+e.getMessage());
				driver.quit();
				Assert.fail();
			}
			
			


	}

}
