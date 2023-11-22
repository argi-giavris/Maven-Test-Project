package org.example;

import org.example.models.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateWriterTest {

    XlsWriterFactory writerFactory = new XlsWriterFactory();

    @Test
    public void testCreateWriterForConsole() {
        Writer<Employee> writer = writerFactory.createWriter("1");
        Assertions.assertTrue(writer instanceof ConsoleWriter);
    }

    @Test
    public void testCreateReaderForUrl() {
        Writer<Employee> writer = writerFactory.createWriter("2");
        Assertions.assertTrue(writer instanceof FileWriter);
    }
}
