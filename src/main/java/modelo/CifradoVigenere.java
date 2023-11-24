package modelo;

public class CifradoVigenere extends CifradoSustitucion {

  private int cifra;

  public CifradoVigenere(String pCifra) {
    cifra = Integer.parseInt(pCifra);
  }

  public static boolean esCifraValida(String pCifra) {
    if (pCifra == null || pCifra.length() != 2) {
      return false;
    }
    try {
      Integer.parseInt(pCifra);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  @Override
  public String cifrar(String pMensaje) {
    String mensaje = pMensaje.toLowerCase();
    StringBuilder mensajeCifrado = new StringBuilder();
    int j = 0;
    for (char caracter : mensaje.toCharArray()) {
      if (caracter == ' ') {
        mensajeCifrado.append(' ');
        j = 0;
      } else {
        int digito = j++ % 2 == 0 ? cifra / 10 : cifra % 10;
        int asciiCifrado = (caracter - 'a' + digito) % 26 + 'a';
        mensajeCifrado.append((char) asciiCifrado);
        j = j % 2;
      }
    }
    return mensajeCifrado.toString();
  }

  @Override
  public String descifrar(String pMensaje) {
    String mensaje = pMensaje.toLowerCase();
    StringBuilder mensajeDescifrado = new StringBuilder();
    int j = 0;
    for (char caracter : mensaje.toCharArray()) {
      if (caracter == ' ') {
        mensajeDescifrado.append(' ');
        j = 0;
      } else {
        int digito = j++ % 2 == 0 ? cifra / 10 : cifra % 10;
        int asciiDescifrado = (caracter - 'a' - digito + 26) % 26 + 'a';
        mensajeDescifrado.append((char) asciiDescifrado);
        j = j % 2;
      }
    }
    return mensajeDescifrado.toString();
  }

  public int getCifra() {
    return cifra;
  }

  public void setCifra(int cifra) {
    this.cifra = cifra;
  }
}
