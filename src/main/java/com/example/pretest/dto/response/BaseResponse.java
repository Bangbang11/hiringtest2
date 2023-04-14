package com.example.pretest.dto.response;

import com.example.pretest._enum.StatusCodeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResponse<T> {
    private String statusCode;
    private String message;
    private List<BaseErrorMessage> errorMessages;
    private T data;

    public BaseResponse() {this.errorMessages = new ArrayList<>();}

    public void setStatusCode(String statusCode) {this.statusCode = statusCode;}

    public void setStatusCodeByEnum(StatusCodeEnum statusCodeByEnum) {
        this.statusCode = statusCodeByEnum.getCode();
        this.message = statusCodeByEnum.getDescription();

    }
}
