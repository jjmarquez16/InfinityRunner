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
    /** Indica si el juego está en progreso */
    private boolean juegoEnProgreso = false;

    public MotorJuego(Modelo modelo, VentanaPrincipal vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.timer = new Timer(20, this);

        // Configurar listeners
        configurarListeners();
    }

    /**
     * Configura todos los listeners de eventos de la aplicación.
     */
    private void configurarListeners() {
        // Botón para iniciar juego
        this.vista.panelMenu.btnJugar.addActionListener(e -> iniciarJuego());
        
        // Botones de resultados
        this.vista.panelResultados.btnReiniciar.addActionListener(e -> reiniciarJuego());
        this.vista.panelResultados.btnVolverAlMenu.addActionListener(e -> volverAlMenu());
        
        // Listeners de teclado
        this.vista.addKeyListener(this);
        this.vista.setFocusable(true);
    }

    /**
     * Inicia una nueva partida del juego.
     * Obtiene el nombre del jugador, reinicia el modelo y muestra el panel de juego.
     */
    private void iniciarJuego() {
        String nombre = vista.panelMenu.txtNombre.getText().trim();
        modelo.setNombreJugador(nombre.isEmpty() ? "Jugador" : nombre);
        
        modelo.reiniciar();
        juegoEnProgreso = true;
        vista.mostrarPanel("JUEGO");
        timer.start();
        vista.requestFocusInWindow();
    }

    /**
     * Reinicia la partida actual manteniendo el nombre del jugador.
     */
    private void reiniciarJuego() {
        modelo.reiniciar();
        juegoEnProgreso = true;
        vista.mostrarPanel("JUEGO");
        timer.start();
        vista.requestFocusInWindow();
    }

    /**
     * Vuelve al menú principal desde la pantalla de resultados.
     */
    private void volverAlMenu() {
        timer.stop();
        juegoEnProgreso = false;
        vista.mostrarPanel("MENU");
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
        if (!modelo.isEnJuego() || !juegoEnProgreso)
            return;

        // Actualizar lógica del juego
        actualizarObstaculos();
        actualizarGravedad();
        verificarColisiones();
        
        // Redibujar
        vista.panelJuego.actualizar(modelo);
    }

    /**
     * Actualiza la posición de los obstáculos.
     */
    private void actualizarObstaculos() {
        // Lógica de movimiento del obstáculo normal
        modelo.setObstaculoX(modelo.getObstaculoX() - modelo.getVelocidadObstaculo());
        
        if (modelo.getObstaculoX() < -20) {
            modelo.setObstaculoX(GameConstants.OBSTACLE_INITIAL_X);
            modelo.incrementarPuntuacion();
            
            // Aparece obstáculo del cielo cada N puntos
            if (modelo.debeActivarObstaculoCielo()) {
                modelo.setObstaculoCieloActivo(true);
                modelo.setObstaculoCieloX(GameConstants.OBSTACLE_INITIAL_X);
            }
            
            // Aumentar velocidad cada N puntos
            if (modelo.debeIncrementarVelocidad()) {
                modelo.incrementarVelocidad();
            }
        }

        // Lógica de movimiento del obstáculo del cielo
        if (modelo.isObstaculoCieloActivo()) {
            modelo.setObstaculoCieloX(modelo.getObstaculoCieloX() - modelo.getVelocidadObstaculo());
            if (modelo.getObstaculoCieloX() < -20) {
                modelo.setObstaculoCieloActivo(false);
            }
        }
    }

    /**
     * Actualiza la gravedad del personaje.
     */
    private void actualizarGravedad() {
        if (modelo.getPersonajeY() < GameConstants.COLLISION_GROUND_Y)
            modelo.setPersonajeY(modelo.getPersonajeY() + GameConstants.GRAVITY);
    }

    /**
     * Verifica colisiones con los obstáculos.
     */
    private void verificarColisiones() {
        // Colisión con obstáculo normal (abajo)
        if (modelo.getObstaculoX() < GameConstants.COLLISION_X_MAX && 
            modelo.getObstaculoX() > GameConstants.COLLISION_X_MIN && 
            (modelo.getPersonajeY() + GameConstants.PLAYER_HEIGHT) > GameConstants.COLLISION_GROUND_Y) {
            finalizarJuego();
            return;
        }

        // Colisión con obstáculo del cielo (arriba)
        if (modelo.isObstaculoCieloActivo() && 
            modelo.getObstaculoCieloX() < GameConstants.COLLISION_X_MAX && 
            modelo.getObstaculoCieloX() > GameConstants.COLLISION_X_MIN && 
            modelo.getPersonajeY() < GameConstants.COLLISION_SKY_Y_MAX) {
            finalizarJuego();
        }
    }

    /**
     * Finaliza la partida actual.
     * Detiene el timer y muestra la pantalla de resultados.
     */
    private void finalizarJuego() {
        timer.stop();
        modelo.setEnJuego(false);
        juegoEnProgreso = false;
        
        // Actualizar panel de resultados
        vista.panelResultados.actualizarResultados(modelo.getNombreJugador(), modelo.getPuntuacion());
        vista.mostrarPanel("RESULTADOS");
    }

    /**
     * Maneja el evento de tecla presionada.
     * Permite que el jugador salte cuando presiona la barra espaciadora.
     * 
     * @param e el evento de teclado
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && 
            modelo.getPersonajeY() >= GameConstants.COLLISION_GROUND_Y && juegoEnProgreso) {
            modelo.setPersonajeY(modelo.getPersonajeY() - GameConstants.JUMP_HEIGHT);
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