package com.poo.pp2.modelo;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Clase que representa un cifrador AES
 */
public class CifradorAes extends Cifrador {

  /**
   * Llave del cifrador
   */
  private final SecretKey llave;

  /**
   * Constructor que inicializa la llave del cifrador
   *
   * @param pLlave Llave del cifrador
   */
  public CifradorAes(SecretKey pLlave) {
    llave = pLlave;
  }

  /**
   * Metodo que verifica si una llave es valida
   *
   * @param pLlave Llave a verificar
   * @return true si la llave es valida, false si no
   */
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

  /**
   * Metodo que cifra un mensaje
   *
   * @param pMensaje Mensaje a cifrar
   * @return Mensaje cifrado
   * @throws Exception Si el mensaje no es valido
   */
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


  /**
   * Metodo que descifra un mensaje
   *
   * @param pMensaje Mensaje a descifrar
   * @return Mensaje descifrado
   * @throws Exception Si el mensaje cifrado no es valido
   */
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

  /**
   * Metodo que codifica un arreglo de bytes a Base64
   *
   * @param data Arreglo de bytes a codificar
   * @return Arreglo de bytes codificado
   */
  private String encode(byte[] data) {
    return Base64.getEncoder().encodeToString(data);
  }

  /**
   * Metodo que decodifica un arreglo de bytes de Base64
   *
   * @param data Arreglo de bytes a decodificar
   * @return Arreglo de bytes decodificado
   */
  private byte[] decode(String data) {
    return Base64.getDecoder().decode(data);
  }

  public SecretKey getLlave() {
    return llave;
  }
}
