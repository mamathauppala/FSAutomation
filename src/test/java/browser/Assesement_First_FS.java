package browser;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

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
		/* Test
		//getting the closed ,open values and set goal
		
		//try {
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
		/*} catch (Exception e) {
			System.out.println("problem while getting values or setting goal : "+e.getMessage());
			Assert.fail();
		}*/
		
		try {
			//clicking on the dropdown menu to select Dashboard
			
			driver.findElement(By.xpath("//button[@title='Show Navigation Menu']")).click();
			driver.findElement(By.xpath("//span[text()='Dashboards']")).click();
		} catch (Exception e) {
			System.out.println("Problem while selecting the Dashboards menu : "+e.getMessage());
			Assert.fail();
		}
		
		
		
		//clicking on dashboard button
		
		try {
			driver.findElement(By.xpath("//div[text()='New Dashboard']")).click();
		} catch (Exception e) {
			System.out.println("Problem while clicking on the New dashboard button : "+e.getMessage());
			Assert.fail();
		}
		
		
		
		try {
			//Enter dashboard details
			WebElement dashboardname=driver.findElement(By.xpath("//input[@id='dashboardNameInput']"));
			
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
			//driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title=\"dashboard\"]")));
			dashboardname.sendKeys("Mamatha Dashboard");
			driver.findElement(By.xpath("//input[@id='dashboardDescriptionInput']")).sendKeys("Dashboard desc");
			driver.findElement(By.xpath("//button[text()='Create']")).click();
		} catch (Exception e) {
			System.out.println("Problem while entering/saving dashboard details :  "+e.getMessage());
			Assert.fail();
		}
		driver.switchTo().defaultContent();
		try {
			driver.findElement(By.linkText("Private Dashboards")).click();
		} catch (Exception e) {
			System.out.println("Problem while clicking on the private dashboards : "+e.getMessage());
			Assert.fail();
		}
		try {
			driver.findElement(By.xpath("//input[@placeholder='Search private dashboards...']")).sendKeys("Mamatha");
			driver.findElement(By.xpath("//input[@placeholder='Search private dashboards...']")).sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.out.println("Problem while searching the dashboard records : "+e.getMessage());
			Assert.fail();
		}
		
		List<WebElement> dashRecords=driver.findElements(By.xpath("//span[text()='No results found']"));
		
		if(dashRecords.size()>0) {
			System.out.println("No Records Found to Delete the dash board");
			Assert.fail();
		}else {
			driver.findElement(By.xpath("//table[@role='grid']/tbody/tr/td[6]/lightning-primitive-cell-factory/span")).click();
			driver.findElement(By.xpath("//a[@role='menuitem']/span[text()='Delete']")).click();
		}
		
	}

}
