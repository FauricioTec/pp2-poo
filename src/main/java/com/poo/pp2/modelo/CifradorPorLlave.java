package com.poo.pp2.modelo;

/**
 * Clase que representa un cifrador binario
 */
public class CifradorPorLlave extends CifradoSustitucion {

  /**
   * Llave del cifrador
   */
  private final String llave;

  /**
   * Constructor de la clase
   *
   * @param pLlave Llave del cifrador
   */
  public CifradorPorLlave(String pLlave) {
    if (!esLlaveValida(pLlave)) {
      throw new IllegalArgumentException("La llave no es válida");
    }
    llave = pLlave.toLowerCase();
  }

  /**
   * Metodo que verifica si una llave es valida
   *
   * @param pLlave Llave a verificar
   * @return True si la llave es valida, false de lo contrario
   */
  public static boolean esLlaveValida(String pLlave) {
    if (pLlave == null || pLlave.isEmpty()) {
      return false;
    }
    for (Character caracter : pLlave.toCharArray()) {
      if (!((caracter >= 'a' && caracter <= 'z') || (caracter >= 'A' && caracter <= 'Z'))) {
        return false;
      }
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
    for (Character caracter : mensaje.toCharArray()) {
      if (caracter == ' ') {
        mensajeCifrado.append(' ');
        j = 0;
      } else {
        int asciiCifrado = (caracter - 'a' + llave.charAt(j++) - 96) % 26 + 'a';
        mensajeCifrado.append((char) asciiCifrado);
        j = j % llave.length();
      }
    }
    return mensajeCifrado.toString();
  }

  /**
   * Metodo que descifra un mensaje
   *
   * @param pMensaje Mensaje a descifrar
   * @return Mensaje descifrado
   * @throws Exception Si el mensaje cifrado no es valido
   */
  @Override
  public String descifrar(String pMensaje) throws Exception {
    if (!esMensajeCifradoValido(pMensaje)) {
      throw new IllegalArgumentException("El mensaje cifrado no es válido");
    }
    StringBuilder mensajeDescifrado = new StringBuilder();
    int j = 0;
    for (Character caracter : pMensaje.toCharArray()) {
      if (caracter == ' ') {
        mensajeDescifrado.append(' ');
        j = 0;
      } else {
        int asciiDescifrado = ((caracter - 'a' - (llave.charAt(j++) - 96)) + 26) % 26 + 'a';
        mensajeDescifrado.append((char) asciiDescifrado);
        j = j % llave.length();
      }
    }
    return mensajeDescifrado.toString();
  }
}
