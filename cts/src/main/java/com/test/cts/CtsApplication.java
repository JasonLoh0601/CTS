package com.test.cts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.math.BigDecimal;

@EnableScheduling
@SpringBootApplication
public class CtsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CtsApplication.class, args);
	}

}
