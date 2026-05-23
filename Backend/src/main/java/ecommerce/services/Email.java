package ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
@Service
public class Email {
	
	@Autowired
	private JavaMailSender javaMailSender;
	

		
	public void simpleEmail(String toEmail,String body,String subject) {
		
			SimpleMailMessage message=new SimpleMailMessage();
			
			message.setFrom("himanshudixit1008@gmail.com");
			message.setTo(toEmail);
			message.setText(body);
			message.setSubject(subject);
		
		javaMailSender.send(message);
		System.out.println("email is being sent");
	 
	}
	}

