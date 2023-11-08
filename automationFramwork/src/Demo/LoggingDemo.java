package Demo;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoggingDemo {
	/*
	 private static Logger logger = LogManager.getLogger();
	    public void performSomeTask(){
	        logger.debug("This is a debug message");
	        logger.info("This is an info message");
	        logger.warn("This is a warn message");
	        logger.error("This is an error message");
	        logger.fatal("This is a fatal message");
	    }
*/
	
	public static void main(String[] args) {
		
		
		// TODO Auto-generated method stub
		
		String baseUrl ="https://www.itgsoftware.com/quality-assurance";
	    String driverPath="C:\\browserdriver\\chromedriver.exe";
	    System.setProperty("webdriver.chrome.driver", driverPath);
	    
	    WebDriver driver = new ChromeDriver();

		  Logger log = LogManager.getLogger(LoggingDemo.class);

	    driver.get(baseUrl);

	    log.debug("opening webiste");

	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	    String expectedTitle= "ITG - Quality Assurance";

	    String actualTitle= driver.getTitle();

	    log.debug("Validating the header");
	    
	    assertEquals(0, 0);

	    org.testng.Assert.assertEquals(actualTitle, expectedTitle);

	    log.debug("close the browser");
	    driver.quit();

	

	}

}
