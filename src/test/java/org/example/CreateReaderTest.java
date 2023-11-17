package org.example;

import org.example.models.XlsFileReader;
import org.example.models.XlsUrlReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;

public class CreateReaderTest {

    Util util = new Util();

    @Test
    public void testCreateReaderForLocalPath() {
        XlsReader reader = util.createReader("1");
        Assertions.assertTrue(reader instanceof XlsFileReader);
    }

    @Test
    public void testCreateReaderForUrl() {
        XlsReader reader = util.createReader("2");
        Assertions.assertTrue(reader instanceof XlsUrlReader);
    }

}
