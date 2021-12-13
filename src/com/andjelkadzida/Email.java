package com.andjelkadzida;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class Email
{
    String username;
    String pass;
    String sender;
    String receiver;
    String host;
    String subject;
    String mailText;

    public Email(String username, String pass, String sender, String receiver, String host, String subject, String mailText)
    {
        this.username = username;
        this.pass = pass;
        this.sender = sender;
        this.receiver = receiver;
        this.host = host;
        this.subject = subject;
        this.mailText = mailText;
    }

    void sending()
    {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(username, pass);
            }
        });

        MimeMessage message = new MimeMessage(session);
        try
        {
            message.setSender(new InternetAddress(sender));
            message.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(receiver)));
            message.setSubject(subject);
            message.setText(mailText);
            Transport.send(message);
        }
        catch (MessagingException exception)
        {
            exception.printStackTrace();
        }
    }
}
