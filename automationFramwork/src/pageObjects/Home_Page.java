package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Home_Page {
    private static WebElement element = null;

    public static WebElement lnk_MyAccount(WebDriver driver) {
        element = driver.findElement(By.id("loginMyAccount"));
        return element;
    }

    public static WebElement Ink_LogOut(WebDriver driver) {
        element = driver.findElement(By.id("login")); 
        return element;
    }
    
    
	
	

}
