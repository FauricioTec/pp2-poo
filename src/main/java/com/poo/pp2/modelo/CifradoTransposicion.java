package com.poo.pp2.modelo;

/**
 * Clase que representa un cifrador Vigenere
 */
public abstract class CifradoTransposicion extends Cifrador {

  /**
   * Constructor de la clase
   */
  public CifradoTransposicion() {

  }

  /**
   * Metodo que cifra un mensaje
   *
   * @param pMensaje Mensaje a cifrar
   * @return Mensaje cifrado¿
   */
  public String invertirString(String pMensaje) {
    StringBuilder mensajeInvertido = new StringBuilder();
    for (int i = pMensaje.length() - 1; i >= 0; i--) {
      mensajeInvertido.append(pMensaje.charAt(i));
    }
    return mensajeInvertido.toString();
  }

}
