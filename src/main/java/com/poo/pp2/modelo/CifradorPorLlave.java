package com.poo.pp2.modelo;

public class CifradorPorLlave extends CifradoSustitucion {

  private final String llave;

  public CifradorPorLlave(String pLlave) {
    if (!esLlaveValida(pLlave)) {
      throw new IllegalArgumentException("La llave no es válida");
    }
    llave = pLlave.toLowerCase();
  }

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


  @Override
  public String cifrar(String pMensaje) throws IllegalArgumentException {
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

  @Override
  public String descifrar(String pMensaje) throws IllegalArgumentException {
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
