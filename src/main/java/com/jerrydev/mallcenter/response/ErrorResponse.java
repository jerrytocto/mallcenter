package com.jerrydev.mallcenter.response;

import lombok.Builder;
import lombok.Data;

@Data@Builder
public class ErrorResponse {
    private int code;
    private String message;
}
