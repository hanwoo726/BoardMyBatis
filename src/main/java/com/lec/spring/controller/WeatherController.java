package com.lec.spring.controller;

import com.lec.spring.dto.Item;
import com.lec.spring.service.WeatherService;
import com.lec.spring.service.WeatherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api")
public class WeatherController {

    @Autowired
    private WeatherServiceImpl weatherService;

    @RequestMapping("/weather")
    public String fetchWeather(Model model){
            List<Item> items = weatherService.fetchWeatherItems();
            model.addAttribute("items", items);
        return "/api/weather";
    }
}
