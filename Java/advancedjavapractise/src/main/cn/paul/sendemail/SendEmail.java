/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package main.cn.paul.sendemail;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by lfp on 2020/11/25.
 */
public class SendEmail {

    public static void main(String args[]) {

        String toPerson = "xxx@163.com";

        String fromPerson = "xxx@xx.com";

        String hostname = "localhost";

        Properties properties = System.getProperties();

        properties.setProperty("mail.smtp.host", hostname);

        Session session = Session.getDefaultInstance(properties);

        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(fromPerson));
            mimeMessage.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(toPerson));


            mimeMessage.setSubject("This is the Subject Line!");

        }catch (AddressException e) {
            e.printStackTrace();
        }catch (MessagingException x ) {
            x.printStackTrace();
        }
    }

    public static void sendMail() {

    }
}
