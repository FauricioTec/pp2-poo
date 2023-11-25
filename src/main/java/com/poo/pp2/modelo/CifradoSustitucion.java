package com.poo.pp2.modelo;

/**
 * Clase que representa un cifrador Vigenere
 */
public abstract class CifradoSustitucion extends Cifrador {

  /**
   * Constructor de la clase
   */
  public CifradoSustitucion() {

  }

  /**
   * Metodo que cifra un mensaje
   *
   * @param pMensaje Mensaje a cifrar
   * @return Mensaje cifrado¿
   */
  @Override
  protected boolean esMensajeValido(String pMensaje) {
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

  /**
   * Metodo que verifica si un caracter es valido
   *
   * @param pCaracter Caracter a verificar
   * @return True si el caracter es valido, false de lo contrario
   */
  private boolean esCaracterValido(char pCaracter) {
    return (pCaracter >= 'a' && pCaracter <= 'z') || (pCaracter >= 'A' && pCaracter <= 'Z')
        || pCaracter == ' ';
  }

  /**
   * Metodo que verifica si un mensaje cifrado es valido
   *
   * @param pMensaje Mensaje cifrado a verificar
   * @return True si el mensaje cifrado es valido, false de lo contrario
   */
  @Override
  protected boolean esMensajeCifradoValido(String pMensaje) {
    return esMensajeValido(pMensaje);
  }

}
