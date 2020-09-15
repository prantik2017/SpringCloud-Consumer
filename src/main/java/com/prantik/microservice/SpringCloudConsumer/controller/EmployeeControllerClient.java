package com.prantik.microservice.SpringCloudConsumer.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class EmployeeControllerClient {
	
	/*
	 * for consumer service default
	 * 
	 */
//	public void getDefaultEmployee() throws RestClientException, IOException {
//		String baseUrl= "http://localhost:7070/springCloudProducer/defaultEmployee";
//		RestTemplate restTemplate = new RestTemplate();
//		ResponseEntity<String> response = null;
//		try {
//			response = restTemplate.exchange(baseUrl, HttpMethod.GET,getHeaders(),String.class);
//		} catch(Exception ex) {
//			System.out.println(ex.getMessage());
//		}
//		System.out.println("Response from Client \n"+response.getBody());
//	}
	
	/*
	 * for consumer servcie with discovery clients
	 * 
	 */	
//	@Autowired
//	private DiscoveryClient discoveryClient;
//	
//	public void getDefaultEmployee() throws RestClientException, IOException {
//		List<ServiceInstance> instances = discoveryClient.getInstances("SpringCloud-Producer");
//		ServiceInstance serviceInstance = instances.get(0);
//		//String baseUrl= "http://localhost:7070/springCloudProducer/defaultEmployee";
//		String baseUrl = serviceInstance.getUri().toString();
//		baseUrl += "/springCloudProducer/defaultEmployee";
//		System.out.println("base Url "+baseUrl);
//		RestTemplate restTemplate = new RestTemplate();
//		ResponseEntity<String> response = null;
//		try {
//			response = restTemplate.exchange(baseUrl, HttpMethod.GET,getHeaders(),String.class);
//		} catch(Exception ex) {
//			System.out.println(ex.getMessage());
//		}
//		System.out.println("Response from Client \n"+response.getBody());
//	}
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	public void getDefaultEmployee() throws RestClientException, IOException {
		ServiceInstance serviceInstance = loadBalancerClient.choose("SpringCloud-Producer");
		String baseUrl = serviceInstance.getUri().toString();
		baseUrl += "/springCloudProducer/defaultEmployee";
		System.out.println("base Url "+baseUrl);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(baseUrl, HttpMethod.GET,getHeaders(),String.class);
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		System.out.println("Response from Client \n"+response.getBody());
	}
	
	private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}

}
