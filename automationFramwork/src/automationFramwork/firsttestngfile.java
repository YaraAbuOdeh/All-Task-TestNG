package automationFramwork;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class firsttestngfile {
	public String baseUrl ="https://www.itgsoftware.com/quality-assurance";
	public String driverPath="C:\\browserdriver\\chromedriver.exe";
	public WebDriver driver;
	@Test 
	public void verifyHomepageTitle() {
		System.out.println("launching chrome browser"); 
		System.setProperty("webdriver.chrome.driver", driverPath);

		driver= new ChromeDriver();

		driver.get(baseUrl);

		String expectedTitle="ITG - Quality Assurance";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle,"Actual:" +actualTitle);

		driver.quit();
		
	}
	

	
}
