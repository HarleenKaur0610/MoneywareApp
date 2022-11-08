package com.moneyware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MoneywareAppBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoneywareAppBackendApplication.class, args);
	}

}
