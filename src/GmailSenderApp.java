package src.src;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class GmailSenderApp {

    private static final String SENDER_EMAIL = "tonly2814@gmail.com";
    private static final String SENDER_PASSWORD = "hdkztqukwtltfozi";

    public static void main(String[] args) {
        String recipientEmail = "techdare7@gmail.com";
        try {
            sendEmail(recipientEmail, "Hello","test","<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <style>\n" +
                    "        body {\n" +
                    "            font-family: Arial, sans-serif;\n" +
                    "            color: #333;\n" +
                    "            background-color: #f9f9f9;\n" +
                    "            margin: 0;\n" +
                    "            padding: 0;\n" +
                    "        }\n" +
                    "        .email-container {\n" +
                    "            max-width: 600px;\n" +
                    "            margin: 0 auto;\n" +
                    "            background: white;\n" +
                    "            border-radius: 10px;\n" +
                    "            overflow: hidden;\n" +
                    "            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);\n" +
                    "        }\n" +
                    "        .header {\n" +
                    "            background-color: #e63946;  /* Soft red color */\n" +
                    "            color: #fff;\n" +
                    "            padding: 20px;\n" +
                    "            text-align: center;\n" +
                    "            font-size: 1.5em;\n" +
                    "        }\n" +
                    "        .header img {\n" +
                    "            max-width: 100px;\n" +
                    "            margin-bottom: 10px;\n" +
                    "        }\n" +
                    "        .content {\n" +
                    "            padding: 20px;\n" +
                    "            text-align: center;\n" +
                    "        }\n" +
                    "        .content h2 {\n" +
                    "            color: #e63946;  /* Soft red color */\n" +
                    "            font-size: 1.8em;\n" +
                    "        }\n" +
                    "        .content p {\n" +
                    "            font-size: 1.1em;\n" +
                    "            line-height: 1.6;\n" +
                    "        }\n" +
                    "        .cta-button {\n" +
                    "            display: inline-block;\n" +
                    "            background-color: #e63946;  /* Soft red color */\n" +
                    "            color: #fff;\n" +
                    "            padding: 12px 25px;\n" +
                    "            border-radius: 30px;\n" +
                    "            font-size: 1em;\n" +
                    "            text-decoration: none;\n" +
                    "            margin-top: 20px;\n" +
                    "        }\n" +
                    "        .footer {\n" +
                    "            background-color: #f1f1f1;\n" +
                    "            color: #666;\n" +
                    "            text-align: center;\n" +
                    "            padding: 20px;\n" +
                    "            font-size: 0.9em;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <div class=\"email-container\">\n" +
                    "        <div class=\"header\">\n" +
                    "            <img src=\"https://drive.google.com/file/d/13FcTibMIJ0xOnGSqk8GQwJ5wBg2swKaN/view?usp=sharing\" alt=\"Logo\">\n" +
                    "            <h1>Thank You for Registering with Us!</h1>\n" +
                    "        </div>\n" +
                    "\n" +
                    "        <div class=\"content\">\n" +
                    "            <h2>Welcome to Our Community!</h2>\n" +
                    "            <p>We’re thrilled to have you on board. Get ready for exciting updates, and much more!</p>\n" +
                    "            <p>Explore our offerings, and let us make your journey with us delightful.</p>\n" +
                    "            \n" +
                    "        </div>\n" +
                    "\n" +
                    "        <div class=\"footer\">\n" +
                    "            <p>If you have any questions, feel free to <a href=\"mailto:tonly2814@gmail.com\" style=\"color:#e63946;\">Contact us</a>.</p>\n" +
                    "            <p>&copy; 2024 Food Rescuers. All rights reserved.</p>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "</body>\n" +
                    "</html>\n");
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            System.err.println("Failed to send email: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void sendEmail(String toAddr, String subject, String text, String htmlcontent) throws MessagingException{
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(SENDER_EMAIL));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddr));
        message.setSubject(subject);
        message.setText(text);
        message.setContent(htmlcontent, "text/html; charset=utf-8");

        Transport.send(message);
    }
    public static void sendEmail(String toAddr, String subject, String text) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(SENDER_EMAIL));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddr));
        message.setSubject(subject);
        message.setText(text);

        Transport.send(message);
    }
}
