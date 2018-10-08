package com.cryptobalancecheck.domain;

import com.cryptobalancecheck.model.ProviderEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import java.util.Date;

@Entity
public class Provider extends CryptoBaseEntity {
    @Column(unique = true)
    @Enumerated
    ProviderEnum provider;
    Date date;
    String json;


    public ProviderEnum getProvider() {
        return provider;
    }

    public void setProvider(ProviderEnum provider) {
        this.provider = provider;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
