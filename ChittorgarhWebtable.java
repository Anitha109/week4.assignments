package week4.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChittorgarhWebtable {

	public static void main(String[] args) {
		// Setup the browser driver
		WebDriverManager.chromedriver().setup();
		// Launch the browser
		ChromeDriver driver = new ChromeDriver();
		// url to open in the browser
		driver.get("https://www.chittorgarh.com/");
		// Maximize the browser
		driver.manage().window().maximize();
		// Add implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		// Click on stock market
		driver.findElement(By.id("navbtn_stockmarket")).click();
		// Click on NSE bulk Deals
		driver.findElement(By.xpath("//a[text() = 'NSE Bulk Deals']")).click();
		// Get all the Security names
		// Locator to find all the value from 3rd row
		List<WebElement> findElements = driver.findElements(
				By.xpath("//table[@class = 'table table-bordered table-condensed table-striped']/tbody/tr/td[3]"));
		// Arraylist
		List<String> secNameList = new ArrayList<String>();
		for (WebElement webElement : findElements) {
			String SecName = webElement.getText();
			System.out.println(SecName);
			secNameList.add(SecName);
		}
		// Convert the list value to Set collection
		Set<String> secNameSet = new LinkedHashSet<String>(secNameList);
		// Ensure whether there are duplicate Security names
		if (secNameList.size() == secNameSet.size()) {
			System.out.println("There are no duplicate Security names");
		} else {
			System.out.println("There are duplicate Security names");
		}
	}

}
