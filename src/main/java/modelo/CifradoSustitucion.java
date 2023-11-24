package modelo;

public abstract class CifradoSustitucion extends Cifrador {


  @Override
  public boolean esMensajeValido(String pMensaje) {
    if (pMensaje == null || pMensaje.isEmpty()) {
      return false;
    }
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
    return esMensajeValido(pMensaje);
  }

}
