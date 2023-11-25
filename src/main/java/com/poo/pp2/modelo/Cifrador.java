package com.poo.pp2.modelo;

public abstract class Cifrador {

  public Cifrador() {

  }

  public abstract String cifrar(String pMensaje) throws Exception;

  public abstract String descifrar(String pMensaje) throws Exception;

  protected boolean esMensajeValido(String pMensaje) {
    return pMensaje != null && !pMensaje.isEmpty();
  }

  protected boolean esMensajeCifradoValido(String pMensaje) {
    return pMensaje != null && !pMensaje.isEmpty();
  }

}