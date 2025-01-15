package ObjectRepository_POM;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.Data;

@Data
public class Superuser {
	
		//public WebDriver driver;
		private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

		@FindBy(className = "main-homepage-btn")
	    private WebElement letBegin;
	    
	    @FindBy(xpath = "(//button[@class=\"login1\"])[1]")
	    private WebElement employeeButton;
	    
	    @FindBy(xpath = "(//button[@class=\"recpage-login1\"])[2]")
	    private WebElement superuserBtn;
	    
	    @FindBy(xpath = "//span[text()=\"Super User\"]")
	    private WebElement superUser;
	    
	    @FindBy(xpath = "//span[text()=\"Billing Dashboard\"]")
	    private WebElement billingDashboard;
	    
	    @FindBy(xpath = "//span[text()=\" Make Invoice\"]")
	    private WebElement makeInvoice;
	    
	    @FindBy(xpath = "//span[text()=\"Sent Profile\"]")
	    private WebElement sentProfile;
	    
	    @FindBy(xpath = "//span[text()=\"Add Client Details\"]")
	    private WebElement addClientDetails;
	    
	    @FindBy(xpath = "//span[text()=\"Add Manager\"]")
	    private WebElement addManager;
	    
	    @FindBy(xpath = "//span[text()=\"Team Details\"]")
	    private WebElement teamDetails;
	    
	    @FindBy(xpath = "//span[text()=\"Capex\"]")
	    private WebElement capex;
	    
	    
	    public static ThreadLocal<WebDriver> getDriver() {
			return driver;
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
