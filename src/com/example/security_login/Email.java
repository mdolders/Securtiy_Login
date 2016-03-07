package com.example.security_login;

import java.util.Properties;
import java.util.Scanner;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	
	public void sendMail(String emailAddress, String TanCode) {

	Properties properties = new Properties();

	properties.put("mail.smtp.auth", "true");
	properties.put("mail.smtp.starttls.enable","true");
	properties.put("mail.smtp.host","smtp.gmail.com");
	properties.put("mail.smtp.port","587");
	
	final String username = "test.noreply.java@gmail.com";
	final String password = "cartmanez";
	final String fromEmailAddress = "test.noreply.java@gmail.com";
	final String subject = "TAN-CODE";

	final String toEmailAddress = emailAddress;
	final String textMessage = TanCode;


	Session session = Session.getDefaultInstance(properties, new Authenticator(){
	@Override
	protected PasswordAuthentication getPasswordAuthentication(){
	return new PasswordAuthentication (username, password);

	}
	});

	try {

	Message message = new MimeMessage(session);
	message.setFrom();
	message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(toEmailAddress));
	message.setSubject(subject);
	message.setText(textMessage);
	Transport.send(message);

	System.out.println("Bericht verstuurd!");

	} catch(MessagingException e){

	throw new RuntimeException(e);
	}
	}
	
}
	
