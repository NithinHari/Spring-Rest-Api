package com.dxc.cba.employeedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication :It is a combination of three annotations
 * @EnableAutoConfiguration, @ComponentScan, and @Configuration.
 *
 * The @EnableAutoConfiguration annotation enables Spring Boot to auto-configure the application context.
 * Therefore, it automatically creates and registers beans based on both the included jar files in the
 * classpath and the beans defined by us.
 *
 * @Configuration annotation which indicates that the class has @Bean definition methods.
 *
 * @ComponentScan is searching packages for Components.
 * @ComponentScan which is used along with the @Configuration annotation to specify the packages that we want to be scanned.
 * @ComponentScan without arguments tells Spring to scan the current package and all of its sub-packages.
 *
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
