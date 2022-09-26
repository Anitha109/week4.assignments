package week4.assignments;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HTMLWebtable {

	public static void main(String[] args) {
		// Setup the browser driver
		WebDriverManager.chromedriver().setup();
		// Launch the browser
		ChromeDriver driver = new ChromeDriver();
		// url to open in the browser
		driver.get("https://html.com/tags/table/");
		// Maximize the browser
		driver.manage().window().maximize();
		// Add implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		// Table 1
		// Get the count of number of rows
		List<WebElement> noRows = driver.findElements(By.xpath("//div[@class = 'render']/table/tbody/tr"));
		System.out.println("Number row from first table " + noRows.size());
		// Get the count of number of columns
		List<WebElement> nocolums = driver.findElements(By.xpath("//div[@class = 'render']/table/thead//th"));
		System.out.println("Number colums from first table " + nocolums.size());

		// Table 2
		// Get the count of number of rows
		List<WebElement> noRows2 = driver.findElements(By.xpath("//div[@class = 'related-pages']/table//tr"));
		System.out.println("Number row from second table " + (noRows2.size() - 1));
		// Get the count of number of columns
		List<WebElement> nocolums2 = driver.findElements(By.xpath("//div[@class = 'related-pages']/table//th"));
		System.out.println("Number colums from second table " + nocolums2.size());
	}

}
