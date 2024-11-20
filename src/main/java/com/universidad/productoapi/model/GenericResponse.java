package com.universidad.productoapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponse {
    private int code;
    private Date date;
    private String message;
    private String details;

}