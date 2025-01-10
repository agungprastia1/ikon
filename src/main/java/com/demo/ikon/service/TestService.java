package com.demo.ikon.service;

import com.demo.ikon.dtos.GetTestRequest;
import com.demo.ikon.dtos.ResponseApi;
import com.demo.ikon.dtos.ResponseDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Log4j2
public class TestService {
    @Autowired
    private RestTemplate restTemplate;
    private final String url = "https://jsonplaceholder.typicode.com/posts";
    public ResponseApi getTest(GetTestRequest request) {
        try {
            List<ResponseDto> responseDtos = getFromJson();
            if (responseDtos == null || responseDtos.isEmpty()) {
                return ResponseApi.builder()
                        .data(Collections.emptyList())
                        .desc("No data found")
                        .statusCode(HttpStatus.OK.value())
                        .build();
            }

            int pageNumber = request.getPageNumber() != null ? request.getPageNumber() : 0;
            int dataPerPage = request.getDataPerpage() != null ? request.getDataPerpage() : 10;

            int start = pageNumber * dataPerPage;
            int end = Math.min(start + dataPerPage, responseDtos.size());

            List<ResponseDto> paginatedList;
            if (start >= responseDtos.size()) {
                paginatedList = Collections.emptyList();
            } else {
                paginatedList = responseDtos.subList(start, end);
            }

            int totalElements = responseDtos.size();
            int totalPages = (int) Math.ceil((double) totalElements / dataPerPage);

            return ResponseApi.builder()
                    .data(paginatedList)
                    .desc(HttpStatus.OK.getReasonPhrase())
                    .statusCode(HttpStatus.OK.value())
                    .totalElements(totalElements)
                    .totalPages(totalPages)
                    .numberOfElements(paginatedList.size())
                    .build();

        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseApi.builder()
                    .data(Collections.emptyList())
                    .desc(e.getMessage())
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .build();
        }
    }


    private List<ResponseDto> getFromJson(){
        try {
            ResponseEntity<List<ResponseDto>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<ResponseDto>>() {}
            );
            return response.getBody();
        }catch (Exception e){
            log.info("Error: {}", e.getMessage());
         return null;
        }
    }
}
