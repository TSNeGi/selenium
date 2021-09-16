package learning.selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Xpath {
	public static void main(String[] args) throws InterruptedException {
		
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\chromedriver.exe");
		
		//Code to open chrome browser in incogito mode
		
		WebDriver driver=new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.manage().window().maximize();
		
		driver.get("https://www.flipkart.com");
		WebElement searchBox= driver.findElement(By.xpath("//input[@name='q']"));
		searchBox.sendKeys("Samsung");
		searchBox.submit();
		
		
		
//		WebElement searchButton= driver.findElement(By.xpath("//button[@class=\"L0Z3Pu\"]"));
//		searchButton.submit();
		
		WebElement showingResult = driver.findElement(By.xpath("//span[contains(text(),'Showing')]"));
		String resultText=showingResult.getText();
		resultText=resultText.substring(12, 14);
		
		
		List<WebElement> resultCard = driver.findElements(By.xpath("//div[contains(@class,'_13oc-S')]"));
		
		
		if(Integer.valueOf(resultText)==resultCard.size()) {
			System.out.println("Correct");
		}
		else {
			System.out.println("Result is incorrect");
		}
		driver.close();
		
		
		
	}
}
