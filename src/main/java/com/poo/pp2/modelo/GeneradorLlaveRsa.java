package com.poo.pp2.modelo;

import java.math.BigInteger;
import java.util.Random;

public class GeneradorLlaveRsa {

  private LlaveRsa llaveRsaPrivada;
  private LlaveRsa llaveRsaPublica;

  public GeneradorLlaveRsa() {
    generarClaves();
  }

  @Override
  public String toString() {
    return "GeneradorLlaveRSA{" +
        "llaveRsaPublica=" + llaveRsaPublica +
        ", llaveRsaPrivada=" + llaveRsaPrivada +
        '}';
  }

  private void generarClaves() {
    int p = generarNumeroPrimoAleatorio(10, 1000);
    int q;
    do {
      q = generarNumeroPrimoAleatorio(10, 1000);
    } while (q == p);

    int n = p * q;

    int phiN = funcionEuler(p, q);

    int e = generarExponenteClavePublica(phiN);

    int d = calcularExponenteClavePrivada(e, phiN);

    llaveRsaPrivada = new LlaveRsa(n, d);
    llaveRsaPublica = new LlaveRsa(n, e);
  }

  private int funcionEuler(int p, int q) {
    return (p - 1) * (q - 1);
  }

  private int generarNumeroPrimoAleatorio(int pMin, int pMax) {
    Random random = new Random();
    int numero;
    do {
      numero = random.nextInt(pMax - pMin) + pMin;
    } while (!esPrimo(numero));
    return numero;
  }


  private int generarNumeroAleatorio(int pMin, int pMax) {
    Random random = new Random();
    int numero;
    do {
      numero = random.nextInt(pMax - pMin) + pMin;
    } while (numero <= 0);
    return numero;
  }

  private int generarExponenteClavePublica(int phiN) {
    int e;
    do {
      e = generarNumeroAleatorio(2, phiN);
    } while (calcularMCD(e, phiN) != 1);
    return e;
  }

  private int calcularMCD(int a, int b) {
    while (b != 0) {
      int temp = b;
      b = a % b;
      a = temp;
    }
    return a;
  }

  private int calcularExponenteClavePrivada(int e, int phiN) {
    BigInteger bigE = BigInteger.valueOf(e);
    BigInteger bigPhiN = BigInteger.valueOf(phiN);

    BigInteger d = BigInteger.ONE;

    while (!bigE.multiply(d).subtract(BigInteger.ONE).mod(bigPhiN).equals(BigInteger.ZERO)) {
      d = d.add(BigInteger.ONE);
    }

    return d.intValue();
  }

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
