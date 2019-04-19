package com.weather.City;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.weather.api.ApiConfig;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@Component
public class CityService {

    /*@Value("${api.key}")
    private String apiKey;*/

    @Autowired
    private ApiConfig apiConfig;



    public  String searchByCityName(String cityName) throws IOException {

        URL url = new URL("http://dataservice.accuweather.com/locations/v1/cities/search?apikey=" + apiConfig.getApikey() + "&q=" + cityName);
        URLConnection con = url.openConnection();
        con.connect();


        JsonParser jsonParser = new JsonParser();
        JsonElement root = jsonParser.parse(new InputStreamReader((InputStream) con.getContent()));
        //JsonObject rootObj = root.getAsJsonObject();
        JsonArray rootArray = root.getAsJsonArray();
        String locationKey = rootArray.get(0).getAsJsonObject().get("Key").getAsString();

        /*Map<String, String> parameters = new HashMap<>();

        parameters.put("apikey", this.getApiKey());
        parameters.put("q", "Sarajevo");

        con.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(ParamBuilder.getParamsString(parameters));
        out.flush();
        out.close();
        con.setRequestProperty("Content-Type", "application/json");

        int responseCode = con.getResponseCode();
        System.out.println("Sending GET request to URL: " + url);
        System.out.println("Server responded with: " + responseCode);





        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = bufferedReader.readLine()) != null) {
            response.append(inputLine);
        }

        bufferedReader.close();
        con.disconnect();
        System.out.println(response);

        return response.toString();*/

        return locationKey;
    }
}
