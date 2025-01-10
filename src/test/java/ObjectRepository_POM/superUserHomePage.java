package ObjectRepository_POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import lombok.Data;

@Data
public class superUserHomePage {

	//public WebDriver driver;
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	@FindBy(xpath = "//span[text()=\"Subscription Plans\"]")
	private WebElement subscription;
	
	@FindBy(xpath = "//span[text()=\"Job Description\"]")
	private WebElement jobdescription;
	
	@FindBy(xpath = "//span[text()=\"View Job Descriptions\"]")
	private WebElement viewJobDescription;
	
	@FindBy(xpath = "//span[text()=\"Super User\"]")
	private WebElement superuser;
	
	@FindBy(xpath = "//span[text()=\"Billing Dashboard\"]")
	private WebElement billingDashboard;
	
	@FindBy(xpath="//span[text()=\" Make Invoice\"]")
	private WebElement makeInvoice;
	
	@FindBy(xpath = "//span[text()=\" Invoice Report\"]")
	private WebElement  invoiceReport;
	
	

}
