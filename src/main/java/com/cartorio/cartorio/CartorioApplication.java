package com.cartorio.cartorio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CartorioApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartorioApplication.class, args);
	}

}
