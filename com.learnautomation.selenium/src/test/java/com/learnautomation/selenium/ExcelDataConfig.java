package com.learnautomation.selenium;

import java.io.File;
import java.io.FileInputStream;

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
