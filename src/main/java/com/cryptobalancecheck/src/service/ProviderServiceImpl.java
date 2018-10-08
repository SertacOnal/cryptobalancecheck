package com.cryptobalancecheck.src.service;


import com.cryptobalancecheck.src.model.ProviderEnum;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@Service
public class ProviderServiceImpl implements ProviderService {

    private final String USER_AGENT = "Mozilla/5.0";

    @Override
    public String getJsonFromProvider(ProviderEnum provider) {
        String url = "https://api.binance.com/api/v1/ticker/24hr";
        try {
            return sendGet(url);
        } catch (Exception e) {
            return url + " den bilgi alinamadi";
        }
    }

    // HTTP GET request
    private String sendGet(String url) throws Exception {


        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        return (response.toString());

    }
}
