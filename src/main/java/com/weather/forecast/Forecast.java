package com.weather.forecast;

import com.weather.Temperature.Temperature;
import lombok.Data;

@Data
public class Forecast {

    public String weatherText;
    public boolean isDayTime;
    public Temperature temperature;


}
