package com.kaitait.SpringMongo.Repository;

import java.util.List;

import com.kaitait.SpringMongo.Domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    Customer findByFirstName(String firstName);
    List<Customer> findByLastName(String lastName);

}