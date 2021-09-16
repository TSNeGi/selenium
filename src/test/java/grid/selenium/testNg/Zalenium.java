package grid.selenium.testNg;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Zalenium {

	RemoteWebDriver driver;
	DesiredCapabilities dc = new DesiredCapabilities();
	// wait = new WebDriverWait(driver, 30);

	@Parameters("browser")
	@BeforeTest
	public void beforeTest(String br) throws MalformedURLException {

		if (br.equals("chrome")) {
			dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
			dc.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);

		} else if (br.equals("firefox")) {
			dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);
			dc.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
		}
		URL url = new URL("http://localhost:4444/wd/hub ");

		driver = new RemoteWebDriver(url, dc);

		driver.get("https://demo.nopcommerce.com/");
		driver.manage().window().maximize();
	}

	@Test
	public void search() throws InterruptedException {
		driver.findElement(By.name("q")).sendKeys("Nokia");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.linkText("Nokia Lumia 1020")).click();
		
		driver.navigate().refresh();
		Assert.assertEquals(driver.getTitle(), "nopCommerce demo store. Nokia Lumia 1020");
	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}

}
