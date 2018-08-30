package com.kaitait.SpringMongo.Repository;

import java.util.List;

import com.kaitait.SpringMongo.Domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    Customer findByFirstName(String firstName);
    List<Customer> findByLastName(String lastName);
    List<Customer> findCustomerByFirstName(String firstName);
    List<Customer> findCustomerByFavouriteBeverage_Name(String name);
    List<Customer> findCustomerByFavouriteBeverage_NameAndAgeGreaterThan(String name, int age);
    List<Customer> findCustomerById(String id);


}