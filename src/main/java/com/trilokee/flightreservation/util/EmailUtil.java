package com.trilokee.flightreservation.util;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

	@Value("${com.trilokee.flightreservation.itinerary.email.body}")
	private String EMAIL_BODY = "Please find your itinerary in the  Attachment";
	
	@Value("${com.trilokee.flightreservation.itinerary.email.subject}")
	private String EMAIL_SUBJECT = "Sending Itinerary of your filght";
	@Autowired
	private JavaMailSender sender;
	
	public void sendItinerary(String toAddress,String filePath)
	{
		MimeMessage message = sender.createMimeMessage();
		
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message,true);
			
			messageHelper.setTo(toAddress);
			messageHelper.setSubject(EMAIL_SUBJECT);
			messageHelper.setText(EMAIL_BODY);
			messageHelper.addAttachment("Itinerary", new File(filePath));
			
			sender.send(message);
			
		} 
		catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//if there is not attachment then we shold pass  false
	}
}
