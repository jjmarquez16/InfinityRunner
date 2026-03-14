package main;

import model.Modelo;
import view.VentanaPrincipal;
import controller.MotorJuego;

/**
 * Clase principal de la aplicación InfinityRunner.
 * Punto de entrada de la aplicación que inicializa el modelo, la vista y el controlador.
 * 
 * @author Jose Ramon Neira Vega
 * @version 1.0
 */
public class Main {
    /**
     * Método principal que inicia la aplicación.
     * Crea las instancias del modelo, vista y controlador.
     * 
     * @param args argumentos de la línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        Modelo modelo = new Modelo();
        VentanaPrincipal vista = new VentanaPrincipal();
        new MotorJuego(modelo, vista);
    }
}
