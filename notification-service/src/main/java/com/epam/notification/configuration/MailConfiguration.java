package com.epam.notification.configuration;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.epam.notification.dto.EmailContentDto;

@Component
public class MailConfiguration {

	@Value("${mail.address.from}")
	private String emailFromAddress;

	public void sendmail(String emailReplyToAddress, EmailContentDto contect) throws MessagingException, IOException {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("mahir@gmail.com", "bolbonago");
			}
		});
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(emailFromAddress, false));

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailReplyToAddress));
		msg.setSubject(contect.getSubject());
		msg.setSentDate(new Date());

		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(contect.getData(), contect.getContentType());

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		msg.setContent(multipart);
		Transport.send(msg);
	}
}
