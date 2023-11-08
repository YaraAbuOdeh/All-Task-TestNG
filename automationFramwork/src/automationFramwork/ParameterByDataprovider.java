package automationFramwork;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.*;

public class ParameterByDataprovider {
	public String driverPath="C:\\browserdriver\\chromedriver.exe";
	public WebDriver driver;
	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://google.com");
	}
		@Test(dataProvider="SearchProvider")
		public void testMethod(String auther ,String searchKey)throws InterruptedException{
			{
				 WebElement searchText= driver.findElement(By.name("q"));
				 //Searching text in google text box

					searchText.sendKeys(searchKey);

					System.out.println("Welcome ->"+ auther +" Your search key is->"+searchKey);

					Thread.sleep(3000);
					String testValue=searchText.getAttribute("value");
					System.out.println(testValue +" :::"+searchKey);
					searchText.clear();
					AssertJUnit.assertTrue(testValue.equalsIgnoreCase(searchKey));
			}
			}
					@DataProvider(name="SearchProvider")
					public Object [][] getDataFromDataprovider(){
						//return new 
						Object[][] data={{"Demo","India"},{"Krishna","UK"},{"Bhupesh","USA"}};
						return data;
								
					}

		
	

}
