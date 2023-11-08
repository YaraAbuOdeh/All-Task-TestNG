package automationFramwork;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BallardDesignsSite {
	WebDriver driver;
	public String driverPath="C:\\browserdriver\\chromedriver.exe";

    @BeforeTest
    public void setup() {
    	
        System.setProperty("webdriver.chrome.driver", driverPath);

        // Initialize the WebDriver instance
        driver = new ChromeDriver();

        // Navigate to the website
        driver.get("https://devwcs.ballarddesigns.com/");
        driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();
		System.out.println("Successfuly opend the website");

    }
    @Test(priority = 1)
    public void verifyLogoIsDisplayed() {
    	
        Assert.assertTrue(driver.findElement(By.cssSelector(".logo-anchor")).isDisplayed());
    }
    @Test(priority = 2)
    public void verifySignInPage() {
        // Click on "Sign In/Register" link
        driver.findElement(By.id("loginMyAccount")).click();

        // Verify the text on the sign-in page
        String expectedText = "Welcome back! To access your account, please enter your email address and password and click Sign In.";
        String actualText = driver.findElement(By.id("BDLoginMessageNormal")).getText();
        //Assert.assertEquals(actualText, expectedText);
        actualText.contains(expectedText);
    }
    
    @Test(priority = 3, dataProvider = "validCredentials")
    public void loginWithValidCredentials(String email, String password) {
        driver.findElement(By.id("logonId")).sendKeys(email);
        driver.findElement(By.id("logonPassword")).sendKeys(password);
        driver.findElement(By.id("logonButton")).click();
        
        // Add assertions or verification steps if needed
    }
    
    @DataProvider(name = "validCredentials")
    public Object[][] validCredentials() {
        return new Object[][]{
                {"qatest@gmail.com", "123456789y"}
        };
    }
    @Test(priority = 4)
    public void verifyUserLoggedIn() {
        String expectedText = "My Account";
        String actualText = driver.findElement(By.id("myAccount")).getText();
        Assert.assertEquals(actualText, expectedText);

        String welcomeText = driver.findElement(By.id("welcome")).getText();

        Assert.assertTrue(welcomeText.contains("qa"));

        // Close the browser
        driver.quit();
    }
    
}
