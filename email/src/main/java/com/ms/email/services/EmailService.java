package com.ms.email.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ms.email.models.EmailModel;
import com.ms.email.models.enums.StatusEmail;
import com.ms.email.repositories.EmailRepository;

import jakarta.transaction.Transactional;

@Service
public class EmailService {
	
	@Autowired
	private EmailRepository repository;
	
	@Autowired
	private JavaMailSender emailSender;
	
	@Value("${spring.mail.username}")
	private String emailFrom;
	
	@Transactional
	@SuppressWarnings("finally")
	public EmailModel sendEmail(EmailModel emailModel) {
		try {
			emailModel.setSendDateTime(LocalDateTime.now());
			emailModel.setEmailFrom(emailFrom);
			
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(emailFrom);
			message.setTo(emailModel.getEmailTo());
			message.setSubject(emailModel.getSubject());
			message.setText(emailModel.getText());
			emailSender.send(message);

			emailModel.setStatusEmail(StatusEmail.SENT);
		}
		catch(MailException e) {
			emailModel.setStatusEmail(StatusEmail.ERROR);
		}
		finally {
			emailModel = repository.save(emailModel);
			System.out.println(emailModel);
			return emailModel;
		}
	}

}
