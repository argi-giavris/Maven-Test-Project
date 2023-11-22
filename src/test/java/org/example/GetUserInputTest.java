package org.example;


import org.example.models.UserInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;


public class GetUserInputTest {

    void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Test
    void testGetUserInput() {
        provideInput("1\nC:\\Users\\argig\\Desktop\\Maven.xlsx\n1\n");

        ArgumentParser argumentParser = new ArgumentParser();
        UserInput userInput = argumentParser.getUserInput();

        Assertions.assertNotNull(userInput);
        Assertions.assertEquals("1", userInput.getReaderOption());
        Assertions.assertEquals("C:\\Users\\argig\\Desktop\\Maven.xlsx", userInput.getFilePath());
        Assertions.assertEquals("1", userInput.getWriterOption());
    }
}
