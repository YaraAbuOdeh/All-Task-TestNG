/*
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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

class LogInTC {
	private WebDriver driver;
	private WebDriverWait wait;

	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\browserdriver\\chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.get("https://devwcs.ballarddesigns.com/");
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();
	}

	@Test
	public void testLoginCases() throws IOException, InterruptedException, TimeoutException {
		String excelFilePath = "C:\\Users\\lenovo\\eclipse-workspace\\automationFramwork\\excel\\login-TC.xlsx";
		writeDataToExcel(excelFilePath);

		FileInputStream excelFile = new FileInputStream(excelFilePath);
		Workbook workbook = new XSSFWorkbook(excelFile);
		Sheet sheet = workbook.getSheetAt(0);

		for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
			Row row = sheet.getRow(i);
			String email = row.getCell(0).getStringCellValue();
			String password = row.getCell(1).getStringCellValue();

			driver.navigate().to("https://devwcs.ballarddesigns.com/ShoppingCartView");

			WebElement signInModal = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#gwt-sign-in-modal")));
			WebElement emailField = driver.findElement(By.id("gwt-sign-in-modal"));
			WebElement passwordField = driver.findElement(By.id("passwordReset"));
			WebElement loginButton = driver.findElement(By.id("logonButton"));

			emailField.sendKeys(email);
			passwordField.sendKeys(password);
			loginButton.click();

			WebElement messageElement = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".gwt-Label")));
			WebElement accountNavigationLink = driver.findElement(By.id("welcome"));

			if (messageElement.getText().contains("Email/Password you entered is not correct. Please try again.")) {
				System.out.println("Email/Password you entered is not correct. Please try again.");
			} else if (messageElement.getText()
					.contains("Error: We don't recognize that email address. Please try again.")) {
				System.out.println("Error: We don't recognize that email address. Please try again.");
			} else if (accountNavigationLink.getText().contains("Welcome, qa!")) {
				System.out.println("User is logged in successfully.");
			} else {
				System.out.println("Unknown Error Message: " + messageElement.getText());
			}
		}

		workbook.close();
		excelFile.close();
	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	private void writeDataToExcel(String excelFilePath) throws IOException {
		try (Workbook workbook = new XSSFWorkbook()) {
			Sheet sheet = workbook.createSheet("User Data");

			Object[][] userData = { { "yara@gmail", "" }, { "qatest@gmail.com", "hello" },
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
*/
