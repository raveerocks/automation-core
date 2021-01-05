package io.raveerocks.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class TestCaseUtil {
    public static List<TestCase> parse(File testCaseFile, int sheetIndex) {
        try {
            XSSFWorkbook testCaseWorkbook = new XSSFWorkbook(testCaseFile);
            XSSFSheet sheet = testCaseWorkbook.getSheetAt(sheetIndex);
            Row titleRow = sheet.getRow(0);
            Iterator<Cell> titleRowIterator = titleRow.iterator();
            Map<String, Integer> indexMap = new HashMap<>();
            for (Cell cell : titleRow) {
                indexMap.put(cell.getStringCellValue(), cell.getColumnIndex());
            }
            List<TestCase> testCases = new ArrayList<>();
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                TestCase testCase = new TestCase();
                testCase.setTestServiceProvider(row.getCell(indexMap.get(CapabilityConstants.TEST_SERVICE_PROVIDER)).getStringCellValue());
                testCase.setOperatingSystem(row.getCell(indexMap.get(CapabilityConstants.OPERATING_SYSTEM)).getStringCellValue());
                testCase.setOperatingSystemVersion(row.getCell(indexMap.get(CapabilityConstants.OPERATING_SYSTEM_VERSION)).getStringCellValue());
                testCase.setResolution(row.getCell(indexMap.get(CapabilityConstants.RESOLUTION)).getStringCellValue());
                testCase.setBrowser(row.getCell(indexMap.get(CapabilityConstants.BROWSER)).getStringCellValue());
                testCase.setBrowserVersion(row.getCell(indexMap.get(CapabilityConstants.BROWSER_VERSION)).getStringCellValue());
                testCase.setName(row.getCell(indexMap.get(CapabilityConstants.NAME)).getStringCellValue());
                testCase.setBuild(row.getCell(indexMap.get(CapabilityConstants.BUILD)).getStringCellValue());
                testCase.setImplicitWaitTime(String.valueOf(row.getCell(indexMap.get(CapabilityConstants.IMPLICIT_WAIT_TIME)).getNumericCellValue()));
                testCase.setHeadLess(String.valueOf(row.getCell(indexMap.get(CapabilityConstants.HEAD_LESS)).getBooleanCellValue()));
                testCase.setParams(row.getCell(indexMap.get(CapabilityConstants.PARAMS)).getStringCellValue().split("\\s*,\\s*"));
                testCase.setExpectedResult(row.getCell(indexMap.get(CapabilityConstants.EXPECTED_RESULT)).getStringCellValue());
                testCases.add(testCase);
            }
            return testCases;
        } catch (InvalidFormatException | IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
