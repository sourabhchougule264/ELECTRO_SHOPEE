package com.cg.hims;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages= {"com.cg.hims"})
public class ElectroShopsy {

	public static void main(String[] args) {
		SpringApplication.run(ElectroShopsy.class, args);
		System.out.println("Online Shopping is Started.....!!!!");
	}
}
