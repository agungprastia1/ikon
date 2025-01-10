package com.demo.ikon.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetTestRequest {
    private Integer pageNumber;
    private Integer dataPerpage;
}
