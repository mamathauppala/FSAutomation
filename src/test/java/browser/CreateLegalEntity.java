package browser;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateLegalEntity extends BaseClass{
@Test
	public  void salesLegalEntity() {
		
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		

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
			driver.findElement(
					By.xpath("//mark[text()='Legal Entities']")).click();
		} catch (Exception e) {
			System.out.println("Problem while searching for legal entities or clicking on legal entities :" + e.getMessage());
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
		try {
			driver.findElement(By.xpath("//span[text()='Name']/parent::label/following-sibling::input")).sendKeys("Salesforce Automation by Mamatha");
			driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();
		} catch (Exception e) {
			System.out.println("Problem while entering the data or clicking on save buton");
			Assert.fail();
		}
		String successAlertMessage="";
		try {
			WebElement alretElement=driver.findElement(By.xpath("//span[@data-aura-class='forceActionsText']"));
			//WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(30));		
			wait.until(ExpectedConditions.visibilityOf(alretElement));
			successAlertMessage = alretElement.getText();
			System.out.println(successAlertMessage);
		} catch (Exception e) {
			System.out.println("Problem while showing success alert :"+e.getMessage());
			driver.quit();
			Assert.fail();
		}
   if(successAlertMessage.equals("Legal Entity \"Salesforce Automation by Mamatha\" was created.")) {
			
			System.out.println("Passed");
			
		}else {
			System.out.println("Probem in showing the success alert.Expected : Legal Entity \"Salesforce Automation by Mamatha\" was created." +"  Actual: "+successAlertMessage);
			driver.quit();
			Assert.fail();
			
		}
	}

}
