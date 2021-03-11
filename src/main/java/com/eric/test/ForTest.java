package com.eric.test;

import com.eric.entity.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuBing
 */
public class ForTest {
    public static void main(String[] args) {
        Person person = new Person();

        List<Person> people = new ArrayList<>();
        for (int i = 0 ; i < 10 ; i ++){

            person.setName(String.valueOf(i));
            person.setAge(i);

            people.add(person);
        }

        System.out.println(people);
    }
}
