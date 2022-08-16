package week4.day2.assignments;

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


import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapDeal {



	public static void main(String[] args) throws Exception {
		
			
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// launch url
		driver.get("https://www.snapdeal.com/");
		Actions builder = new Actions(driver);
		String mainwindow = driver.getWindowHandle();

        //Go to Mens Fashion
		driver.findElement(By.xpath("//span[@class='catText']")).click();
	    
		//Go to Sports Shoes
	    driver.findElement(By.xpath("//span[text()='Sports Shoes']")).click();
		
		//Get the count of the sports shoes
	    String countOfShoes= driver.findElement(By.xpath(" (//div[@class='child-cat-count '])[2]")).getText();
	    System.out.println("Count of Shoes : "+countOfShoes);
		//Click Training shoes
	    driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		//select Sort by Low to High
	    driver.findElement(By.xpath("//i[contains(@class,'sd-icon sd-icon-expand-arrow')]")).click();
	    WebElement low = driver.findElement(By.xpath("(//li[@data-index='1'])[2]"));
		builder.moveToElement(low).click().perform();
		Thread.sleep(3000);
		//Check if the items displayed are sorted correctly
	     List<WebElement> price = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		List<String> shoe= new ArrayList<String>();
		System.out.println("sorted price!!");
		for (int i = 0; i < price.size(); i++) {
			String text=price.get(i).getText();
			System.out.println(text);
			shoe.add(text);
			
		}    
		// Select the price range (900-1200)
		WebElement rate = driver.findElement(By.xpath("//input[@name='fromVal']"));
		rate.clear();
		rate.sendKeys("900");
		WebElement torate = driver.findElement(By.xpath("//input[@name='toVal']"));
		torate.clear();
		torate.sendKeys("1200");
		driver.findElement(By.xpath("//div[contains(@class,'price-go-arrow btn')]")).click();
		Thread.sleep(3000);
		//Click view more button
		driver.findElement(By.xpath("//button[contains(@class,'view-more-button btn')]")).click();
		// Filter with color Navy 
		Thread.sleep(3000);
		driver.findElement(By.xpath("//label[@for='Color_s-Navy']")).click();
		// verify the all applied filters 
		
		String filter = driver.findElement(By.xpath("//a[@class='clear-filter-pill']")).getText();
		System.out.println("Applied filters : "+filter);
		String colour = driver.findElement(By.xpath("//label[@for='Color_s-Navy']")).getText();
		System.out.println("Colour selected : "+colour);
		if(colour.contains("Navy")) {
			System.out.println("Navy colour is selected");
		}
		else {
			System.out.println("Other colour is selected");
		}
		
		Thread.sleep(3000);
		//Mouse Hover on first resulting Training shoes
		WebElement firstshoe = driver.findElement(By.xpath("//p[@class='product-title']"));
		builder.moveToElement(firstshoe).click().perform();
		Thread.sleep(3000);
		Set<String> allWindows = driver.getWindowHandles();
		List<String> handles = new ArrayList<String>(allWindows);
		driver.switchTo().window(handles.get(1));
	
		//driver.findElement(By.xpath("//div[contains(@class,'center quick-view-bar')]")).click();
		
		//Print the cost 
		String getprice = driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText();
		System.out.println("Price of the shoe is : "+getprice);
			
		//Take the snapshot of the shoes
		File source = driver.getScreenshotAs(OutputType.FILE);
		//Create the physical file
		File destination =new File("screenshort./snapdeal.png");
		//copy souce file to destination
		FileUtils.copyFile(source, destination);
		
	    //Close the current window
		driver.close();
		//Close the main window
		driver.quit();
	
	}

}
