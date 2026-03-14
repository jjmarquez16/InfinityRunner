package model;

/**
 * Modelo de datos para el juego InfinityRunner.
 * Contiene el estado del juego incluyendo la posición del jugador, obstáculos y puntuación.
 * 
 * @author Equipo de Desarrollo
 * @version 1.0
 */
public class Modelo {
    /** Nombre del jugador actual */
    public String nombreJugador = "";
    /** Posición vertical del personaje (eje Y) */
    public int personajeY = 300;
    /** Posición horizontal del obstáculo (eje X) */
    public int obstaculoX = 800;
    /** Puntuación actual del jugador */
    public int puntuacion = 0;
    /** Flag que indica si el juego está en curso */
    public boolean enJuego = false;
    /** Velocidad de movimiento del obstáculo */
    public int velocidadObstaculo = 8;

    /**
     * Reinicia el estado del juego a sus valores iniciales.
     * Resetea la posición del personaje, obstáculo, puntuación y velocidad.
     */
    public void reiniciar() {
        personajeY = 300;
        obstaculoX = 800;
        puntuacion = 0;
        velocidadObstaculo = 8;
        enJuego = true;
    }
}
