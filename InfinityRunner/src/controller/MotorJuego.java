package controller;

import javax.swing.*;
import java.awt.event.*;

public class MotorJuego implements ActionListener, KeyListener {
    private Modelo modelo;
    private VistaPrincipal vista;
    private Timer timer;

    public MotorJuego(Modelo modelo, VistaPrincipal vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.timer = new Timer(20, this);

        // Listeners
        this.vista.panelMenu.btnJugar.addActionListener(e -> iniciarJuego());
        this.vista.addKeyListener(this);
        this.vista.setFocusable(true);
    }

    private void iniciarJuego() {
        modelo.nombreJugador = vista.panelMenu.txtNombre.getText();
        if (modelo.nombreJugador.isEmpty())
            modelo.nombreJugador = "Jugador 1";
        modelo.reiniciar();
        vista.tarjetas.show(vista.contenedor, "JUEGO");
        timer.start();
        vista.requestFocusInWindow();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!modelo.enJuego)
            return;

        // Lógica de movimiento
        modelo.obstaculoX -= 8;
        if (modelo.obstaculoX < -20) {
            modelo.obstaculoX = 800;
            modelo.puntuacion++;
        }

        // Gravedad simple
        if (modelo.personajeY < 300)
            modelo.personajeY += 5;

        // Colisión
        if (modelo.obstaculoX < 90 && modelo.obstaculoX > 50 && modelo.personajeY > 270) {
            finalizarJuego();
        }

        vista.panelJuego.actualizar(modelo);
    }

    private void finalizarJuego() {
        timer.stop();
        modelo.enJuego = false;
        JOptionPane.showMessageDialog(vista, "¡Game Over, " + modelo.nombreJugador + "!\nPuntos: " + modelo.puntuacion);
        vista.tarjetas.show(vista.contenedor, "MENU");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && modelo.personajeY >= 300) {
            modelo.personajeY -= 100; // Salto instantáneo
        }
    }

    // Resto de métodos KeyListener vacíos...
    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }
}