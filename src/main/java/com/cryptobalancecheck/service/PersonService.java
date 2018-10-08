package com.cryptobalancecheck.service;

import com.cryptobalancecheck.domain.Person;

import java.util.List;


public interface PersonService {

    List<Person> listAll();

    Person getById(Long id);

    Person saveOrUpdate(Person person);

    void delete(Long id);

}
