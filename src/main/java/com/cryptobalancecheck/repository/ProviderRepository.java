package com.cryptobalancecheck.repository;

import com.cryptobalancecheck.domain.Provider;
import com.cryptobalancecheck.model.ProviderEnum;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface ProviderRepository extends CrudRepository<Provider, Long> {

    Provider getProviderByProviderAndCreateDateAfter(ProviderEnum provider, Date createDate);


}
