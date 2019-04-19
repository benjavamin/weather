package com.weather.controller;

import com.weather.City.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class Controller {

    @Autowired
    private CityService cityService;


    @GetMapping("/key")
    public String key() throws IOException {
        return cityService.searchByCityName("Sarajevo");
    }

}
