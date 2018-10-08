package com.cryptobalancecheck.src.service;

import com.cryptobalancecheck.src.domain.Person;

import java.util.List;


public interface PersonService {

    List<Person> listAll();

    Person getById(Long id);

    Person saveOrUpdate(Person person);

    void delete(Long id);

}
