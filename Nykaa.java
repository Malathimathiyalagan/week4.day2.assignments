package week4.day2.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws Exception {

		// initialize driver
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		// open login page
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.nykaa.com/");
		String mainwindow = driver.getWindowHandle();
		
		// webelements brand
		WebElement brands = driver.findElement(By.xpath("(//a[@class='css-1mavm7h'])[2]"));

		// wait for 5 seconds
		Thread.sleep(5000);
		
		// mouse over to brand menu
		Actions builder = new Actions(driver);
		builder.moveToElement(brands).perform();
		
		// webelement search box
		WebElement search = driver.findElement(By.id("brandSearchBox"));
		
		// enter search text
		search.sendKeys("L'Oreal Paris");

		// wait for 5 seconds until link appears
		Thread.sleep(5000);
		WebElement searchbrands = driver.findElement(By.linkText("L'Oreal Paris"));

		// click the brand link
		searchbrands.click();
		
		// wait for 5 seconds until the brand page is displayed
		Thread.sleep(5000);
		//get title for L'Oreal Paris page
		System.out.println(driver.getTitle());
		
		//click sort by
		driver.findElement(By.className("sort-name")).click();
		driver.findElement(By.xpath("//label[@for='radio_customer top rated_undefined']")).click();
		driver.findElement(By.xpath("//span[text()='Category']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class='control-indicator checkbox ']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//label[@for='checkbox_Color Protection_10764']")).click();
		//check whether shampoo is displayed in filter
		boolean displayed = driver.findElement(By.xpath("//span[text()='Shampoo']")).isDisplayed();
		System.out.println("Element is Displayed !! " +displayed);
		//
		driver.findElement(By.className("css-xrzmfa")).click();
		
		//Nextwindow handles
		Set<String> allWindows = driver.getWindowHandles();
		List<String> handles = new ArrayList<String>(allWindows);
		driver.switchTo().window(handles.get(1));
		System.out.println(driver.getTitle());
		WebElement source= driver.findElement(By.className("css-2t5nwu"));
		Select drop=new Select(source);
	    drop.selectByIndex(1);
	    //print mrp
	    String mrp = driver.findElement(By.xpath("//span[text()='MRP:']/following ::span[1]")).getText();
	    System.out.println("Mrp of shampoo : "+mrp);
	    //click Add to Bag
	    driver.findElement(By.xpath("//span[text()='Add to Bag']")).click();
		Thread.sleep(3000);
		//click Shopping bag 
		driver.findElement(By.xpath("//span[text()='1']")).click();
		Thread.sleep(3000);
		 //switch to frame
		driver.switchTo().frame(0);
		//print grand total
		String gtot = driver.findElement(By.xpath("//div[@class='first-col']")).getText();
		String replaceAll = gtot.replaceAll("\\D", "");
		int grandtotal=Integer.parseInt(replaceAll);
		System.out.println("Grand Total : "+grandtotal);
		//click proceed
		driver.findElement(By.xpath("//button[@class='btn full fill no-radius proceed ']")).click();
		//continue as guest
		driver.findElement(By.xpath("//button[@class='btn full big']")).click();
		//print grand total
		String comGtot = driver.findElement(By.xpath("//div[text()='Grand Total']/following::span[1]")).getText();
		String replaceAllgt = comGtot.replaceAll("\\D", "");
		int comGtot1=Integer.parseInt(replaceAllgt);
		System.out.println("Grand Total : "+comGtot1);
		//compare grand total
		if(comGtot1==grandtotal){
			System.out.println("Grand total is same");
			}
		else {
				System.out.println("Grand total is different" );
			}
		driver.quit();
		
	}
	   
	}


