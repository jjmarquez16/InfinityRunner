package controller;

import javax.swing.*;
import java.awt.event.*;
import model.Modelo;
import view.VentanaPrincipal;

/**
 * Controlador principal del juego InfinityRunner.
 * Maneja la lógica de juego, gestión de eventos de teclado, colisiones y actualización del estado.
 * Implementa el patrón MVC (Modelo-Vista-Controlador).
 * 
 * @author Equipo de Desarrollo
 * @version 1.0
 */
public class MotorJuego implements ActionListener, KeyListener {
    /** Referencia al modelo de datos del juego */
    private Modelo modelo;
    /** Referencia a la ventana principal de la aplicación */
    private VentanaPrincipal vista;
    /** Timer para controlar el ciclo de juego */
    private Timer timer;

    /**
     * Constructor del MotorJuego.
     * Inicializa el modelo, la vista y configura los listeners de eventos.
     * 
     * @param modelo el modelo de datos del juego
     * @param vista la ventana principal
     */
    public MotorJuego(Modelo modelo, VentanaPrincipal vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.timer = new Timer(20, this);

        // Listeners
        this.vista.panelMenu.btnJugar.addActionListener(e -> iniciarJuego());
        this.vista.addKeyListener(this);
        this.vista.setFocusable(true);
    }

    /**
     * Inicia una nueva partida del juego.
     * Obtiene el nombre del jugador, reinicia el modelo y muestra el panel de juego.
     */
    private void iniciarJuego() {
        modelo.nombreJugador = vista.panelMenu.txtNombre.getText();
        if (modelo.nombreJugador.isEmpty())
            modelo.nombreJugador = "Jugador 1";
        modelo.reiniciar();
        vista.tarjetas.show(vista.contenedor, "JUEGO");
        timer.start();
        vista.requestFocusInWindow();
    }

    /**
     * Actualiza la lógica del juego en cada ciclo del timer.
     * Maneja el movimiento de obstáculos, gravedad, colisiones y puntuación.
     * 
     * @param e el evento de acción del timer
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!modelo.enJuego)
            return;

        // Lógica de movimiento
        modelo.obstaculoX -= modelo.velocidadObstaculo;
        if (modelo.obstaculoX < -20) {
            modelo.obstaculoX = 800;
            modelo.puntuacion++;
            
            // Aumentar velocidad cada 3 obstáculos
            if (modelo.puntuacion % 3 == 0) {
                modelo.velocidadObstaculo++;
            }
        }

        // Gravedad simple
        if (modelo.personajeY < 300)
            modelo.personajeY += 5;

        // Colisión
        if (modelo.obstaculoX < 90 && modelo.obstaculoX > 50 && (modelo.personajeY + 50) > 300) {
            finalizarJuego();
        }

        vista.panelJuego.actualizar(modelo);
    }

    /**
     * Finaliza la partida actual.
     * Detiene el timer, muestra un cuadro de diálogo con la puntuación y vuelve al menú.
     */
    private void finalizarJuego() {
        timer.stop();
        modelo.enJuego = false;
        JOptionPane.showMessageDialog(vista, "¡Game Over, " + modelo.nombreJugador + "!\nPuntos: " + modelo.puntuacion);
        vista.tarjetas.show(vista.contenedor, "MENU");
    }

    /**
     * Maneja el evento de tecla presionada.
     * Permite que el jugador salte cuando presiona la barra espaciadora.
     * 
     * @param e el evento de teclado
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && modelo.personajeY >= 300) {
            modelo.personajeY -= 150; // Salto
        }
    }

    /**
     * Maneja el evento de tecla liberada.
     * 
     * @param e el evento de teclado
     */
    @Override
    public void keyReleased(KeyEvent e) {}

    /**
     * Maneja el evento de tecla tiprada.
     * 
     * @param e el evento de teclado
     */
    @Override
    public void keyTyped(KeyEvent e) {}
}