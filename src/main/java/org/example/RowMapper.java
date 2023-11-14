package org.example;

import org.apache.poi.ss.usermodel.Row;

public interface RowMapper<T>{

     T mapRow(Row row) ;

}
