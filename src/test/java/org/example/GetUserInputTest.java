package org.example;


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
        List<String> userInput = argumentParser.getUserInput();

        Assertions.assertNotNull(userInput);
        Assertions.assertEquals(3, userInput.size());
        Assertions.assertEquals("1", userInput.get(0));
        Assertions.assertEquals("C:\\Users\\argig\\Desktop\\Maven.xlsx", userInput.get(1));
        Assertions.assertEquals("1", userInput.get(2));
    }
}
