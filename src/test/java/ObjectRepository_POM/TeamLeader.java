package ObjectRepository_POM;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TeamLeader {

//public WebDriver driver;
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	@FindBy(className = "main-homepage-btn")
	private WebElement letBegin;
	
	@FindBy(xpath = "(//button[@class=\"login1\"])[1]")
	private WebElement empLoginButton;
	
	@FindBy(xpath = "(//button[@class=\"recpage-login\"])[2]")
	private WebElement teamLeaderbtn;

	public ThreadLocal<WebDriver> getDriver() {
		return driver;
	}

	public WebElement getLetBegin() {
		return letBegin;
	}

	public WebElement getEmpLoginButton() {
		return empLoginButton;
	}

	public WebElement getTeamLeaderbtn() {
		return teamLeaderbtn;
	}
	
	public TeamLeader(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public TeamLeader teamLeaderPage(WebDriver driver) throws InterruptedException {
	
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        try {
	            // Wait and click "Let Begin" button
	            wait.until(ExpectedConditions.elementToBeClickable(letBegin)).click();
	            System.out.println("Clicked on 'Let Begin' button successfully.");
	        } catch (Exception e) {
	            System.out.println("Failed to click 'Let Begin' button: " + e.getMessage());
	        }

	        try {
	            // Wait and click "Employee Login" button
	            wait.until(ExpectedConditions.elementToBeClickable(empLoginButton)).click();
	            System.out.println("Clicked on 'Employee Login' button successfully.");
	        } catch (Exception e) {
	            System.out.println("Failed to click 'Employee Login' button: " + e.getMessage());
	        }

	        try {
	            // Wait and click "Team Leader" button
	            wait.until(ExpectedConditions.elementToBeClickable(teamLeaderbtn)).click();
	            System.out.println("Clicked on 'Team Leader' button successfully.");
	        } catch (Exception e) {
	            System.out.println("Failed to click 'Team Leader' button: " + e.getMessage());
	        }
	
		return new TeamLeader(driver);
	}
	
	public TeamLeader teamLeaderlogin(WebDriver driver) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        try {
	            // Wait and click "Team Leader" button
	            wait.until(ExpectedConditions.visibilityOf(teamLeaderbtn));
	            teamLeaderbtn.click();
	            System.out.println("Clicked on 'Team Leader' button successfully.");
	        } catch (Exception e) {
	            System.out.println("Failed to click 'Team Leader' button: " + e.getMessage());
	        }
		return new TeamLeader(driver);
		
	}
	
	
	
	
}
