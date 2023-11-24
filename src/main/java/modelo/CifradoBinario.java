package modelo;

public class CifradoBinario extends Cifrador {

  @Override
  public String cifrar(String pMensaje) {
    String mensaje = pMensaje.toLowerCase();
    StringBuilder mensajeCifrado = new StringBuilder();

    for (int i = 0; i < mensaje.length(); i++) {
      if (mensaje.charAt(i) == ' ') {
        mensajeCifrado.append("*");
      } else {
        String binario = Integer.toBinaryString(mensaje.charAt(i) - 'a');
        while (binario.length() < 5) {
          binario = "0" + binario;
        }
        mensajeCifrado.append(binario);
      }
      if (i != mensaje.length() - 1) {
        mensajeCifrado.append(" ");
      }
    }

    return mensajeCifrado.toString();
  }

  @Override
  public String descifrar(String pMensaje) {
    StringBuilder mensajeDescifrado = new StringBuilder();

    String[] mensaje = pMensaje.split(" ");
    for (String palabra : mensaje) {
      if (palabra.equals("*")) {
        mensajeDescifrado.append(" ");
      } else {
        mensajeDescifrado.append((char) (Integer.parseInt(palabra, 2) + 'a'));
      }
    }

    return mensajeDescifrado.toString();
  }

  @Override
  public boolean esMensajeValido(String pMensaje) {
    for (char caracter : pMensaje.toCharArray()) {
      if (!esCaracterValido(caracter)) {
        return false;
      }
    }
    return true;
  }

  private boolean esCaracterValido(char pCaracter) {
    return (pCaracter >= 'a' && pCaracter <= 'z') || (pCaracter >= 'A' && pCaracter <= 'Z')
        || pCaracter == ' ';
  }

  @Override
  public boolean esMensajeCifradoValido(String pMensaje) {
    String[] mensaje = pMensaje.split(" ");
    for (String palabra : mensaje) {
      if (!esCifradoValido(palabra)) {
        return false;
      }
    }
    return true;
  }

  private boolean esCifradoValido(String pCifrado) {
    if (pCifrado.equals("*")) {
      return true;
    }
    if (pCifrado.length() != 5) {
      return false;
    }
    try {
      Integer.parseInt(pCifrado);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

}
