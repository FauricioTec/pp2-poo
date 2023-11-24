package com.poo.pp2;

import com.formdev.flatlaf.FlatDarkLaf;
import controlador.ControladorCifrador;
import vista.FormCifrador;

public class Pp2 {

  public static void main(String[] args) {
    FlatDarkLaf.setup();

    FormCifrador vista = new FormCifrador();

    ControladorCifrador controladorCifrador = new ControladorCifrador(vista);

    controladorCifrador.vista.setVisible(true);
    controladorCifrador.vista.setLocationRelativeTo(null);
    controladorCifrador.vista.setResizable(false);
    controladorCifrador.vista.setTitle("Cifrador");
    controladorCifrador.vista.setIconImage(
        new javax.swing.ImageIcon("src\\main\\resources\\images\\icon.png").getImage());
  }

}
