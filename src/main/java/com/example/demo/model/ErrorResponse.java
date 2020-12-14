package com.example.demo.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {

    private String mensagem;
    private int httpStatus;
    private long timeStamp;
}
