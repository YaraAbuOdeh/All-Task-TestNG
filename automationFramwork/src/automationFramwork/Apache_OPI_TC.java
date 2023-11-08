package automationFramwork;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
import utilts.Constant;
import utilts.ExcelUtils;
import pageObjects.*;
import  appModules.Signln_Action;
@Test 
public class Apache_OPI_TC {
	private static WebDriver driver =null;

	public static void main(String[] args) throws Exception {
	
		// TODO Auto-generated method stub
		ExcelUtils.setExcelFile(Constant.Path_TestData ,"Sheet1");
		driver= new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get(Constant.URL);
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();
		System.out.println("Successfuly opend the website");
		Signln_Action.Execute(driver);

		System.out.println("Login Successfully, now it is the time to Log Off buddy.");

		Home_Page.Ink_LogOut(driver).click();

		driver.quit();

		//This is to send the PASS value to the Excel sheet in the result column.

		ExcelUtils.setCellData("Pass", 1,3);


		

	}

}
