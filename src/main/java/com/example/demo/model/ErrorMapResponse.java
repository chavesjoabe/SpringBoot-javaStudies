package com.example.demo.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class ErrorMapResponse {

    private int httpStatus;
    private Map<String, String> erros;
    private Long timeStamp;

}
