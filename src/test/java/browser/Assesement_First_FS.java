package browser;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assesement_First_FS {

	public static void main(String[] args) {
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
			driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys("Service Console");
			
			// clicking on sales button
			driver.findElement(
					By.xpath("//mark[text()='Service Console']"))
					.click();
					
		} catch (Exception e) {
			System.out.println("Problem while searching for sales or clicking on sales :"+e.getMessage());
			Assert.fail();
		}

		
		try {
			//clicking on the dropdown menu to select home
			
			driver.findElement(By.xpath("//button[@title='Show Navigation Menu']")).click();
			driver.findElement(By.xpath("//span[text()='Home']")).click();
		} catch (Exception e) {
			System.out.println("Problem while selecting the home menu : "+e.getMessage());
			Assert.fail();
		}
		
		//getting the closed ,open values and set goal
		
		try {
			String closedValue=driver.findElement(By.xpath("(//ul[@class='metricRow']/li/span[@data-aura-class='uiOutputText'])[1]")).getText();
			System.out.println(closedValue);
			closedValue = closedValue.replaceAll("[^\\d]", " ");
			System.out.println(closedValue);
			String openValue=driver.findElement(By.xpath("(//ul[@class='metricRow']/li/span[@data-aura-class='uiOutputText'])[2]")).getText();
			System.out.println(openValue);
			openValue = closedValue.replaceAll("[^\\d]", " ");
			System.out.println(openValue);
			
			int sum=Integer.parseInt(closedValue)+Integer.parseInt(openValue);
			if(sum<10000) {
				driver.findElement(By.xpath("//*[@data-key='edit']")).click();
				driver.findElement(By.xpath("//div[@class='goalInputRow']/input")).sendKeys("10000");
				driver.findElement(By.xpath("//span[text()='Save']")).click();
				
			}
		} catch (Exception e) {
			System.out.println("problem while getting values or setting goal : "+e.getMessage());
			Assert.fail();
		}
	}

}
