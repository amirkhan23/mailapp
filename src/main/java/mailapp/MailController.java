package mailapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailController implements Initializable {

	@FXML
	TextField to;
	@FXML
	TextField subject;
	@FXML
	TextArea msg;
	@FXML
	Button send;

	@FXML
	public void sendMail(ActionEvent event) {

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.googlemail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("amirk8161@gmail.com", "amir@khan");
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("amirk8161@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to.getText()));
			message.setSubject(subject.getText());
			message.setText(msg.getText());
			Transport.send(message);
			System.out.println("Done");
			System.out.println(to.getText());
			System.out.println(msg.getText());

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
