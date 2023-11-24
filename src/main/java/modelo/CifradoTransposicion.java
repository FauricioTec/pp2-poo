package modelo;

public abstract class CifradoTransposicion extends Cifrador {

  public String invertirString(String pMensaje) {
    StringBuilder mensajeInvertido = new StringBuilder();
    for (int i = pMensaje.length() - 1; i >= 0; i--) {
      mensajeInvertido.append(pMensaje.charAt(i));
    }
    return mensajeInvertido.toString();
  }

}
