package com.islington.summer.collectiondemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    private String contactNumber;
    private String gender;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", contactNumber='" + contactNumber + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public Person(PersonDto personDto) {
        this.name = personDto.getName();
        this.age = personDto.getAge();
        this.contactNumber = personDto.getContactNumber();
        this.gender = personDto.getGender();
        this.id = personDto.getId();
    }
}
