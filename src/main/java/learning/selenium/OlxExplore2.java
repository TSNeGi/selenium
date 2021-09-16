package learning.selenium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OlxExplore2 {

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
		driver.get("https://www.olx.in");
		driver.findElement(By.xpath("//span[contains(@class,'_2uhZ0')]//button[contains(@class,'rui-1rYgw')]")).click();
		driver.findElement(By.xpath("//span[starts-with(text(),'OLX Autos')]")).click();
		
		
		System.out.println("Step 3.1: Selecting Car Brand and Verifying Name ");
		WebElement checkboxMaruti= driver.findElement(By.id("c-make-maruti-suzuki"));
		System.out.println("	Maruti is selected :"+checkboxMaruti.isSelected());
		checkboxMaruti.click();
		String marutiNumber= driver.findElement(By.xpath("//label[@title='Maruti Suzuki']//span[2]")).getText();
		System.out.println("	Avail Maruti Car Number :"+ marutiNumber);
		
		
		WebElement checkboxHyundai= driver.findElement(By.id("c-make-hyundai"));
		System.out.println("	Hyundai is selected :"+checkboxHyundai.isSelected());
		checkboxHyundai.click();
		String hyundaiNumber= driver.findElement(By.xpath("//label[@title='Hyundai']//span[2]")).getText();
		System.out.println("	Hyundai Car Number :"+ hyundaiNumber);
		String st =driver.findElement(By.xpath("//div[@class='_2VogW']")).getText();
		System.out.println("	Selected Car Brand :" +st);
		int totalSelectCars=Integer.parseInt(marutiNumber.replaceAll("[^0-9]", ""))+Integer.parseInt(hyundaiNumber.replaceAll("[^0-9]", ""));;
		
		
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//button[@data-aut-id='btnLoadMore']//span"), "load more"));
		
		driver.navigate().refresh();
		String results=driver.findElement(By.id("adsResultsIn")).getText();
		int pageResult=Integer.parseInt(results.replaceAll("[^0-9]", ""));
		System.out.println("Step 3.2: Verifying selected car brand with result page ");
		
		System.out.println(totalSelectCars==pageResult?"	Verified |"+totalSelectCars:" Not same as shown on page |"
			+ pageResult);
		
		
		System.out.println("Step 4.1: Sorting Low to High Price | Verifying the same");
		driver.findElement(By.xpath("//span[contains(text(),'Sort By')]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Low to High')]")).click();
		
		List<WebElement> lowToHigh = driver.findElements(By.xpath("//span[contains(@data-aut-id,'itemPrice')]"));
		List<Integer> Prices =new ArrayList<Integer>();
		List<Integer> PricesWithoutF =new ArrayList<Integer>();
		for(WebElement e: lowToHigh) {
			Prices.add(Integer.parseInt(e.getText().replaceAll("[^0-9]", "")));
		}
		List<WebElement> lowToHighFe = driver.findElements(By.xpath("//div[@class='IKo3_']//div[@class='_2Vp0i']//following::span[1]"));
		for(WebElement e: lowToHighFe) {
			PricesWithoutF.add(Integer.parseInt(e.getText().replaceAll("[^0-9]", "")));
		}
		Prices.removeAll(PricesWithoutF);
		
		List<Integer> tmp = new ArrayList<Integer>(Prices);
		Collections.sort(tmp);
		System.out.println(tmp.equals(Prices)?"	Sorting Low to High is correct":" 	Sorting is incorrect");

		
		Prices.clear();
		PricesWithoutF.clear();
		
		System.out.println("Step 4.2: Sorting High to Low Price | Verifying the same");
		driver.findElement(By.xpath("//span[contains(text(),'Sort By')]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'High to Low')]")).click();
		
		List<WebElement> highToLow = driver.findElements(By.xpath("//span[contains(@data-aut-id,'itemPrice')]"));
		
		for(WebElement e: highToLow) {
			Prices.add(Integer.parseInt(e.getText().replaceAll("[^0-9]", "")));
		}
		List<WebElement> highToLowFe = driver.findElements(By.xpath("//div[@class='IKo3_']//div[@class='_2Vp0i']//following::span[1]"));
		for(WebElement e: highToLowFe) {
			PricesWithoutF.add(Integer.parseInt(e.getText().replaceAll("[^0-9]", "")));
		}
		Prices.removeAll(PricesWithoutF);
		for( int i=1; i<Prices.size();i++) {
			if(!(Prices.get(i-1)>=Prices.get(i))) {
				System.out.println("Sorting is incorrect");
				break;				
			}
		}
//		List<Integer> tmp1 = new ArrayList<Integer>(Prices);
//		Collections.sort(tmp1,Collections.reverseOrder());
//		System.out.println(tmp1.equals(Prices)?"	Sorting High To Low is correct":" 	Sorting is incorrect");

		driver.close();
		
		
	}

}

