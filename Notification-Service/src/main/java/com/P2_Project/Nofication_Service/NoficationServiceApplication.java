package com.P2_Project.Nofication_Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootApplication
@Slf4j
public class NoficationServiceApplication {

	@Autowired
	private JavaMailSender mailSender;

	public static void main(String[] args) {
		SpringApplication.run(NoficationServiceApplication.class, args);
	}

	@KafkaListener(topics = "notificationTopic")
	public void handleNotification(OrderPlaceEvent orderPlaceEvent) {
		System.out.println("received email");
		log.info("Received Notification for order - {}", orderPlaceEvent.getOrderId());
		sendEmailNotification(orderPlaceEvent, orderPlaceEvent.getUserEmail());
	}

	private void sendEmailNotification(OrderPlaceEvent orderPlaceEvent, String recipientEmail) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("revshop@demomailtrap.com");
		message.setTo(recipientEmail); // Set recipient's email dynamically
		message.setSubject("Order Notification");
		message.setText("Your order with number " + orderPlaceEvent.getOrderId() + " has been received.");

		try {
			mailSender.send(message);
			log.info("Email sent successfully to {}", recipientEmail);
		} catch (Exception e) {
			log.error("Failed to send email to {}: {}", recipientEmail, e.getMessage());
		}
	}

}
