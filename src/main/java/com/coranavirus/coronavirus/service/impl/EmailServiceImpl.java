package com.coranavirus.coronavirus.service.impl;

import com.coranavirus.coronavirus.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.lang.module.Configuration;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private final Configuration configuration;

    @Override
    public void sendMail(String subject, String body, String recipientAddress, String senderAddress) {
        var template = configuration.getTemplate(mailData.getTemplate() + ".ftl");
        var content = FreeMarkerTemplateUtils.processTemplateIntoString()
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom(senderAddress);
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(body);
        mailSender.send(email);
    }
}
