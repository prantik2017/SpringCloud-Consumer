package com.prantik.microservice.SpringCloudConsumer;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClientException;

import com.prantik.microservice.SpringCloudConsumer.controller.EmployeeControllerClient;

@SpringBootApplication
public class SpringCloudConsumerApplication {

	public static void main(String[] args) throws RestClientException, IOException {
		ApplicationContext ctx = SpringApplication.run(SpringCloudConsumerApplication.class, args);
		EmployeeControllerClient employeeControllerClient = ctx.getBean(EmployeeControllerClient.class);
		System.out.println(employeeControllerClient);
		employeeControllerClient.getDefaultEmployee();
	}
	
	@Bean
	public EmployeeControllerClient employeeControllerClient() {
		return new EmployeeControllerClient();
	}

}
