package modelo;

public class CifradoCesar extends CifradoSustitucion {

  private static final int DESPLAZAMIENTO = 3;

  public String cifrar(String pMensaje) {
    String mensaje = pMensaje.toUpperCase();
    StringBuilder mensajeCifrado = new StringBuilder();
    for (int i = 0; i < mensaje.length(); i++) {
      char caracter = mensaje.charAt(i);
      if (caracter == ' ') {
        mensajeCifrado.append(' ');
      } else {
        int asciiCifrado = (caracter - 'A' + DESPLAZAMIENTO) % 26 + 'A';
        mensajeCifrado.append((char) asciiCifrado);
      }
    }
    return mensajeCifrado.toString();
  }

  public String descifrar(String pMensaje) {
    String mensaje = pMensaje.toUpperCase();
    StringBuilder mensajeDescifrado = new StringBuilder();
    for (int i = 0; i < mensaje.length(); i++) {
      char caracter = mensaje.charAt(i);
      if (caracter == ' ') {
        mensajeDescifrado.append(' ');
      } else {
        int asciiDescifrado = (caracter - 'A' - DESPLAZAMIENTO + 26) % 26 + 'A';
        mensajeDescifrado.append((char) asciiDescifrado);
      }
    }
    return mensajeDescifrado.toString();
  }
}
