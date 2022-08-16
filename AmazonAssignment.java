package week4.day2.assignments;

import java.io.File;
import java.io.IOException;
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

public class AmazonAssignment {

	public static void main(String[] args) throws Exception {
		

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// launch url
		driver.get("https://www.amazon.in/");
		WebElement search= driver.findElement(By.xpath("//input[@class='nav-input nav-progressive-attribute']"));
		search.sendKeys("oneplus 9 pro ");
		Actions builder = new Actions(driver);
		builder.moveToElement(search).perform();
		
		// webElement search box
		WebElement searchbox = driver.findElement(By.id("nav-search-submit-button"));	
	    searchbox.click();
	    
	    //view price
	    String price = driver.findElement(By.xpath("//span[@class='a-price-whole']")).getText();
		System.out.println("Price : "+price);
		
		WebElement rating = driver.findElement(By.xpath("(//i[@class='a-icon a-icon-star-small a-star-small-4 aok-align-bottom'])[1]"));
		builder.moveToElement(rating).click().perform();
		
		//view customer rating
		String rate = driver.findElement(By.xpath("//span[@class='a-size-base s-underline-text']")).getText();
		System.out.println("Customer Rating : "+rate);
		//(//a[@class='a-link-normal 5star'])[1]
		
		//second window(shopping cart)
		driver.findElement(By.xpath("(//span[@class='a-size-medium a-color-base a-text-normal'])[1]")).click();
		Set<String> allWindows = driver.getWindowHandles();
		List<String> mobile = new ArrayList<String>(allWindows);
		driver.switchTo().window(mobile.get(1));

        //snapshot
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination =new File("screenshort./amz.png");
		FileUtils.copyFile(source, destination);
		System.out.println("Clicked Snapshot");
		Thread.sleep(3000);
		
		//click to cart
		driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
		Thread.sleep(3000);
		
		//click Cart button
		driver.findElement(By.xpath("(//input[@class='a-button-input'])//preceding::span[@id='attach-sidesheet-view-cart-button']")).click();
		String url= driver.getCurrentUrl();
		System.out.println(url);
		Thread.sleep(3000);
		
		//print cart subtotal
		String cart = driver.findElement(By.xpath("(//span[contains(@class,'a-size-medium a-color-base')])[3]")).getText();	
		String repWhite =cart.replaceAll("\\s","");
		String s=repWhite.replaceAll(".0*$", "");
		
		System.out.println("Cart Subtotal : "+s);
		//String[] split=cart.split("\\s");
		//System.out.println(split.length);
		//Get the cart subtotal and verify if it is correct.
		if(price.equals(s)){
			System.out.println("Subtotal is same");
		}
		else
		{
			System.out.println("Subtotal is different");
	}

}}
