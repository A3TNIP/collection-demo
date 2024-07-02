package com.islington.summer.collectiondemo.fixtures;

import com.islington.summer.collectiondemo.model.Person;
import com.islington.summer.collectiondemo.model.PersonDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PersonFixtures {
    private List<Person> rawList;
    private List<PersonDto> rawDtoList;

    public PersonFixtures() {
        rawList = new ArrayList<>(){{
            add(new Person("John Doe", 30, "9999999999", "Male"));
            add(new Person("Rachel Blair", 26, "8888888888", "Female"));
            add(new Person("Jane Doe", 21, "7777777777", "Female"));
            add(new Person("William Smith", 17, "6666666666", "Male"));
            add(new Person("Anna Klein", 16, "5555555555", "Female"));
        }};

        rawDtoList = new ArrayList<>(){{
                add(new PersonDto("John Doe", 30, true, "9999999999", "Male"));
                add(new PersonDto("Rachel Blair", 26, true, "8888888888", "Female"));
                add(new PersonDto("Jane Doe", 21, true, "7777777777", "Male"));
                add(new PersonDto("William Smith", 17, false, "6666666666", "Male"));
                add(new PersonDto("Anna Klein", 16, false, "5555555555", "Female"));
            }};
    }
}
