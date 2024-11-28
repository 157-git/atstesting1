package ObjectRepository_POM;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DataBase {
	
	public WebDriver driver;

	@FindBy(xpath = "//span[text()=\"Upload Files\"]")
	private WebElement UploadFiles;
	
	@FindBy(xpath = "//span[text()=\"Uploaded Excel Data\"]")
	private WebElement UploadExcelData;
	
	@FindBy(xpath = "(//input[@type=\"file\"])[1]")
	private WebElement ChooseExcel;
	
	@FindBy(xpath = "(//button[text()=\"View\"])[1]")
	private WebElement ExcelView;
	
	@FindBy(xpath = "(//button[text()=\"Upload\"])[1]")
	private WebElement ExcelUpload;
	
	@FindBy(xpath = "(//input[@type=\"checkbox\"])[1]")
	private WebElement SheetCheckbox;
	
	@FindBy(xpath = "(//input[@type=\"file\"])[2]")
	private WebElement ChooseResume;
	
	@FindBy(xpath = "(//button[text()=\"View\"])[2]")
	private WebElement ResumeView;
	
	@FindBy(xpath = "(//button[text()=\"Upload\"])[2]")
	private WebElement ResumeUpload;
	
	public DataBase(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getUploadFiles() {
		return UploadFiles;
	}


	public WebElement getUploadExcelData() {
		return UploadExcelData;
	}

	
	public WebElement getSheetCheckbox() {
		return SheetCheckbox;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getChooseExcel() {
		return ChooseExcel;
	}

	public WebElement getExcelView() {
		return ExcelView;
	}

	public WebElement getExcelUpload() {
		return ExcelUpload;
	}

	public WebElement getChooseResume() {
		return ChooseResume;
	}

	public WebElement getResumeView() {
		return ResumeView;
	}

	public WebElement getResumeUpload() {
		return ResumeUpload;
	}
	
	
	//...........................................
	public dataBaseExcelView dbDropdown(WebDriver driver) {
		WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(10));
		w.until(ExpectedConditions.visibilityOf(UploadFiles));
		UploadFiles.click();
		return new dataBaseExcelView(driver);
	}
	
	public dataBaseExcelView dbDropdown2(WebDriver driver) {
		WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(10));
		w.until(ExpectedConditions.visibilityOf(UploadFiles));
		UploadExcelData.click();
		return new dataBaseExcelView(driver);
	}
	
	//..............................................
	public dataBaseExcelView ExcelView(WebDriver driver) throws InterruptedException {
		WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(10));
		w.until(ExpectedConditions.visibilityOf(ExcelView));
		ExcelView.click();
		return new dataBaseExcelView(driver);
	}
	public dataBaseExcelView ExcelUpload(WebDriver driver) throws InterruptedException {
		ChooseExcel.sendKeys("C:\\Users\\hp\\Downloads\\Calling_Tracker_Format (1).xlsx");
		Thread.sleep(1000);
		if (SheetCheckbox.isDisplayed()) {
			SheetCheckbox.click();
		}
		Thread.sleep(500);
		ExcelUpload.click();
		return new dataBaseExcelView(driver);
	}
	

}
