package recruiter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import CommonUtil.ApplicantListnerImplementation;
import CommonUtil.ExcelUtil;
import CommonUtil.JavaUtil;
import CommonUtil.PropertyFileUtil;
import CommonUtil.WebDriverUtil;
import CommonUtil.baseClass_applicant;

@Listeners(ApplicantListnerImplementation.class)
public class applicantForm extends baseClass_applicant{

	PropertyFileUtil pfu = new PropertyFileUtil();
	WebDriverUtil wdu=new WebDriverUtil();
	ExcelUtil eu=new ExcelUtil();
	JavaUtil ju = new JavaUtil();
	public WebDriver sdriver;
	
	
	
	@Test(enabled = false)
	public void ApplicantForm() throws IOException, InterruptedException {
		WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor j=(JavascriptExecutor) driver;
		System.out.println("applicant form");
		
		String applicantForm = driver.getCurrentUrl();
		
		String NAME = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 0);
		String CONTACT_NUMBER = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 1);
		String EMAIL = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 2);
		String PREF_LOCATION = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 3);
		String NOTICE_PERIOD = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 4);
		//String CERTIFICATION = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 5);
		//String DOB = eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 6);
		String CTC=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 7);
		String EXP_CTC=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 8);
		String EDUCATION=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 9);
		String JOBDESIGNATION=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 10);
		String CURRENT_LOCATION=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 11);
		String TOTAL_EXPERIENCE=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 12);
		//String RELEVENT_EXPERIENCE=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 13);
		String GENDER=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 14);
		//String COMPANYNAME=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 15);
		//String NEGOTIATION=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 16);
		//String OFFER_SALARY=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 17);
		//String OFFER_DETAILS=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 18);
		//String WHATSAPP=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 19);
		//String DISABILITY=eu.getDataFromExcel("src\\test\\resources\\Excel.xlsx", "applicant", 1, 20);
		
		WebElement Name = driver.findElement(By.name("candidateName"));
		Name.sendKeys(NAME);
		
		WebElement contact_number = driver.findElement(By.name("contactNumber"));
		 // Parse the number as a double
        double value = Double.parseDouble(CONTACT_NUMBER);
        // Convert to a long to remove decimal places
        long longValue = (long) value;
        CONTACT_NUMBER= String.valueOf(longValue);
        contact_number.sendKeys(CONTACT_NUMBER);
		
		WebElement email = driver.findElement(By.name("candidateEmail"));
		email.sendKeys(EMAIL);
		
		String CTC_L = CTC.split(",")[0];
		WebElement ctcLakh = driver.findElement(By.name("lineUp.currentCTCLakh"));
		ctcLakh.sendKeys(CTC_L);
		WebElement ctcThousand = driver.findElement(By.name("lineUp.currentCTCThousand"));
		String ctc_T = "";
		if (CTC.contains(",")) {
			ctc_T=CTC.substring(CTC.indexOf(",")+1).trim();
		}
		ctcThousand.sendKeys(ctc_T);
		
		String EXP_CTC_L = EXP_CTC.split(",")[0];
		WebElement expCTC_L = driver.findElement(By.name("lineUp.expectedCTCLakh"));
		expCTC_L.sendKeys(EXP_CTC_L);
		WebElement expCTC_T = driver.findElement(By.name("lineUp.expectedCTCThousand"));
		String exp_T="";
		if (EXP_CTC.contains(",")) {
			exp_T=EXP_CTC.substring(EXP_CTC.indexOf(",")+1).trim();
		}
		expCTC_T.sendKeys(exp_T);
		
		j.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		
		Thread.sleep(1000);
		WebElement education = driver.findElement(By.name("lineUp.qualification"));
		education.sendKeys(EDUCATION);
		
		Thread.sleep(1000);
		WebElement jobDesignation = driver.findElement(By.name("jobDesignation"));
		jobDesignation.sendKeys(JOBDESIGNATION);
		
		Thread.sleep(1000);
		WebElement currentLOC = driver.findElement(By.name("currentLocation"));
		currentLOC.sendKeys(CURRENT_LOCATION);
		
		j.executeScript("window.scrollTo(0, 0);");
		
		WebElement pref_Location = driver.findElement(By.name("lineUp.preferredLocation"));
		pref_Location.sendKeys(PREF_LOCATION);
		
		WebElement notice_period = driver.findElement(By.name("lineUp.noticePeriod"));
		notice_period.sendKeys(NOTICE_PERIOD);
		
