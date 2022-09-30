package week4.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NykaaShopping {

	public static void main(String[] args) throws InterruptedException {
		// Setup the browser driver
		WebDriverManager.chromedriver().setup();

		ChromeOptions option = new ChromeOptions();
		option.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
		// Launch the browser
		ChromeDriver driver = new ChromeDriver(option);
		// url to open in the browser
		driver.get("https://www.nykaa.com/");
		// Maximize the browser
		driver.manage().window().maximize();
		// Add implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// Mouseover on Brands Search L'Oreal Paris
		WebElement brandEle = driver.findElement(By.xpath("//a[text() = 'brands']"));
		Actions builder = new Actions(driver);
		builder.moveToElement(brandEle).perform();
		Thread.sleep(2000);
		// Search L'Oreal Paris
		WebElement brandSearchEle = driver.findElement(By.id("brandSearchBox"));
		brandSearchEle.sendKeys("L'Oreal Paris");
		brandSearchEle.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		// Click L'Oreal Paris
		driver.findElement(By.xpath("//a[text() = \"L'Oreal Paris\"]")).click();

		// Click on somewhere to make the page active
		driver.findElement(By.className("overflow-hidden")).click();
		// Check the title contains L'Oreal Paris(Hint-GetTitle)
		if (driver.getTitle().contains("L'Oreal Paris") == true) {
			System.out.println("Title contains L'Oreal Paris");
		} else {
			System.out.println("Title doesn't contains L'Oreal Paris");
		}

		// Click sort By and select customer top rated
		driver.findElement(By.xpath("//button[@class =' css-n0ptfk']")).click();
		driver.findElement(By
				.xpath("//label[@for ='radio_customer top rated_undefined']//div[@class = 'control-indicator radio ']"))
				.click();
		Thread.sleep(2000);

		// Click Category and click Hair->Click haircare->Shampoo
		driver.findElement(By.id("first-filter")).click();
		driver.findElement(By.xpath("//span[text() = 'Hair']")).click();
		driver.findElement(By.xpath("//span[@class ='filter-name ' and text() = 'Hair Care']")).click();
		driver.findElement(By.xpath("//span [text() = 'Shampoo']")).click();

		// Click->Concern->Color Protection
		driver.findElement(By.xpath("//span [text() = 'Concern']")).click();
		driver.findElement(By.xpath("//span [text() = 'Color Protection']")).click();

		// check whether the Filter is applied with Shampoo
		String productText = driver
				.findElement(By.xpath("//div [@class = 'product-listing']//div[@class = 'css-1rd7vky']/div")).getText();
		System.out.println(productText);
		if (productText.contains("Shampoo")) {
			System.out.println("Filter is applied with Shampoo");
		} else {
			System.out.println("Filter is not applied with Shampoo");
		}

		// Click on L'Oreal Paris Colour Protect Shampoo
		driver.findElement(By.xpath("//div[@class = 'css-43m2vm']/img")).click();
		// GO to the new window and select size as 175ml
		// Switch to the new window
		Set<String> windowHandles = driver.getWindowHandles();
		// Convery the Set values to List to get single value
		List<String> windows = new ArrayList<String>(windowHandles);
		// Switch to the second window using get method
		driver.switchTo().window(windows.get(1));
		driver.manage().window().maximize();
		// select size as 175ml
		WebElement sizeDD = driver.findElement(By.className("css-2t5nwu"));
		Select size = new Select(sizeDD);
		size.selectByVisibleText("175ml");

		// Print the MRP of the product
		String price = driver.findElement(By.className("css-1jczs19")).getText();
		System.out.println(price);

		// Click on ADD to BAG
		driver.findElement(By.className("btn-text")).click();
		// Couldnt proceed further because server is not working in the country
		// i am currently in
		// Go to Shopping Bag
		// 14) Print the Grand Total amount
		// 15) Click Proceed
		// 16) Click on Continue as Guest
		// 17) Check if this grand total is the same in step 14
		// 18) Close all windows

	}

}
