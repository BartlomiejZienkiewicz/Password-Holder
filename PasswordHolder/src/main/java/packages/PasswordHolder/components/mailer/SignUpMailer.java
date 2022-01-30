package packages.PasswordHolder.components.mailer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
public class SignUpMailer {

    private JavaMailSender mailSender;

    private SignUpMailTextFactory signUpMailTextFactory;

    @Autowired
    public SignUpMailer(JavaMailSender mailSender , SignUpMailTextFactory signUpMailTextFactory){
        this.mailSender = mailSender;
        this.signUpMailTextFactory = signUpMailTextFactory;
    }

    public void sendMessage(String to, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
    public void sendConfirmationLink(String email, String token){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(signUpMailTextFactory.getConfirmationMailSubject());
        message.setText(signUpMailTextFactory.getConfirmationMailtext(token));
        mailSender.send(message);

    }
}
