package recruiter;

import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v118.database.Database;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import CommonUtil.ExcelUtil;
import CommonUtil.JavaUtil;
import CommonUtil.PropertyFileUtil;
import CommonUtil.WebDriverUtil;
import CommonUtil.baseClass;
import CommonUtil.listenerImplementation;
import ObjectRepository_POM.DataBase;
import ObjectRepository_POM.FindCandidate;
import ObjectRepository_POM.RecruiterGear;
import ObjectRepository_POM.RecruiterhomePage;
import ObjectRepository_POM.ShortListed;
import ObjectRepository_POM.loginPage;
import ObjectRepository_POM.logoutPage;

@Listeners(listenerImplementation.class)
public class databaseUpdateNoListTestNG extends baseClass{    //699

	PropertyFileUtil pfu = new PropertyFileUtil();
	WebDriverUtil wdu = new WebDriverUtil();
	ExcelUtil eu = new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	public WebDriver sdriver;
	
	@Test(enabled = true)
	public void UpdatedNoOfCandidate() throws IOException, InterruptedException {
		
		String USERNAME = pfu.getDataFromPropertyFile("username");
		String PASSWORD = pfu.getDataFromPropertyFile("password");
		WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(20));
		String URL="https://rg.157careers.in/Dashboard/12/Recruiters";
		
		RecruiterGear r = new RecruiterGear(driver);
		r.RecruiterPage(driver);
		
		Thread.sleep(2000);
		String LoginPageUrl=driver.getCurrentUrl();
		System.out.println(LoginPageUrl);
		
		//login
		loginPage lp = new loginPage(driver);
		lp.login(USERNAME, PASSWORD);

		Thread.sleep(2000);
		String RecPageUrl=driver.getCurrentUrl();
		System.out.println(RecPageUrl);
		
