package com.shopping.portal;

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

	@Autowired
	private EurekaClient eurekaClient;
	
	@Autowired
	private RestTemplateBuilder builder;
	
	Logger log = LoggerFactory.getLogger(MainController.class);
	
	@GetMapping("order/{orderId}")
	public String getOrder(@PathVariable int orderId) {
		log.info("From Shopping Portal");
		String response = builder.build().getForObject(eurekaClient.getNextServerFromEureka("Order Management Service", false).getHomePageUrl()+"/order/"+orderId, String.class);
		log.info("Received Response from Order Portal "+response);
		return response;
	}
	
	@GetMapping("payment/{amount}")
	public String getPayment(@PathVariable int amount) {
		log.info("From Shopping Portal");
		String response = builder.build().getForObject(eurekaClient.getNextServerFromEureka("Payment Service", false).getHomePageUrl()+"/payment/"+amount, String.class);
		log.info("Received Response from Payment Service");
		return response;
	}
}