//		LocalDate currentDate = LocalDate.now();
//        LocalDate nextDate = currentDate.plusDays(1);
//        // Format the next date to 'YYYY-MM-DD' format
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        String formattedDate = nextDate.format(formatter);
//		WebElement interviewdate = driver.findElement(By.name("lineUp.availabilityForInterview"));
//		interviewdate.sendKeys(formattedDate);
		
//		WebElement joiningDate = driver.findElement(By.name("lineUp.expectedJoinDate"));
//		LocalDate joiningAfter = currentDate.plusDays(90);
//		String joinDate = joiningAfter.format(formatter);
//		joiningDate.sendKeys(joinDate);
		
		Thread.sleep(1000);
		String EXP_Y = TOTAL_EXPERIENCE.split(",")[0].replaceAll("[^0-9]", "");
		WebElement totalExp_y = driver.findElement(By.name("lineUp.experienceYear"));
		totalExp_y.sendKeys(EXP_Y);
		WebElement totalExp_m = driver.findElement(By.name("lineUp.experienceMonth"));
		String EXP_M="";
		if (TOTAL_EXPERIENCE.contains(",")) {
			EXP_M=TOTAL_EXPERIENCE.substring(TOTAL_EXPERIENCE.indexOf(",")+1).trim();
		}
		totalExp_m.sendKeys(EXP_M);
		
		j.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		
//		Thread.sleep(1000);
//		WebElement releventExp = driver.findElement(By.name("lineUp.relevantExperience"));
//		String REL_EXP = RELEVENT_EXPERIENCE.replaceAll("[^0-9.]", ""); 
//		releventExp.sendKeys(REL_EXP);
//		j.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	
		if (GENDER.contains("Male")) {
			driver.findElement(By.xpath("//span[text()=\"Male\"]")).click();
		} else {
			driver.findElement(By.xpath("//span[text()=\"Female\"]")).click();
		}
		
//		Thread.sleep(1000);
//		if (!(DISABILITY.isEmpty())) {
//			driver.findElement(By.xpath("(//span[text()=\"Yes\"])[1]")).click();
//			w.until(ExpectedConditions.visibilityOfElementLocated(By.className("disability-dropdown")));
//			WebElement DIS_DD = driver.findElement(By.name("lineUp.disabilityDetails"));
//			DIS_DD.click();
//			Thread.sleep(500);
//			wdu.handleDropdown(DIS_DD, DISABILITY);
//			} else {
//			driver.findElement(By.xpath("(//span[text()=\"No\"])[1]")).click();
//		}
		
//		j.executeScript("window.scrollTo(0, 0);");
//		
//		WebElement certification = driver.findElement(By.name("lineUp.certificates[0].certificateName"));
//		certification.sendKeys(CERTIFICATION);
		
//		File certificate=new File("src\\test\\resources\\certificate.pdf");
//		WebElement certificateUpload = driver.findElement(By.name("lineUp.certificates[0].certificateFile"));
//		certificateUpload.sendKeys(certificate.getAbsolutePath());
		
//		WebElement dob = driver.findElement(By.name("lineUp.dateOfBirth"));
//		dob.sendKeys("26-10-2000");
		
		
		Thread.sleep(1000);
		j.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		File resume=new File("src\\test\\resources\\RecTesting1.pdf");
		WebElement resumeUpload = driver.findElement(By.id("resumeUpload"));
		resumeUpload.sendKeys(resume.getAbsolutePath());
		
		Thread.sleep(1000);
		File photo=new File("src\\test\\resources\\uploadPhotoProfile.jpeg");
		WebElement photoUpload = driver.findElement(By.id("lineUp.photo"));
		photoUpload.sendKeys(photo.getAbsolutePath());
		
