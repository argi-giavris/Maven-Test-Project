package org.example;


import java.io.IOException;
import java.util.List;
public interface Writer<T> {

    void write(List<T> data) throws IOException;
}
