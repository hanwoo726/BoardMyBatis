package com.lec.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private String stnNm;   // 지역명
    private String tm;  // 날씨 날짜
    private String avgTa;   // 평균 온도
    private String minTa;   // 최저 온도
    private String maxTa;   // 최고 온도
}
