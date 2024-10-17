package ObjectRepository_POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShortListed {
 
	public WebDriver driver;
	
	@FindBy(xpath = "(//i[@class=\"fa-regular fa-pen-to-square\"])[1]")
	private WebElement editAction;
	
	public WebElement getEditAction() {
		return editAction;
	}



	public ShortListed(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
    public WebDriver Action(WebDriver driver) {
         editAction.click();
   return driver;
  }
    
   public WebDriver Form(WebDriver driver) {
	   
	return driver; 
   }

}
