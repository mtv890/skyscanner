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
		List<String> destinations = new ArrayList<String>(Arrays.asList("rome","mila","barc","lond","veni","madr"/*,"flor","pisa","muni","zrh","pari",*/));
		Iterator<String> iterator = destinations.iterator();
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Mariano\\workspace\\Selenium test\\geckodriver.exe");
        LocalDate initialDate = new LocalDate(
        		2018,8,25
        );
        LocalDate endDate = new LocalDate(
        		2018,10,15
        );
        Integer numberOfDaysA = 24;
        Integer numberOfDaysB = 0;
        Integer flexibleDays = 4; // 0 for fixed Dates

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
      				driver.get(
      						"https://www.skyscanner.com/transport/flights/buea/"
	      						+ destination
	      						+ "/" 
	      						+ departureDate.toString("yyMMdd")
	      						+ "/" 
	      						+ returnDate.toString("yyMMdd")
	      						+ "?adults=1&children=0&adultsv2=1&childrenv2=&infants=0&cabinclass=economy&rtn=1&preferdirects=false&outboundaltsenabled=false&inboundaltsenabled=false&ref=home&market=AR&locale=es-AR&currency=ARS#results"
	      				
	      					/*	"https://www.espanol.skyscanner.com/transporte/d/buea/"
	      						+ departureDate.toString("yyyy-MM-dd")
	      						+ "/nyca/nyca/"
	      						+ middleDate.toString("yyyy-MM-dd")
	      						+ "/tyoa/tyoa/"
	      						+ returnDate.toString("yyyy-MM-dd")
	      						+ "/buea?adults=1&children=0&adultsv2=1&childrenv2=&infants=0&cabinclass=economy&market=US&locale=es-AR&currency=ARS#results"
	      						*/
	      			);
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
	      				 e.printStackTrace();
	      				 try {
	      					if ( null != driver.findElement(By.id("distilCaptchaForm"))) {
	      						text += "Bot detected :/" + "\r\n";
	      					} else {
	      						text += "Time Out! :@" + "\r\n";
	      					}
	      				 } catch (Exception e1) {
	      					e1.printStackTrace();
	      					text += "Time Out2! :@" + "\r\n";
	      				 }
	      			} finally {
	   		      		logFile.addText(text);
	      			}	
	      		}// fin FOR flexible days
      		} //fin FOR +1 maxReturnDate
      	}
	}
}
