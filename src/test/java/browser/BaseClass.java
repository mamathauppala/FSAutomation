package browser;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;



public class BaseClass {
	public  ChromeDriver driver;
	@Parameters({"url","username","password"})
	@BeforeMethod
	public void baseClasssales(String url,String username,String password) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		 driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		

		try {
			// launch the application
			driver.get(url);
			// sign in
			driver.findElement(By.id("username")).sendKeys(username);
			driver.findElement(By.id("password")).sendKeys(password);
			driver.findElement(By.id("Login")).click();
		} catch (Exception e) {
			System.out.println("Problem while logging into the application :"+ e.getMessage());
			Assert.fail();
		}
		
		
	}
	@AfterMethod
	public void  Postmethod() {
		driver.close();
	}
	

}
