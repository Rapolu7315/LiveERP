package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil {
	
	Workbook wb;
	//writing constructor for excel path
	
	public ExcelFileUtil(String Excelpath) throws Throwable
	{
		FileInputStream fb = new FileInputStream(Excelpath);
		wb = WorkbookFactory.create(fb);
	}
	
	//counting no of rows in a sheet
	public int rowcount(String sheetname)
	{
		return wb.getSheet(sheetname).getLastRowNum();
	}
	//read cell data
	
	public String getcelldata(String sheetname,int row,int cloumn)
	{
		String data = "";
		if(wb.getSheet(sheetname).getRow(row).getCell(cloumn).getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
			//read integer type cell data
			int celldata =(int) wb.getSheet(sheetname).getRow(row).getCell(cloumn).getNumericCellValue();
			data = String.valueOf(celldata);
			
		}
		else
		{
			data = wb.getSheet(sheetname).getRow(row).getCell(cloumn).getStringCellValue();
			
		}
		return data;
		
	}
	//method for set cell data
	public void setcelldata(String sheetname,int row,int column,String status,String writeExcel)throws Throwable
	{
		//get sheet from wb
		Sheet ws = wb.getSheet(sheetname);
		//get row from sheet
		Row rowNum = ws.getRow(row);
		//create cell in a row
		Cell cell = rowNum.createCell(column);
		//write status
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("pass"))
		{
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			//colour text
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			rowNum.getCell(column).setCellStyle(style);
			
		}
		else if(status.equalsIgnoreCase("fail"))
		{
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			//colour text
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			style.setFont(font);
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			rowNum.getCell(column).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("Blocked"))
		{
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			//colour text
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			style.setFont(font);
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			rowNum.getCell(column).setCellStyle(style);
		}
		FileOutputStream fo = new FileOutputStream(writeExcel);
		wb.write(fo);
	}
	public static void main(String[] args)throws Throwable
	{
		ExcelFileUtil xl = new ExcelFileUtil(null);
		int rc = xl.rowcount(null);
		System.out.println(rc);
		for(int i=1;i<=rc;i++)
		{
			String fname = xl.getcelldata(null, rc, i);
			String mname = xl.getcelldata(null, rc, 1);
			String lname = xl.getcelldata(null, rc, 2);
			String eid = xl.getcelldata(null, rc, 3);
			System.out.println(fname+"      "+mname+"      "+lname+"    "+eid);
			xl.setcelldata(null, rc, 4, "pass", "D:/Results.xlsx");
			
		}
		
				
	}

}
