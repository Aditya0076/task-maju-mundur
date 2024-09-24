package com.abpgroup.majumundur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(scanBasePackages = "com.abpgroup.majumundur")
@Configuration
public class MajuMundurApplication {

	public static void main(String[] args) {
		SpringApplication.run(MajuMundurApplication.class, args);
	}

}
