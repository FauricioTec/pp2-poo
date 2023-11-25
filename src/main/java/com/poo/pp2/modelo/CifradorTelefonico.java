package com.poo.pp2.modelo;

/**
 * Clase que representa un cifrador binario
 */
public class CifradorTelefonico extends Cifrador {

  /**
   * Teclado de un telefono
   */
  private static final char[][] TECLADO = {
      {'*', ' '},
      {'2', 'a', 'b', 'c'},
      {'3', 'd', 'e', 'f'},
      {'4', 'g', 'h', 'i'},
      {'5', 'j', 'k', 'l'},
      {'6', 'm', 'n', 'o'},
      {'7', 'p', 'q', 'r', 's'},
      {'8', 't', 'u', 'v'},
      {'9', 'w', 'x', 'y', 'z'}
  };

  /**
   * Constructor de la clase
   */
  public CifradorTelefonico() {

  }

  /**
   * Metodo que cifra un mensaje
   *
   * @param pMensaje Mensaje a cifrar
   * @return Mensaje cifrado
   * @throws Exception Si ocurre un error al cifrar el mensaje
   */
  @Override
  public String cifrar(String pMensaje) throws Exception {
    if (!esMensajeValido(pMensaje)) {
      throw new IllegalArgumentException("El mensaje no es válido");
    }
    String mensaje = pMensaje.toLowerCase();
    StringBuilder mensajeCifrado = new StringBuilder();

    for (int i = 0; i < mensaje.length(); i++) {
      String cifrado = cifrarCaracter(mensaje.charAt(i));
      mensajeCifrado.append(cifrado);
      if (i != mensaje.length() - 1) {
        mensajeCifrado.append(" ");
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
  @Override
  public String descifrar(String pMensaje) throws Exception {
    if (!esMensajeCifradoValido(pMensaje)) {
      throw new IllegalArgumentException("El mensaje cifrado no es válido");
    }
    StringBuilder mensajeDescifrado = new StringBuilder();

    String[] mensaje = pMensaje.split(" ");
    for (String palabra : mensaje) {
      mensajeDescifrado.append(descifrarCaracter(palabra));
    }

    return mensajeDescifrado.toString();
  }

  /**
   * Metodo que cifra un caracter
   *
   * @param pCaracter Caracter a cifrar
   * @return Caracter cifrado
   */
  private String cifrarCaracter(char pCaracter) {
    if (pCaracter == ' ') {
      return "*";
    }

    for (char[] chars : TECLADO) {
      for (int j = 0; j < chars.length; j++) {
        if (chars[j] == pCaracter) {
          return "" + chars[0] + j;
        }
      }
    }

    return "";
  }

  /**
   * Metodo que descifra un caracter
   *
   * @param pCifrado Caracter a descifrar
   * @return Caracter descifrado
   */
  private String descifrarCaracter(String pCifrado) {
    if (pCifrado.equals("*")) {
      return " ";
    }

    int primerDigito = pCifrado.charAt(0) - '0';
    int segundoDigito = pCifrado.charAt(1) - '0';

    return "" + TECLADO[primerDigito - 1][segundoDigito];
  }

  /**
   * Metodo que verifica si un mensaje es valido
   *
   * @param pMensaje Mensaje a verificar
   * @return True si el mensaje es valido, false de lo contrario
   */
  @Override
  protected boolean esMensajeValido(String pMensaje) {
    if (pMensaje == null || pMensaje.isEmpty()) {
      return false;
    }
    for (char caracter : pMensaje.toCharArray()) {
      if (!esCaracterValido(caracter)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Metodo que verifica si un caracter es valido
   *
   * @param pCaracter Caracter a verificar
   * @return True si el caracter es valido, false de lo contrario
   */
  private boolean esCaracterValido(char pCaracter) {
    return (pCaracter >= 'a' && pCaracter <= 'z') || (pCaracter >= 'A' && pCaracter <= 'Z')
        || pCaracter == ' ';
  }

  /**
   * Metodo que verifica si un mensaje cifrado es valido
   *
   * @param pMensaje Mensaje cifrado a verificar
   * @return True si el mensaje cifrado es valido, false de lo contrario
   */
  @Override
  protected boolean esMensajeCifradoValido(String pMensaje) {
    String[] mensaje = pMensaje.split(" ");
    for (String palabra : mensaje) {
      if (!esCifradoValido(palabra)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Metodo que verifica si un cifrado es valido
   *
   * @param pCifrado Cifrado a verificar
   * @return True si el cifrado es valido, false de lo contrario
   */
  private boolean esCifradoValido(String pCifrado) {
    if (pCifrado.equals("*")) {
      return true;
    }

    if (pCifrado.length() != 2) {
      return false;
    }

    for (char[] chars : TECLADO) {
      if (chars[0] == pCifrado.charAt(0) && Character.isDigit(pCifrado.charAt(1)) &&
          (pCifrado.charAt(1) - '0') < chars.length) {
        return true;
      }
    }
    return false;
  }
}