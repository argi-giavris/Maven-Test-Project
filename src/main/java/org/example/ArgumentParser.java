package org.example;

import org.example.models.UserInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArgumentParser {

    public UserInput getUserInput(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Choose an option:\n1. Enter file path from the console\n2. Download file from the internet\nOption: ");
        String option = scanner.nextLine();

        UserInput userInput = new UserInput();

        switch (option) {
            case "1","2" -> {
                userInput.setReaderOption(option);
            }
            default -> throw new IllegalArgumentException("Invalid option");
        }

        if (userInput.getReaderOption().equals("1")){
            System.out.print("Enter the Excel file path: ");
        }else {
            System.out.print("Enter the URL to download the file: ");
        }
        String filePath = scanner.nextLine();


        userInput.setFilePath(filePath);
        System.out.print("Choose an option:\n1. Print on console\n2. Print on file\nOption: ");
        option = scanner.nextLine();

        switch (option) {
            case "1","2" -> {
                userInput.setWriterOption(option);
            }
            default -> throw new IllegalArgumentException("Invalid option");
        }

        return userInput;
    }
}
