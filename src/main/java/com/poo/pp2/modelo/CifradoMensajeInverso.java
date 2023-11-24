package com.poo.pp2.modelo;

public class CifradoMensajeInverso extends CifradoTransposicion {

  @Override
  public String cifrar(String pMensaje) {
    return invertirString(pMensaje);
  }

  @Override
  public String descifrar(String pMensaje) {
    return cifrar(pMensaje);
  }
}
