package com.islington.summer.collectiondemo.service;

import com.islington.summer.collectiondemo.model.Person;
import com.islington.summer.collectiondemo.model.PersonDto;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class CollectionService {

    /**
     * Display all the persons in the list
     * @param personList List of {@link Person} class
     */
    public void displayPersonList(List<Person> personList) {

    }

    /**
     * Should return all possible pairs
     * @param numberList Example: [1,2,3]
     * @param stringList Example: [a,b,c]
     * @return Expected Output: [1a,1b,1c,2a,2b,2c,3a,3b,3c]
     */
    public List<String> findAllPairs(List<Integer> numberList, List<String> stringList) {
        return null;
    }

    /**
     * Create a List of person DTO with the same number of persons given as input
     * @param persons List of {@link Person} class
     * @return List of {@link PersonDto} class of the same size as input
     */
    public List<PersonDto> mapToDto(List<Person> persons){
        return Collections.emptyList();
    }

    /**
     * Create a List of person object with the same number of person dto's given as input
     * @param personDtoList List of {@link PersonDto} class
     * @return List of {@link Person} class of the same size as input
     */
    public List<Person> mapToPerson(List<PersonDto> personDtoList){
        return Collections.emptyList();
    }

    /**
     * Find the contact number of a person
     * @param personList List of the persons
     * @param name
     * @return
     */
    public String findContactNumberByName(List<Person> personList, String name){
        return null;
    }

    /**
     * Create a phonebook where the key is the name of a person and its value is the contact number
     * @param persons List of {@link Person} class
     * @return {@link Map}
     */
    public Map<String, String> createPhoneBook(List<Person> persons){
        return Collections.emptyMap();
    }

    /**
     * Group the person list by their genders
     * @param persons
     * @return
     */
    public Map<String, List<PersonDto>> groupPersonListByGender(List<Person> persons){
        return Collections.emptyMap();
    }



}
