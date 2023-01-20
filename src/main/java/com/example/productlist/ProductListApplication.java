package com.example.productlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ProductListApplication {

  public static void main(String[] args) {
    SpringApplication.run(ProductListApplication.class, args);
  }
}
