package com.kaitait.SpringMongo.Controller;

import com.kaitait.SpringMongo.Domain.Beverage;
import com.kaitait.SpringMongo.Domain.Customer;
import com.kaitait.SpringMongo.Repository.BeverageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class BeverageController {

    @Autowired
    private BeverageRepository repository;

    @RequestMapping(path = "/setup", method = GET)
    public String bar() throws Exception {
        run();
        return "bar bar bar";
    }
    @RequestMapping(value = "/getBeverages", method = GET)
    public List<Customer> getAll() throws Exception {
        List customers = repository.findAll();
        System.out.println("customers: " + customers);
        return customers;
    }

    public void run() throws Exception {

        repository.deleteAll();

        // save a couple of customers
        repository.save(new Beverage("Beer", "Cold", Beverage.Type.ALCOHOLIC));
        repository.save(new Beverage("Water", "Cold", Beverage.Type.NON_ALCOHOLIC));
        repository.save(new Beverage("Coffee", "Hot", Beverage.Type.NON_ALCOHOLIC));

        // fetch all customers
        System.out.println("Beverages found with findAll():");
        System.out.println("-------------------------------");
        for (Beverage beverage : repository.findAll()) {
            System.out.println(beverage);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Beverages found with findByName('Beer'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByName("Beer"));

        System.out.println("Beverages found with findByType('Cold'):");
        System.out.println("--------------------------------");
        for (Beverage beverage : repository.findByTemperature("Cold")) {
            System.out.println(beverage);
        }

    }
}
