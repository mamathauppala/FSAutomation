package browser;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CustomerServiceOptions {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

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
			driver.quit();
			Assert.fail();
		}

		try {
			driver.findElement(By.xpath("//span[text()='Learn More']")).click();
		} catch (Exception e) {
			System.out.println("Problem while clicking on the learn more button ");
			driver.quit();
			Assert.fail();
		}
		Thread.sleep(5000);
		String parent = driver.getWindowHandle();
		System.out.println("parent window" + driver.getTitle());
		Set<String> allWindows = driver.getWindowHandles();
		int count = allWindows.size();
		System.out.println("window count :"+count);
		for (String child : allWindows) {
			if (!parent.equalsIgnoreCase(child)) {
				driver.switchTo().window(child);
			}

		}
		System.out.println("child window" + driver.getTitle());
		Thread.sleep(9000);
		WebElement ele = driver.findElement(By.xpath("//span[text()='Products']"));
		Actions action = new Actions(driver);
		action.moveToElement(ele).build().perform();
		driver.findElement(By.xpath("//span[text()='Service']")).click();
	}

}
