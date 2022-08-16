package marathon;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {
	

	public static void main(String[] args) throws Exception {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// launch url
		driver.get("https://www.amazon.in/");
		WebElement search= driver.findElement(By.xpath("//input[@class='nav-input nav-progressive-attribute']"));
		search.sendKeys("bags for girls");
		Actions builder = new Actions(driver);
		builder.moveToElement(search).perform();
		
		// webelement search box
		WebElement searchbox = driver.findElement(By.id("nav-search-submit-button"));
		
        searchbox.click();
		String text = driver.findElement(By.xpath("//span[text()='1-48 of over 80,000 results for']")).getText();
		System.out.println("First result  " +text);
		//driver.findElement(By.xpath("//span[text()='Brand']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//span[text()='FunBlast'])[2]")).click();
		driver.findElement(By.xpath("(//span[text()='Generic'])[1]")).click();
		//sort by
		driver.findElement(By.xpath("//span[text()='Sort by:']")).click();
		Thread.sleep(3000);
		//select newArrivals
		driver.findElement(By.xpath("//a[@id='s-result-sort-select_4']")).click();
		Thread.sleep(3000);
		String text1 = driver.findElement(By.xpath("//div[contains(@class,'a-section a-spacing-small')]")).getText();
		System.out.println("Second result  "+text1);
		driver.findElement(By.xpath("//span[contains(text(),'Women Satan Girls')]")).click();
		if(text.equals(text1)){
		System.out.println("Result is Not reduced");
		}else {
			System.out.println("Result is Reduced");
		}
		//switch to next win
		//String mainwindow = driver.getWindowHandle();
		Thread.sleep(3000);
		Set<String> allWindows = driver.getWindowHandles();
		List<String> handles = new ArrayList<String>(allWindows);
		driver.switchTo().window(handles.get(1));
		String pdtTitle = driver.findElement(By.id("productTitle")).getText();
		System.out.println("Product title : "+ pdtTitle);
		String pdtPercentage = driver.findElement(By.xpath("//span[contains(@class,'a-size-large a-color-price')]")).getText();
		System.out.println("Product Percentage :"+ pdtPercentage);
		//
		String price = driver.findElement(By.className("a-price-whole")).getText();
		String expectedprice ="750";
		System.out.println("Comparision Price of previsous and present");
		if(price.equals(expectedprice)) {
			System.out.println("Results Matched !!");
		}
		else {
			System.out.println("Results did not Mach !!");
		}
		//snapshot
		//File source = driver.getScreenshotAs(OutputType.FILE);
		//Create the physical file
		//File destination =new File("screenshort./amazon.png");
		//copy souce file to destination
		//FileUtils.copyFile(source, destination);


	}

}
