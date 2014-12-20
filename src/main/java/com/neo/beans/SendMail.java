package com.neo.beans;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	final static String username = "rabatmiage@gmail.com";
	final static String password = "miagerabat1314";

	public static void envoyer(String mail,String compte, String pass) throws AddressException, MessagingException {

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from-email@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(mail));
			message.setSubject("Votre compte NEONE");
			message.setText("Cher client,"
					+ "\n\n Les informations ci-dessous vous permettrons de vous connecter sur notre site "
					+"a fin de suivre l'évolution de vos campagnes en cours."
					+ "\n\nCompte: "+compte+"\nMot de Passe: "+pass+"\n "
							+ "Nous vous remercions pour votre confiance!");

			Transport.send(message);
	}

}
