package main;

import model.Modelo;
import view.VentanaPrincipal;
import controller.MotorJuego;

public class Main {
    public static void main(String[] args) {
        Modelo modelo = new Modelo();
        VentanaPrincipal vista = new VentanaPrincipal();
        new MotorJuego(modelo, vista);
    }
}
