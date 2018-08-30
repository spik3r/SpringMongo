package com.kaitait.SpringMongo.Domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.Date;

@Slf4j
@Document(collection = "Customer")
public class Customer {

    @Id
    public String id;
    public String firstName;
    public String lastName;
    public int age;
    public Beverage favouriteBeverage;

    @Field
    @Indexed(name="someDateFieldIndex", expireAfterSeconds=20)
    Date someDateField;

    public Customer() {}

    public Customer(String firstName, String lastName, int age, Beverage favouriteBeverage, Date someDateField) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.favouriteBeverage = favouriteBeverage;
        this.someDateField = someDateField;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, firstName='%s', lastName='%s', age='%s', favouriteBeverage='%s', someDateField='%s']",
                id, firstName, lastName, age, favouriteBeverage, someDateField);
    }

}