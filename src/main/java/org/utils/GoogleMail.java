package org.utils;

import com.sun.mail.smtp.SMTPTransport;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.*;
import java.security.Security;
import java.util.Properties;


public class GoogleMail {

    private final static String USERNAME = "iFoober";
    private final static String PASSWORD = "fooberlab1";

    private GoogleMail() {
    }

    /**
     * Send email using GMail SMTP server.
     *
     * @param recipientEmail TO recipient
     * @param title          title of the message
     * @param message        message to be sent
     * @throws AddressException   if the email address parse failed
     * @throws MessagingException if the connection is dead or not in the connected state or if the message is not a MimeMessage
     */
    static void send(String recipientEmail, String title, String message) throws MessagingException {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.smtps.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtps.auth", "true");

        /*
        If set to false, the QUIT command is sent and the connection is immediately closed. If set
        to true (the default), causes the transport to wait for the response to the QUIT command.

        ref :   http://java.sun.com/products/javamail/javadocs/com/sun/mail/smtp/package-summary.html
                http://forum.java.sun.com/thread.jspa?threadID=5205249
                smtpsend.java - demo program from javamail
        */
        props.put("mail.smtps.quitwait", "false");

        Session session = Session.getInstance(props, null);

        // Create a default MimeMessage object.
        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(USERNAME + "@gmail.com"));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail, false));

        // Set Subject: header field
        msg.setSubject(title);

        // This mail has 2 part, the BODY and the embedded image
        MimeMultipart multipart = new MimeMultipart("related");

        // first part (the html)
        BodyPart messageBodyPart = new MimeBodyPart();
        final String htmlFinal = addSignature(message);
        messageBodyPart.setContent(htmlFinal, "text/html");
        // add it
        multipart.addBodyPart(messageBodyPart);
        msg.setContent(multipart);


        SMTPTransport t = (SMTPTransport) session.getTransport("smtps");

        t.connect("smtp.gmail.com", USERNAME, PASSWORD);
        t.sendMessage(msg, msg.getAllRecipients());
        t.close();
    }

    private static String addSignature(String currentText) throws MessagingException {
        final String img = "\n\n<img src=\"https://image.ibb.co/bNqb67/Foober_Logo_Mini.png\" alt=\"Logo\" title=\"Logo\" style=\"display:block\" width=\"100\" height=\"100\">";
        final String sig = "\n<h5><i>Sincerely,\nThe Foober Team </i>©</h5>";
        return currentText + img + sig;
    }
}