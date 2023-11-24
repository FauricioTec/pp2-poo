package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.CifradoBinario;
import modelo.CifradoCesar;
import modelo.CifradoMensajeInverso;
import modelo.CifradoPalabraInversa;
import modelo.CifradoPorLlave;
import modelo.CifradoRsa;
import modelo.CifradoTelefonico;
import modelo.CifradoVigenere;
import modelo.Cifrador;
import modelo.GeneradorLlaveRsa;
import modelo.LlaveRsa;
import utilidad.GestorEmail;
import vista.FormCifrador;

public class ControladorCifrador implements ActionListener {

  public FormCifrador vista;

  public ControladorCifrador(FormCifrador pVista) {
    vista = pVista;

    vista.aplicarAlgoritmoButton.addActionListener(this);
    vista.enviarEmailButton.addActionListener(this);
    vista.abrirArchivoButton.addActionListener(this);
    vista.salirButton.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Aplicar algoritmo" -> aplicarAlgoritmo();
      case "Enviar email" -> enviarEmail();
      case "Abrir archivo" -> abrirArchivo();
      case "Salir" -> salir();
    }
  }

  public void enviarEmail() {
    String email = vista.emailTextField.getText();
    GestorEmail gestorEmail = new GestorEmail();
    if (GestorEmail.validarEmail(email)) {
      try {
        String salida = vista.salidaTextArea.getText();
        if (salida.isEmpty()) {
          JOptionPane.showMessageDialog(vista, "No hay mensaje para enviar");
          return;
        }
        String cuerpo = "Este es el resultado de aplicar el algoritmo: " + salida;
        gestorEmail.enviarEmail(email, "Resultado de aplicar el algoritmo", cuerpo);
        JOptionPane.showMessageDialog(vista, "Email enviado");
      } catch (Exception e) {
        JOptionPane.showMessageDialog(vista, "Error al enviar el email");
      }
    } else {
      JOptionPane.showMessageDialog(vista, "El email no es valido");
    }
  }

  public void abrirArchivo() {

  }

  public void salir() {
    int opcion = JOptionPane.showConfirmDialog(vista, "¿Desea salir?", "Salir",
        JOptionPane.YES_NO_OPTION);
    if (opcion == JOptionPane.YES_OPTION) {
      System.exit(0);
    }
  }

  public void aplicarAlgoritmo() {
    Cifrador cifrador = obtenerCifradorSeleccionado();

    if (cifrador == null) {
      JOptionPane.showMessageDialog(vista, "No se ha podido aplicar el algoritmo");
      return;
    }

    if (vista.tipoOperacionComboBox.getSelectedIndex() == 0) {
      cifrarMensaje(cifrador);
    } else {
      descifrarMensaje(cifrador);
    }
  }

  private Cifrador obtenerCifradorSeleccionado() {
    int index = vista.tipoCifradoComboBox.getSelectedIndex();

    return switch (index) {
      case 0 -> new CifradoCesar();
      case 1 -> obtenerCifradorPorLlave();
      case 2 -> obtenerCifradorVigenere();
      case 3 -> new CifradoPalabraInversa();
      case 4 -> new CifradoMensajeInverso();
      case 5 -> new CifradoTelefonico();
      case 6 -> new CifradoBinario();
      case 7 -> obtenerCifradorRsa();
      case 8 -> new CifradoCesar(); //TODO: Cifrado de DES
      case 9 -> new CifradoCesar(); //TODO: Cifrado de AES
      default -> null;
    };
  }

  private Cifrador obtenerCifradorPorLlave() {
    String llave = vista.llaveTextField.getText();
    if (CifradoPorLlave.esLlaveValida(llave)) {
      return new CifradoPorLlave(llave);
    } else {
      JOptionPane.showMessageDialog(vista, "La llave no es válida");
      return null;
    }
  }

  private Cifrador obtenerCifradorVigenere() {
    String cifra = vista.llaveTextField.getText();
    if (CifradoVigenere.esCifraValida(cifra)) {
      return new CifradoVigenere(cifra);
    } else {
      JOptionPane.showMessageDialog(vista, "La cifra no es válida");
      return null;
    }
  }

  private Cifrador obtenerCifradorRsa() {
    String llaveTexto = vista.llaveTextField.getText().trim();

    if (vista.tipoOperacionComboBox.getSelectedIndex() == 0 && llaveTexto.isEmpty()) {
      GeneradorLlaveRsa generadorLlaveRsa = new GeneradorLlaveRsa();
      JOptionPane.showMessageDialog(vista,
          "Su llave pública es: " + generadorLlaveRsa.getLlaveRsaPublica() + "\n"
              + "Su llave privada es: " + generadorLlaveRsa.getLlaveRsaPrivada());
      return new CifradoRsa(generadorLlaveRsa.getLlaveRsaPublica());
    }

    if (LlaveRsa.esLlaveRsaValida(llaveTexto)) {
      return new CifradoRsa(LlaveRsa.toLlaveRsa(llaveTexto));
    } else {
      JOptionPane.showMessageDialog(vista, "La llave no es válida");
      return null;
    }
  }

  private void cifrarMensaje(Cifrador cifrador) {
    String mensaje = vista.entradaTextArea.getText();
    if (cifrador != null && cifrador.esMensajeValido(mensaje)) {
      String mensajeCifrado = cifrador.cifrar(mensaje);
      vista.salidaTextArea.setText(mensajeCifrado);
    } else {
      JOptionPane.showMessageDialog(vista, "El mensaje no es válido");
    }
  }

  private void descifrarMensaje(Cifrador cifrador) {
    String mensajeCifrado = vista.entradaTextArea.getText();
    if (cifrador != null && cifrador.esMensajeCifradoValido(mensajeCifrado)) {
      String mensaje = cifrador.descifrar(mensajeCifrado);
      vista.salidaTextArea.setText(mensaje);
    } else {
      JOptionPane.showMessageDialog(vista, "El mensaje cifrado no es válido");
    }
  }

}