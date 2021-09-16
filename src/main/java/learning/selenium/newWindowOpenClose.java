package learning.selenium;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class newWindowOpenClose {
	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\src\\test\\resources\\chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		options.merge(capabilities);

		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		System.out.println("Step 1: Opening Chrome in Incognito Code");
		driver.manage().window().maximize();
		
		System.out.println("Step 2: Maximized the window");

		driver.get("https://www.google.com");
		
		System.out.println("Step 3.1: Open google.com");
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("test");
		driver.findElement(By.name("btnK")).click();
		
		System.out.println("Step 3.2: Searched 'test' ");

		List<WebElement> searchedResultUrl = driver.findElements(
				By.xpath("//div[@class='tF2Cxc']//a[contains(@data-ved,'2ah') and contains(@href,'https')]"));
		ArrayList<String> urls = new ArrayList<String>();

		ArrayList<String> windowUrls = new ArrayList<String>();
		ArrayList<String> windowTitle = new ArrayList<String>();
		for (WebElement e : searchedResultUrl) {
			urls.add(e.getAttribute("href"));

		}
		System.out.println("Step 4.1:  collected link  of searched page ");
		Actions actions = new Actions(driver);
		
		for (int i = 0; i < 4; i++) {

			actions.keyDown(Keys.LEFT_CONTROL).click(searchedResultUrl.get(i)).keyUp(Keys.LEFT_CONTROL).build()
					.perform();

		}
		System.out.println("Step 4.2:  Opened the top results in new windows  ");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		for (int i = 1; i < 5; i++) {
			driver.switchTo().window(tabs.get(i));
			windowUrls.add(driver.getCurrentUrl());
			windowTitle.add(driver.getTitle());
			System.out.println("	switching to Window :"+i);

		}
		System.out.println("Step 4.3: Switched to each windows | collected url of tabs");
		System.out.println("Step 5 Verifying searched page url with open windows url ");
		System.out.println(urls.containsAll(windowUrls) ? "Links are matched" : "Broken links");
		System.out.println("Step 5.1 Verified searched page url with open windows url ");
		driver.quit();
		System.out.println("Step 6:Closed each window | End the program");
	}
}
