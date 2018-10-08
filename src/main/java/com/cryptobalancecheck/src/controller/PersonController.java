package com.cryptobalancecheck.src.controller;


import com.cryptobalancecheck.src.domain.Person;
import com.cryptobalancecheck.src.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    PersonService ps;

    @RequestMapping("/all")
    public List<Person> listAll() {
        return ps.listAll();
    }

    @RequestMapping("{id}")
    public Person getPerson(@PathVariable("id") Long id) {
        return ps.getById(id);
    }

    @RequestMapping("/delete/{id}")
    public void deletePerson(@PathVariable("id") Long id) {
        ps.delete(id);
    }

    @RequestMapping("/add/{first}/{last}/{age}")
    public void addPerson(@PathVariable("first") String firstName, @PathVariable("last") String lastName, @PathVariable("age") int age) {
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setAge(age);
        ps.saveOrUpdate(person);
    }


}
