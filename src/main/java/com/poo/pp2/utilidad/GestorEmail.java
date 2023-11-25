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

/**
 * Clase que gestiona el envio de emails y la verificacion de emails
 */
public class GestorEmail {

  /**
   * Usuario de la cuenta de correo
   */
  private final String usuario = "correosautomaticos673@gmail.com";
  /**
   * Contraseña de la cuenta de correo
   */
  private final String contrasenna = "dwhv ixzp lnud ljwl";
  /**
   * Propiedades de la cuenta de correo
   */
  private final Properties properties;

  /**
   * Constructor que inicializa las propiedades de la cuenta de correo
   */
  public GestorEmail() {
    properties = new Properties();
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.port", "587");
  }

  /**
   * Metodo que verifica si un email es valido
   *
   * @param pEmail Email a verificar
   * @return true si el email es valido, false si no
   */
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

  /**
   * Metodo que envia un email
   *
   * @param pEmail   Email al que se le va a enviar el mensaje
   * @param pAsunto  Asunto del mensaje
   * @param pMensaje Mensaje a enviar
   * @throws Exception Si ocurre un error al enviar el email
   */
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

}
