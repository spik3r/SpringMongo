package com.kaitait.SpringMongo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SpringMongoApplication {


	public static void main(String[] args) {
		SpringApplication.run(SpringMongoApplication.class, args);
        log.info("testing 123");
	}
}
