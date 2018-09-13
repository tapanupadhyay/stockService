package com.learning.stock.stockService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//@EnableEurekaClient
@ComponentScan("com.learning.stock")
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class  StockServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockServiceApplication.class, args);
	}
}
