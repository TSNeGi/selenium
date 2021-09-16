package learning.selenium;

import java.awt.AWTException;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;
import java.awt.Robot;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FileUpload {

	public static void main(String[] args) throws InterruptedException, AWTException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\src\\test\\resources\\chromedriver.exe");
		
		System.out.println("Step 1: Opening Chrome in Incognito Code");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		options.merge(capabilities);
		WebDriver drv = new ChromeDriver(options);
		drv.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		System.out.println("Step 2: Maximized the window");
		drv.manage().window().maximize();
		drv.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);// for page load
		drv.get("https://www.grammarly.com/plagiarism-checker");// open testing website
		drv.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);// for Implicit wait

		JavascriptExecutor js = (JavascriptExecutor) drv; // Scroll operation using Js Executor
		js.executeScript("window.scrollBy(0,200)"); // Scroll Down(+ve) upto browse option
		//Thread.sleep(2000); // suspending execution for specified time period

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
		drv.close();
	}

}