package com.cryptobalancecheck.domain;

import com.cryptobalancecheck.model.ProviderEnum;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;


@Entity
public class Provider extends CryptoBaseEntity {

    @Column(unique = true)
    @Enumerated
    ProviderEnum provider;
    @Type(type = "text")
    String json;


    public ProviderEnum getProvider() {
        return provider;
    }

    public void setProvider(ProviderEnum provider) {
        this.provider = provider;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
