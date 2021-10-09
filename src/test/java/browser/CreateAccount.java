package browser;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateAccount extends BaseClass {
@Test
	public  void salescreateaccount() throws InterruptedException {
		
		

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
			//clicking on the Accounts
			driver.findElement(By.xpath("//a[@title='Accounts']/following-sibling::one-app-nav-bar-item-dropdown/div")).click();
		} catch (Exception e) {
			System.out.println("Problem while clicking on the accounts dropdown :"+e.getMessage());
			Assert.fail();
		}
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		try {
			WebElement ele=driver.findElement(By.xpath("//span[text()='New Account']"));
			 
			js.executeScript("arguments[0].click();",ele);
		} catch (Exception e) {
			System.out.println("Problem while clicking on the new account button : "+e.getMessage());
			Assert.fail();
		}
		try {
			driver.findElement(By.xpath("//input[@name='Name']")).sendKeys("Salesforce Automation by Mamatha");
			String Text =driver.findElement(By.xpath("//input[@name='Name']")).getText();
			WebElement Element=driver.findElement(By.xpath("//label[text()='Ownership']/following::div"));
			js.executeScript("arguments[0].scrollIntoView();", Element);		
			Element.click();
			driver.findElement(By.xpath("//div/lightning-base-combobox-item[@data-value='Public']")).click();		
			driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
		} catch (Exception e) {
			System.out.println("Problem while entering or saving the account : "+e.getMessage());
			Assert.fail();
		}
		String successAlertMessage="";
		try {
			WebElement alretElement=driver.findElement(By.xpath("//span[@data-aura-class='forceActionsText']"));
			WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(30));		
			wait.until(ExpectedConditions.visibilityOf(alretElement));
			successAlertMessage = alretElement.getText();
			System.out.println(successAlertMessage);
		} catch (Exception e) {
			System.out.println("Problem while showing success alert :"+e.getMessage());
			Assert.fail();
		}
			String ActualMessage="Account \"Salesforce Automation by Mamatha\" was created.";
		System.out.println(ActualMessage);
		//Assert.assertEquals(successAlertMessage, "Opportunity \"Salesforce Automation by Mamatha\" was created.");
		
		if(successAlertMessage.equals("Account \"Salesforce Automation by Mamatha\" was created.")) {
			
			System.out.println("Passed");
			
		}else {
			System.out.println("Probem in showing the success alert.Expected : "+ActualMessage +"  Actual: "+successAlertMessage);
			driver.quit();
			Assert.fail();
			
		}
		
	}	
	

}
