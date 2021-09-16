package dataProvider;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class GoogleSearch extends Excel {

	WebDriver driver;
	Map<String, String> result = new HashMap<String, String>();

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir")
						+ "\\src\\test\\resources\\chromedriver.exe");

	}

	@Test(dataProvider = "dp")
	public void googleIt(String keywords) {
		driver = new ChromeDriver();

		driver.manage().window().maximize();
//		System.out.println(Thread.currentThread().getId());

		driver.get("https://www.google.com");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// WebDriverWait wait = new WebDriverWait(driver, 10);
		// wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
		driver.findElement(By.name("q")).sendKeys(keywords);

		driver.findElement(By.name("btnK")).click();

		result.put(keywords,
				driver.findElement(
						By.xpath("//cite[@class='iUh30 Zu0yb tjvcx']"))
						.getText());

	}

	 @Test
	 public void setExcel() {
	 String setResult=Excel.addinExcel("\\src\\test\\resources\\results1.xlsx",
	 "Sheet1", result);
	 System.out.println(setResult);
	 }

	@DataProvider(name = "dp") // ,parallel = true
	public Object[][] dp() {
		try {
			return Excel.getArray("\\src\\test\\resources\\keywords.xlsx",
					"Sheet1");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Object[][]{{}};
	}

	@AfterMethod
	public void burnDown() {
		driver.quit();
	}

}
