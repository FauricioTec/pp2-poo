package com.poo.pp2.modelo;

public class CifradoPalabraInversa extends CifradoTransposicion {

  @Override
  public String cifrar(String pMensaje) {
    StringBuilder mensajeCifrado = new StringBuilder();
    String[] palabras = pMensaje.split(" ");
    for (String palabra : palabras) {
      mensajeCifrado.append(invertirString(palabra)).append(" ");
    }
    return mensajeCifrado.toString().trim();
  }

  @Override
  public String descifrar(String pMensaje) {
    return cifrar(pMensaje);
  }
}
