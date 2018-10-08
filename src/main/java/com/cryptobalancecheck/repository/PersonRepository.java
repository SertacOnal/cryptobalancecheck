package com.cryptobalancecheck.repository;

import com.cryptobalancecheck.domain.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
