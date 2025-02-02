package br.edu.utfpr.ProjetoIDRAPI.entity.email.impl;

import java.time.LocalDateTime;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.edu.utfpr.ProjetoIDRAPI.entity.email.Email;
import br.edu.utfpr.ProjetoIDRAPI.entity.email.EmailRepository;
import br.edu.utfpr.ProjetoIDRAPI.entity.email.EmailService;

@Service
public class EmailServiceImpl implements EmailService{
	@Autowired
	private EmailRepository emailRepository;
	
	@Autowired
	private JavaMailSender emailSender;
	
	@Override
	public Email sendEmail(Email email) {
		email.setSendDateEmail(LocalDateTime.now());
		
		try {
			MimeMessage mail = emailSender.createMimeMessage();
			MimeMessageHelper message = new MimeMessageHelper(mail);
			//SimpleMailMessage message = new SimpleMailMessage();
			
			message.setFrom(email.getEmailFrom());
			message.setTo(email.getEmailTo());
			message.setSubject(email.getSubject());
			message.setText(email.getText(), true);
			
			emailSender.send(message.getMimeMessage());
			
			email.setStatusEmail("The email has been sent!");
		}catch(MailException e) {
			email.setStatusEmail("Email not sent. Try again later");
		}finally {
            return emailRepository.save(email);
        }
	}

	@Override
	public Email findByEmailTo(String emailTo) {
		return emailRepository.findByEmailTo(emailTo);
	}

	@Override
	public void deleteById(long id) {
		emailRepository.deleteById(id);
	}

}
