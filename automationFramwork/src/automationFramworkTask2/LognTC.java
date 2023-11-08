package automationFramworkTask2;
import org.apache.poi.ss.usermodel.*;


import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;
public class LognTC {
	private WebDriver driver;

    @BeforeClass
    public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\browserdriver\\chromedriver.exe");
		driver = new ChromeDriver();
		// driver.manage().window().maximize();

		driver.get("https://devwcs.ballarddesigns.com/");
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();
    }
    
    @Test
    public void testInvalidEmailFormat() throws IOException, InterruptedException, TimeoutException {
        // Writing data to Excel sheet
        String excelFilePath = "C:\\Users\\lenovo\\eclipse-workspace\\automationFramwork\\excel\\login-TC.xlsx";
        writeDataToExcel(excelFilePath);

        FileInputStream excelFile = new FileInputStream(excelFilePath);
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet sheet = workbook.getSheetAt(0);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Retrieve data for the first row (invalid email format)
        Row row = sheet.getRow(0);
        String invalidEmail = row.getCell(0).getStringCellValue();
        String emptyPassword = row.getCell(1).getStringCellValue();


        driver.navigate().to("https://devwcs.ballarddesigns.com/ShoppingCartView");

        // Wait for the sign-in modal to be present
        WebElement signInModal = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#gwt-sign-in-modal")));
        WebElement usernameField = driver.findElement(By.id("gwt-sign-in-modal"));
        WebElement passwordField = driver.findElement(By.id("passwordReset"));
        WebElement loginButton = driver.findElement(By.id("logonButton"));

        signInModal.sendKeys(invalidEmail);
        passwordField.sendKeys(emptyPassword); // Set a valid password for the test
        loginButton.click();

        WebElement actualErrorMessage = driver.findElement(By.cssSelector(".gwt-Label"));
        Assert.assertEquals(actualErrorMessage.getText(), "Error: We don't recognize qwthat email address. Please try again."); 

        workbook.close();
        excelFile.close();
    }

    
    @Test
    public void testValidEmailInvalidPassword() throws IOException, InterruptedException, TimeoutException {
        // Writing data to Excel sheet
        String excelFilePath = "C:\\Users\\lenovo\\eclipse-workspace\\automationFramwork\\excel\\login-TC.xlsx";
        writeDataToExcel(excelFilePath);

        FileInputStream excelFile = new FileInputStream(excelFilePath);
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet sheet = workbook.getSheetAt(0);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Retrieve data for the second row (invalid email)
        Row row = sheet.getRow(1);
        String validEmail = row.getCell(0).getStringCellValue();
        String invalidPassword = row.getCell(1).getStringCellValue();

        driver.navigate().to("https://devwcs.ballarddesigns.com/ShoppingCartView");

        // Wait for the sign-in modal to be present
        WebElement signInModal = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("gwt-sign-in-modal")));

        // Explicit wait for the username and password fields to be clickable
        WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("gwt-sign-in-modal")));
        WebElement passwordField = driver.findElement(By.id("passwordReset"));
        WebElement loginButton = driver.findElement(By.id("logonButton"));

        usernameField.sendKeys(validEmail);
        passwordField.sendKeys(invalidPassword);
        loginButton.click();

        // Wait for the error message element and ensure its text is non-empty
        ExpectedCondition<WebElement> nonEmptyTextCondition = new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver driver) {
                WebElement element = driver.findElement(By.cssSelector(".gwt-Label"));
                if (element.isDisplayed() && !element.getText().isEmpty()) {
                    return element;
                }
                return null;
            }
        };

        WebElement actualErrorMessage = wait.until(nonEmptyTextCondition);

        Assert.assertEquals(actualErrorMessage.getText(), "Email/Password you entered is not correct. Please try again.");

        workbook.close();
        excelFile.close();
    }


    
    @Test
    public void testValidLogin() throws IOException, InterruptedException, TimeoutException {
        // Writing data to Excel sheet
        String excelFilePath = "C:\\Users\\lenovo\\eclipse-workspace\\automationFramwork\\excel\\login-TC.xlsx";
        writeDataToExcel(excelFilePath);

        FileInputStream excelFile = new FileInputStream(excelFilePath);
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet sheet = workbook.getSheetAt(0);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Retrieve data for the third row (valid login)
        Row row = sheet.getRow(2);
        String validEmail = row.getCell(0).getStringCellValue();
        String validPassword = row.getCell(1).getStringCellValue();

        driver.navigate().to("https://devwcs.ballarddesigns.com/ShoppingCartView");

        // Wait for the sign-in modal to be present
        WebElement signInModal = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#gwt-sign-in-modal")));
        WebElement usernameField = driver.findElement(By.id("gwt-sign-in-modal"));
        WebElement passwordField = driver.findElement(By.id("passwordReset"));
        WebElement loginButton = driver.findElement(By.id("logonButton"));

        signInModal.sendKeys(validEmail);
        passwordField.sendKeys(validPassword);
        loginButton.click();

        ExpectedCondition<WebElement> elementIsVisibleAndTextNotEmpty = new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver driver) {
                WebElement element = driver.findElement(By.cssSelector("#welcome"));
                if (element.isDisplayed() && !element.getText().isEmpty()) {
                    return element;
                }
                return null;
            }
        };

        WebElement accountNavigationLink = wait.until(elementIsVisibleAndTextNotEmpty);
        Assert.assertEquals(accountNavigationLink.getText(), "Welcome, qa!");

        workbook.close();
        excelFile.close();
    }

    
    @AfterClass
    public void tearDown() {
    	if (driver != null) 
    	{ 
    		driver.quit();
    	}
    }
    
    private void writeDataToExcel(String excelFilePath) throws IOException {
    	
    	try (Workbook workbook = new XSSFWorkbook()) {
			Sheet sheet = workbook.createSheet("User Data");

			Object[][] userData = {{ "yara@gmail", "" }, { "qatest@gmail.com", "hello" },
					{ "qatest@gmail.com", "123456789y" } };

			int rowCount = 0;
			for (Object[] user : userData) {
				Row row = sheet.createRow(rowCount++);
				int columnCount = 0;
				for (Object field : user) {
					Cell cell = row.createCell(columnCount++);
					if (field instanceof String) {
						cell.setCellValue((String) field);
					}
				}
			}

			try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
				workbook.write(outputStream);
				System.out.println("Data has been written to the Excel file successfully.");
			}
    	}
    }
    
    
}
