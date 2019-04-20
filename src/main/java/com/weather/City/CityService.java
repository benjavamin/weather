package com.weather.City;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.weather.api.ApiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@Component
public class CityService {

    @Autowired
    private ApiConfig apiConfig;


    public String searchByCityName(String cityName) throws IOException {

        URL urlKey = new URL("http://dataservice.accuweather.com/locations/v1/cities/search?apikey=" + apiConfig.getApikey() + "&q=" + cityName);
        URLConnection con = urlKey.openConnection();
        con.connect();


        JsonParser jsonParser = new JsonParser();
        JsonElement root = jsonParser.parse(new InputStreamReader((InputStream) con.getContent()));
        JsonArray rootArray = root.getAsJsonArray();
        String locationKey = rootArray.get(0).getAsJsonObject().get("Key").getAsString();

        URL urlTemp = new URL("http://dataservice.accuweather.com/currentconditions/v1/");
        con = urlTemp.openConnection();
        con.connect();

        return locationKey;
    }
}
