package com.poo.pp2.modelo;

/**
 * Clase que representa un cifrador Vigenere
 */
public class CifradorVigenere extends CifradoSustitucion {

  /**
   * Cifra del cifrador
   */
  private final int cifra;

  /**
   * Constructor de la clase
   *
   * @param pCifra Cifra del cifrador
   * @throws IllegalArgumentException Si la cifra no es valida
   */
  public CifradorVigenere(String pCifra) {
    if (!esCifraValida(pCifra)) {
      throw new IllegalArgumentException("La cifra debe ser un número de dos dígitos");
    }
    cifra = Integer.parseInt(pCifra);
  }

  /**
   * Metodo que verifica si una cifra es valida
   *
   * @param pCifra Cifra a verificar
   * @return True si la cifra es valida, false de lo contrario
   */
  public static boolean esCifraValida(String pCifra) {
    if (pCifra == null || pCifra.length() != 2) {
      return false;
    }
    try {
      Integer.parseInt(pCifra);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  /**
   * Metodo que cifra un mensaje
   *
   * @param pMensaje Mensaje a cifrar
   * @return Mensaje cifrado
   * @throws Exception Si el mensaje no es valido
   */
  @Override
  public String cifrar(String pMensaje) throws Exception {
    if (!esMensajeValido(pMensaje)) {
      throw new IllegalArgumentException("El mensaje no es válido");
    }
    String mensaje = pMensaje.toLowerCase();
    StringBuilder mensajeCifrado = new StringBuilder();
    int j = 0;
    for (char caracter : mensaje.toCharArray()) {
      if (caracter == ' ') {
        mensajeCifrado.append(' ');
        j = 0;
      } else {
        int digito = j++ % 2 == 0 ? cifra / 10 : cifra % 10;
        int asciiCifrado = (caracter - 'a' + digito) % 26 + 'a';
        mensajeCifrado.append((char) asciiCifrado);
        j = j % 2;
      }
    }
    return mensajeCifrado.toString();
  }

  /**
   * Metodo que descifra un mensaje
   *
   * @param pMensaje Mensaje a descifrar
   * @return Mensaje descifrado
   * @throws Exception Si el mensaje no es valido
   */
  @Override
  public String descifrar(String pMensaje) throws Exception {
    if (!esMensajeCifradoValido(pMensaje)) {
      throw new IllegalArgumentException("El mensaje cifrado no es válido");
    }
    String mensaje = pMensaje.toLowerCase();
    StringBuilder mensajeDescifrado = new StringBuilder();
    int j = 0;
    for (char caracter : mensaje.toCharArray()) {
      if (caracter == ' ') {
        mensajeDescifrado.append(' ');
        j = 0;
      } else {
        int digito = j++ % 2 == 0 ? cifra / 10 : cifra % 10;
        int asciiDescifrado = (caracter - 'a' - digito + 26) % 26 + 'a';
        mensajeDescifrado.append((char) asciiDescifrado);
        j = j % 2;
      }
    }
    return mensajeDescifrado.toString();
  }
}
