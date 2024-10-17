package ObjectRepository_POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RecruiterGear {

	public WebDriver driver;
	
	@FindBy(className = "main-homepage-btn")
	private WebElement letBegin;
	
	@FindBy(xpath = "(//button[@class=\"login1\"])[1]")
	private WebElement empLoginButton;
	
	@FindBy(xpath = "(//button[@class=\"recpage-login\"])[1]")
	private WebElement recLoginBtn;

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getLetBegin() {
		return letBegin;
	}

	public WebElement getEmpLoginButton() {
		return empLoginButton;
	}

	public WebElement getRecLoginBtn() {
		return recLoginBtn;
	}
	
	public RecruiterGear(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	

	public RecruiterGear RecruiterPage(WebDriver driver) throws InterruptedException {
		letBegin.click();
		Thread.sleep(2000);
		empLoginButton.click();
		Thread.sleep(2000);
		recLoginBtn.click();
		return new RecruiterGear(driver);
	}
	
}
