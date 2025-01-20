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

import CommonUtil.WebDriverUtil;
import lombok.Data;

@Data
public class logoutPage {
	
	WebDriverUtil wdu = new WebDriverUtil();
	
	//@FindBy(xpath= "//div[@class=\"sidebar-menu\"]/ul/li[13]/a/i")
	@FindBy(css = ".fa-solid.fa-power-off")
	private WebElement Logout;
	
	@FindBy(xpath="//button[text()=\"Yes\"]")
	private WebElement yes;
	
	@FindBy(xpath="//button[text()=\"No\"]")
	private WebElement no;


	public logoutPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	
	public logoutPage logout(WebDriver driver,String value) throws InterruptedException {
		
		//WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor j=(JavascriptExecutor) driver;
	
			//sign out
			//scroll down to sign out
			wdu.mouseHover(driver, Logout);
			//j.executeScript("arguments[0].scollintoview;", Logout);
			Thread.sleep(1000);
			Logout.click();
			
			//select yes or no
			Thread.sleep(1000);
			WebElement tl_out = driver.findElement(By.className("modal-body"));
			//w.until(ExpectedConditions.visibilityOf(tl_out));
			if (tl_out.isDisplayed()) {
				if (value.equalsIgnoreCase("Yes")) {
					yes.click();
					System.out.println("SIGNOUT");
				} else {
					no.click();
				}
			}else {
				System.out.println("logout box not displayed");
			}
			
			
		
		
		return new logoutPage(driver);
	}
}
