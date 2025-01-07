package ObjectRepository_POM;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Superuser {
	
		//public WebDriver driver;
		private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

		@FindBy(className = "main-homepage-btn")
	    private WebElement letBegin;
	    
	    @FindBy(xpath = "(//button[@class=\"login1\"])[1]")
	    private WebElement employeeButton;
	    
	    @FindBy(xpath = "(//button[@class=\"recpage-login1\"])[2]")
	    private WebElement superuserBtn;
	    
	    public static ThreadLocal<WebDriver> getDriver() {
			return driver;
		}

		public WebElement getLetBegin() {
			return letBegin;
		}

		public WebElement getEmployeeButton() {
			return employeeButton;
		}

		public WebElement getManagerBtn() {
			return superuserBtn;
		}

		public Superuser(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
	    
		public Superuser superuserPage(WebDriver driver) throws InterruptedException {
			
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
		            wait.until(ExpectedConditions.elementToBeClickable(employeeButton)).click();
		            System.out.println("Clicked on 'Employee Login' button successfully.");
		        } catch (Exception e) {
		            System.out.println("Failed to click 'Employee Login' button: " + e.getMessage());
		        }

		        try {
		            // Wait and click "Team Leader" button
		            wait.until(ExpectedConditions.elementToBeClickable(superuserBtn)).click();
		            System.out.println("Clicked on 'Superuser' button successfully.");
		        } catch (Exception e) {
		            System.out.println("Failed to click 'Superuser' button: " + e.getMessage());
		        }
		
			return new Superuser(driver);
		}
		
		
		public Superuser superuserLogin(WebDriver driver) {
			 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		        try {
		            // Wait and click "Team Leader" button
		            wait.until(ExpectedConditions.visibilityOf(superuserBtn));
		            superuserBtn.click();
		            System.out.println("Clicked on 'Superuser' button successfully.");
		        } catch (Exception e) {
		            System.out.println("Failed to click 'Superuser' button: " + e.getMessage());
		        }
			return new Superuser(driver);
			
		} 

}
