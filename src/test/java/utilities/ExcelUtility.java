package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static FileInputStream fis;
	public static FileOutputStream fos;
	public static String path;
	public static CellStyle style;
	
	public ExcelUtility(String path) {
		this.path=path;
	}
	
	
	public int getRowCount(String sheetName) throws IOException {
		fis= new FileInputStream(path);
		workbook= new XSSFWorkbook(fis);
		sheet=workbook.getSheet(sheetName);
		int rowCount=sheet.getLastRowNum();
		workbook.close();
		fis.close();
		return rowCount;
	}
	
	public int getCellCount(String sheetName, int rowNum) throws IOException {
		fis= new FileInputStream(path);
		workbook= new XSSFWorkbook(fis);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rowNum);
		int cellCount=row.getLastCellNum();
		workbook.close();
		fis.close();
		return cellCount;
	}
	
	public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
		fis= new FileInputStream(path);
		workbook= new XSSFWorkbook(fis);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rowNum);
		cell=row.getCell(colNum);
		
		DataFormatter formatter= new DataFormatter();
		String data;
		
		try {
			data=formatter.formatCellValue(cell);
		}
		catch(Exception e) {
			data="";
		}
		
		workbook.close();
		fis.close();
		return data;
	}
	
	public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {
		File file= new File(path);
		
		if(!file.exists()) {			//if file not exist then create new file
			workbook= new XSSFWorkbook();
			fos= new FileOutputStream(path);
			workbook.write(fos);
		}
		fis= new FileInputStream(path);
		workbook= new XSSFWorkbook(fis);
		
		if(workbook.getSheetIndex(sheetName)==-1) {			//if sheet not exist then create new one
			workbook.createSheet(sheetName);
		}
		sheet=workbook.getSheet(sheetName);
		
		if(sheet.getRow(rowNum)==null) {					//if row not exist then create new one
			sheet.createRow(rowNum);
		}
		row=sheet.getRow(rowNum);
		
		cell=row.createCell(colNum);
		cell.setCellValue(data);
		fos= new FileOutputStream(path);
		workbook.write(fos);		
		workbook.close();
		fis.close();
		fos.close();
	}
	
public static void fillGreenColor(String fileName, String sheetName, int rowNum, int cellNum) throws IOException {
		
		fis= new FileInputStream(path);
		workbook= new XSSFWorkbook(fis);
		sheet= workbook.getSheet(sheetName);
		row=sheet.getRow(rowNum);
		
		cell=row.getCell(cellNum);
		style=workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		fos= new FileOutputStream(fileName);
		workbook.write(fos);
		workbook.close();
		fis.close();
		fos.close();
		
	
	}
	public static void fillRedColor(String fileName, String sheetName, int rowNum, int cellNum) throws IOException {
		
		fis= new FileInputStream(path);
		workbook= new XSSFWorkbook(fis);
		sheet= workbook.getSheet(sheetName);
		row=sheet.getRow(rowNum);
		cell=row.getCell(cellNum);
		
		style=workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		fos= new FileOutputStream(fileName);
		workbook.write(fos);
		workbook.close();
		fis.close();
		fos.close();
	
	}

}
