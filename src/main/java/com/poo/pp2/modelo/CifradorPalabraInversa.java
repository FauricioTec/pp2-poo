package com.poo.pp2.modelo;

/**
 * Clase que representa un cifrador binario
 */
public class CifradorPalabraInversa extends CifradoTransposicion {

  /**
   * Constructor de la clase
   */
  public CifradorPalabraInversa() {

  }

  /**
   * Metodo que cifra un mensaje
   *
   * @param pMensaje Mensaje a cifrar
   * @return Mensaje cifrado
   * @throws Exception Si ocurre un error al cifrar el mensaje
   */
  @Override
  public String cifrar(String pMensaje) throws Exception {
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

  /**
   * Metodo que descifra un mensaje
   *
   * @param pMensaje Mensaje a descifrar
   * @return Mensaje descifrado
   * @throws Exception Si ocurre un error al descifrar el mensaje
   */
  @Override
  public String descifrar(String pMensaje) throws Exception {
    if (!esMensajeCifradoValido(pMensaje)) {
      throw new IllegalArgumentException("El mensaje cifrado no es válido");
    }
    return cifrar(pMensaje);
  }
}
