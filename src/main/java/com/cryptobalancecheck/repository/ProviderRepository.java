package com.cryptobalancecheck.repository;

import com.cryptobalancecheck.domain.Provider;
import com.cryptobalancecheck.model.ProviderEnum;
import org.springframework.data.repository.CrudRepository;


public interface ProviderRepository extends CrudRepository<Provider, Long> {
    Provider getProviderByProvider(ProviderEnum provider);
}
