package com.kaitait.SpringMongo.Controller;

import com.kaitait.SpringMongo.Domain.Beverage;
import com.kaitait.SpringMongo.Domain.Customer;
import com.kaitait.SpringMongo.Repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Slf4j
@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository repository;
    private MongoTemplate mongoTemplate;

    @RequestMapping(value = "/addCustomer/{firstName}/{lastName}/{age}/{favouriteBeverage}", method = GET)
    @ResponseBody
    public String addCustomer(@PathVariable String firstName, @PathVariable String lastName, @PathVariable int age, @PathVariable String favouriteBeverage) {
        repository.save(new Customer(firstName, lastName, age, new Beverage("Whiskey", "Cold", Beverage.Type.ALCOHOLIC), new Date()));
        return "New Customer created: " + firstName + " " + lastName + " " + age + " " + favouriteBeverage;
    }

    @RequestMapping(path = "/bar", method = GET)
    public String bar() throws Exception {
        run();
        return "customers setup";
    }

    @RequestMapping(value = "/getAll", method = GET)
    public List<Customer> getAll() throws Exception {
        List customers = repository.findAll();
        log.info("customers: " + customers);
        return customers;
    }

    @RequestMapping(value = "/getFirstByFirstName", method = GET)
    public List<Customer> getFirstByFirstName(@RequestParam String firstName) throws Exception {
        List<Customer> customers = repository.findCustomerByFirstName(firstName);
        log.info("getFirstByFirstName: " + firstName);
        System.out.println("customers: " + customers);
        return customers;
    }

    @RequestMapping(value = "/getWhiskey", method = GET)
    public List<Customer> getWhiskey(@RequestParam String name) throws Exception {
        List<Customer> customers = repository.findCustomerByFavouriteBeverage_Name(name);
        log.info("getWhiskey: " + name);
        System.out.println("customers: " + customers);
        return customers;
    }
    @RequestMapping(value = "/getWhiskeyAge", method = GET)
    public List<Customer> getWhiskeyAge(@RequestParam String name, @RequestParam int age) throws Exception {
        List<Customer> customers = repository.findCustomerByFavouriteBeverage_NameAndAgeGreaterThan(name, age);
        log.info("getWhiskey: " + name);
        System.out.println("customers: " + customers);
        return customers;
    }
    @RequestMapping(value = "/setAge", method = GET)
    public List<Customer> findCustomerById(@RequestParam String id, @RequestParam int age) throws Exception {
        List<Customer> customers = repository.findCustomerById(id);
        Customer c = customers.get(0);
        c.age = age;
        repository.save(c);

        log.info("getWhiskey: " + id);
        System.out.println("customers before: " + customers);
        System.out.println("customers after: " + c);
        return customers;
    }

    @RequestMapping(value = "/findByLastName/{lastName}", method = GET)
    public List<Customer> findByLastName(String lastName) throws Exception {
        List<Customer> customers = repository.findByLastName(lastName);
        System.out.println("customers: " + customers);
        log.info("findByLastName: " + lastName);
        log.info("customers: " + customers);
        return customers;
    }

    public void run() throws Exception {

        repository.deleteAll();

        // save a couple of customers
        repository.save(new Customer("Alice", "Smith", 123, new Beverage("Coffee", "Hot", Beverage.Type.NON_ALCOHOLIC), new Date()));
        repository.save(new Customer("Bob", "Smith", 55, new Beverage("Beer", "Cold", Beverage.Type.ALCOHOLIC), new Date()));
        repository.save(new Customer("Tobi", "Ehrler", 21, new Beverage("Whiskey", "Cold", Beverage.Type.ALCOHOLIC), new Date()));

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
