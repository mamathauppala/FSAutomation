package browser;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EditAccount {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		//System.setProperty("webdriver.chrome.driver", "D:\\SelPreparation\\MavenProject\\src\\test\\resources\\Browsers\\chromedriver.exe");
		
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
		try {
			// clicking on the Accounts
			driver.findElement(By.xpath("//a[@title='Accounts']/following-sibling::one-app-nav-bar-item-dropdown/div"))
					.click();
		} catch (Exception e) {
			System.out.println("Problem while clicking on the accounts dropdown :" + e.getMessage());
			Assert.fail();
		}
		// creating new account
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			WebElement ele = driver.findElement(By.xpath("//span[text()='New Account']"));

			js.executeScript("arguments[0].click();", ele);
		} catch (Exception e) {
			System.out.println("Problem while clicking on the new account button : " + e.getMessage());
			Assert.fail();
		}
		try {
			driver.findElement(By.xpath("//input[@name='Name']")).sendKeys("EditACcountByMamatha");
			String Text = driver.findElement(By.xpath("//input[@name='Name']")).getText();
			WebElement Element = driver.findElement(By.xpath("//label[text()='Ownership']/following::div"));
			js.executeScript("arguments[0].scrollIntoView();", Element);
			Element.click();
			driver.findElement(By.xpath("//div/lightning-base-combobox-item[@data-value='Public']")).click();
			driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
		} catch (Exception e) {
			System.out.println("Problem while entering or saving the account : " + e.getMessage());
			Assert.fail();
		}
		String successAlertMessage = "";
		WebElement alretElement = driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div/div/span"));

		try {
			wait.until(ExpectedConditions.visibilityOf(alretElement));
			successAlertMessage = alretElement.getText();
			System.out.println(successAlertMessage);
		} catch (Exception e) {
			System.out.println("Problem while showing success alert :" + e.getMessage());
			Assert.fail();
		}
		String ActualMessage = "Account \"EditACcountByMamatha\" was created.";
		System.out.println(ActualMessage);
		// Assert.assertEquals(successAlertMessage, "Opportunity \"Salesforce Automation
		// by Mamatha\" was created.");

		if (successAlertMessage.equals("Account \"EditACcountByMamatha\" was created.")) {

			System.out.println("Passed");
			
		} else {
			System.out.println("Probem in showing the success alert.Expected : " + ActualMessage + "  Actual: "
					+ successAlertMessage);
			driver.quit();
			Assert.fail();

		}
