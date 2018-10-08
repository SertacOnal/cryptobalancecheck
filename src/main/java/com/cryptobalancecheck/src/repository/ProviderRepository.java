package com.cryptobalancecheck.src.repository;

import com.cryptobalancecheck.src.domain.Provider;
import org.springframework.data.repository.CrudRepository;

public interface ProviderRepository extends CrudRepository<Provider, Long> {
}
