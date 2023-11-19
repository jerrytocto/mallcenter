package com.jerrydev.mallcenter.response;

import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse implements Serializable {

    private int code;
    private String message ;
    private Object object;
}
