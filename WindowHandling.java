package week4.day2.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowHandling {

	public static void main(String[] args) {
		//1.https://github.com/TestLeafPages/SeleniumAssignments/blob/master/week3/day2/Assignments/MergeContact.java

			//2.http://www.leafground.com/pages/Window.html
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("demosalesmanager");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("crmsfa");
		driver.findElement(By.xpath("//input[@class='decorativeSubmit']")).click();
		driver.findElement(By.xpath("//a[contains(text(),'CRM/SFA')]")).click();
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//a[@href='/crmsfa/control/mergeContactsForm']")).click();
		
		
		System.out.println(driver.getTitle());//current window title
		String windowHandle = driver.getWindowHandle();
         System.out.println(windowHandle);
         //to second window
         driver.findElement(By.xpath("//img[@src='/images/fieldlookup.gif']")).click();
         Set<String> firstwindowHandles = driver.getWindowHandles();
         List<String> firstHandles=new ArrayList<String>(firstwindowHandles);
         String secondWindow = firstHandles.get(1);
         System.out.println(secondWindow);
         driver.switchTo().window(firstHandles.get(1)); //To move the control to  second
         //print the title
         System.out.println(driver.getTitle());
         driver.findElement(By.xpath("//a[text()='17507']")).click();
         driver.switchTo().window(firstHandles.get(0));
         //to third window 
         driver.findElement(By.xpath("(//img[@src='/images/fieldlookup.gif'])[2]")).click();
         Set<String> secondwindowHandles = driver.getWindowHandles();
         List<String> secondHandles=new ArrayList<String>(secondwindowHandles);
         String thirdWindow = secondHandles.get(1);
         System.out.println(thirdWindow);
         driver.switchTo().window(secondHandles.get(1));//to move the control to third
         driver.findElement(By.xpath("//a[text()='17511']")).click();
         driver.switchTo().window(secondHandles.get(0));
         driver.findElement(By.xpath("//a[text()='Merge']")).click();  
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        alert.accept();
        System.out.println(driver.getTitle());
        
         // driver.close();
         

		
		
	}

}
