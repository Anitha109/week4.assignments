package week4.assignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Snapdeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		// Setup the browser driver
		WebDriverManager.chromedriver().setup();
		// Launch the browser
		ChromeDriver driver = new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("-disabled-notifications");
		// url to open in the browser
		driver.get("https://www.snapdeal.com/");
		// Maximize the browser
		driver.manage().window().maximize();
		// Add implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		// decline the notification
		driver.findElement(By.id("pushDenied")).click();
		Actions builder = new Actions(driver);
		WebElement mensfasEle = driver.findElement(By.xpath("//span[@class= 'catText']"));
		builder.moveToElement(mensfasEle).perform();
		// Go to Sports Shoes
		driver.findElement(By.xpath("//span[text() = 'Sports Shoes']")).click();
		// Get the count of the sports shoes
		String shoesCount = driver.findElement(By.xpath("//span[@class = 'category-name category-count']")).getText();
		System.out.println("The count of the sports shoes : " + shoesCount);
		// Click Training shoes
		driver.findElement(By.xpath("//div[text() = 'Training Shoes']")).click();
		// Sort by Low to High
		driver.findElement(By.className("sort-selected")).click();
		driver.findElement(By.xpath("//li[@data-sorttype = 'plth']")).click();
		Thread.sleep(3000);
		// Check if the items displayed are sorted correctly
		List<WebElement> priceEle = driver.findElements(By.xpath(
				"//section[@class = 'js-section clearfix dp-widget dp-fired']//span[@class='lfloat product-price']"));
		// int size = priceEle.size();
		// System.out.println(size);
		int preAmount = 0;
		// get the price of all the sorted products
		for (WebElement webElement : priceEle) {
			// amount is there with text
			String text = webElement.getText();
			// taking the amount from the text
			String replaceAll = text.replaceAll("[^0-9]", "");
			// Printing all the price amount
			System.out.println(replaceAll);
			// Converting the amount which we got in string format to integer
			int parseInt = Integer.parseInt(replaceAll);
			// Condition to check whether the amount is in sorted order
			if (parseInt >= preAmount) {
				preAmount = parseInt;
			} else {
				// if not print the amount of the product which is not in
				// expected order
				System.out.println("Item with amount " + replaceAll + " is not sorted correctly");
			}
		}
		// Select the price range (900-1200)
		WebElement sliderFrom = driver.findElement(By.xpath("//input[@class = 'input-filter' and @name = 'fromVal']"));
		sliderFrom.clear();
		sliderFrom.sendKeys("900");
		WebElement sliderEnd = driver.findElement(By.xpath("//input[@class = 'input-filter' and @name = 'toVal']"));
		sliderEnd.clear();
		sliderEnd.sendKeys("1200");
		driver.findElement(By.xpath("//div[contains (text() ,'GO')]")).click();
		Thread.sleep(2000);
		// Filter with color Navy
		driver.findElement(By.xpath("//span[@class ='filter-color-tile Blue ']")).click();
		Thread.sleep(2000);
		// verify the all applied filters
		String color = driver.findElement(By.className("product-title")).getText();
		if (color.contains("Blue")) {
			System.out.println("Product is listed as per the filter");
		} else {
			System.out.println("Product is not as per the filter");
		}
		// Mouse Hover on first resulting Training shoes
		WebElement product = driver.findElement(By.className("product-title"));
		builder.moveToElement(product).perform();
		// click QuickView button
		driver.findElement(By.xpath("//div[@class = 'clearfix row-disc']")).click();
		// Print the cost and the discount percentage

		String price = driver.findElement(By.xpath("//span[@class = 'strikee ']")).getText();
		String discount = driver.findElement(By.xpath("//span[@class= 'percent-desc ']")).getText();
		System.out.println("Price      = " + price);
		System.out.println("Discount % = " + discount);
		// Take the snapshot of the shoes.
		File screenshotAs = driver.getScreenshotAs(OutputType.FILE);
		File destination = new File("./snap/screenshot.png");
		FileUtils.copyFile(screenshotAs, destination);

		// Close the current window
		driver.findElement(By.xpath("//div[@class = 'close close1 marR10']/i[@class = 'sd-icon sd-icon-delete-sign']"))
				.click();
		// Close the main window
		driver.close();
	}

}
