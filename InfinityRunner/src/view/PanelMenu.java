package view;

import javax.swing.*;
import java.awt.*;

/**
 * Panel encargado de mostrar el menú principal del juego.
 * Permite al usuario ingresar su nombre y comenzar una nueva partida.
 * 
 * @author Equipo de Desarrollo
 * @version 1.0
 */
public class PanelMenu extends JPanel {
    /** Botón para iniciar una nueva partida */
    public JButton btnJugar = new JButton("EMPEZAR PARTIDA");
    /** Campo de texto para ingresar el nombre del jugador */
    public JTextField txtNombre = new JTextField(15);

    /**
     * Constructor del panel de menú.
     * Inicializa los componentes visuales del menú con un diseño profesional.
     */
    public PanelMenu() {
        setLayout(new GridBagLayout()); // Centrado profesional
        setBackground(new Color(41, 128, 185));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;

        JLabel titulo = new JLabel("INFINITY RUNNER");
        titulo.setFont(new Font("Verdana", Font.BOLD, 30));
        titulo.setForeground(Color.WHITE);

        add(titulo, gbc);
        gbc.gridy = 1;
        add(new JLabel("Tu Nombre:"), gbc);
        gbc.gridy = 2;
        add(txtNombre, gbc);
        gbc.gridy = 3;
        add(btnJugar, gbc);
    }
}
