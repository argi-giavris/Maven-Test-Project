package org.example;

import org.example.Writer;
import org.example.XlsReader;
import org.example.models.*;

public class XlsReaderFactory {

    public XlsReader createReader(String userOption){

        if (userOption.equals("1")){        //local path
            return new XlsFileReader();
        }else{                          //url link
            return new XlsUrlReader();
        }
    }
}
