package ObjectRepository_POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import lombok.Data;

@Data
public class JobDescription {

	public JobDescription(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[text()=\"View Job Descriptions\"]")
	private WebElement viewJD;
	
	@FindBy(xpath = "(//button[text()=\"View\"])[1]")
	private WebElement viewBtn;
	
	
	public JobDescription viewJD(WebDriver driver) {
		viewJD.click();
		return new JobDescription(driver);
	}
	public JobDescription viewbtn(WebDriver driver) {
		viewBtn.click();
		return new JobDescription(driver);
	}

}
