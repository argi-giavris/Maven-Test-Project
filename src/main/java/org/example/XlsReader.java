package org.example;

import java.io.IOException;
import java.io.InputStream;

public interface XlsReader{
    InputStream read(String filePath) throws IOException;
}
