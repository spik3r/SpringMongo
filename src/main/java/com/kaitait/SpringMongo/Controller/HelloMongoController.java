package com.kaitait.SpringMongo.Controller;

import java.util.List;

import com.kaitait.SpringMongo.Domain.Customer;
import com.kaitait.SpringMongo.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class HelloMongoController {

    @Autowired
    private CustomerRepository repository;

    @RequestMapping(value = "/addCustomer/{firstName}/{lastName}", method = GET)
    @ResponseBody
    public String addCustomer(@PathVariable String firstName, @PathVariable String lastName) {
        repository.save(new Customer(firstName, lastName));
        return "New Customer created: " + firstName + " " + lastName;
    }

    @RequestMapping(path = "/bar", method = GET)
    public String bar() throws Exception {
        run();
        return "bar bar bar";
    }
    @RequestMapping(value = "/getAll", method = GET)
    public List<Customer> getAll() throws Exception {
        List customers = repository.findAll();
        System.out.println("customers: " + customers);
        return customers;
    }

    public void run() throws Exception {

        repository.deleteAll();

        // save a couple of customers
        repository.save(new Customer("Alice", "Smith"));
        repository.save(new Customer("Bob", "Smith"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : repository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByFirstName("Alice"));

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (Customer customer : repository.findByLastName("Smith")) {
            System.out.println(customer);
        }

    }
}
