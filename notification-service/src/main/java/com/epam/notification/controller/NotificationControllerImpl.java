package com.epam.notification.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.epam.notification.configuration.MailConfiguration;
import com.epam.notification.dto.EmailContentDto;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class NotificationControllerImpl implements NotificationController {

	@Autowired
	private MailConfiguration mailConfiguration;

	@Override
	public void sendProjectStatusEmail(ConsumerRecord<String, String> record) {
		log.info("Email Notofocation: " + record.toString());
		try {
			mailConfiguration.sendmail("mahir@epam.com",
					new EmailContentDto("Hotel management service", record.value(), "text/html"));
			mailConfiguration.sendmail("labib@epam.com",
					new EmailContentDto("Hotel management service", record.value(), "text/html"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}