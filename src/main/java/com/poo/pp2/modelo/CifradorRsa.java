package com.poo.pp2.modelo;

import java.math.BigInteger;

public class CifradorRsa extends Cifrador {

  private final LlaveRsa llave;

  public CifradorRsa(LlaveRsa pLlave) {
    llave = pLlave;
  }

  @Override
  public String cifrar(String pMensaje) throws IllegalArgumentException {
    if (!esMensajeValido(pMensaje)) {
      throw new IllegalArgumentException("El mensaje no es válido");
    }
    StringBuilder mensajeCifrado = new StringBuilder();
    for (int i = 0; i < pMensaje.length(); i++) {
      int caracter = pMensaje.charAt(i);
      BigInteger asciiCifrado = BigInteger.valueOf(caracter).pow(llave.getExponente())
          .mod(BigInteger.valueOf(llave.getModulo()));
      mensajeCifrado.append(asciiCifrado);
      if (i != pMensaje.length() - 1) {
        mensajeCifrado.append("*");
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
    String[] partes = pMensaje.split("\\*");
    for (String parte : partes) {
      BigInteger asciiCifrado = new BigInteger(parte);
      int asciiDescifrado = asciiCifrado.pow(llave.getExponente())
          .mod(BigInteger.valueOf(llave.getModulo())).intValue();
      mensajeDescifrado.append((char) asciiDescifrado);
    }
    return mensajeDescifrado.toString();
  }

  @Override
  protected boolean esMensajeCifradoValido(String pMensaje) {
    String[] partes = pMensaje.split("\\*");
    for (String parte : partes) {
      try {
        Integer.parseInt(parte);
      } catch (NumberFormatException e) {
        return false;
      }
    }
    return true;
  }
}

