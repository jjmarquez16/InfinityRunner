package view;

import javax.swing.*;
import java.awt.*;

/**
 * Panel encargado de mostrar los resultados finales del juego.
 * Muestra la puntuación del jugador y opciones para volver al menú o ver estadísticas.
 * 
 * @author Equipo de Desarrollo
 * @version 1.0
 */
public class PanelResultados extends JPanel {
    /** Etiqueta para el nombre del jugador */
    public JLabel lblNombreJugador = new JLabel();
    /** Etiqueta para la puntuación final */
    public JLabel lblPuntuacion = new JLabel();
    /** Botón para volver al menú */
    public JButton btnVolverAlMenu = new JButton("VOLVER AL MENÚ");
    /** Botón para reintentar */
    public JButton btnReiniciar = new JButton("REINTENTAR");

    /**
     * Constructor del panel de resultados.
     * Inicializa los componentes visuales del panel de resultados.
     */
    public PanelResultados() {
        setLayout(new GridBagLayout());
        setBackground(new Color(25, 25, 25));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        // Título
        JLabel titulo = new JLabel("¡GAME OVER!");
        titulo.setFont(new Font("Verdana", Font.BOLD, 40));
        titulo.setForeground(new Color(255, 100, 100));
        add(titulo, gbc);

        // Nombre del jugador
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        JLabel lblJugador = new JLabel("Jugador:");
        lblJugador.setFont(new Font("Arial", Font.PLAIN, 16));
        lblJugador.setForeground(Color.WHITE);
        add(lblJugador, gbc);

        gbc.gridx = 1;
        lblNombreJugador.setFont(new Font("Arial", Font.BOLD, 16));
        lblNombreJugador.setForeground(new Color(100, 200, 255));
        add(lblNombreJugador, gbc);

        // Puntuación
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lblScore = new JLabel("Puntuación:");
        lblScore.setFont(new Font("Arial", Font.PLAIN, 16));
        lblScore.setForeground(Color.WHITE);
        add(lblScore, gbc);

        gbc.gridx = 1;
        lblPuntuacion.setFont(new Font("Arial", Font.BOLD, 20));
        lblPuntuacion.setForeground(new Color(100, 255, 100));
        add(lblPuntuacion, gbc);

        // Botones
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(20, 10, 10, 10);
        btnReiniciar.setFont(new Font("Arial", Font.BOLD, 12));
        btnReiniciar.setFocusPainted(false);
        btnReiniciar.setBackground(new Color(100, 200, 100));
        btnReiniciar.setForeground(Color.WHITE);
        add(btnReiniciar, gbc);

        gbc.gridx = 1;
        btnVolverAlMenu.setFont(new Font("Arial", Font.BOLD, 12));
        btnVolverAlMenu.setFocusPainted(false);
        btnVolverAlMenu.setBackground(new Color(100, 150, 200));
        btnVolverAlMenu.setForeground(Color.WHITE);
        add(btnVolverAlMenu, gbc);
    }

    /**
     * Actualiza la información mostrada en el panel de resultados.
     * 
     * @param nombreJugador el nombre del jugador
     * @param puntuacion la puntuación final
     */
    public void actualizarResultados(String nombreJugador, int puntuacion) {
        lblNombreJugador.setText(nombreJugador);
        lblPuntuacion.setText(String.valueOf(puntuacion));
    }
}
