package com.poo.pp2.modelo;

public class CifradorMensajeInverso extends CifradoTransposicion {

  @Override
  public String cifrar(String pMensaje) throws IllegalArgumentException {
    if (!esMensajeValido(pMensaje)) {
      throw new IllegalArgumentException("El mensaje no es válido");
    }
    return invertirString(pMensaje);
  }

  @Override
  public String descifrar(String pMensaje) throws IllegalArgumentException {
    if (!esMensajeCifradoValido(pMensaje)) {
      throw new IllegalArgumentException("El mensaje cifrado no es válido");
    }
    return cifrar(pMensaje);
  }
}
