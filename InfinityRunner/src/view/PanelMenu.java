package view;

import javax.swing.*;
import java.awt.*;

/**
 * Panel encargado de mostrar el menú principal del juego.
 * Permite al usuario ingresar su nombre y comenzar una nueva partida.
 * Incluye elementos personalizados para mejorar la experiencia del usuario.
 * 
 * @author Equipo de Desarrollo
 * @version 1.0
 */
public class PanelMenu extends JPanel {
    /** Botón para iniciar una nueva partida */
    public JButton btnJugar = new JButton("EMPEZAR PARTIDA");
    /** Campo de texto para ingresar el nombre del jugador */
    public JTextField txtNombre = new JTextField(20);

    /**
     * Constructor del panel de menú.
     * Inicializa los componentes visuales del menú con un diseño profesional.
     */
    public PanelMenu() {
        setLayout(new GridBagLayout());
        setBackground(new Color(30, 60, 100));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        // Título principal
        JLabel titulo = new JLabel("INFINITY RUNNER");
        titulo.setFont(new Font("Verdana", Font.BOLD, 48));
        titulo.setForeground(new Color(100, 200, 255));
        add(titulo, gbc);

        // Subtítulo
        gbc.gridy = 1;
        JLabel subtitulo = new JLabel("El Juego del Corredor Infinito");
        subtitulo.setFont(new Font("Verdana", Font.ITALIC, 18));
        subtitulo.setForeground(new Color(150, 200, 255));
        add(subtitulo, gbc);

        // Separador
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(100, 150, 200));
        add(sep, gbc);

        // Etiqueta de nombre
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        JLabel lblNombre = new JLabel("Tu Nombre:");
        lblNombre.setFont(new Font("Arial", Font.BOLD, 14));
        lblNombre.setForeground(Color.WHITE);
        add(lblNombre, gbc);

        // Campo de nombre
        gbc.gridx = 1;
        txtNombre.setFont(new Font("Arial", Font.PLAIN, 14));
        txtNombre.setText("Jugador 1");
        txtNombre.setCaretColor(Color.WHITE);
        txtNombre.setBackground(new Color(50, 80, 120));
        txtNombre.setForeground(Color.WHITE);
        add(txtNombre, gbc);

        // Botón de jugar
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(25, 15, 15, 15);
        btnJugar.setFont(new Font("Arial", Font.BOLD, 16));
        btnJugar.setForeground(Color.WHITE);
        btnJugar.setBackground(new Color(100, 200, 100));
        btnJugar.setFocusPainted(false);
        btnJugar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(btnJugar, gbc);

        // Etiqueta de instrucciones
        gbc.gridy = 5;
        gbc.insets = new Insets(10, 15, 15, 15);
        JLabel instrucciones = new JLabel("Presiona ESPACIO para saltar los obstáculos");
        instrucciones.setFont(new Font("Arial", Font.ITALIC, 12));
        instrucciones.setForeground(new Color(150, 150, 150));
        add(instrucciones, gbc);
    }
}
