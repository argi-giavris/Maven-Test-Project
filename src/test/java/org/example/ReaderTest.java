package org.example;

import org.example.models.XlsFileReader;
import org.example.models.XlsUrlReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;



public class ReaderTest {

    @Test
    public void testLocalFileReader() throws IOException {
        XlsReader reader = new XlsFileReader();
        String filePath = "C:\\Users\\argig\\Desktop\\Maven.xlsx";
        InputStream inputStream = reader.read(filePath);
        Assertions.assertNotNull(inputStream);
    }

    @Test
    public void testUrlReader() throws IOException {
        XlsReader reader = new XlsUrlReader();
        String url = "https://someOnlineExcel";
        InputStream inputStream = reader.read(url);
        Assertions.assertNotNull(inputStream);

    }
}
