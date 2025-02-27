package CommonUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;

public class ExcelUtil {
		///////////////////////////////////////////////////////////////////////////////

		    
		    // Method to get cell value as string from an Excel sheet
		    public String getDataFromExcel(String excelFilePath, String sheetName, int rowNum, int colNum) {
		        String cellData = "";

		        try (FileInputStream fis = new FileInputStream(excelFilePath);
		             Workbook workbook = new XSSFWorkbook(fis)) {

		            Sheet sheet = workbook.getSheet(sheetName);
		            if (sheet == null) {
		                throw new IllegalArgumentException("Sheet " + sheetName + " does not exist.");
		            }

		            Row row = sheet.getRow(rowNum);
		            if (row == null) {
		                throw new IllegalArgumentException("Row " + rowNum + " does not exist.");
		            }

		            Cell cell = row.getCell(colNum);
		            if (cell != null) {
		                switch (cell.getCellType()) {
		                    case STRING:
		                        cellData = cell.getStringCellValue();
		                        break;
		                    case NUMERIC:
		                    	if (DateUtil.isCellDateFormatted(cell)) {
		                            // Format the date if the cell contains a date
		                            Date date = cell.getDateCellValue();
		                           // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		                            
		                            cellData = dateFormat.format(date);
		                        } else {
		                            // Handle as a regular numeric value
		                            cellData = String.valueOf(cell.getNumericCellValue());
		                        }
		                  
		                        break;
		                    case BOOLEAN:
		                        cellData = String.valueOf(cell.getBooleanCellValue());
		                        break;
		                    case FORMULA:
		                        cellData = cell.getCellFormula();
		                        break;
		                    
		                    default:
		                        cellData = "";
		                        break;
		                }
		            } else {
		                throw new IllegalArgumentException("Cell at row " + rowNum + " and column " + colNum + " does not exist.");
		            }

		        } catch (IOException e) {
		            e.printStackTrace();
		        }

		        return cellData;
		    }
		////////////////////////////////////////////////////////////////////////////////
		    public String writeDataInExcel(String SheetName,int rowNumber,int cellNum,String data) throws IOException {
		    	
//		    	File file=new File("src\\test\\resources\\Excel.xlsx");
//		    	FileInputStream fis=new FileInputStream(file);
//		    	XSSFWorkbook wb=new XSSFWorkbook(fis);
//		    	XSSFSheet sheet=wb.getSheet("shortlisted");
//		    	XSSFRow row=sheet.createRow(RowNumber);
//		    	row.createCell(CellNum).setCellValue(data);
//		    	
//		    	FileOutputStream fos=new FileOutputStream("src\\test\\resources\\Excel.xlsx");
//		    	wb.write(fos);
//		    	wb.close();
//		    	return data;
		    	
		    	 File file = new File("src\\test\\resources\\Excel.xlsx");
		         Workbook workbook;

		         // Try-with-resources for automatic resource management
		         try (FileInputStream fis = new FileInputStream(file)) {
		             workbook = new XSSFWorkbook(fis);
		         }

		         Sheet sheet = workbook.getSheet(SheetName);
		         if (sheet == null) {
		             sheet = workbook.createSheet(SheetName); // Create sheet if it doesn't exist
		         }

		         Row row = sheet.getRow(rowNumber);
		         if (row == null) {
		             row = sheet.createRow(rowNumber); // Create a new row if it doesn't exist
		         }

		         // Create or update the cell
		         Cell cell = row.createCell(cellNum, CellType.STRING);
		         cell.setCellValue(data);

		         // Write back to the Excel file
		         try (FileOutputStream fos = new FileOutputStream(file)) {
		             workbook.write(fos);
		         } catch (IOException e) {
		             System.err.println("Error writing to Excel file: " + e.getMessage());
		             throw e; // Rethrow the exception for higher-level handling
		         } finally {
		             workbook.close(); // Ensure the workbook is closed properly
		         }

		         return data;
		    }
		    
		    //read data from external excel sheet 
		    public static int countRowsInExcel(String filePath, String sheetName) {
		        int rowCount = 0;

		        try (FileInputStream fis = new FileInputStream(new File(filePath))) {
		            // Create a workbook instance from the Excel file
		            Workbook workbook = new XSSFWorkbook(fis);
		            
		            // Get the sheet by its name
		            Sheet sheet = workbook.getSheet(sheetName);
		            
		            if (sheet != null) {
		                // Get the number of rows (this counts the rows with content)
		                rowCount = sheet.getPhysicalNumberOfRows();
		                
		                // Optionally, you can also count rows even if they're blank
		                // rowCount = sheet.getLastRowNum() + 1;
		            }

		            workbook.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }

		        return rowCount;
		    }
	}

