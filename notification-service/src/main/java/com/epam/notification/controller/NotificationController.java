package com.epam.notification.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/notifications")
public interface NotificationController {
	@GetMapping("/email")
	@KafkaListener(topicPattern = "${kafka.topics.project-status-changed}", autoStartup = "${kafka.enabled}", groupId = "group-id")
	public void sendProjectStatusEmail(ConsumerRecord<String, String> record);

}
