package com.poo.pp2.modelo;

/**
 * Clase que representa un cifrador Cesar
 */
public class CifradorCesar extends CifradoSustitucion {

  /**
   * Desplazamiento del cifrador
   */
  private static final int DESPLAZAMIENTO = 3;

  /**
   * Constructor de la clase
   */
  public CifradorCesar() {

  }

  /**
   * Metodo que cifra un mensaje
   *
   * @param pMensaje Mensaje a cifrar
   * @return Mensaje cifrado
   * @throws Exception Si ocurre un error al cifrar el mensaje
   */
  public String cifrar(String pMensaje) throws Exception {
    if (!esMensajeValido(pMensaje)) {
      throw new IllegalArgumentException("El mensaje no es válido");
    }
    String mensaje = pMensaje.toUpperCase();
    StringBuilder mensajeCifrado = new StringBuilder();
    for (int i = 0; i < mensaje.length(); i++) {
      char caracter = mensaje.charAt(i);
      if (caracter == ' ') {
        mensajeCifrado.append(' ');
      } else {
        int asciiCifrado = (caracter - 'A' + DESPLAZAMIENTO) % 26 + 'A';
        mensajeCifrado.append((char) asciiCifrado);
      }
    }
    return mensajeCifrado.toString();
  }

  /**
   * Metodo que descifra un mensaje
   *
   * @param pMensaje Mensaje a descifrar
   * @return Mensaje descifrado
   * @throws Exception Si ocurre un error al descifrar el mensaje
   */
  public String descifrar(String pMensaje) throws Exception {
    if (!esMensajeCifradoValido(pMensaje)) {
      throw new IllegalArgumentException("El mensaje cifrado no es válido");
    }
    String mensaje = pMensaje.toUpperCase();
    StringBuilder mensajeDescifrado = new StringBuilder();
    for (int i = 0; i < mensaje.length(); i++) {
      char caracter = mensaje.charAt(i);
      if (caracter == ' ') {
        mensajeDescifrado.append(' ');
      } else {
        int asciiDescifrado = (caracter - 'A' - DESPLAZAMIENTO + 26) % 26 + 'A';
        mensajeDescifrado.append((char) asciiDescifrado);
      }
    }
    return mensajeDescifrado.toString();
  }
}
