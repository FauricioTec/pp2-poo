package com.poo.pp2.modelo;

import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class CifradorModerno extends Cifrador{

  private final SecretKey llave;
  private final Cipher cifrador;

  public CifradorModerno(SecretKey pLlave, String pAlgoritmo) throws Exception {
    if (!pAlgoritmo.equals("AES") && !pAlgoritmo.equals("DESede")) {
      throw new NoSuchAlgorithmException("El algoritmo no es válido");
    }
    if (esLlaveValida(pLlave.toString(), pAlgoritmo)) {
      throw new IllegalArgumentException("La llave no puede ser nula");
    }
    llave = pLlave;
    cifrador = Cipher.getInstance(pAlgoritmo);
  }

  public static boolean esLlaveValida(String pLlave, String algoritmo) {
    try {
      byte[] llaveBytes = pLlave.getBytes();

      if (algoritmo.equals("AES") && (llaveBytes.length != 16 && llaveBytes.length != 24 && llaveBytes.length != 32)) {
        return false;
      } else if (algoritmo.equals("DESede") && llaveBytes.length != 24) {
        return false;
      }

      new SecretKeySpec(llaveBytes, algoritmo);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public String cifrar(String pMensaje) throws Exception {
    return null;
  }

  @Override
  public String descifrar(String pMensaje) throws Exception {
    return null;
  }
}
