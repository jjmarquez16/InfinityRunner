package model;

public class Modelo {
    public String nombreJugador = "";
    public int personajeY = 300;
    public int obstaculoX = 800;
    public int puntuacion = 0;
    public boolean enJuego = false;
    public int velocidadObstaculo = 8;

    public void reiniciar() {
        personajeY = 300;
        obstaculoX = 800;
        puntuacion = 0;
        velocidadObstaculo = 8;
        enJuego = true;
    }
}
