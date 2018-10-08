package com.cryptobalancecheck.src.controller;


import com.cryptobalancecheck.src.model.ProviderEnum;
import com.cryptobalancecheck.src.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider")
public class ProviderController {

    @Autowired
    ProviderService ps;

    @RequestMapping("/get")
    public String listAll() {
        return ps.getJsonFromProvider(ProviderEnum.BINANCE);
    }


}
