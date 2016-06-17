package comregpro;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.testng.annotations.Test;

public class Mailing {
	static MimeBodyPart messageBodyPart;
	static Multipart multipart;
	static Message message;
	public static void main(String args[]) throws IOException, InterruptedException{
    MLS();
    }
	public static void MLS() throws IOException, InterruptedException {

    final String username = "haideraftab32@gmail.com";
    final String password = "test";
    

    Properties props = new Properties();
    props.put("mail.smtp.auth", true);
    props.put("mail.smtp.starttls.enable", true);
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
     try {
     message = new MimeMessage(session);
     message.setFrom(new InternetAddress("haideraftab32@gmail.com"));
     message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse("shariq.abbas@laitkor.com"));
     message.setSubject("PMD-Regression Battery Completed : Autosender Enabled");
     message.setText("Dear All, \n\n\tab\tab Please find Automation Report \n Regards \n Seshu" );
     messageBodyPart = new MimeBodyPart();
     multipart = new MimeMultipart();
     Thread.sleep(5000);
        String file = System.getProperty("user.home")+"/Desktop/PMR/Results"+"/emailable-report.html";  
        addAttachment(messageBodyPart,multipart,file );
        System.out.println("Sending");
        Transport.send(message);
        System.out.println("Done");
        } catch (MessagingException e) {
        e.printStackTrace();
    }
  }
	private static void addAttachment(MimeBodyPart messageBodyPart,Multipart multipart, String file) throws MessagingException
	{
DataSource source = new FileDataSource(file);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(file);
        multipart.addBodyPart(messageBodyPart);
        message.setContent(multipart);   
	}
}
