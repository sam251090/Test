package csl.v4.smokeTest.Automation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataConfig {

	XSSFWorkbook wb;
	XSSFSheet sheet;

	public ExcelDataConfig(String excelPath)
	{
		try {
			File src=new File("C:\\Excel Data\\TestData.xlsx");
			FileInputStream fis=new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		} catch (Exception e) {
			System.out.println("Exception in ExcelDataConfig");
		}
	}	
	
	public String getData(int sheetNumber, int row, int column)
	{
		sheet=wb.getSheetAt(sheetNumber);
		String data=sheet.getRow(row).getCell(column).getStringCellValue();
		return data;
	}
	
	public void writeData(int sheetNumber, int row, int column, int data) throws IOException
	{
		try {
		sheet=wb.getSheetAt(sheetNumber);
		Row roww = sheet.getRow(row);
		Cell columnn = roww.getCell(column);
/*		String updatename = columnn.getStringCellValue();
		updatename=data;  */
		columnn.setCellValue(data);
		File src=new File("C:\\Excel Data\\TestData.xlsx");
		FileOutputStream outputStream = new FileOutputStream(src);
		wb.write(outputStream);
		outputStream.close();
		
		}
		catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public void writeStringData(int sheetNumber, int row, int column, String data) throws IOException
	{
		try {
		sheet=wb.getSheetAt(sheetNumber);
		Row roww = sheet.getRow(row);
		Cell columnn = roww.getCell(column);
/*		String updatename = columnn.getStringCellValue();
		updatename=data;  */
		columnn.setCellValue(data);
		File src=new File("C:\\Excel Data\\TestData.xlsx");
		FileOutputStream outputStream = new FileOutputStream(src);
		wb.write(outputStream);
		outputStream.close();
		
		}
		catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public int getRowCount(int sheetIndex) {
		// TODO Auto-generated method stub
		int row=wb.getSheetAt(sheetIndex).getLastRowNum();
		row=row+1;
		return row;
	}

}
