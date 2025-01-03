package ObjectRepository_POM;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RecruiterGear {

	//public WebDriver driver;
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	@FindBy(className = "main-homepage-btn")
	private WebElement letBegin;
	
	@FindBy(xpath = "(//button[@class=\"login1\"])[1]")
	private WebElement empLoginButton;
	
	@FindBy(xpath = "(//button[@class=\"recpage-login\"])[1]")
	private WebElement recLoginBtn;

	public ThreadLocal<WebDriver> getDriver() {
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
		WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(30));
		  try {
	            // Wait and click "Let Begin" button
	            w.until(ExpectedConditions.elementToBeClickable(letBegin)).click();
	            System.out.println("Clicked on 'Let Begin' button successfully.");
	        } catch (Exception e) {
	            System.out.println("Failed to click 'Let Begin' button: " + e.getMessage());
	        }

	        try {
	            // Wait and click "Employee Login" button
	            w.until(ExpectedConditions.elementToBeClickable(empLoginButton)).click();
	            System.out.println("Clicked on 'Employee Login' button successfully.");
	        } catch (Exception e) {
	            System.out.println("Failed to click 'Employee Login' button: " + e.getMessage());
	        }

	        try {
	            // Wait and click "Team Leader" button
	            w.until(ExpectedConditions.elementToBeClickable(recLoginBtn)).click();
	            System.out.println("Clicked on 'recruiter' button successfully.");
	        } catch (Exception e) {
	            System.out.println("Failed to click 'Recruiter' button: " + e.getMessage());
	        }
		return new RecruiterGear(driver);
	}
	
	public RecruiterGear RecruiterLogin(WebDriver driver) {
		WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(10));
		w.until(ExpectedConditions.visibilityOf(recLoginBtn));
		recLoginBtn.click();
		return new RecruiterGear(driver);
	}
}
