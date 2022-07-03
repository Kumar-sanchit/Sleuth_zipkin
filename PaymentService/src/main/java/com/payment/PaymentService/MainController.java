package com.payment.PaymentService;

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
	private RestTemplateBuilder restTemplateBuilder;
	
	@Autowired
	private EurekaClient eurekaClient;
	
	@GetMapping("/payment/{amount}")
	public String getPaymentFromClient(@PathVariable int amount) {
		log.info("From Payment Service! with Amount :"+amount);
		String response = restTemplateBuilder.build().getForObject(eurekaClient.getNextServerFromEureka("Notification Service", false).getHomePageUrl()+"/notification/PaymentService", String.class);
		log.info("Received response from Notification service");
		return response;
	}
}