		if (RecPageUrl.equals(LoginPageUrl)) {
			System.out.println("login failed");
			WebElement error = driver.findElement(By.className("loginpage-error"));
			if (error.isDisplayed()) {
				System.out.println(error.getText());
			}
			//Assert.fail("Invalid login details");
		} else if(RecPageUrl.equals(URL)){
			System.out.println("login successfull");
			
			//click on data base
			RecruiterhomePage hp = new RecruiterhomePage(driver);
			//click on dataBase
			hp.dataBase(driver);
			
			
			DataBase db=new DataBase(driver);
			//click on upload files
			db.dbDropdown2(driver);
			System.out.println(".....database......");
			
//			Thread.sleep(1000);
//			
//			//click on view excel
//			db.ExcelView(driver);
//			
//			System.out.println("..........1.........");
//			
			WebElement table = driver.findElement(By.cssSelector("table[class=\"attendance-table\"]"));
			w.until(ExpectedConditions.visibilityOf(table));
			
			//Locate the table and count initial rows
//			 List<WebElement> initialRows = driver.findElements(By.xpath("//table[@class='attendance-table']/tbody/tr"));
//	         int initialRowCount = initialRows.size();
//	         System.out.println("Initial row count: " + initialRowCount);
	         
	         WebElement totalresult = driver.findElement(By.xpath("//div[@class=\"search-count-last-div\"]"));
	        String text=totalresult.getText();
	        String number = text.replaceAll("[^0-9]", "");
	        int initialRowCount = Integer.parseInt(number);
	        System.out.println("CANDIDATE IN EXCEL :"+initialRowCount);
	         
	        Thread.sleep(1000);
	        //click on choose file
	        db.dbDropdown(driver);
	        Thread.sleep(1000);
			db.excelUploadSheet(driver);
			System.out.println("......choose file......");

	        //click on view 
			Thread.sleep(2000);
			db.dbDropdown2(driver);
	        
			System.out.println(".......uploaded excel viewed.........");
			
	        // Count rows again after adding data
			WebElement table1 = driver.findElement(By.cssSelector("table[class=\"attendance-table\"]"));
			w.until(ExpectedConditions.visibilityOf(table1));
			
	      //  List<WebElement> finalRows = driver.findElements(By.xpath("//table[@class='selfcalling-table attendance-table']/tbody/tr"));
//			List<WebElement> finalRows = driver.findElements(By.xpath("//table[@class='attendance-table']/tbody/tr"));
//	        int finalRowCount = finalRows.size();
//	        System.out.println("Final row count: " + finalRowCount);
			
			 WebElement finaltotalresult = driver.findElement(By.xpath("//div[@class=\"search-count-last-div\"]"));
		     String finaltext=finaltotalresult.getText();
		     String finalnumber = finaltext.replaceAll("[^0-9]", "");
		     int finalRowCount = Integer.parseInt(finalnumber);
		     System.out.println("CANDIDATE IN EXCEL AFTER UPLOADING :"+finalRowCount);
			
	        // Calculate the difference
	        int rowsAdded = finalRowCount - initialRowCount;
	        System.out.println("Number of rows added: " + rowsAdded);
	        
	        Thread.sleep(1000);
	        hp.dataBase(driver);
			
	        //logout............update:-12-9-24----119-122
			Thread.sleep(1000);
			logoutPage lo=new logoutPage(driver);
			lo.logout(driver, "Yes");
		}	
	}
	
	
	@Test(enabled = false)
	public void uploadExcelData() throws IOException, InterruptedException {
		
		String USERNAME = pfu.getDataFromPropertyFile("username");
		String PASSWORD = pfu.getDataFromPropertyFile("password");
		WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(20));
		String URL="https://rg.157careers.in/Dashboard/12/Recruiters";
		
		RecruiterGear r = new RecruiterGear(driver);
		r.RecruiterPage(driver);
		
		Thread.sleep(2000);
		String LoginPageUrl=driver.getCurrentUrl();
		System.out.println(LoginPageUrl);
		
		//login
		loginPage lp = new loginPage(driver);
		lp.login(USERNAME, PASSWORD);

		Thread.sleep(2000);
		String RecPageUrl=driver.getCurrentUrl();
		System.out.println(RecPageUrl);
		
		if (RecPageUrl.equals(LoginPageUrl)) {
			System.out.println("login failed");
			WebElement error = driver.findElement(By.className("loginpage-error"));
			if (error.isDisplayed()) {
				System.out.println(error.getText());
			}
		} else if(RecPageUrl.equals(URL)){
			System.out.println("login successfull");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			
			Thread.sleep(1000);
			int sheetRows = eu.countRowsInExcel("C:\\Users\\hp\\Downloads\\Calling_Tracker_Format (1).xlsx", "Sheet1");
			//exclude row-1 =it contains column name
			int excelSheetData=sheetRows-1;
			System.out.println("Rows Present in Excel Sheet File :- "+(sheetRows-1));
			
			//click on data base
			Thread.sleep(1000);
			RecruiterhomePage hp = new RecruiterhomePage(driver);
			//click on dataBase
			hp.dataBase(driver);
			
			DataBase db=new DataBase(driver);
			//click on upload excel files
			db.dbDropdown2(driver);
			Thread.sleep(2000);
			
			WebElement totalResult = driver.findElement(By.className("search-count-last-div"));
			String text=totalResult.getText();
			String numberOnly = text.replaceAll("[^0-9]", "");
			int total = Integer.parseInt(numberOnly);
			System.out.println("Total result :"+total);
			
			int TotalInitalRows = 0;
			Boolean NextPage = true;
			int pageNumber = 1;

			while (NextPage) {
			    // Get the rows of the current page's table
				Thread.sleep(2000);
			    List<WebElement> UploadexcelDataRows = driver.findElements(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr"));
			    
			    // Update total rows count
			    TotalInitalRows += UploadexcelDataRows.size();

			    // Log current page and total rows
			    Thread.sleep(1000);
			    System.out.println("Page " + pageNumber + " - Rows in this page: " + UploadexcelDataRows.size() + " - Total Rows: " + TotalInitalRows);

			    // Check for the next page button
			    Thread.sleep(2000);
			    js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			    List<WebElement> next = driver.findElements(By.cssSelector(".anticon.anticon-right"));

			    Thread.sleep(2000);
			    if (!next.isEmpty()) {
			        // Explicitly check if the 'Next' button is disabled by inspecting the 'disabled' attribute
			        WebElement nextButton = next.get(0);
			        
			        // Log the state of the 'Next' button (disabled or enabled)
			        boolean isNextPageButtonDisabled = nextButton.getAttribute("disabled") != null;
			        System.out.println("Next Page button disabled: " + isNextPageButtonDisabled);
			        Thread.sleep(2000);
			        
			        if (isNextPageButtonDisabled) {
			        	
			        	// If the 'Next' button is enabled, click it
			            System.out.println("Next Page button is enabled. Moving to next page...");
			           // nextButton.click();
			            js.executeScript("arguments[0].click();", nextButton);
			            
			            // Wait for the table rows to become visible on the next page
			            w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr")));
			            
			            // Wait for the next page button to become clickable again (to ensure the page transition is complete)
			            w.until(ExpectedConditions.elementToBeClickable(nextButton));
			            
			            // Increment page number for logging purposes
			            pageNumber++;
			           
			           
			        } else {
			            // Log when the 'Next' button is disabled (indicating the end of pagination)
			            System.out.println("Next Page button is disabled. No more pages.");
			            NextPage = false;  // Exit the loop
			        }
			        
			        if (TotalInitalRows==total) {
		            	NextPage = false;
		            	isNextPageButtonDisabled=true;
					}
			        
			    } else {
			        // Log if the 'Next' button is not found (indicating no further pages)
			        System.out.println("Next Page button not found. No more pages.");
			        NextPage = false;  // Exit the loop
			    }
			    
			   
			    
			}


			
			 // Print the total row count
             System.out.println("Total number of rows across all pages: " + TotalInitalRows);
			
			 Thread.sleep(1000);
		     //click on upload file
			 db.dbDropdown(driver);
		     Thread.sleep(1000); 
		     //upload excel file
		     db.excelUploadSheet(driver);
		     
		   Thread.sleep(3000);
		  WebElement a =driver.findElement(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr[1]"));
		 // w.until(ExpectedConditions.visibilityOf(a)); 
		  Thread.sleep(3000);
		  if (a.isDisplayed()) {
			System.out.println("data present in excel sheet");
			Thread.sleep(1000);
			
			WebElement totalResult_uploadExcel = driver.findElement(By.className("search-count-last-div"));
			String text_uploadExcel =totalResult_uploadExcel.getText();
			String numberOnly_uploadExcel  = text_uploadExcel.replaceAll("[^0-9]", "");
			int total_uploadExcel = Integer.parseInt(numberOnly_uploadExcel);
			System.out.println("Total result on uploading excel :"+total_uploadExcel );
			
			List<WebElement> rowfetch = driver.findElements(By.xpath("//table[@class=\"attendance-table\"]/tbody/tr"));
			int fetchRows=rowfetch.size();
			System.out.println("Rows fetch from excel sheet :- "+fetchRows);
			
			//click on upload excel data
			Thread.sleep(1000);
			db.dbDropdown2(driver);
			
			//.........................................................
			
			int TotalFinallRows = 0;
			Boolean NextPage_1 = true;
			int pageNumber_1 = 1;

			while (NextPage_1) {
			    // Get the rows of the current page's table
			    List<WebElement> UploadexcelDataRows = driver.findElements(By.xpath("//table[@class='attendance-table']/tbody/tr"));
			    
			    // Update total rows count
			    TotalFinallRows += UploadexcelDataRows.size();

			    // Log current page and total rows (for debugging)
			    System.out.println("Page " + pageNumber_1 + " - Rows in this page: " + UploadexcelDataRows.size() + " - Total Rows: " + TotalFinallRows);

			    // Check for the next page button
			    List<WebElement> next = driver.findElements(By.cssSelector(".anticon.anticon-right"));

			    if (next.size() > 0 && (next.size()==next.size())) {
			        // Explicitly check if the 'Next' button is disabled by inspecting the 'disabled' attribute
			        WebElement nextButton = next.get(0);
			        
			        // Log the state of the 'Next' button (disabled or enabled)
			        boolean isNextPageButtonDisabled = nextButton.getAttribute("disabled") != null;
			        System.out.println("Next Page button disabled: " + isNextPageButtonDisabled);
			        
			        if (isNextPageButtonDisabled) {
			            // If the 'Next' button is enabled, click it
			            System.out.println("Next Page button is enabled. Moving to next page...");
			            nextButton.click();
			            
			            // Wait for the table rows to become visible on the next page
			            w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='attendance-table']/tbody/tr")));
			            
			            // Wait for the next page button to become clickable again (to ensure the page transition is complete)
			            w.until(ExpectedConditions.elementToBeClickable(nextButton));
			            
			            // Increment page number for logging purposes
			            pageNumber_1++;
			        } else {
			            // Log when the 'Next' button is disabled (indicating the end of pagination)
			            System.out.println("Next Page button is disabled. No more pages.");
			            NextPage_1 = false;  // Exit the loop
			        }
			    } else {
			        // Log if the 'Next' button is not found (indicating no further pages)
			        System.out.println("Next Page button not found. No more pages.");
			        NextPage_1 = false;  // Exit the loop
			    }
			}


			
			 // Print the total row count
             System.out.println("Total number of rows across all pages: " + TotalFinallRows);
			
			
			//.........................................................
			int addedRows=TotalFinallRows-TotalInitalRows;
			System.out.println("NUMBER OF ROWS ADDED :-"+addedRows);
			
			if (excelSheetData==addedRows) {
				System.out.println("Data fully fetch successfully");
			} else {
				System.out.println("Data fully Not fetch successfully");
			}
             
             
             
			
		} else {
			System.out.println("data NOT present in excel sheet");
		}
		  
		  
		Thread.sleep(1000);
		logoutPage lo=new logoutPage(driver);
		lo.logout(driver, "Yes");  
			
		}
	}
}
