package ObjectRepository_POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
	
	public RechomePage login(String usernameData,String passwordData) {
		usernametf.sendKeys(usernameData);
		passwordtf.sendKeys(passwordData);
		loginButton.click();
		return new RechomePage(driver);
		
	}
}
