package browser;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifySortingAccount {
	public static List<String> ActualAccList=new ArrayList<String>();
	public static List<String> ExpectedAccList=new ArrayList<String>();;
	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
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
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			WebElement AccEle = driver.findElement(By.xpath("//a[@title='Accounts']/span"));
			js.executeScript("arguments[0].click();", AccEle);
		} catch (Exception e) {
			System.out.println("Problem while clicking on the accounts link :" + e.getMessage());
			Assert.fail();
		}
		List<WebElement> accs=driver.findElements(By.xpath("//table[@role='grid']/tbody/tr/th/span"));
		for(WebElement acc: accs) {
			ExpectedAccList.add(acc.getText());
			
		}
		Collections.sort(ExpectedAccList,String.CASE_INSENSITIVE_ORDER);
		System.out.println(ExpectedAccList);
		

		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[@title='Account Name']")));
		Thread.sleep(10000);
		
		List<WebElement> allaccs=driver.findElements(By.xpath("//table[@role='grid']/tbody/tr/th/span"));
		for(WebElement acc: allaccs) {
			ActualAccList.add(acc.getText());
			
		}
		System.out.println(ActualAccList);
		 if(ActualAccList.equals(ExpectedAccList)) {
			 System.out.println("Sorted : Passed");
		 }else {
			 driver.quit();
			 System.out.println("Not sorted correctly");
			 Assert.fail();
		 }
				 
		
		
	}

}
