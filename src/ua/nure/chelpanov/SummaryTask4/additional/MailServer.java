package ua.nure.chelpanov.SummaryTask4.additional;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailServer {
	private String username;
	private String password;
	private Properties props;

	public MailServer(String username, String password) {
	this.username = username;
	this.password = password;

	props = new Properties();
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.port", "587");
	}

	public void send(String subject, String text, String fromEmail, String toEmail) {
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			// From
			message.setFrom(new InternetAddress(username));
			// To
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
			// Title
			message.setSubject(subject);
			// Text
			message.setText(text);

			// Send
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
