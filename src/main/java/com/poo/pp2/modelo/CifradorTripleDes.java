package com.poo.pp2.modelo;

import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class CifradorTripleDes extends Cifrador {

  private final SecretKey llave;

  public CifradorTripleDes(SecretKey pLlave) {
    llave = pLlave;
  }

  public static boolean esLlaveValida(String pLlave) {
    try {
      byte[] llaveBytes = pLlave.getBytes();

      if (llaveBytes.length != 24) {
        return false;
      }

      KeySpec keySpec = new DESedeKeySpec(llaveBytes);
      SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DESede");
      secretKeyFactory.generateSecret(keySpec);

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
    Cipher cifrador = Cipher.getInstance("DESede/CBC/PKCS5Padding");
    cifrador.init(Cipher.ENCRYPT_MODE, llave, new IvParameterSpec(new byte[8]));
    byte[] mensajeCifrado = cifrador.doFinal(pMensaje.getBytes());
    return encode(mensajeCifrado);
  }

  @Override
  public String descifrar(String pMensaje) throws Exception {
    if (!esMensajeCifradoValido(pMensaje)) {
      throw new IllegalArgumentException("El mensaje cifrado no es válido");
    }
    Cipher cifrador = Cipher.getInstance("DESede/CBC/PKCS5Padding");
    cifrador.init(Cipher.DECRYPT_MODE, llave, new IvParameterSpec(new byte[8]));
    byte[] mensajeDescifrado = cifrador.doFinal(Base64.getDecoder().decode(pMensaje));
    return new String(mensajeDescifrado);
  }

  private String encode(byte[] data) {
    return Base64.getEncoder().encodeToString(data);
  }

  public SecretKey getLlave() {
    return llave;
  }
}
