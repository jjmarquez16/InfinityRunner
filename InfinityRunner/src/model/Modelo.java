package model;

import controller.GameConstants;

/**
 * Modelo de datos para el juego InfinityRunner.
 * Contiene el estado del juego incluyendo la posición del jugador, obstáculos y puntuación.
 * Utiliza constantes centralizadas para mayor mantenibilidad.
 * 
 * @author Equipo de Desarrollo
 * @version 1.0
 */
public class Modelo {
    /** Nombre del jugador actual */
    private String nombreJugador = "";
    /** Posición vertical del personaje (eje Y) */
    private int personajeY;
    /** Posición horizontal del obstáculo (eje X) */
    private int obstaculoX;
    /** Posición horizontal del obstáculo del cielo (eje X) */
    private int obstaculoCieloX;
    /** Posición vertical del obstáculo del cielo (eje Y) */
    private int obstaculoCieloY;
    /** Flag que indica si el obstáculo del cielo está activo */
    private boolean obstaculoCieloActivo = false;
    /** Puntuación actual del jugador */
    private int puntuacion = 0;
    /** Flag que indica si el juego está en curso */
    private boolean enJuego = false;
    /** Velocidad de movimiento del obstáculo */
    private int velocidadObstaculo;

    /**
     * Constructor del Modelo.
     * Inicializa todos los valores a sus estados iniciales.
     */
    public Modelo() {
        reiniciar();
    }

    /**
     * Reinicia el estado del juego a sus valores iniciales.
     * Resetea la posición del personaje, obstáculo, puntuación y velocidad.
     */
    public void reiniciar() {
        personajeY = GameConstants.PLAYER_INITIAL_Y;
        obstaculoX = GameConstants.OBSTACLE_INITIAL_X;
        obstaculoCieloX = GameConstants.OBSTACLE_INITIAL_X;
        obstaculoCieloY = GameConstants.OBSTACLE_SKY_Y;
        obstaculoCieloActivo = false;
        puntuacion = 0;
        velocidadObstaculo = GameConstants.BASE_OBSTACLE_SPEED;
        nombreJugador = "";
        enJuego = true;
    }

    // ==================== GETTERS ====================
    
    public String getNombreJugador() {
        return nombreJugador;
    }

    public int getPersonajeY() {
        return personajeY;
    }

    public int getObstaculoX() {
        return obstaculoX;
    }

    public int getObstaculoCieloX() {
        return obstaculoCieloX;
    }

    public int getObstaculoCieloY() {
        return obstaculoCieloY;
    }

    public boolean isObstaculoCieloActivo() {
        return obstaculoCieloActivo;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public boolean isEnJuego() {
        return enJuego;
    }

    public int getVelocidadObstaculo() {
        return velocidadObstaculo;
    }

    // ==================== SETTERS ====================
    
    public void setNombreJugador(String nombre) {
        this.nombreJugador = nombre != null ? nombre : "";
    }

    public void setPersonajeY(int y) {
        this.personajeY = Math.max(0, Math.min(y, GameConstants.WINDOW_HEIGHT));
    }

    public void setObstaculoX(int x) {
        this.obstaculoX = x;
    }

    public void setObstaculoCieloX(int x) {
        this.obstaculoCieloX = x;
    }

    public void setObstaculoCieloActivo(boolean activo) {
        this.obstaculoCieloActivo = activo;
    }

    public void setPuntuacion(int puntos) {
        this.puntuacion = Math.max(0, puntos);
    }

    public void setEnJuego(boolean enJuego) {
        this.enJuego = enJuego;
    }

    public void setVelocidadObstaculo(int velocidad) {
        this.velocidadObstaculo = Math.max(GameConstants.BASE_OBSTACLE_SPEED, velocidad);
    }

    // ==================== MÉTODOS DE LÓGICA ====================
    
    /**
     * Incremente la puntuación en 1.
     */
    public void incrementarPuntuacion() {
        puntuacion++;
    }

    /**
     * Incrementa la velocidad del obstáculo.
     */
    public void incrementarVelocidad() {
        velocidadObstaculo += GameConstants.SPEED_INCREMENT;
    }

    /**
     * Detecta si debe aparecer un obstáculo del cielo.
     * @return true si debe activarse el obstáculo del cielo
     */
    public boolean debeActivarObstaculoCielo() {
        return puntuacion > 0 && puntuacion % GameConstants.OBSTACLE_SKY_INTERVAL == 0;
    }

    /**
     * Detecta si debe incrementarse la velocidad.
     * @return true si debe aumentar la velocidad
     */
    public boolean debeIncrementarVelocidad() {
        return puntuacion > 0 && puntuacion % GameConstants.SPEED_INCREMENT_INTERVAL == 0;
    }
}
