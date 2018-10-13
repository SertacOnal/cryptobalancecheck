package com.cryptobalancecheck.controller;


import com.cryptobalancecheck.model.ProviderEnum;
import com.cryptobalancecheck.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.EnumSet;

@RestController
@RequestMapping("/provider")
public class ProviderController {

    @Autowired
    ProviderService ps;

    @RequestMapping("/get")
    public String listAll() {
        return ps.getJsonFromProvider(ProviderEnum.BINANCE);
    }

    @RequestMapping("/getForAll")
    public String getForAll() {
        EnumSet.allOf(ProviderEnum.class)
                .forEach(providerEnum -> ps.getJsonFromProvider(providerEnum));
        return "Hepsi Guncellendi";
    }


}
