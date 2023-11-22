package org.example.models;

public class UserInput {

    String readerOption;
    String filePath;
    String writerOption;

    public UserInput(String readerOption, String filePath, String writerOption) {
        this.readerOption = readerOption;
        this.filePath = filePath;
        this.writerOption = writerOption;
    }

    public UserInput(){};

    public String getReaderOption() {
        return readerOption;
    }

    public void setReaderOption(String readerOption) {
        this.readerOption = readerOption;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getWriterOption() {
        return writerOption;
    }

    public void setWriterOption(String writerOption) {
        this.writerOption = writerOption;
    }
}
