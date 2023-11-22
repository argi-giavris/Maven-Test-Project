package org.example;
import org.example.models.Employee;
import org.example.models.EmployeeRowMapper;
import org.example.models.FileWriter;
import org.example.models.XlsFileReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;


public class ReadAndWriteTest {

    String filePath = "src/main/resources/Maven.xlsx";
    XlsReader reader = new XlsFileReader();
    Writer writer = new FileWriter();
    RowMapper<Employee> employeeRowMapper = new EmployeeRowMapper();
    Util util = new Util();

    @Test
    public void testReadAndWrite() throws IOException {
        util.readAndWrite(filePath,reader,employeeRowMapper,writer,"Name","Employee Number", "Department");

        Path outputPath = Paths.get("output.txt");
        Assertions.assertTrue(Files.exists(outputPath));
        long fileSize = Files.size(outputPath);
        Assertions.assertTrue(fileSize > 0);

        List<String> fileContent = Files.readAllLines(outputPath);
        Assertions.assertFalse(fileContent.isEmpty());

        Assertions.assertEquals("Name, ID, Department", fileContent.get(0));

        //10 employees and the header row
        Assertions.assertEquals(11, fileContent.size());
    }
}
