package com.poo.pp2.modelo;

/**
 * Clase que representa un cifrador binario
 */
public class CifradorBinario extends Cifrador {

  /**
   * Constructor de la clase
   */
  public CifradorBinario() {

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
      if (mensaje.charAt(i) == ' ') {
        mensajeCifrado.append("*");
      } else {
        String binario = Integer.toBinaryString(mensaje.charAt(i) - 'a');
        while (binario.length() < 5) {
          binario = "0" + binario;
        }
        mensajeCifrado.append(binario);
      }
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
      if (palabra.equals("*")) {
        mensajeDescifrado.append(" ");
      } else {
        mensajeDescifrado.append((char) (Integer.parseInt(palabra, 2) + 'a'));
      }
    }

    return mensajeDescifrado.toString();
  }

  /**
   * Metodo que verifica si un mensaje es valido
   *
   * @param pMensaje Mensaje a verificar
   * @return true si el mensaje es valido, false si no
   */
  @Override
  protected boolean esMensajeValido(String pMensaje) {
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
   * @return true si el caracter es valido, false si no
   */
  private boolean esCaracterValido(char pCaracter) {
    return (pCaracter >= 'a' && pCaracter <= 'z') || (pCaracter >= 'A' && pCaracter <= 'Z')
        || pCaracter == ' ';
  }


  /**
   * Metodo que verifica si un mensaje cifrado es valido
   *
   * @param pMensaje Mensaje cifrado a verificar
   * @return true si el mensaje cifrado es valido, false si no
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
   * @return true si el cifrado es valido, false si no
   */
  private boolean esCifradoValido(String pCifrado) {
    if (pCifrado.equals("*")) {
      return true;
    }
    if (pCifrado.length() != 5) {
      return false;
    }
    try {
      Integer.parseInt(pCifrado);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

}
