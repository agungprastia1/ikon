package com.demo.ikon.service;

import com.demo.ikon.dtos.ResponseApi;
import com.demo.ikon.dtos.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service

public class TestService {
    @Autowired
    private RestTemplate restTemplate;
    private final String url = "https://jsonplaceholder.typicode.com/posts";
    public ResponseApi getTest() {
        Pageable pageable;
        try{
            List<ResponseDto> responseDtos = getFromJson();
            System.out.println(responseDtos);
            List<ResponseDto> listResponse = new ArrayList<>();
            if (responseDtos!=null&&!responseDtos.isEmpty()){
                for (ResponseDto responseDto :responseDtos){
                    ResponseDto responseDto1 = new ResponseDto();
                    responseDto1.setId(responseDto.getId());
                    responseDto1.setTitle(responseDto.getTitle());
                    listResponse.add(responseDto);
                }
            }
            return ResponseApi.builder()
                    .data(listResponse)
                    .desc(HttpStatus.OK.getReasonPhrase())
                    .statusCode(HttpStatus.OK.value())
                    .build();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    private List<ResponseDto> getFromJson(){
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(url);
            String getFrom = sb.toString();
            return restTemplate.getForObject(getFrom,List.class);
        }catch (Exception e){
         e.printStackTrace();
         return null;
        }
    }
}
