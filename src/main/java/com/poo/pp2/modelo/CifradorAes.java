package com.poo.pp2.modelo;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class CifradorAes extends Cifrador {

  private final SecretKey llave;

  public CifradorAes(SecretKey pLlave) {
    llave = pLlave;
  }

  public static boolean esLlaveValida(String pLlave) {
    try {
      byte[] llaveBytes = pLlave.getBytes();

      if (llaveBytes.length != 16 && llaveBytes.length != 24 && llaveBytes.length != 32) {
        return false;
      }
      new SecretKeySpec(llaveBytes, "AES");
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public String cifrar(String pMensaje) throws Exception {
    if (!esMensajeValido(pMensaje)) {
      throw new IllegalArgumentException("El mensaje no es válido");
    }
    Cipher cifrador = Cipher.getInstance("AES/ECB/PKCS5Padding");
    byte[] dataInBytes = pMensaje.getBytes();
    cifrador.init(Cipher.ENCRYPT_MODE, llave);
    byte[] encryptedBytes = cifrador.doFinal(dataInBytes);
    return encode(encryptedBytes);
  }

  @Override
  public String descifrar(String pMensaje) throws Exception {
    if (!esMensajeCifradoValido(pMensaje)) {
      throw new IllegalArgumentException("El mensaje cifrado no es válido");
    }
    Cipher cifrador = Cipher.getInstance("AES/ECB/PKCS5Padding");
    cifrador.init(Cipher.DECRYPT_MODE, llave);
    byte[] decryptedBytes = cifrador.doFinal(decode(pMensaje));
    return new String(decryptedBytes);
  }

  private String encode(byte[] data) {
    return Base64.getEncoder().encodeToString(data);
  }

  private byte[] decode(String data) {
    return Base64.getDecoder().decode(data);
  }

  public SecretKey getLlave() {
    return llave;
  }
}
