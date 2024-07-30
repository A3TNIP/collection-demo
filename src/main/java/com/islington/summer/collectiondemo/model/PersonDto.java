package com.islington.summer.collectiondemo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonDto {
    private Long id;
    private String name;
    private int age;
    private boolean isAdult;
    private String contactNumber;
    private String gender;

    public PersonDto(Person person) {
        this.name = person.getName();
        this.age = person.getAge();
        this.contactNumber = person.getContactNumber();
        this.gender = person.getGender();
        this.isAdult = person.getAge() >= 18;
        this.id = person.getId();
    }
}
