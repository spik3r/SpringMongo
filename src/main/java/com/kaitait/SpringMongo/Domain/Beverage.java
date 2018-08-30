package com.kaitait.SpringMongo.Domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Beverage")
public class Beverage {

    @Id
    public String id;

    public String name;
    public String temperature;
    public Type type;

    public Beverage() {}

    public Beverage(String name, String temperature, Type type) {
        this.name = name;
        this.temperature = temperature;
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format(
                "Beverage[id=%s, name='%s', temperature='%s', alcoholic='%s']",
                id, name, temperature, type);
    }


    public enum Type {
        ALCOHOLIC,
        NON_ALCOHOLIC
    };
}