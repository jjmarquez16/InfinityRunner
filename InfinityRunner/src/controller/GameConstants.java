package controller;

import java.awt.*;

/**
 * Clase de constantes del juego InfinityRunner.
 * Centraliza todos los valores configurables para facilitar mantenimiento y modificación.
 * 
 * @author Equipo de Desarrollo
 * @version 1.0
 */
public final class GameConstants {
    
    //  VENTANA 
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 500;
    public static final String WINDOW_TITLE = "InfinityRunner - El Juego del Corredor Infinito";
    
    //  FÍSICAS DEL JUEGO 
    public static final int GRAVITY = 5;                    // Píxeles por frame
    public static final int JUMP_HEIGHT = 150;              // Altura del salto
    public static final int BASE_OBSTACLE_SPEED = 8;        // Velocidad inicial
    public static final int SPEED_INCREMENT = 1;            // Incremento de velocidad
    public static final int SPEED_INCREMENT_INTERVAL = 3;   // Cada N puntos
    
    // POSICIONES INICIALES 
    public static final int PLAYER_INITIAL_Y = 300;         // Y inicial del personaje
    public static final int PLAYER_X = 50;                  // X fijo del personaje
    public static final int PLAYER_WIDTH = 60;              // Ancho del personaje
    public static final int PLAYER_HEIGHT = 60;             // Alto del personaje
    
    public static final int OBSTACLE_INITIAL_X = 800;       // X de aparición
    public static final int OBSTACLE_GROUND_Y = 305;        // Y del obstáculo (suelo)
    public static final int OBSTACLE_WIDTH = 30;            // Ancho
    public static final int OBSTACLE_HEIGHT = 45;           // Alto
    
    public static final int OBSTACLE_SKY_Y = 140;           // Y del obstáculo (cielo)
    public static final int OBSTACLE_SKY_HEIGHT = 35;       // Alto
    public static final int OBSTACLE_SKY_INTERVAL = 5;      // Aparece cada N puntos
    
    //  DETECCIÓN DE COLISIONES 
    public static final int COLLISION_X_MIN = 50;           // X mínima de colisión
    public static final int COLLISION_X_MAX = 90;           // X máxima de colisión
    public static final int COLLISION_GROUND_Y = 300;       // Y de división suelo-aire
    public static final int COLLISION_SKY_Y_MAX = 140;      // Y máximo del cielo
    
    //  TIMMER Y FPS 
    public static final int TIMER_DELAY = 20;               // Milisegundos (50 FPS)
    public static final int TARGET_FPS = 50;
    
    //  COLORES 
    public static final Color COLOR_BG_GAME = new Color(57, 255, 20);      // Verde neón
    public static final Color COLOR_GROUND = new Color(34, 139, 34);       // Verde oscuro
    public static final Color COLOR_OBSTACLE = Color.BLACK;
    public static final Color COLOR_OBSTACLE_SKY = new Color(255, 100, 0); // Naranja
    
    // Menú
    public static final Color COLOR_MENU_BG = new Color(30, 60, 100);
    public static final Color COLOR_TITLE = new Color(100, 200, 255);
    public static final Color COLOR_SUBTITLE = new Color(150, 200, 255);
    public static final Color COLOR_INPUT_BG = new Color(50, 80, 120);
    public static final Color COLOR_BUTTON_PLAY = new Color(100, 200, 100);
    public static final Color COLOR_BUTTON_SECONDARY = new Color(100, 150, 200);
    
    // Resultados
    public static final Color COLOR_RESULTS_BG = new Color(25, 25, 25);
    public static final Color COLOR_GAME_OVER = new Color(255, 100, 100);
    public static final Color COLOR_SCORE_VALUE = new Color(100, 255, 100);
    public static final Color COLOR_INFO_VALUE = new Color(100, 200, 255);
    
    // Panel info
    public static final Color COLOR_INFO_BG = new Color(30, 30, 30);
    public static final Color COLOR_INFO_BORDER = new Color(100, 150, 200);
    public static final Color COLOR_INFO_TEXT = new Color(100, 200, 255);
    public static final Color COLOR_SCORE_TEXT = new Color(100, 255, 100);
    
    //  FUENTES 
    public static final Font FONT_TITLE = new Font("Verdana", Font.BOLD, 48);
    public static final Font FONT_SUBTITLE = new Font("Verdana", Font.ITALIC, 18);
    public static final Font FONT_LABEL = new Font("Arial", Font.BOLD, 14);
    public static final Font FONT_INPUT = new Font("Arial", Font.PLAIN, 14);
    public static final Font FONT_BUTTON = new Font("Arial", Font.BOLD, 16);
    public static final Font FONT_SCORE = new Font("Arial", Font.BOLD, 25);
    public static final Font FONT_INSTRUCTION = new Font("Arial", Font.ITALIC, 12);
    public static final Font FONT_INFO = new Font("Arial", Font.BOLD, 14);
    
    //  VALORES DE UI 
    public static final int INSET_LARGE = 15;
    public static final int INSET_MEDIUM = 10;
    public static final int BORDER_SIZE = 2;
    
    //  TEXTOS 
    public static final String TEXT_TITLE = "INFINITY RUNNER";
    public static final String TEXT_SUBTITLE = "El Juego del Corredor Infinito";
    public static final String TEXT_LABEL_NAME = "Tu Nombre:";
    public static final String TEXT_DEFAULT_NAME = "Jugador 1";
    public static final String TEXT_BUTTON_PLAY = "EMPEZAR PARTIDA";
    public static final String TEXT_BUTTON_RETRY = "REINTENTAR";
    public static final String TEXT_BUTTON_MENU = "VOLVER AL MENÚ";
    public static final String TEXT_INSTRUCTION = "Presiona ESPACIO para saltar los obstáculos";
    public static final String TEXT_GAME_OVER = "GAME OVER!";
    public static final String TEXT_PLAYER = "Jugador:";
    public static final String TEXT_SCORE = "Puntuación:";
    
    // Privado para evitar instanciación
    private GameConstants() {
        throw new AssertionError("No se puede instanciar GameConstants");
    }
}
