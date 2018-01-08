package timebank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class EmailSenderService {

  @Autowired
  private JavaMailSender javaMailSender;

   public void sendEmail(String to, String title, String content) {
    MimeMessage mail = javaMailSender.createMimeMessage();
    try {
      MimeMessageHelper helper = new MimeMessageHelper(mail, true);
      helper.setTo(to);
      helper.setReplyTo("timebank.manager@gmail.com");
      helper.setFrom("timebank.manager@gmail.com");
      helper.setSubject(title);
      helper.setText(content, true);

    } catch (MessagingException e) {
      e.printStackTrace();
    }
    javaMailSender.send(mail);
  }
}
