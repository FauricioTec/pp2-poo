package com.poo.pp2.modelo;

public class CifradoTelefonico extends Cifrador {

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

  @Override
  public String cifrar(String pMensaje) {
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

  @Override
  public String descifrar(String pMensaje) {
    StringBuilder mensajeDescifrado = new StringBuilder();

    String[] mensaje = pMensaje.split(" ");
    for (String palabra : mensaje) {
      mensajeDescifrado.append(descifrarCaracter(palabra));
    }

    return mensajeDescifrado.toString();
  }

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

  private String descifrarCaracter(String pCifrado) {
    if (pCifrado.equals("*")) {
      return " ";
    }

    int primerDigito = pCifrado.charAt(0) - '0';
    int segundoDigito = pCifrado.charAt(1) - '0';

    return "" + TECLADO[primerDigito - 1][segundoDigito];
  }

  @Override
  public boolean esMensajeValido(String pMensaje) {
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

  private boolean esCaracterValido(char pCaracter) {
    return (pCaracter >= 'a' && pCaracter <= 'z') || (pCaracter >= 'A' && pCaracter <= 'Z')
        || pCaracter == ' ';
  }

  @Override
  public boolean esMensajeCifradoValido(String pMensaje) {
    String[] mensaje = pMensaje.split(" ");
    for (String palabra : mensaje) {
      if (!esCifradoValido(palabra)) {
        return false;
      }
    }
    return true;
  }


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