package skyscanner;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;



import org.joda.time.LocalDate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class Main {
	public static void main(String[] args) throws InterruptedException {
		String origin = "buea";
		List<String> destinations = new ArrayList<String>(Arrays.asList("rome"/*,"mila","barc","lond","veni","madr","flor","pisa","muni","zrh","pari",*/));
		Iterator<String> iterator = destinations.iterator();

        LocalDate initialDate = new LocalDate(2018,9,10);
        LocalDate endDate = new LocalDate(2018,9,20);
        Integer numberOfDaysA = 24;
        Integer numberOfDaysB = 0;
        Integer flexibleDays = 4; // 0 for fixed Dates
        Options options = new Options().setMarket("AR");
        Boolean roundTrip = true;
        
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Mariano\\workspace\\Selenium test\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		WriteFile logFile = new WriteFile();
		WebDriverWait wait = new WebDriverWait(driver, 60);
		String text = new String();
		WebElement elem;
	
        while(iterator.hasNext()) {
        	String destination = (String) iterator.next();
	      	for (LocalDate departureDate = initialDate; departureDate.isBefore(endDate) ; departureDate = departureDate.plusDays(1)) {
	      		//LocalDate middleDate = departureDate.plusDays(numberOfDaysA);
	      		LocalDate maxReturnDate = departureDate.plusDays(numberOfDaysA+numberOfDaysB); 
	      		for (LocalDate returnDate = maxReturnDate.minusDays(flexibleDays) ; returnDate.isBefore(maxReturnDate.plusDays(1)); returnDate = returnDate.plusDays(1)) {
	      			driver.manage().deleteAllCookies();
	      			if(roundTrip) {
	      				driver.get(
	      					"https://www.skyscanner.com/transport/flights/buea/"
		      					+ destination
		      					+ "/" 
		      					+ departureDate.toString("yyMMdd")
		      					+ "/" 
		      					+ returnDate.toString("yyMMdd")
		      					+ options
		      			);
	      			} else {
	      				driver.get(
	  						"https://www.skyscanner.com/transport/d/buea/"
		  						+ departureDate.toString("yyyy-MM-dd")
		  						+ "/rome/veni/"
		  						+ returnDate.toString("yyyy-MM-dd")
		  						+ "/buea"
		  						+ options
  						);
	      			}
	      			text = origin + "->" + destination
	      				 + " from " + departureDate.toString("yy-MM-dd")
	      				 + " to " + returnDate.toString("yy-MM-dd")
	      				 + " Price: ";
	      			
	      			Thread.sleep(5000);
	      			try {
		      			wait.until(ExpectedConditions.visibilityOfElementLocated(
		      					By.cssSelector("#fqs-tabs > table > tbody > tr > td:nth-child(2) > button > span.fqs-price")
		      			));
		      			wait.until(ExpectedConditions.invisibilityOfElementLocated(
		      					By.className("day-list-progress")
		      			));
	      				elem = driver.findElement(By.cssSelector("#fqs-tabs > table > tbody > tr > td:nth-child(2) > button > span.fqs-price"));
	      				text += elem.getAttribute("innerText") + "\r\n";	
	    			} catch (Exception e) {
	      				 try {
	      					if ( null != driver.findElement(By.id("distilCaptchaForm"))) {
	      						text += "Bot detected :/" + "\r\n";
	      						Thread.sleep(5000);
	      					} else {
	      						text += "Time Out! :@" + "\r\n"; //unreachable
	      					}
	      				 } catch (Exception e1) {
	      					text += "Time Out2! :@" + "\r\n";
	      				 }
		      			returnDate = returnDate.minusDays(1);
	      			} finally {
	   		      		logFile.addText(text);
	      			}	
	      		}// fin FOR flexible days
      		} //fin FOR +1 maxReturnDate
      	}
	}
}
