package modelo;

public abstract class Cifrador {

  public abstract String cifrar(String pMensaje);

  public abstract String descifrar(String pMensaje);

  public boolean esMensajeValido(String pMensaje) {
    return pMensaje != null && !pMensaje.isEmpty();
  }

  public boolean esMensajeCifradoValido(String pMensaje) {
    return pMensaje != null && !pMensaje.isEmpty();
  }

}