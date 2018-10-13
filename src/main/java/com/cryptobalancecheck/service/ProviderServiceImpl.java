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
import java.time.temporal.ChronoUnit;


@Service
public class ProviderServiceImpl implements ProviderService {

    private final String USER_AGENT = "Mozilla/5.0";
    private final ProviderRepository providerRepository;
    private final long minutesToLookBack = 5;

    private final String BINANCE_URL = "https://api.binance.com/api/v1/ticker/24hr";
    private final String POLONIEX_URL = "https://poloniex.com/public?command=returnTicker";
    private final String BTCTURK_URL = "https://www.btcturk.com/api/ticker";



    @Autowired
    public ProviderServiceImpl(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @Override
    public String getJsonFromProvider(ProviderEnum provider) {
        try {
            return checkDBandIfNecessaryGetFromProvider(provider);
        } catch (Exception e) {
            return provider.name() + " den bilgi alinamadi";
        }
    }

    private String findUrl(ProviderEnum provider) {

        switch (provider) {
            case BINANCE:
                return BINANCE_URL;
            case POLONIEX:
                return POLONIEX_URL;
            case BTCTURK:
                return BTCTURK_URL;
            default:
                return "";
        }
    }

    private String checkDBandIfNecessaryGetFromProvider(ProviderEnum provider) throws Exception {

        LocalDateTime minutesAgo = LocalDateTime.now().minus(Duration.of(minutesToLookBack, ChronoUnit.MINUTES));
        Provider providerFromDB = providerRepository.getProviderByProvider(provider);
        if (null != providerFromDB) {
            return checkDBWithinIntervalAndReturnJson(provider, minutesAgo, providerFromDB);
        } else {
            return getFromProviderAndSave(provider);
        }
    }

    private String getFromProviderAndSave(ProviderEnum provider) throws Exception {
        String jsonFromUrl = sendGet(findUrl(provider));
        Provider providerToDB = new Provider();
        providerToDB.setProvider(provider);
        providerToDB.setJson(jsonFromUrl);
        providerToDB.setDate(LocalDateTime.now());
        providerRepository.save(providerToDB);
        return jsonFromUrl;
    }

    private String checkDBWithinIntervalAndReturnJson(ProviderEnum provider, LocalDateTime minutesAgo, Provider providerFromDB) throws Exception {
        if (providerFromDB.getDate().isBefore(minutesAgo)) {
            String jsonFromUrl = sendGet(findUrl(provider));
            providerFromDB.setJson(jsonFromUrl);
            providerFromDB.setDate(LocalDateTime.now());
            providerRepository.save(providerFromDB);
            return jsonFromUrl;
        } else {
            return providerFromDB.getJson();
        }
    }


    private String sendGet(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
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
