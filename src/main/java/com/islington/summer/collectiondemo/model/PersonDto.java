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
    private String name;
    private int age;
    private boolean isAdult;
    private String contactNumber;
    private String gender;
}