//Editing account
		//clicking on the account link
		try {
			WebElement AccEle = driver.findElement(By.xpath("//a[@title='Accounts']/span"));
			js.executeScript("arguments[0].click();", AccEle);
		} catch (Exception e) {
			System.out.println("Problem while clicking on the accounts link :"+e.getMessage());
			Assert.fail();
		}
		//Searching for account
		try {
			WebElement SearchEle = driver.findElement(By.xpath("(//input[@type='search'])[2]"));
			SearchEle.sendKeys("EditACcountByMamatha");
			SearchEle.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.out.println("Problem while Searching the account :"+e.getMessage());
			Assert.fail();
		}
		//clicking on show actions dropdown for account

		try {
			WebElement ShowActionEle = driver
					.findElement(By.xpath("//span[text()='Show Actions']/preceding-sibling::span"));
			wait.until(ExpectedConditions.visibilityOf(ShowActionEle));
			ShowActionEle.click();
		} catch (Exception e) {
			System.out.println("Problem while slecting the show actions menu :"+e.getMessage());
			Assert.fail();
		}
		//clciking on the edit button
		try {
			driver.findElement(By.xpath("(//a[@title='Edit']")).click();
		} catch (Exception e) {
			System.out.println("Problem while clicking on the edit button :"+e.getMessage());
			Assert.fail();
		}
		//Entering data for editing account
		String mobileNum="9878789871";
		try {
			driver.findElement(By.xpath("//input[@name='Phone']")).sendKeys(mobileNum);
			//mobileNum=driver.findElement(By.xpath("//input[@name='Phone']")).getText();
			System.out.println(mobileNum);
			WebElement typeElement = driver.findElement(By.xpath("//label[text()='Type']/following::div"));
			js.executeScript("arguments[0].scrollIntoView();", typeElement);
			typeElement.click();
			driver.findElement(By.xpath("//div/lightning-base-combobox-item[@data-value='Technology Partner']")).click();
			WebElement industryElement = driver.findElement(By.xpath("//label[text()='Industry']/following::div"));
			js.executeScript("arguments[0].scrollIntoView();", industryElement);
			industryElement.click();
			driver.findElement(By.xpath("//div/lightning-base-combobox-item[@data-value='Healthcare']")).click();
			driver.findElement(By.xpath("(//textarea[@name='street'])[1]")).sendKeys("Test leaf");
			driver.findElement(By.xpath("(//textarea[@name='street'])[2]")).sendKeys("Test leaf1");
			driver.findElement(By.xpath("(//input[@name='city'])[1]")).sendKeys("chennai");
			driver.findElement(By.xpath("(//input[@name='city'])[2]")).sendKeys("chennai");
			driver.findElement(By.xpath("(//input[@name='province'])[1]")).sendKeys("Tamilnadu");
			driver.findElement(By.xpath("(//input[@name='province'])[2]")).sendKeys("Tamilnadu");
			driver.findElement(By.xpath("(//input[@name='postalCode'])[1]")).sendKeys("600045");
			driver.findElement(By.xpath("(//input[@name='postalCode'])[2]")).sendKeys("600045");
			driver.findElement(By.xpath("(//input[@name='country'])[1]")).sendKeys("India");
			driver.findElement(By.xpath("(//input[@name='country'])[2]")).sendKeys("India");

			WebElement CustomerElement = driver
					.findElement(By.xpath("//label[text()='Customer Priority']/following::div/div/input"));
			js.executeScript("arguments[0].scrollIntoView();", CustomerElement);
			CustomerElement.click();
			driver.findElement(By.xpath("//div/lightning-base-combobox-item[@data-value='Low']")).click();

			WebElement slaElement = driver.findElement(By.xpath("//label[text()='SLA']/following::div/div/input"));
			js.executeScript("arguments[0].scrollIntoView();", slaElement);
			slaElement.click();
			driver.findElement(By.xpath("//div/lightning-base-combobox-item[@data-value='Silver']")).click();

			WebElement UpsellElement = driver
					.findElement(By.xpath("//label[text()='Upsell Opportunity']/following::div/div/input"));
			js.executeScript("arguments[0].scrollIntoView();", UpsellElement);
			UpsellElement.click();
			driver.findElement(By.xpath("//div/lightning-base-combobox-item[@data-value='No']")).click();

			WebElement ActiveElement = driver.findElement(By.xpath("//label[text()='Active']/following::div/div/input"));
			js.executeScript("arguments[0].scrollIntoView();", ActiveElement);
			UpsellElement.click();
			driver.findElement(By.xpath("//div/lightning-base-combobox-item[@data-value='No']")).click();
			driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
		} catch (Exception e) {
			System.out.println("Problem while entering the data for edit or clicking on save button :"+e.getMessage());
			Assert.fail();
		}
		
		//Capturing the edit successmessage
		WebElement alretElementEdit = driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div/div/span"));

		try {
			wait.until(ExpectedConditions.visibilityOf(alretElementEdit));
			String successEditMsg = alretElementEdit.getText();
			System.out.println(successEditMsg);
		} catch (Exception e) {
			System.out.println("Problem while capturing the edit success message : "+ e.getMessage());
			Assert.fail();
		}
		
		//Capturing the mobile number after  edit success
		String actualMobilenum=driver.findElement(By.xpath("//table[@data-aura-class='uiVirtualDataTable']/tbody/tr[1]/td[4]/span/span[1]")).getText();
		System.out.println(actualMobilenum);
		actualMobilenum=actualMobilenum.replaceAll("[^a-zA-Z0-9]", " ");
		actualMobilenum=actualMobilenum.replaceAll(" ", "");
		System.out.println(actualMobilenum);
		//verifying the mobile number
		if(actualMobilenum.equals(mobileNum)) {
			System.out.println("Mobile number matched ");
			driver.quit();
		}else {
			System.out.println("Mobile number not matched .Expected : "+mobileNum+ " Actual : "+actualMobilenum);
			driver.quit();
			Assert.fail();
		}

	}

}
