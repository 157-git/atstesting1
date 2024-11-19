package ObjectRepository_POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RecruiterhomePage {

	public WebDriver driver;
	
	@FindBy(xpath = "//span[text()=\"Shortlisted \"]")
	private WebElement Shortlisted;
	
	@FindBy(xpath = "//span[text()=\"Add Candidate\"]")
	private WebElement AddCandidate;
	
	@FindBy(xpath = "//span[text()=\"Find Candidate\"]")
	private WebElement FindCandidate;
	
	@FindBy(xpath = "//span[text()=\"Job Description\"]")
	private WebElement JobDescription;
	
	@FindBy(xpath = "//span[text()=\"Database\"]")
	private WebElement DataBase;
	
	@FindBy(xpath = "//span[text()=\"Chat Section\"]")
	private WebElement ChatSection;
	
	@FindBy(xpath = "//span[text()=\"Note Pad\"]")
	private WebElement NotePad;
	
	@FindBy(xpath = "//span[text()=\"Portal\"]")
	private WebElement Portal;
	
	@FindBy(xpath = "//span[text()=\"About Us\"]")
	private WebElement AboutUs;
	
	@FindBy(xpath = "//span[text()=\"Help\"]")
	private WebElement Help;
	
	@FindBy(xpath = "//span[text()=\"Choose Colour\"]")
	private WebElement ChooseColor;
	
	@FindBy(xpath = "//span[text()='Logout']")
	private WebElement Logout;

	public WebElement getShortlisted() {
		return Shortlisted;
	}

	public WebElement getAddCandidate() {
		return AddCandidate;
	}

	public WebElement getFindCandidate() {
		return FindCandidate;
	}

	public WebElement getJobDescription() {
		return JobDescription;
	}

	public WebElement getDataBase() {
		return DataBase;
	}

	public WebElement getChatSection() {
		return ChatSection;
	}

	public WebElement getNotePad() {
		return NotePad;
	}

	public WebElement getPortal() {
		return Portal;
	}

	public WebElement getAboutUs() {
		return AboutUs;
	}

	public WebElement getHelp() {
		return Help;
	}

	public WebElement getChooseColor() {
		return ChooseColor;
	}

	public WebElement getLogout() {
		return Logout;
	}
     
	
    public RecruiterhomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
    
   public ShortListed home(WebDriver driver) {
	   Shortlisted.click();
	   return new ShortListed(driver);
   } 
   
   public AddCandidate addCan(WebDriver driver) {
	   AddCandidate.click();
	   return new AddCandidate(driver);
   }

   public FindCandidate FinCan(WebDriver driver) {
	   FindCandidate.click();
	   return new FindCandidate(driver);
   }
   
  
}
