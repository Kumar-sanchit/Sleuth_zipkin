package com.notification.notification.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	private Logger log = LoggerFactory.getLogger(MainController.class);

	@GetMapping("/notification/{service}")
	public String getNotification(@PathVariable String service) {
		log.info("From Notification Service! "+service);
		return "Notified Service :"+service;
	}
}
