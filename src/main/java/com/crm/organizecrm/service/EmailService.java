//package com.crm.organizecrm.service;
//
//import com.crm.organizecrm.exception.EmailException;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.context.Context;
//import lombok.RequiredArgsConstructor;
//
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//
//@Service
//@RequiredArgsConstructor
//public class EmailService {
//
//    private final JavaMailSender mailSender;
//    private final TemplateEngine templateEngine;
//
//    public void sendEmail(String to, String subject, String templateName, Context context) {
//        String body = templateEngine.process(templateName, context);
//        try {
//            sendHtmlEmail(to, subject, body);
//        } catch (MessagingException e) {
//            throw new EmailException("Failed to send email to " + to, e);
//        }
//    }
//
//    private void sendHtmlEmail(String to, String subject, String body) throws MessagingException {
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        helper.setTo(to);
//        helper.setSubject(subject);
//        helper.setText(body, true);
//        mailSender.send(message);
//    }
//}