//		Thread.sleep(1000);
//		if (!(COMPANYNAME.isEmpty())) {
//			driver.findElement(By.xpath("(//span[text()=\"Yes\"])[2]")).click();
//			j.executeScript("window.scrollTo(0, document.body.scrollHeight);");
//			
//			WebElement CompanyName = driver.findElement(By.name("lineUp.companyName"));
//			CompanyName.sendKeys(COMPANYNAME);
//			
//			driver.findElement(By.name("lineUp.offersalary")).sendKeys(OFFER_SALARY);
//			
//			if (!(NEGOTIATION.isEmpty())) {
//				driver.findElement(By.xpath("(//span[text()=\"Yes\"])[3]")).click();
//			} else {
//				driver.findElement(By.xpath("(//span[text()=\"No\"])[3]")).click();
//			}
//			
//			driver.findElement(By.name("lineUp.offerdetails")).sendKeys(OFFER_DETAILS);	
//			
//		} else {
//			driver.findElement(By.xpath("//span[text()=\"No\"]")).click();
//		}
		
		
//		if (!(WHATSAPP.isEmpty())) {
//			driver.findElement(By.xpath("(//span[text()=\"Yes\"])[4]")).click();
//			w.until(ExpectedConditions.visibilityOfElementLocated(By.name("alternateNumber")));
//			WebElement whatsup = driver.findElement(By.name("alternateNumber"));
//			 double value1 = Double.parseDouble(WHATSAPP);
//		        // Convert to a long to remove decimal places
//		        long longValue1 = (long) value1;
//		        WHATSAPP= String.valueOf(longValue1);
//		        whatsup.sendKeys(WHATSAPP);
//			
//		} else {
//			driver.findElement(By.xpath("(//span[text()=\"No\"])[4]")).click();
//		}
		
		j.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		Thread.sleep(500);
		WebElement submit = driver.findElement(By.xpath("//button[text()=\"Submit\"]"));
		j.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", submit);
		Thread.sleep(1000);
		submit.click();
		
		//String afterSubmitURL = driver.getCurrentUrl();
//		WebElement error = driver.findElement(By.xpath("//span[@class=\"error\"]"));
		
//		if (error.isDisplayed()) {
//			Assert.fail(" REQUIRED DATA IS NOT CORRECT");
//			wdu.ScreenShot(driver, "applicantForm");
//		} else {
			
			WebElement toastmsg = w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"Toastify__toast-body\"]/div[2]")));
			String msg = toastmsg.getText();
			System.out.println(msg);
			Thread.sleep(1000);
			msg=msg.toLowerCase();
			if (msg.equals("Please fill all required fields")) {
				Assert.fail("ALL REQUIRED FILLED DATA IS REQUIRED");
				wdu.ScreenShot(driver, "applicantForm");
			} else if(msg.equals("Failed to submit details")){
				Assert.fail("FAILED TO SUBMIT DETAILS");
				wdu.ScreenShot(driver, "applicantForm");
			}else if(msg.contains("form submitted successfully")){
				System.out.println("FORM SUBMITED SUCCESSFULLY");
				wdu.ScreenShot(driver, "applicantForm");
			}else {
				Assert.fail("FIX THE ERROR TO SUBMIT DETAILS");
			}
