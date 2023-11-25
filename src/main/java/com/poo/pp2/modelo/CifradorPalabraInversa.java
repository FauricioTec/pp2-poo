package com.poo.pp2.modelo;

public class CifradorPalabraInversa extends CifradoTransposicion {

  public CifradorPalabraInversa() {

  }

  @Override
  public String cifrar(String pMensaje) throws IllegalArgumentException {
    if (!esMensajeValido(pMensaje)) {
      throw new IllegalArgumentException("El mensaje no es válido");
    }
    StringBuilder mensajeCifrado = new StringBuilder();
    String[] palabras = pMensaje.split(" ");
    for (String palabra : palabras) {
      mensajeCifrado.append(invertirString(palabra)).append(" ");
    }
    return mensajeCifrado.toString().trim();
  }

  @Override
  public String descifrar(String pMensaje) throws IllegalArgumentException {
    if (!esMensajeCifradoValido(pMensaje)) {
      throw new IllegalArgumentException("El mensaje cifrado no es válido");
    }
    return cifrar(pMensaje);
  }
}
