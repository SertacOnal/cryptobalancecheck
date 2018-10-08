package com.cryptobalancecheck.service;


import com.cryptobalancecheck.domain.Provider;
import com.cryptobalancecheck.model.ProviderEnum;
import com.cryptobalancecheck.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;


@Service
public class ProviderServiceImpl implements ProviderService {

    private final String USER_AGENT = "Mozilla/5.0";
    private final ProviderRepository providerRepository;
    private final long minutesToLookBack = 5;


    @Autowired
    public ProviderServiceImpl(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @Override
    public String getJsonFromProvider(ProviderEnum provider) {
        String url = "https://api.binance.com/api/v1/ticker/24hr";
        try {
            return checkDBandIfNecessaryGetFromProvider(provider.BINANCE);
        } catch (Exception e) {
            return url + " den bilgi alinamadi";
        }
    }

    private String checkDBandIfNecessaryGetFromProvider(ProviderEnum provider) throws Exception {

        LocalDateTime dateTime = LocalDateTime.now().minus(Duration.of(minutesToLookBack, ChronoUnit.MINUTES));
        Date minutesAgo = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
        Provider providerFromDB = providerRepository.getProviderByProviderAndCreateDateAfter(provider, minutesAgo);

        if (null != providerFromDB) {
            return providerFromDB.getJson();
        } else {
            String jsonFromUrl = sendGet("https://api.binance.com/api/v1/ticker/24hr");
            Provider providerToDB = new Provider();
            providerToDB.setProvider(provider);
            providerToDB.setJson(jsonFromUrl);
            providerRepository.save(providerToDB);
            return jsonFromUrl;
        }
//        return sendGet("https://api.binance.com/api/v1/ticker/24hr");


    }


    private String sendGet(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
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
        return (response.toString());
    }
}
