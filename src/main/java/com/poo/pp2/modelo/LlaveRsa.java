package com.poo.pp2.modelo;

/**
 * Clase que representa un cifrador Vigenere
 */
public class LlaveRsa {

  /**
   * Modulo de la llave
   */
  private int modulo;
  /**
   * Exponente de la llave
   */
  private int exponente;

  /**
   * Constructor de la clase
   *
   * @param pModulo    Modulo de la llave
   * @param pExponente Exponente de la llave
   */
  public LlaveRsa(int pModulo, int pExponente) {
    modulo = pModulo;
    exponente = pExponente;
  }

  /**
   * Metodo que convierte una llave RSA a un string
   *
   * @param llaveRsaPublica Llave RSA a convertir
   * @return String de la llave RSA
   */
  public static LlaveRsa toLlaveRsa(String llaveRsaPublica) {
    String[] partes = llaveRsaPublica.split("-");
    int n = Integer.parseInt(partes[0]);
    int e = Integer.parseInt(partes[1]);
    return new LlaveRsa(n, e);
  }

  /**
   * Metodo que verifica si una llave RSA es valida
   *
   * @param llaveRsaPublica Llave RSA a verificar
   * @return True si la llave RSA es valida, false de lo contrario
   */
  public static boolean esLlaveRsaValida(String llaveRsaPublica) {
    String[] partes = llaveRsaPublica.split("-");
    if (partes.length != 2) {
      return false;
    }
    try {
      Integer.parseInt(partes[0]);
      Integer.parseInt(partes[1]);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return modulo + "-" + exponente;
  }

  public int getModulo() {
    return modulo;
  }

  public void setModulo(int modulo) {
    this.modulo = modulo;
  }

  public int getExponente() {
    return exponente;
  }

  public void setExponente(int exponente) {
    this.exponente = exponente;
  }
}
