package testdatamanagement;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.aventstack.extentreports.ExtentTest;

import baseframework.ExtentFactory;
import constants.Dataconstants;
import customexceptions.TestDataException;
import models.testdatamodel.ExcelData;
import utilities.FilePathBuilder;

public class ExcelTestData implements TestData {
	String sheetName;
	Workbook workbook;
	ExtentTest logger;
	private static Map<String, List<ExcelData>> totalObjects;
	List<ExcelData> totalRowDatas;
	ExcelData testcaseData;

	public ExcelTestData(String sheetName) {
		this.sheetName = sheetName;
		totalObjects = new HashMap<>();
	}

	public void setTestData(String inputDataFile) {
		logger = ExtentFactory.getInstance().getExtent();
		FilePathBuilder filePathBuilder = new FilePathBuilder(inputDataFile);
		filePathBuilder.setParentDirectory(Dataconstants.TESTDATA_DIRECTORY);
		try (FileInputStream file = new FileInputStream(filePathBuilder.getFilePath())) {
			Sheet sheet = setExcelWorkbook(inputDataFile, file);

			int sheetRows = sheet.getLastRowNum();
			if (sheetRows == 0) {
				logger.info("Empty sheet in the WorkBook with name: " + sheetName);
				return;
			}
			List<String> headers = null;

			for (int rows = 0; rows <= sheetRows; rows++) {
				Row row = sheet.getRow(rows);
				String celldata = row.getCell(row.getFirstCellNum()).toString();
				if (celldata.startsWith(Dataconstants.HEADER_TAG)) {
					if (rows != 0)
						totalObjects.put(testcaseData.getTestCaseName(), totalRowDatas);
					totalRowDatas = new ArrayList<>();
					headers = collectHeaders(row.cellIterator());

				} else {
					List<String> dataList = fetchcellsfromRow(headers, row);
					if (dataList.size() > 2)
						createDataobjects(headers, dataList);
				}

			}

			totalObjects.put(testcaseData.getTestCaseName(), totalRowDatas);

		} catch (Exception e) {
			logger.info(e);
		}

	}

	public Sheet setExcelWorkbook(String inputDataFile, FileInputStream file) throws IOException, TestDataException {
		if (inputDataFile.endsWith("xlsx")) {
			workbook = new XSSFWorkbook(file);
		} else if (inputDataFile.endsWith("xls")) {
			workbook = new HSSFWorkbook(file);
		} else {
			throw new TestDataException("incorrect file provided please provide file with xlsx or xls extentions");
		}
		Sheet sheet = workbook.getSheet(sheetName);
		if (sheet == null) {
			logger.info("No sheet available in the Workbook with :" + sheetName);
			throw new TestDataException(sheetName + " is not available");
		}
		return sheet;
	}

	private List<String> collectHeaders(Iterator<Cell> cellIterator) {
		List<String> headers = new ArrayList<>();
		while (cellIterator.hasNext()) {
			headers.add(cellIterator.next().toString().trim());
		}
		return headers;
	}

	private List<String> fetchcellsfromRow(List<String> headers, Row row) {
		List<String> dataList = new ArrayList<>();
		Iterator<Cell> cellIterator = row.cellIterator();
		for (int i = 0; cellIterator.hasNext(); i++) {
			dataList.add(cellIterator.next().toString().trim());
			if (i > headers.size())
				break;
		}
		return dataList;
	}

	private void createDataobjects(List<String> headers, List<String> dataList) {
		if (headers.size() == dataList.size()) {
			String testcaseName = dataList.get(0);
			String testCaseId = dataList.get(1);
			testcaseData = new ExcelData(testcaseName);
			testcaseData.setTestCaseID(testCaseId);
			for (int i = 0; i < dataList.size(); i++) {
				testcaseData.setTestData(headers.get(i), dataList.get(i));
			}
			
			totalRowDatas.add(testcaseData);

		}
	}

	public String getValueFromSheet(Sheet sheet, int rowNum, int colNum) throws TestDataException {
		checkSheet(sheet);
		String value = "";
		logger.info(rowNum+"is row");
		logger.info(colNum+"is col");
		Row row = sheet.getRow(2);
		Cell cell = row.getCell(3);
		if (cell == null)
			throw new TestDataException("there no value in the cell");
		else
			value = String.valueOf(cell);
		return value;

	}

	public List<String> getRowData(Sheet sheet, int row) throws TestDataException {
		checkSheet(sheet);
		Row sheetRow = sheet.getRow(row);
		List<String> rowValues = new ArrayList<>();
		if (sheetRow == null) {
			throw new TestDataException("Empty sheet in the WorkBook with this name: " + sheet.getSheetName());
		} else {
			Iterator<Cell> cellIterator = sheetRow.cellIterator();
			while (cellIterator.hasNext()) {
				rowValues.add(cellIterator.next().toString().trim());
			}
		}
		return rowValues;

	}

	public List<String> getAllColumnValuesFromSheet(Sheet sheet, int col) throws TestDataException {
		checkSheet(sheet);

		int sheetRowcount = sheet.getLastRowNum();
		List<String> colValues = new ArrayList<>();
		if (sheetRowcount == 0) {
			throw new TestDataException("Empty sheet in the WorkBook with sheet name: " + sheet.getSheetName());
		} else {
			for (int i = 0; i <=sheetRowcount; i++) {
				colValues.add(String.valueOf(sheet.getRow(i).getCell(col)));
			}

		}
		return colValues;
	}

	private void checkSheet(Sheet sheet) throws TestDataException {
		if (sheet == null)
			throw new TestDataException("Empty sheet in the WorkBook giving null value as return ");
	}

	public synchronized Object[][] getdata(String testCaseName) throws TestDataException {
		Object[][] samples = null;

		List<ExcelData> testdata = totalObjects.get(testCaseName);
		if (testdata != null) {
			samples = new Object[testdata.size()][1];
			for (int i = 0; i < testdata.size(); i++) {

				samples[i][0] = testdata.get(i);

			}
		} else {
			throw new TestDataException(
					"one of the data in the excel is not having method name as " + testCaseName + " in " + sheetName);

		}

		return samples;

	}
}
