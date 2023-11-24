package modelo;

import java.math.BigInteger;

public class CifradoRsa extends Cifrador{

  private LlaveRsa llave;

  public CifradoRsa(LlaveRsa pLlave) {
    llave = pLlave;
  }

  @Override
  public String cifrar(String pMensaje) {
    StringBuilder mensajeCifrado = new StringBuilder();
    for (int i = 0; i < pMensaje.length(); i++) {
      int caracter = pMensaje.charAt(i);
      BigInteger asciiCifrado = BigInteger.valueOf(caracter).pow(llave.getExponente()).mod(BigInteger.valueOf(llave.getModulo()));
      mensajeCifrado.append(asciiCifrado);
      if (i != pMensaje.length() - 1) {
        mensajeCifrado.append("*");
      }
    }
    return mensajeCifrado.toString();
  }

  @Override
  public String descifrar(String pMensaje) {
    StringBuilder mensajeDescifrado = new StringBuilder();
    String[] partes = pMensaje.split("\\*");
    for (String parte : partes) {
      BigInteger asciiCifrado = new BigInteger(parte);
      int asciiDescifrado = asciiCifrado.pow(llave.getExponente()).mod(BigInteger.valueOf(llave.getModulo())).intValue();
      mensajeDescifrado.append((char) asciiDescifrado);
    }
    return mensajeDescifrado.toString();
  }

  @Override
  public boolean esMensajeCifradoValido(String pMensaje) {
    String[] partes = pMensaje.split("\\*");
    for (String parte : partes) {
      try {
        Integer.parseInt(parte);
      } catch (NumberFormatException e) {
        return false;
      }
    }
    return true;
  }

  public LlaveRsa getLlave() {
    return llave;
  }

  public void setLlave(LlaveRsa llave) {
    this.llave = llave;
  }
}
