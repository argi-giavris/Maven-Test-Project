package org.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XlsParser {

    public <T> List<T> readFile(InputStream is, RowMapper<T> rowMapper, String... columns) throws IOException {


        try (XSSFWorkbook wb = new XSSFWorkbook(is)) {

            Sheet sheet = wb.getSheetAt(0);

            if (excelHasCorrectFormat(sheet, columns)) {

                List<T> dataList = new ArrayList<>();

                for (int n = 1; n < sheet.getPhysicalNumberOfRows(); n++) {
                    Row row = sheet.getRow(n);
                    T data = rowMapper.mapRow(row);
                    dataList.add(data);
                }
                return dataList;
            } else {
                throw new RuntimeException("Wrong excel format");
            }
        }
    }

    private boolean excelHasCorrectFormat(Sheet sheet, String[] columns) {
        Row headerRow = sheet.getRow(0);

        if (headerRow != null) {
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.getCell(i);
                if (cell != null) {
                    String actualHeader = cell.getStringCellValue();
                    if (!columns[i].equals(actualHeader)) {
                        System.out.println("Header mismatch at column " + i);
                        return false;
                    }
                } else {
                    System.out.println("Header mismatch at column" + i);
                    return false;
                }
            }
        } else {
            System.out.println("Header row is missing");
            return false;
        }
        return true;
    }
}
