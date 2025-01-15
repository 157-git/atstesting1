package ObjectRepository_POM;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class loginPage {

	public WebDriver driver;
	
	@FindBy(name = "employeeId")
	private WebElement usernametf;
	
	@FindBy(name = "password")
	private WebElement passwordtf;
	
	@FindBy(xpath = "//button[text()=\"Login\"]")
	private WebElement loginButton;

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getUsernametf() {
		return usernametf;
	}

	public WebElement getPasswordtf() {
		return passwordtf;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}
	
	public loginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public RecruiterhomePage login(String usernameData,String passwordData) {
		try {
			Thread.sleep(1000);
			usernametf.sendKeys(usernameData);
			passwordtf.sendKeys(passwordData);
			loginButton.click();
			} 
			catch (Exception e) {
	            System.out.println("Failed to click on employee ID : " + e.getMessage());
	        }
		return new RecruiterhomePage(driver);
	}
}
