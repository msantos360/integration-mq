package com.fiap.grp2.integrationmq.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

import static com.fiap.grp2.integrationmq.util.Constants.*;

public class Email {

    public JavaMailSender getGmailJavaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(GMAIL_SMTP);
        mailSender.setPort(GMAIL_PORT);
        mailSender.setUsername(GMAIL_USERNAME);
        mailSender.setPassword(GMAIL_PASSWD);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    public void sendGmailSimpleMail(String para, String assunto, String conteudo){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(GMAIL_USERNAME);
        message.setTo(para);
        message.setSubject(assunto);
        message.setText(conteudo);

        getGmailJavaMailSender().send(message);
    }

}
