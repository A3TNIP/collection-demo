package com.islington.summer.collectiondemo.service;

import com.islington.summer.collectiondemo.fixtures.PersonFixtures;
import com.islington.summer.collectiondemo.model.PersonDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class CollectionServiceTest {
    @InjectMocks
    private CollectionService collectionService;


    @Test
    public void testMapToDto() {
        var fixtures = new PersonFixtures();
        var rawList = fixtures.getRawList();
        var resultList = collectionService.mapToDto(rawList);

        assertEquals(fixtures.getRawList().size(), resultList.size());
        for (int i = 0; i < rawList.size(); i++) {
            assertEquals(rawList.get(i).getAge(), resultList.get(i).getAge());
            assertEquals(rawList.get(i).getName(), resultList.get(i).getName());
            assertEquals(rawList.get(i).getGender(), resultList.get(i).getGender());
            if (resultList.get(i).isAdult()) {
                assertTrue(rawList.get(i).getAge() >= 18);
            } else {
                assertTrue(rawList.get(i).getAge() < 18);
            }
        }
    }

    @Test
    public void testMapToObject() {
        var fixtures = new PersonFixtures();
        var rawList = fixtures.getRawDtoList();
        var resultList = collectionService.mapToPerson(rawList);

        assertEquals(fixtures.getRawList().size(), resultList.size());
        for (int i = 0; i < rawList.size(); i++) {
            assertEquals(rawList.get(i).getAge(), resultList.get(i).getAge());
            assertEquals(rawList.get(i).getName(), resultList.get(i).getName());
            assertEquals(rawList.get(i).getGender(), resultList.get(i).getGender());
            assertEquals(rawList.get(i).getContactNumber(), resultList.get(i).getContactNumber());
        }
    }

    @Test
    public void testFindContactNumber() {
        var fixtures = new PersonFixtures();
        var rawList = fixtures.getRawList();
        assertEquals(collectionService.findContactNumberByName(rawList, "John Doe"), "9999999999");
        assertEquals(collectionService.findContactNumberByName(rawList, "Rachel Blair"), "8888888888");
        assertEquals(collectionService.findContactNumberByName(rawList, "Jane Doe"), "7777777777");
        assertEquals(collectionService.findContactNumberByName(rawList, "William Smith"), "6666666666");
        assertEquals(collectionService.findContactNumberByName(rawList, "Anna Klein"), "5555555555");
    }

    @Test
    public void testFindAllPairs() {
        String[] arr = new String[]{"1a","1b","1c","2a","2b","2c","3a","3b","3c"};
        List<String> result = collectionService.findAllPairs(List.of(1,2,3), List.of("a","b","c"));
        assertEquals(result.size(), arr.length);
        for (String s : arr) {
            assertTrue(result.contains(s));
        }
    }

    @Test
    public void testCreatePhoneBook() {
        var fixtures = new PersonFixtures();
        var rawList = fixtures.getRawList();
        Map<String, String> result = collectionService.createPhoneBook(rawList);
        assertEquals(rawList.size(), result.size());
        result.keySet().forEach(key -> {
            var person = rawList.stream()
                    .filter(x -> x.getName().equals(key))
                    .findFirst().orElseThrow();

            assertEquals(result.get(key), person.getContactNumber());
        });
    }

    @Test
    public void testGroupingBy() {
        var fixtures = new PersonFixtures();
        var rawList = fixtures.getRawList();
        Map<String, List<PersonDto>> result = collectionService.groupPersonListByGender(rawList);
        assertEquals(result.size(), 2);
        assertEquals(result.get("Female").size(), 3);
        assertEquals(result.get("Male").size(), 2);
    }
}
