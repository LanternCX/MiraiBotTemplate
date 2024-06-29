package xyz.caoxin.group.bot.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import xyz.caoxin.group.bot.data.WireRepo;

@Service
public class Email {

    private static JavaMailSender emailSender;

    public Email(JavaMailSender emailSender) {
        Email.emailSender = emailSender;
    }

    public static void sendWarnMessage(String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(WireRepo.configLoader.getMailUserName());
        message.setSubject(subject);
        message.setText(text);
        message.setFrom(WireRepo.configLoader.getMailUserName());
        emailSender.send(message);
    }
}