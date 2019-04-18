package com.weather.controller;

import com.weather.City.CityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class Controller {


    @GetMapping("/key")
    public String key() throws IOException {
        CityService cService = new CityService();
        return cService.searchByCityName("Sarajevo");
    }

}
