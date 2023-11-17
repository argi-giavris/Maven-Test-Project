package org.example;

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.formula.functions.T;
import org.example.models.Employee;
import org.example.models.EmployeeRowMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class XlsParserTest {

    @Mock
    private RowMapper<T> rowMapper;

    public XlsParserTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testReadFile() throws IOException {

        String filePath = "src/main/resources/Maven.xlsx";
        InputStream inputStream = new FileInputStream(filePath);

        when(rowMapper.mapRow(any())).thenReturn(new T());

        XlsParser parser = new XlsParser();
        List<T> dataList = parser.readFile(inputStream, rowMapper, "Name", "Employee Number", "Department");
        Assertions.assertEquals(10, dataList.size());

        // Verify that mapRow was called for each row
        verify(rowMapper, times(10)).mapRow(any());
    }


}
