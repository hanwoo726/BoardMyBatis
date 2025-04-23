package com.lec.spring.service;

import com.lec.spring.dto.Item;
import com.lec.spring.dto.WeatherApi;
import com.lec.spring.external.ApiClient;
import com.lec.spring.repository.WeatherRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService{

    private final RestTemplate restTemplate;

    @Autowired
    public WeatherServiceImpl(SqlSession sqlSession, RestTemplate restTemplate) {

        this.restTemplate = restTemplate;
    }

    public List<Item> fetchWeatherItems(){
        String url = "http://apis.data.go.kr/1360000/AsosDalyInfoService/getWthrDataList?serviceKey=CXNQtTlcD3FTqSRh9%2FTcpft83eV93QY3vwOOOAD4H3RaW5JcCIK6bhNoy147SmIprhiTN3DP3rLNifcOOm0Z1A%3D%3D&pageNo=1&numOfRows=10&dataType=JSON&dataCd=ASOS&dateCd=DAY&startDt=20250413&endDt=20250414&stnIds=108";

        ResponseEntity<WeatherApi> response =  restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        // 예외
        WeatherApi weatherApi = response.getBody();
        if(weatherApi != null &&
        weatherApi.getBody() != null &&
        weatherApi.getBody().getItems() != null){

            return weatherApi.getBody().getItems().getItem();
        }

        return List.of();

    }






}
