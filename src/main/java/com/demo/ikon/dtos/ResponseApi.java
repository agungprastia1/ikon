package com.demo.ikon.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseApi {
    private String desc;
    private Integer statusCode;
    private Object data;
}
