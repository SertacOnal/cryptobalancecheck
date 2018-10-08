package com.cryptobalancecheck.src.repository;

import com.cryptobalancecheck.src.domain.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
