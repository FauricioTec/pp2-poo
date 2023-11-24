package com.poo.pp2.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import com.poo.pp2.modelo.CifradoBinario;
import com.poo.pp2.modelo.CifradoCesar;
import com.poo.pp2.modelo.CifradoMensajeInverso;
import com.poo.pp2.modelo.CifradoPalabraInversa;
import com.poo.pp2.modelo.CifradoPorLlave;
import com.poo.pp2.modelo.CifradoRsa;
import com.poo.pp2.modelo.CifradoTelefonico;
import com.poo.pp2.modelo.CifradoVigenere;
import com.poo.pp2.modelo.Cifrador;
import com.poo.pp2.modelo.GeneradorLlaveRsa;
import com.poo.pp2.modelo.LlaveRsa;
import com.poo.pp2.utilidad.GestorEmail;
import com.poo.pp2.vista.FormCifrador;

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
      case "Enviar email" -> {
        Thread emailThread = new Thread(this::enviarEmail);
        emailThread.start();
      }
      case "Abrir archivo" -> abrirArchivo();
      case "Salir" -> salir();
    }
  }

  public void enviarEmail() {
    String email = vista.emailTextField.getText();
    GestorEmail gestorEmail = new GestorEmail();
    if (!GestorEmail.esEmailValido(email)) {
      JOptionPane.showMessageDialog(vista, "El email no es valido");
      return;
    }
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