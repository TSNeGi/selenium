package testNg.selenium;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class fileUpload {
	WebDriver drv;
	
	@BeforeTest
	void setup(){
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\src\\test\\resources\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		options.merge(capabilities);
		drv = new ChromeDriver(options);
		
	}
	
	@Test
	void upload() throws AWTException, InterruptedException {
		drv.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		drv.manage().window().maximize();
		drv.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);// for page load
		drv.get("https://www.grammarly.com/plagiarism-checker");// open testing website
		drv.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);// for Implicit wait

		JavascriptExecutor js = (JavascriptExecutor) drv; // Scroll operation using Js Executor
		js.executeScript("window.scrollBy(0,200)"); // Scroll Down(+ve) upto browse option
	

		WebElement browse = drv.findElement(By.id("file-upload-form"));
		// using linkText, to click on browse element
		browse.click(); // Click on browse option on the webpage
		
		// creating object of Robot class
		Robot rb = new Robot();

		// copying File path to Clipboard
		StringSelection str = new StringSelection("C:\\Users\\tanegi\\Downloads\\hello.txt");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

		// press Contol+V for pasting
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);

		// release Contol+V for pasting
		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);

		// for pressing and releasing Enter
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);

	}
	
	@AfterTest
	void tearDown() {
		drv.close();
		
	}
}
