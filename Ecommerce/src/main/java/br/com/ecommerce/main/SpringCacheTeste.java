package br.com.ecommerce.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("br.com.ecommerce")
public class SpringCacheTeste {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringCacheTeste.class, args);
		
		
		
	}
}