//		}	
	
	}
	
	
	
	@Test(enabled = true)
	public void AapplicantFormMYSQL() throws InterruptedException, ParseException {
		WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor j=(JavascriptExecutor) driver;
		System.out.println("applicant form");
		
		String applicantForm = driver.getCurrentUrl();
		
		//...................get the data from database...................
		
		String ApplicantEmail="rahul@gmail.com";		
		
		
		Connection connection=BS();
		if (connection == null) {
            System.out.println("Database connection is null, cannot insert data.");
            return;
        }
		
		
		String query = "SELECT * FROM applicantform where ApplicantEmail=?";
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1,ApplicantEmail);  
            
            // Execute the query and get the result set
            ResultSet resultSet = preparedStatement.executeQuery();
            
            
            // Process the result set
            if (resultSet.next()) {
            	System.out.println("candidate data found with :"+ApplicantEmail);
            	
            	String NAME= resultSet.getString("ApplicantName");
            	String EMAIL=resultSet.getString("ApplicantEmail");
            	String CONTACT=resultSet.getString("ContactNumber");
            	String PREFFERED_LOCATION=resultSet.getString("PrefferedLocation");
            	String CURRENT_LOCATION=resultSet.getString("CurrentLocation");
            	String CURRENT_SALARY=resultSet.getString("CurrentSalary");
            	String EXPECTED_SALARY=resultSet.getString("ExpectedSalary");
            	String JOB_DESIGNATION=resultSet.getString("JobDesignation");
            	String EDUCATION=resultSet.getString("Education");
            	String NOTICE_PERIOD=resultSet.getString("NoticePeriod");
//            	String INTERVIEW_DATE=resultSet.getString("InterviewDate");
//            	String JOINING_DATE=resultSet.getString("JoiningDate");
            	String TOTAL_EXP=resultSet.getString("TotalExperience");
            	//String RELEVENT_EXP=resultSet.getString("ReleventExperience");
            	String GENDER=resultSet.getString("Gender");
            	//String COMPANY_NAME=resultSet.getString("CompanyName");
            	//String OFFER_SALARY=resultSet.getString("OfferSalary");
            	//String OFFER_DETAILS=resultSet.getString("OfferDetails");
            	//String CERTIFICATION=resultSet.getString("Certification");
            	//String DOB=resultSet.getString("DateOfBirth");
            	//String WHATSAPP=resultSet.getString("whatsappNumber");
            	//String DISABILITY=resultSet.getString("disability");
            	//String NEGOTIATION=resultSet.getString("negotiation");
            	//certificates and profile not added
            	Blob RESUME = resultSet.getBlob("Resume");
            	
            	
            	
            	WebElement Name = driver.findElement(By.name("candidateName"));
        		Name.sendKeys(NAME);
        		
        		WebElement contact_number = driver.findElement(By.name("contactNumber"));
        		contact_number.sendKeys(CONTACT);
        		
        		WebElement email = driver.findElement(By.name("candidateEmail"));
        		email.sendKeys(EMAIL);
        		
        		String CTC_L = CURRENT_SALARY.split(",")[0];
        		WebElement ctcLakh = driver.findElement(By.name("lineUp.currentCTCLakh"));
        		ctcLakh.sendKeys(CTC_L);
        		WebElement ctcThousand = driver.findElement(By.name("lineUp.currentCTCThousand"));
        		String ctc_T = "";
        		if (CURRENT_SALARY.contains(",")) {
        			ctc_T=CURRENT_SALARY.substring(CURRENT_SALARY.indexOf(",")+1).trim();
        		}
        		ctcThousand.sendKeys(ctc_T);
        		
        		String EXP_CTC_L = EXPECTED_SALARY.split(",")[0];
        		WebElement expCTC_L = driver.findElement(By.name("lineUp.expectedCTCLakh"));
        		expCTC_L.sendKeys(EXP_CTC_L);
        		WebElement expCTC_T = driver.findElement(By.name("lineUp.expectedCTCThousand"));
        		String exp_T="";
        		if (EXPECTED_SALARY.contains(",")) {
        			exp_T=EXPECTED_SALARY.substring(EXPECTED_SALARY.indexOf(",")+1).trim();
        		}
        		expCTC_T.sendKeys(exp_T);
        		
        		Thread.sleep(1000);
        		WebElement education = driver.findElement(By.name("lineUp.qualification"));
        		education.sendKeys(EDUCATION);
        		j.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        		
        		Thread.sleep(1000);
        		WebElement jobDesignation = driver.findElement(By.name("jobDesignation"));
        		jobDesignation.sendKeys(JOB_DESIGNATION);
        		
        		Thread.sleep(1000);
        		WebElement currentLOC = driver.findElement(By.name("currentLocation"));
        		currentLOC.sendKeys(CURRENT_LOCATION);
        		
        		j.executeScript("window.scrollTo(0, 0);");
        		WebElement prefferedLocation = driver.findElement(By.name("lineUp.preferredLocation"));
        		prefferedLocation.sendKeys(PREFFERED_LOCATION);
        		   		
        		WebElement notice_period = driver.findElement(By.name("lineUp.noticePeriod"));
        		notice_period.sendKeys(NOTICE_PERIOD);
        		
//        		LocalDate currentDate = LocalDate.now();
//                LocalDate nextDate = currentDate.plusDays(1);
//                // Format the next date to 'YYYY-MM-DD' format
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//                String formattedDate = nextDate.format(formatter);
//        		WebElement interviewdate = driver.findElement(By.name("lineUp.availabilityForInterview"));
//        		interviewdate.sendKeys(formattedDate);
        		
//        		Thread.sleep(1000);
//        		LocalDate joiningAfter = currentDate.plusDays(60);
//        		String joinDate = joiningAfter.format(formatter);
//        		WebElement joiningDate = driver.findElement(By.id("lineUp.expectedJoinDate"));
//        		joiningDate.sendKeys(joinDate);
        		
        		Thread.sleep(1000);
        		//String  EXP_Y= TOTAL_EXP.split(",")[0];
        		String EXP_Y = TOTAL_EXP.split(",")[0].replaceAll("[^0-9]", "");
        		WebElement totalExp_y = driver.findElement(By.name("lineUp.experienceYear"));
        		totalExp_y.sendKeys(EXP_Y);
        		WebElement totalExp_m = driver.findElement(By.name("lineUp.experienceMonth"));
        		String EXP_M="";
        		if (TOTAL_EXP.contains(",")) {
        			EXP_M=TOTAL_EXP.substring(TOTAL_EXP.indexOf(",")+1).trim();
        		}
        		totalExp_m.sendKeys(EXP_M);
        		
//        		Thread.sleep(1000);
//        		WebElement releventExp = driver.findElement(By.name("lineUp.relevantExperience"));
//        		String REL_EXP = RELEVENT_EXP.replaceAll("[^0-9.]", ""); 
//        		releventExp.sendKeys(REL_EXP);
//        		j.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        		
        		if (GENDER.contains("Male")) {
        			driver.findElement(By.xpath("//span[text()=\"Male\"]")).click();
        		} else {
        			driver.findElement(By.xpath("//span[text()=\"Female\"]")).click();
        		}
        		
//        		Thread.sleep(1000);
//        		if (!(DISABILITY.isEmpty())) {
//        			driver.findElement(By.xpath("(//span[text()=\"Yes\"])[1]")).click();
//        			w.until(ExpectedConditions.visibilityOfElementLocated(By.className("disability-dropdown")));
//        			WebElement DIS_DD = driver.findElement(By.name("lineUp.disabilityDetails"));
//        			DIS_DD.click();
//        			Thread.sleep(500);
//        			wdu.handleDropdown(DIS_DD, DISABILITY);
//        			} else {
//        			driver.findElement(By.xpath("(//span[text()=\"No\"])[1]")).click();
//        		}
        		
//        		j.executeScript("window.scrollTo(0, 0);");
//        		Thread.sleep(1000);
//        		WebElement certification = driver.findElement(By.name("lineUp.certificates[0].certificateName"));
//        		certification.sendKeys(CERTIFICATION);
        		
//        		JavascriptExecutor js = (JavascriptExecutor) driver;
//        		WebElement dob = driver.findElement(By.name("lineUp.dateOfBirth"));
//        		System.out.println(DOB);
//        		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
//        		SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
//        		Date date = inputFormat.parse(DOB);
//        		String formattedDate1 = dateFormat1.format(date);
//    	       // js.executeScript("arguments[0].value='" + formattedDate1 + "';", dob);
//        		dob.sendKeys(formattedDate1);
//    	        System.out.println(formattedDate1);
    	        
        		Thread.sleep(1000);
        		j.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        		WebElement resumeUpload = driver.findElement(By.id("resumeUpload"));
//        		String filepath="src/test/resources/Resumedownloadedfile.pdf";
//        		try (InputStream inputStream = RESUME.getBinaryStream();
//                        FileOutputStream fileOutputStream = new FileOutputStream(filepath)) {
//
//                       // Read from the InputStream and write to the output file
//                       byte[] buffer = new byte[1024];
//                       int bytesRead;
//                       while ((bytesRead = inputStream.read(buffer)) != -1) {
//                           fileOutputStream.write(buffer, 0, bytesRead);
//                       }
//
//                       System.out.println("File saved as: " + filepath);
//                   }
//            	
//
//			 catch (SQLException e) {
//           e.printStackTrace();
//				} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
        		
        		File resume=new File("src\\test\\resources\\Resumedownloadedfile.pdf");
        		resumeUpload.sendKeys(resume.getAbsolutePath());
        	
        		
        		Thread.sleep(1000);
        		WebElement photoUpload = driver.findElement(By.id("lineUp.photo"));
        		File photo=new File("src\\test\\resources\\uploadPhotoProfile.jpeg");
        		photoUpload.sendKeys(photo.getAbsolutePath());
        		
//        		Thread.sleep(1000);
//        		if (!(COMPANY_NAME.isEmpty())) {
//        			driver.findElement(By.xpath("(//span[text()=\"Yes\"])[2]")).click();
//        			j.executeScript("window.scrollTo(0, document.body.scrollHeight);");
//        			
//        			WebElement CompanyName = driver.findElement(By.name("lineUp.companyName"));
//        			CompanyName.sendKeys(COMPANY_NAME);
//        			
//        			driver.findElement(By.name("lineUp.offersalary")).sendKeys(OFFER_SALARY);
//        			
//        			if (!(NEGOTIATION.isEmpty())) {
//        				driver.findElement(By.xpath("(//span[text()=\"Yes\"])[3]")).click();
//					} else {
//						driver.findElement(By.xpath("(//span[text()=\"No\"])[3]")).click();
//					}
//        			
//        			driver.findElement(By.name("lineUp.offerdetails")).sendKeys(OFFER_DETAILS);
//        			
//        		} else {
//        			driver.findElement(By.xpath("//span[text()=\"No\"]")).click();
//        		}
        		
//        		j.executeScript("window.scrollTo(0, document.body.scrollHeight);");
//        		if (!(WHATSAPP.isEmpty())) {
//					driver.findElement(By.xpath("(//span[text()=\"Yes\"])[4]")).click();
//					w.until(ExpectedConditions.visibilityOfElementLocated(By.name("alternateNumber")));
//					driver.findElement(By.name("alternateNumber")).sendKeys(WHATSAPP);
//					
//				} else {
//					driver.findElement(By.xpath("(//span[text()=\"No\"])[4]")).click();
//				}
        		
        		
        		Thread.sleep(1000);
        		WebElement submit = driver.findElement(By.xpath("//button[text()=\"Submit\"]"));
        		j.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", submit);
        		Thread.sleep(2000);
        		submit.click();
        		
//        		WebElement error = driver.findElement(By.xpath("//span[@class=\"error\"]"));
//        		
//        		if (error.isDisplayed()) {
//        			Assert.fail(" REQUIRED DATA IS NOT CORRECT");
//        			wdu.ScreenShot(driver, "applicantForm");
//        		} else {
        		
        		WebElement toastmsg = w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"Toastify__toast-body\"]/div[2]")));
        		String msg = toastmsg.getText();
        		System.out.println(msg);
        		Thread.sleep(1000);
                msg = msg.toLowerCase();

                if (msg.contains("please fill all required fields")) {
                    wdu.ScreenShot(driver, "applicantForm");
                    Assert.fail("ERROR: All required fields must be filled.");
                } else if (msg.contains("failed to submit details")) {
                    wdu.ScreenShot(driver, "applicantForm");
                    Assert.fail("ERROR: Failed to submit form details.");
                } else if (msg.contains("form submitted successfully")) {
                    System.out.println("✅ FORM SUBMITTED SUCCESSFULLY");
                    wdu.ScreenShot(driver, "applicantForm"); // Optional: Capture success
                } else {
                    wdu.ScreenShot(driver, "applicantForm");
                    Assert.fail("ERROR: Fix the issues to submit details.");
                }
        	
//        		}	
        		

            	
            } else {
                System.out.println("No candidate found with EMAIL in DATABASE: " +ApplicantEmail );
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	
		
	
	}
	
}
