package org.example;

import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.models.*;
import org.example.models.FileWriter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.net.URL;
import java.util.zip.ZipInputStream;

public class Util {

    private static final int ZIP_FILE_HEADER_SIZE = 4;
    private static final byte[] ZIP_FILE_HEADER = {0x50, 0x4B, 0x03, 0x04}; // ZIP file header

    public <T> void readAndWrite(String filePath, XlsReader reader, RowMapper<T> mapper, Writer<T> writer, String... columns) {

        try (InputStream file = reader.read(filePath)) {
            final XlsParser parser = new XlsParser();

            List<T> employeeList = parser.readFile(file,mapper,columns);
            writer.write(employeeList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public boolean isExcelFile(Path filePath) throws IOException {
        // Open the file with a ZipInputStream to check for the ZIP file header
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(filePath.toFile()))) {
            byte[] header = new byte[ZIP_FILE_HEADER_SIZE];
            zipInputStream.read(header);
            return Arrays.equals(header, ZIP_FILE_HEADER);
        }
    }
}