package week4.day2.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowLeafGround {

	public static void main(String[] args) throws Exception {

		try {

			// TODO Auto-generated method stub
			WebDriverManager.chromedriver().setup();
			ChromeDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

			// launch url
			driver.get("https://leafground.com/window.xhtml");

			// WebElements
			WebElement openbutton = driver.findElement(By.xpath("//span[text()='Open']"));
			WebElement openmultiplebutton = driver.findElement(By.xpath("//span[text()='Open Multiple']"));


			// Get primary window handles
			String mainwindow = driver.getWindowHandle();

			// Open button
			openbutton.click();

			// Verify dashboard page title
			String exceptedtitle = "Dashboard";

			if (driver.getTitle().equalsIgnoreCase(exceptedtitle)) {
				System.out.println("Dashboard title is matched successfully");
			} else {
				System.out.println("Dashboard title is not matched successfully");
			}

			// Get all window handles
			Set<String> allWindows = driver.getWindowHandles();

			// Totalnumber of open windows
			System.out.println("Total number of open windows : " + allWindows.size());

			// Covert to list
			List<String> handles = new ArrayList<String>(allWindows);

			// Switch driver to the dashboard page
			driver.switchTo().window(handles.get(1));
			driver.close();

			// Switch driver to primary window
			driver.switchTo().window(handles.get(0));

			// Open the multiple window
			openmultiplebutton.click();

			// Get all window handles
			Set<String> allWindowsForMultiplebrowsers = driver.getWindowHandles();
			System.out.println("Total number of open windows : " + allWindowsForMultiplebrowsers.size());

			// convert to list
			List<String> allWindowsForMultiplebrowsersaslist = new ArrayList<String>(allWindowsForMultiplebrowsers);

			// close multiple windows
			for (String handle : allWindowsForMultiplebrowsersaslist) {
				System.out.println(handle);
				if (!handle.equals(mainwindow)) {
					driver.switchTo().window(handle);
					driver.close();
				}
			}

			// Switch driver to primary window
			driver.switchTo().window(handles.get(0));
			
			// Wait for 5 seconds until the page is completly loaded
			Thread.sleep(5000);
			
			// Click the Close windows button
			WebElement closeWindows = driver.findElement(By.xpath("//span[text()='Close Windows']"));
			closeWindows.click();

			Set<String> allWindowsforClose = driver.getWindowHandles();
			System.out.println("Total number of open windows : " + allWindowsForMultiplebrowsers.size());

			// convert to list
			List<String> closeWindowslist = new ArrayList<String>(allWindowsforClose);

			// close multiple windows
			for (String handle : closeWindowslist) {
				System.out.println(handle);
				if (!handle.equals(mainwindow)) {
					driver.switchTo().window(handle);
					driver.close();
				}
			}
			driver.switchTo().window(handles.get(0));
			// Wait for 5 seconds until the page is completly loaded
			Thread.sleep(5000);
			WebElement openwithDelayWindows = driver.findElement(By.xpath("//span[text()='Open with delay']"));
			openwithDelayWindows.click();
			Set<String> delaywindows = driver.getWindowHandles();
			List<String> delaywindowindowslist = new ArrayList<String>(delaywindows);
			// close multiple windows
						for (String handle :  delaywindowindowslist) {
							System.out.println(handle);
							if (!handle.equals(mainwindow)) {
								driver.switchTo().window(handle);
								driver.close();
							}
						}
						driver.switchTo().window(handles.get(0));
						driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
