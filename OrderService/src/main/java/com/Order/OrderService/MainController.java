package com.Order.OrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.EurekaClient;

@RestController
public class MainController {

	private Logger log = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private EurekaClient eurekaClient;
	
	@Autowired
	private RestTemplateBuilder restTemplateBuilder;
	
	
	@GetMapping("/order/{orderId}")
	public String order(@PathVariable int orderId) {
		String response = "";
		log.info("From Order Microservices With Order Id:"+orderId);
		response = restTemplateBuilder.build().getForObject(eurekaClient.getNextServerFromEureka("Notification Service", false).getHomePageUrl()+"/notification/OrderManagement", String.class);
		log.info("Received Response from Notification service");
		return response;
	}
}
