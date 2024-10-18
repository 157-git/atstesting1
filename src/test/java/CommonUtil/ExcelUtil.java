package CommonUtil;

import java.io.FileInputStream;
import java.io.IOException;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
		///////////////////////////////////////////////////////////////////////////////

		    
		    // Method to get cell value as string from an Excel sheet
		    public static String getDataFromExcel(String excelFilePath, String sheetName, int rowNum, int colNum) {
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
		                        cellData = String.valueOf(cell.getNumericCellValue());
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
	}

