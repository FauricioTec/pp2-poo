package com.poo.pp2.modelo;

/**
 * Clase abstracta que representa un cifrador
 */
public abstract class Cifrador {

  public Cifrador() {

  }

  /**
   * Metodo que cifra un mensaje
   *
   * @param pMensaje Mensaje a cifrar
   * @return Mensaje cifrado
   * @throws Exception Si el mensaje no es valido
   */
  public abstract String cifrar(String pMensaje) throws Exception;

  /**
   * Metodo que descifra un mensaje
   *
   * @param pMensaje Mensaje a descifrar
   * @return Mensaje descifrado
   * @throws Exception Si el mensaje cifrado no es valido
   */
  public abstract String descifrar(String pMensaje) throws Exception;

  /**
   * Metodo que verifica si un mensaje es valido
   *
   * @param pMensaje Mensaje a verificar
   * @return true si el mensaje es valido, false si no
   */
  protected boolean esMensajeValido(String pMensaje) {
    return pMensaje != null && !pMensaje.isEmpty();
  }

  /**
   * Metodo que verifica si un mensaje cifrado es valido
   *
   * @param pMensaje Mensaje cifrado a verificar
   * @return true si el mensaje cifrado es valido, false si no
   */
  protected boolean esMensajeCifradoValido(String pMensaje) {
    return pMensaje != null && !pMensaje.isEmpty();
  }

}