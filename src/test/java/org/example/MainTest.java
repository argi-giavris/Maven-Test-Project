package org.example;


import org.example.models.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    Util util ;

    void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }


    @BeforeEach
    void setUp() {
        util = new Util();

    }

    @Test
    void testExcelFileExists() throws IOException {

        provideInput("C:\\Users\\argig\\Desktop\\Maven.xlsx");
        List<Employee> employeeList = util.read();
        assertNotNull(employeeList);
        assertFalse(employeeList.isEmpty());
    }


    @Test
    void testExcelHasNoRows() throws IOException {

        provideInput("C:\\Users\\argig\\Desktop\\test.xlsx");
        List<Employee> employeeList = util.read();
        assertTrue(employeeList.isEmpty());
    }

    @Test
    void testExcelInvalidPath() throws IOException {
        String wrongPath = "Path:\\invalid_input.xlsx";
        provideInput(wrongPath);
        // This should throw a RuntimeException

        RuntimeException runtimeException = assertThrows(RuntimeException.class, ()->{
            util.read();
        });

        assertEquals(runtimeException.getMessage(), "File not found: " + wrongPath);
    }

    @Test
    void testExcelInvalidFormat() {
        String wrongFormat = "C:\\Users\\argig\\Desktop\\wrong format.xlsx";
        provideInput(wrongFormat);

        RuntimeException runtimeException = assertThrows(RuntimeException.class, ()->{
            util.read();
        });

        assertEquals(runtimeException.getMessage(), "Wrong excel format");
    }
}


