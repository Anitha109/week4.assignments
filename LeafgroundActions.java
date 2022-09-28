package week4.assignments;

//drag and drop row is not working yet
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LeafgroundActions {

	public static void main(String[] args) throws InterruptedException {
		// Setup the browser driver
		WebDriverManager.chromedriver().setup();
		// Launch the browser
		ChromeDriver driver = new ChromeDriver();
		// url to open in the browser
		driver.get("https://www.leafground.com/drag.xhtml");
		// Maximize the browser
		driver.manage().window().maximize();
		// Add implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		// Action class
		Actions builder = new Actions(driver);
		WebElement drag = driver.findElement(By.id("form:conpnl"));
		int xOffset = 300;
		int yOffset = 50;
		builder.dragAndDropBy(drag, xOffset, yOffset).perform();

		WebElement target = driver.findElement(By.id("form:drop"));
		WebElement source = driver.findElement(By.id("form:drag"));
		builder.dragAndDrop(source, target).perform();

		// drag and drop the 1st column to 2nd colum
		WebElement column2 = driver.findElement(By.id("form:j_idt94:j_idt95"));
		WebElement column3 = driver.findElement(By.id("form:j_idt94:j_idt99"));
		builder.dragAndDrop(column2, column3).perform();
		String text = driver.findElement(By.xpath("//span[@class = 'ui-growl-title']")).getText();
		System.out.println("Alert after the drag and drop the column : " + text);

		// drag and drop the 5th row to 2nd row
		WebElement row2 = driver.findElement(By.xpath("//tbody[@id = 'form:j_idt111_data']/tr[@data-ri = '1']"));
		WebElement row5 = driver.findElement(By.xpath("//tbody[@id = 'form:j_idt111_data']/tr[@data-ri = '4']"));
		builder.dragAndDrop(row5, row2).perform();

		// Handling Process Bar
		driver.findElement(By.xpath("//span[@class = 'ui-button-text ui-c']")).click();

		// Handling slider
		WebElement sliderDrag = driver.findElement(By.xpath("//div[@id = 'form:j_idt125']/span"));
		builder.dragAndDropBy(sliderDrag, 80, 0).perform();

	}

}
