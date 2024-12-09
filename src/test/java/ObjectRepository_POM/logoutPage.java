package ObjectRepository_POM;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class logoutPage {
	
	
	@FindBy(xpath = "//span[text()='Logout']")
	private WebElement Logout;
	
	@FindBy(xpath="//button[text()=\"Yes\"]")
	private WebElement yes;
	
	@FindBy(xpath="//button[text()=\"No\"]")
	private WebElement no;

	public WebElement getLogout() {
		return Logout;
	}

	public logoutPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	
	public logoutPage logout(WebDriver driver,String value) throws InterruptedException {
		
		WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor j=(JavascriptExecutor) driver;
		//sign out
		//scroll down to sign out
			
			j.executeScript("arguments[0].scollintoview", Logout);
			Thread.sleep(500);
			Logout.click();
			
			//select yes or no
			Thread.sleep(500);
			WebElement tl_out = driver.findElement(By.className("modal-body"));
			w.until(ExpectedConditions.visibilityOf(tl_out));
			if (value.equalsIgnoreCase("Yes")) {
				yes.click();
				System.out.println("SIGNOUT");
			} else {
				no.click();
			}
			
		
		
		return new logoutPage(driver);
	}
}
