package xyz.caoxin.group.bot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import xyz.caoxin.group.bot.ConfigLoader;

import java.util.Properties;

@Configuration
public class EmailConfig {
    private ConfigLoader configLoader;

    @Autowired
    public void autoWired(ConfigLoader configLoader) {
        this.configLoader = configLoader;
    }

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(configLoader.getMailHost().split(":")[0]);
        mailSender.setPort(Integer.parseInt(configLoader.getMailHost().split(":")[1]));
        mailSender.setUsername(configLoader.getMailUserName());
        mailSender.setPassword(configLoader.getMailPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
