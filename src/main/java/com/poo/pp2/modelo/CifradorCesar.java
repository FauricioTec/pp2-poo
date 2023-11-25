package com.poo.pp2.modelo;

public class CifradorCesar extends CifradoSustitucion {

  private static final int DESPLAZAMIENTO = 3;

  public CifradorCesar() {

  }

  public String cifrar(String pMensaje) throws IllegalArgumentException {
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

  public String descifrar(String pMensaje) throws IllegalArgumentException {
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
