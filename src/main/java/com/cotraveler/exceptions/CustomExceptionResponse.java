/*
 * Copyright (c)  Aleksey Plekhanov 03.02.2023, 18:55
 */

package com.cotraveler.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class CustomExceptionResponse {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Getter
    @Setter
    private Date timestamp;
    @Getter
    @Setter
    private int status;
    @Getter
    @Setter
    private String message;

}
