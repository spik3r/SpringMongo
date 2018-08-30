package com.kaitait.SpringMongo.Repository;


import com.kaitait.SpringMongo.Domain.Beverage;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface BeverageRepository extends MongoRepository<Beverage, String> {

    Beverage findByName(String name);
    List<Beverage> findByTemperature(String temperature);

}
