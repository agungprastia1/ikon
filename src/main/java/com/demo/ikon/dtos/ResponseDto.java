package com.demo.ikon.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
    private Long id;
    @JsonIgnore
    private Long userId;
    private String title;
    @JsonIgnore
    private String body;
}
