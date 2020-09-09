package demo.smtp.email;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
//   @RequestMapping(value = "/sendemail")
//   public String sendEmail() {
//      return "Email sent successfully";
//   } 
   
   @RequestMapping(value = "/sendemail")
   public String sendEmail() throws AddressException, MessagingException, IOException {
      sendmail();
      return "Email sent successfully";   
   }
   
   
   private void sendmail() throws AddressException, MessagingException, IOException {
	   Properties props = new Properties();
	   props.put("mail.smtp.auth", "true");
	   props.put("mail.smtp.starttls.enable", "true");
	   props.put("mail.smtp.host", "smtp-mail.outlook.com");
	   props.put("mail.smtp.port", "587");
	   
	   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	      protected PasswordAuthentication getPasswordAuthentication() {
	         return new PasswordAuthentication("saininderpreet994@outlook.com", "$ay0narA3");
	      }
	   });
	   Message msg = new MimeMessage(session);
	   msg.setFrom(new InternetAddress("saininderpreet994@outlook.com", false));

	   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("saininderpreet994@outlook.com"));
	   msg.setSubject("Tutorials point email");
	   msg.setContent("Tutorials point email", "text/html");
	   msg.setSentDate(new Date());

	   MimeBodyPart messageBodyPart = new MimeBodyPart();
	   messageBodyPart.setContent("Tutorials point email", "text/html");

	   Multipart multipart = new MimeMultipart();
	   multipart.addBodyPart(messageBodyPart);
//	   MimeBodyPart attachPart = new MimeBodyPart();
//
//	   attachPart.attachFile("/var/tmp/Untitled.png");
//	   multipart.addBodyPart(attachPart);
	   msg.setContent(multipart);
	   Transport.send(msg);   
	}
}



