package com.ninjatutorials.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Utilities {

	public static final int IMPLICIT_WAIT_TIMEOUT = 10;
	public static final int PAGE_WAIT_TIMEOUT = 5;

	public static String generateEmailWithTimeStamp() {
		Date date = new Date();
		String dateWithTimeStamp = date.toString().replace(" ", "_").replace(":", "_");
		return "testerone" + dateWithTimeStamp + "@gmail.com";
	}

	public static Object[][] getTestDataFromExcel(String sheetName) {

		File file = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\ninjatutorials\\testdata\\Tutorilsninjatestdata.xlsx");

		Object[][] data = null;

		try {
			FileInputStream fis = new FileInputStream(file);
			Workbook workbook = WorkbookFactory.create(fis);
			Sheet sheet = workbook.getSheet(sheetName);
			data = new Object[sheet.getPhysicalNumberOfRows() - 1][sheet.getRow(0).getPhysicalNumberOfCells()];

			for (int row = sheet.getFirstRowNum() + 1; row <= sheet.getLastRowNum(); row++) {
				for (int cell = sheet.getRow(row).getFirstCellNum(); cell < sheet.getRow(row)
						.getLastCellNum(); cell++) {
					Cell cellData = sheet.getRow(row).getCell(cell);
					CellType cellType = cellData.getCellType();

					switch (cellType) {
					case STRING:
						data[row - 1][cell] = cellData.getStringCellValue();
						break;
					case NUMERIC:
						data[row - 1][cell] = Integer.toString((int) cellData.getNumericCellValue());
						break;

					case BOOLEAN:
						data[row - 1][cell] = cellData.getBooleanCellValue();
					default:
						break;
					}
					
					
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return data;
	}

}
