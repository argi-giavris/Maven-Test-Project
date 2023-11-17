package org.example.models;

import org.example.Util;
import org.example.XlsReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class XlsUrlReader implements XlsReader {

    public InputStream read(String filePath) throws IOException {

        URL url = new URL(filePath);

        Path tempFilePath = Files.createTempFile("downloadedFile", ".tmp");
        try (InputStream inputStream = url.openStream()) {
            Files.copy(inputStream, tempFilePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Error downloading file from the internet", e);
        }

        Util util = new Util();
        if (util.isExcelFile(tempFilePath)) {
            // Convert the temporary file to a FileInputStream
            return new FileInputStream(tempFilePath.toFile());
        } else {
            throw new RuntimeException("The downloaded file is not a valid Excel file.");
        }
    }
}
