package com.poo.pp2.modelo;

public class LlaveRsa {

  private int modulo;
  private int exponente;

  public LlaveRsa(int pModulo, int pExponente) {
    modulo = pModulo;
    exponente = pExponente;
  }

  @Override
  public String toString() {
    return modulo + "-" + exponente;
  }

  public static LlaveRsa toLlaveRsa(String llaveRsaPublica) {
    String[] partes = llaveRsaPublica.split("-");
    int n = Integer.parseInt(partes[0]);
    int e = Integer.parseInt(partes[1]);
    return new LlaveRsa(n, e);
  }

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
