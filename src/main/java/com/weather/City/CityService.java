package com.weather.City;

import com.weather.ParameterBuilder.ParamBuilder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
@Data
public class CityService {

    @Value("{api.key}")
    public String apiKey;


    public String searchByCityName(String cityName) throws IOException {

        URL url = new URL("http://dataservice.accuweather.com/locations/v1/cities/search");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        Map<String, String> parameters = new HashMap<>();

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

        return response.toString();

    }
}
