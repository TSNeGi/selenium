package learning.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UploadDoc {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\src\\test\\resources\\chromedriver.exe");
		
		
		System.out.println("Step 1: Opening Chrome in Incognito Code");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		options.merge(capabilities);
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		
		System.out.println("Step 2: Maximized the window");
		driver.manage().window().maximize();
		
		System.out.println("Step 3: Go to Olx.in | Select categories | Click on OLX Autos");
		driver.get("https://smallpdf.com/pdf-to-word");
		
		driver.findElement(By.xpath("//button//span[contains(text(),'Choose File')]")).sendKeys("C:\\Users\\tanegi\\Downloads\\demo.pdf");
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[contains(text(),'Convert to Word')]"), "Convert to Word"));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[contains(text(),'Convert to Word')]")).click();
		
		driver.close();
	}
	
}
