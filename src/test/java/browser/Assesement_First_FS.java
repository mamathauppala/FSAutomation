package browser;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assesement_First_FS extends BaseClass{
	@Test

	public  void Assessement() throws InterruptedException {		

		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
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
			driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys("Service Console");

			// clicking on sales button
			driver.findElement(By.xpath("//mark[text()='Service Console']")).click();

		} catch (Exception e) {
			System.out.println("Problem while searching for sales or clicking on sales :" + e.getMessage());
			Assert.fail();
		}

		try {
			// clicking on the dropdown menu to select home

			driver.findElement(By.xpath("//button[@title='Show Navigation Menu']")).click();
			driver.findElement(By.xpath("//span[text()='Home']")).click();
		} catch (Exception e) {
			System.out.println("Problem while selecting the home menu : " + e.getMessage());
			Assert.fail();
		}

		// getting the closed ,open values and set goal

		
		  String closedValue=driver.findElement(By.xpath("(//ul[@class='metricRow']/li/span[@data-aura-class='uiOutputText'])[1]")).
		  getText(); System.out.println(closedValue); closedValue =
		  closedValue.replaceAll("[^\\d]", " "); System.out.println(closedValue);
		  String openValue=driver.findElement(By.xpath("(//ul[@class='metricRow']/li/span[@data-aura-class='uiOutputText'])[2]")).
		  getText(); System.out.println(openValue); openValue =
		  closedValue.replaceAll("[^\\d]", " "); System.out.println(openValue);		  
		  int sum=Integer.parseInt(closedValue)+Integer.parseInt(openValue);
		  if(sum<10000) {
		  driver.findElement(By.xpath("//*[@data-key='edit']")).click();
		  driver.findElement(By.xpath("//div[@class='goalInputRow']/input")).sendKeys("10000"); 
		  driver.findElement(By.xpath("//span[text()='Save']")).click(); 
		  }
		 

		try {
			// clicking on the dropdown menu to select Dashboard

			driver.findElement(By.xpath("//button[@title='Show Navigation Menu']")).click();
			driver.findElement(By.xpath("//span[text()='Dashboards']")).click();
		} catch (Exception e) {
			System.out.println("Problem while selecting the Dashboards menu : " + e.getMessage());
			Assert.fail();
		}

		// clicking on dashboard button

		try {
			driver.findElement(By.xpath("//div[text()='New Dashboard']")).click();
		} catch (Exception e) {
			System.out.println("Problem while clicking on the New dashboard button : " + e.getMessage());
			Assert.fail();
		}

		Thread.sleep(6000);

		try {
			// Enter dashboard details

			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='dashboard']")));
			WebElement dashboardname = driver.findElement(By.xpath("//input[@id='dashboardNameInput']"));

			dashboardname.sendKeys("Mamatha Dashboard");
			driver.findElement(By.xpath("//input[@id='dashboardDescriptionInput']")).sendKeys("Dashboard desc");
			driver.findElement(By.xpath("//button[text()='Create']")).click();
		} catch (Exception e) {
			System.out.println("Problem while entering/saving dashboard details :  " + e.getMessage());
			Assert.fail();
		}

		// Clicking on the Done Button
		driver.switchTo().defaultContent();
		Thread.sleep(5000);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Done']")));
		driver.findElement(By.xpath("//button[text()='Done']")).click();
		// driver.switchTo().defaultContent();
		// verifying the dashboard created or not
		Thread.sleep(3000);
		String dashTitle = driver.findElement(By.xpath("//h1/span[text()='Dashboard']/following-sibling::span"))
				.getText();
		try {
			if (dashTitle.equals("Mamatha Dashboard")) {
				System.out.println("Dashboard created successfully");
			}
		} catch (Exception e) {
			System.out.println("Problem with dashboard creation .Error :" + e.getMessage());
			Assert.fail();
		}

		driver.findElement(By.xpath("//button[text()='Subscribe']")).click();
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//span[text()='Daily']")).click();
		// 15. Time as 10:00 AM
		WebElement dropdown = driver.findElement(By.xpath("//select[contains(@class,'select')]"));
		Select option = new Select(dropdown);
		option.selectByValue("10");

		// 16. Click on Save
		driver.findElement(By.xpath("//button[@title='Save']")).click();
		Thread.sleep(5000);

		// Verify subscription success message
		try {
			String sucessALert = driver.findElement(By.xpath("//span[@data-aura-class='forceActionsText']")).getText();
			if (sucessALert.equalsIgnoreCase("Your subscription is all set.")) {
				System.out.println("Subscription success");
			}
		} catch (Exception e) {
			System.out.println("Problem while subscribing : " + e.getMessage());
			Assert.fail();
		}
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[@title=\"Dashboards\"]")).click();

		try {
			driver.findElement(By.linkText("Private Dashboards")).click();
		} catch (Exception e) {
			System.out.println("Problem while clicking on the private dashboards : " + e.getMessage());
			Assert.fail();
		}
		try {
			driver.findElement(By.xpath("//input[@placeholder='Search private dashboards...']")).sendKeys("Mamatha");
			driver.findElement(By.xpath("//input[@placeholder='Search private dashboards...']")).sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.out.println("Problem while searching the dashboard records : " + e.getMessage());
			Assert.fail();
		}

		List<WebElement> dashRecords = driver.findElements(By.xpath("//span[text()='No results found']"));

		if (dashRecords.size() > 0) {
			System.out.println("No Records Found to Delete the dash board");
			Assert.fail();
		} else {
			driver.findElement(By.xpath("//table[@role='grid']/tbody/tr/td[6]/lightning-primitive-cell-factory/span"))
					.click();
			driver.findElement(By.xpath("//a[@role='menuitem']/span[text()='Delete']")).click();
			driver.findElement(By.xpath("(//span[text()='Delete'])[2]")).click();

		}
		try {
			String deleteALert = driver.findElement(By.xpath("//span[@data-aura-class='forceActionsText']")).getText();
			if (deleteALert.equalsIgnoreCase("Dashboard was deleted.")) {
				System.out.println("delete was success");
			}
		} catch (Exception e) {
			System.out.println("Delete not success : " + e.getMessage());
			Assert.fail();

		}

	}

}
