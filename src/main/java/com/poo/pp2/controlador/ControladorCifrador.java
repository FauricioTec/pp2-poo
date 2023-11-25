package com.poo.pp2.controlador;

import com.poo.pp2.modelo.CifradorBinario;
import com.poo.pp2.modelo.CifradorCesar;
import com.poo.pp2.modelo.CifradorMensajeInverso;
import com.poo.pp2.modelo.CifradorPalabraInversa;
import com.poo.pp2.modelo.CifradorPorLlave;
import com.poo.pp2.modelo.CifradorRsa;
import com.poo.pp2.modelo.CifradorTelefonico;
import com.poo.pp2.modelo.CifradorTripleDes;
import com.poo.pp2.modelo.CifradorVigenere;
import com.poo.pp2.modelo.Cifrador;
import com.poo.pp2.modelo.GeneradorLlaveRsa;
import com.poo.pp2.modelo.LlaveRsa;
import com.poo.pp2.utilidad.GestorEmail;
import com.poo.pp2.vista.FormCifrador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.crypto.SecretKey;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

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
      case "Aplicar algoritmo" -> {
        Thread aplicarAlgoritmoThread = new Thread(this::aplicarAlgoritmo);
        aplicarAlgoritmoThread.start();
      }
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
      String cuerpo = "<html><body><p style='font-family: Arial, sans-serif; font-size: 14px;'>"
          + "Este es el resultado de aplicar el algoritmo:<br><br>" + salida + "</p></body></html>";
      gestorEmail.enviarEmail(email, "Resultado de aplicar el algoritmo", cuerpo);
      JOptionPane.showMessageDialog(vista, "Email enviado");
    } catch (Exception e) {
      JOptionPane.showMessageDialog(vista, "Error al enviar el email");
    }
  }

  public void abrirArchivo() {
    JFileChooser fileChooser = new JFileChooser();

    FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
    fileChooser.setFileFilter(filter);

    int opcion = fileChooser.showOpenDialog(vista);

    if (opcion == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      try {
        String fileContent = leerArchivo(selectedFile);
        vista.entradaTextArea.setText(fileContent);
      } catch (Exception ex) {
        JOptionPane.showMessageDialog(vista, "Error al leer el archivo");
      }
    }
  }

  private String leerArchivo(File pFile) throws Exception {
    StringBuilder fileContent = new StringBuilder();
    java.util.Scanner scanner = new java.util.Scanner(pFile);
    while (scanner.hasNextLine()) {
      fileContent.append(scanner.nextLine()).append("\n");
    }
    scanner.close();
    return fileContent.toString();
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
      JOptionPane.showMessageDialog(vista, "No ha sido posible aplicar el algoritmo");
      return;
    }

    vista.salidaTextArea.setText("");
    if (vista.tipoOperacionComboBox.getSelectedIndex() == 0) {
      cifrarMensaje(cifrador);
    } else {
      descifrarMensaje(cifrador);
    }
  }

  private Cifrador obtenerCifradorSeleccionado() {
    int index = vista.tipoCifradoComboBox.getSelectedIndex();

    return switch (index) {
      case 0 -> new CifradorCesar();
      case 1 -> obtenerCifradorPorLlave();
      case 2 -> obtenerCifradorVigenere();
      case 3 -> new CifradorPalabraInversa();
      case 4 -> new CifradorMensajeInverso();
      case 5 -> new CifradorTelefonico();
      case 6 -> new CifradorBinario();
      case 7 -> obtenerCifradorRsa();
      case 8 -> obtenerCifradorTripleDes();
      case 9 -> new CifradorCesar(); //TODO: Cifrado de AES
      default -> null;
    };
  }

  private Cifrador obtenerCifradorPorLlave() {
    String llave = vista.llaveTextField.getText();
    if (CifradorPorLlave.esLlaveValida(llave)) {
      return new CifradorPorLlave(llave);
    } else if (llave.isEmpty()) {
      JOptionPane.showMessageDialog(vista, "Requiere ingresar una llave");
      return null;
    } else {
      JOptionPane.showMessageDialog(vista, "La llave no es válida");
      return null;
    }
  }

  private Cifrador obtenerCifradorVigenere() {
    String cifra = vista.llaveTextField.getText();
    if (CifradorVigenere.esCifraValida(cifra)) {
      return new CifradorVigenere(cifra);
    } else if (cifra.isEmpty()) {
      JOptionPane.showMessageDialog(vista, "Requiere ingresar una cifra como llave");
      return null;
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
      return new CifradorRsa(generadorLlaveRsa.getLlaveRsaPublica());
    }

    if (LlaveRsa.esLlaveRsaValida(llaveTexto)) {
      return new CifradorRsa(LlaveRsa.toLlaveRsa(llaveTexto));
    } else if (llaveTexto.isEmpty()) {
      JOptionPane.showMessageDialog(vista, "Requiere ingresar una llave");
      return null;
    } else {
      JOptionPane.showMessageDialog(vista, "La llave no es válida");
      return null;
    }
  }

  private Cifrador obtenerCifradorTripleDes() {
    String llave = vista.llaveTextField.getText();

    if (CifradorTripleDes.esLlaveValida(llave)) {
      SecretKey llaveTripleDes = new javax.crypto.spec.SecretKeySpec(llave.getBytes(), "DESede");
      return new CifradorTripleDes(llaveTripleDes);
    } else if (llave.isEmpty()) {
      JOptionPane.showMessageDialog(vista, "Requiere ingresar una llave");
      return null;
    } else {
      JOptionPane.showMessageDialog(vista, "La llave no es válida");
      return null;
    }

  }

  private void cifrarMensaje(Cifrador cifrador) {
    String mensaje = vista.entradaTextArea.getText();
    if (mensaje.isEmpty()) {
      JOptionPane.showMessageDialog(vista,
          "No ha sido posible cifrar el mensaje\n" + "El mensaje no es válido");
      return;
    }
    try {
      String mensajeCifrado = cifrador.cifrar(mensaje);
      vista.salidaTextArea.setText(mensajeCifrado);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(vista,
          "No ha sido posible cifrar el mensaje\n" + e.getMessage());
    }
  }

  private void descifrarMensaje(Cifrador cifrador) {
    String mensajeCifrado = vista.entradaTextArea.getText();
    if (mensajeCifrado.isEmpty()) {
      JOptionPane.showMessageDialog(vista, "No ha sido posible descifrar el mensaje\n"
          + "El mensaje cifrado no puede ser nulo o vacío");
      return;
    }
    try {
      String mensajeDescifrado = cifrador.descifrar(mensajeCifrado);
      vista.salidaTextArea.setText(mensajeDescifrado);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(vista,
          "No ha sido posible descifrar el mensaje\n" + e.getMessage());
    }
  }

}