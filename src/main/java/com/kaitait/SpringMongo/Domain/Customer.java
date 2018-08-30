package com.kaitait.SpringMongo.Domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Slf4j
@Document(collection = "Customer")
public class Customer {

    @Id
    public String id;

    public String firstName;
    public String lastName;
    public int age;
    public Beverage favouriteBeverage;

    public Customer() {}

    public Customer(String firstName, String lastName, int age, Beverage favouriteBeverage) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.favouriteBeverage = favouriteBeverage;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, firstName='%s', lastName='%s', age='%s', favouriteBeverage='%s']",
                id, firstName, lastName, age, favouriteBeverage);
    }

}