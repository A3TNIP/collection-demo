package com.islington.summer.collectiondemo.controller;

import com.islington.summer.collectiondemo.model.Person;
import com.islington.summer.collectiondemo.model.PersonDto;
import com.islington.summer.collectiondemo.repository.CustomPersonRepository;
import com.islington.summer.collectiondemo.repository.PersonRepository;
import com.islington.summer.collectiondemo.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collection")
public class CollectionController {
    private final CollectionService collectionService;
    private final PersonRepository personRepository;
    private final CustomPersonRepository customPersonRepository;

    @Autowired
    public CollectionController(CollectionService collectionService, PersonRepository personRepository, CustomPersonRepository customPersonRepository) {
        this.collectionService = collectionService;
        this.personRepository = personRepository;
        this.customPersonRepository = customPersonRepository;
    }

    @GetMapping("findAll")
    public List<PersonDto> findAll() {
        return this.collectionService.mapToDto(this.personRepository.findAll());
    }

    @GetMapping("/lastName/{lastname}")
    public List<Person> getByLastName(@PathVariable String lastname) {
        return this.customPersonRepository.getByLastName(lastname);
    }


    @PostMapping("save")
    public Person save(@RequestBody PersonDto personDto) {
        Person person = new Person(personDto);
        return this.personRepository.save(person);
    }
}
