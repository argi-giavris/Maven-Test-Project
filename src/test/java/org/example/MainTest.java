package org.example;
import org.apache.poi.ss.usermodel.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import java.io.FileInputStream;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MainTest {

    private Workbook workbook;

    @BeforeEach
    void setUp() {
        try {
            ClassLoader classLoader = Main.class.getClassLoader();
            try (InputStream inputStream = classLoader.getResourceAsStream("Maven.xlsx")) {

                workbook = WorkbookFactory.create(Objects.requireNonNull(inputStream));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testExcelFileExists() {
        assertNotNull(workbook);
    }

    @Test
    void testExcelHasSheet(){
        Sheet sheet = workbook.getSheetAt(0);
        assertNotNull(sheet);
    }

    @Test
    void testExcelHasRows(){
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();

        assertNotNull(rowIterator);
    }

    @Test
    void testReadingExcelData() {
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();

        // Assuming the first row is the header row
        if (rowIterator.hasNext()) {
            Row headerRow = rowIterator.next();
            assertEquals("Name", headerRow.getCell(0).getStringCellValue());
            assertEquals("Employee Number", headerRow.getCell(1).getStringCellValue());
            assertEquals("Department", headerRow.getCell(2).getStringCellValue());
        }


        int rowCount = 0;
        while (rowIterator.hasNext() && rowCount < 10) {
            Row row = rowIterator.next();
            rowCount++;
        }

        assertEquals(10, rowCount);
    }
}


