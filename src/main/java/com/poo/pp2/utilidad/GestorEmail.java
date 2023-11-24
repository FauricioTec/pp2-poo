package com.poo.pp2.utilidad;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GestorEmail {

  private final String usuario = "correosautomaticos673@gmail.com";
  private final String contrasenna = "dwhv ixzp lnud ljwl";

  private final Properties properties;

  public GestorEmail() {
    properties = new Properties();
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.port", "587");
  }

  public static boolean esEmailValido(String pEmail) {
    if (pEmail == null || pEmail.isEmpty()) {
      return false;
    }

    String regex = "^(.+)@(\\S+)$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(pEmail);

    if (!matcher.matches()) {
      return false;
    }

    ClienteEmailable clienteEmailable = new ClienteEmailable("live_89f34a797cc9a9b60ef3");

    try {
      return clienteEmailable.verificarEmail(pEmail).equals("deliverable");
    } catch (Exception e) {
      return false;
    }
  }

  public void enviarEmail(String pEmail, String pAsunto, String pMensaje) throws Exception {
    Session session = Session.getInstance(properties, new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(usuario, contrasenna);
      }
    });

    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress(usuario));

    Address[] toAddresses = new Address[1];
    toAddresses[0] = new InternetAddress(pEmail);
    message.setRecipients(Message.RecipientType.TO, toAddresses);

    message.setSubject(pAsunto);

    message.setContent(pMensaje, "text/html");

    Transport.send(message);

  }
  
    public static void main(String[] args) {
        String email = "fauricio.gr@gmail.com";
        ClienteEmailable clienteEmailable = new ClienteEmailable("live_89f34a797cc9a9b60ef3");
        GestorEmail gestorEmail = new GestorEmail();
        if (GestorEmail.esEmailValido(email)) {
            try {
                gestorEmail.enviarEmail(email, "Prueba", "Este es un mensaje de prueba");
                System.out.println("Email enviado");
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        } else {
            System.out.println("El email no es valido");
        }
    }

}
