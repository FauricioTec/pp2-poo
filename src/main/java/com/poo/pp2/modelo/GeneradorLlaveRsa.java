package com.poo.pp2.modelo;

import java.math.BigInteger;
import java.util.Random;

/**
 * Clase que representa un generador de llaves RSA
 */
public class GeneradorLlaveRsa {

  /**
   * Llave RSA privada
   */
  private LlaveRsa llaveRsaPrivada;
  /**
   * Llave RSA publica
   */
  private LlaveRsa llaveRsaPublica;

  /**
   * Constructor de la clase
   */
  public GeneradorLlaveRsa() {

  }

  @Override
  public String toString() {
    return "GeneradorLlaveRSA{" + "llaveRsaPublica=" + llaveRsaPublica + ", llaveRsaPrivada="
        + llaveRsaPrivada + '}';
  }

  /**
   * Metodo que genera las llaves RSA
   */
  public void generarClaves() {
    int p = generarNumeroPrimoAleatorio(10, 200);
    int q;
    do {
      q = generarNumeroPrimoAleatorio(10, 200);
    } while (q == p);

    int n = p * q;

    int phiN = funcionEuler(p, q);

    int e = generarExponenteClavePublica(phiN);

    int d = calcularExponenteClavePrivada(e, phiN);

    llaveRsaPrivada = new LlaveRsa(n, d);
    llaveRsaPublica = new LlaveRsa(n, e);
  }

  /**
   * Metodo que calcula la funcion de Euler
   *
   * @param p Primer numero primo
   * @param q Segundo numero primo
   * @return Funcion de Euler
   */
  private int funcionEuler(int p, int q) {
    return (p - 1) * (q - 1);
  }

  /**
   * Metodo que genera un numero primo aleatorio
   *
   * @param pMin Valor minimo del numero primo
   * @param pMax Valor maximo del numero primo
   * @return Numero primo aleatorio
   */
  private int generarNumeroPrimoAleatorio(int pMin, int pMax) {
    Random random = new Random();
    int numero;
    do {
      numero = random.nextInt(pMax - pMin) + pMin;
    } while (!esPrimo(numero));
    return numero;
  }

  /**
   * Metodo que genera un numero aleatorio
   *
   * @param pMin Valor minimo del numero
   * @param pMax Valor maximo del numero
   * @return Numero aleatorio
   */
  private int generarNumeroAleatorio(int pMin, int pMax) {
    Random random = new Random();
    int numero;
    do {
      numero = random.nextInt(pMax - pMin) + pMin;
    } while (numero <= 0);
    return numero;
  }

  /**
   * Metodo que genera el exponente de la clave publica
   *
   * @param phiN Funcion de Euler
   * @return Exponente de la clave publica
   */
  private int generarExponenteClavePublica(int phiN) {
    int e;
    do {
      e = generarNumeroAleatorio(2, phiN);
    } while (calcularMCD(e, phiN) != 1);
    return e;
  }

  /**
   * Metodo que calcula el maximo comun divisor
   *
   * @param a Primer numero
   * @param b Segundo numero
   * @return Maximo comun divisor
   */
  private int calcularMCD(int a, int b) {
    while (b != 0) {
      int temp = b;
      b = a % b;
      a = temp;
    }
    return a;
  }

  /**
   * Metodo que calcula el exponente de la clave privada
   *
   * @param e    Exponente de la clave publica
   * @param phiN Funcion de Euler
   * @return Exponente de la clave privada
   */
  private int calcularExponenteClavePrivada(int e, int phiN) {
    BigInteger bigE = BigInteger.valueOf(e);
    BigInteger bigPhiN = BigInteger.valueOf(phiN);

    BigInteger d = BigInteger.ONE;

    while (!bigE.multiply(d).subtract(BigInteger.ONE).mod(bigPhiN).equals(BigInteger.ZERO)) {
      d = d.add(BigInteger.ONE);
    }

    return d.intValue();
  }

  /**
   * Metodo que verifica si un numero es primo
   *
   * @param pNumero Numero a verificar
   * @return True si el numero es primo, false de lo contrario
   */
  private boolean esPrimo(int pNumero) {
    if (pNumero < 2) {
      return false;
    }
    for (int i = 2; i < (pNumero / 2) + 1; i++) {
      if (pNumero % i == 0) {
        return false;
      }
    }
    return true;
  }

  public LlaveRsa getLlaveRsaPrivada() {
    return llaveRsaPrivada;
  }

  public void setLlaveRsaPrivada(LlaveRsa llaveRsaPrivada) {
    this.llaveRsaPrivada = llaveRsaPrivada;
  }

  public LlaveRsa getLlaveRsaPublica() {
    return llaveRsaPublica;
  }

  public void setLlaveRsaPublica(LlaveRsa llaveRsaPublica) {
    this.llaveRsaPublica = llaveRsaPublica;
  }
}
