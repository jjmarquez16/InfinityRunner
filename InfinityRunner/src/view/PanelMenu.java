package view;

import javax.swing.*;
import java.awt.*;
import controller.GameConstants;

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
    public JButton btnJugar;
    /** Campo de texto para ingresar el nombre del jugador */
    public JTextField txtNombre;

    /**
     * Constructor del panel de menú.
     * Inicializa los componentes visuales del menú con un diseño profesional.
     */
    public PanelMenu() {
        setLayout(new GridBagLayout());
        setBackground(GameConstants.COLOR_MENU_BG);

        GridBagConstraints gbc = crearGridBagConstraints();

        // Título principal
        agregarTítulo(gbc);

        // Subtítulo
        gbc.gridy = 1;
        agregarEtiqueta(gbc, GameConstants.TEXT_SUBTITLE, GameConstants.FONT_SUBTITLE, 
                       GameConstants.COLOR_SUBTITLE, 2);

        // Separador
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(new JSeparator(), gbc);

        // Campo de nombre
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        agregarCampoNombre(gbc);

        // Botón de jugar
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(25, GameConstants.INSET_LARGE, GameConstants.INSET_LARGE, GameConstants.INSET_LARGE);
        agregarBotónJugar(gbc);

        // Instrucciones
        gbc.gridy = 5;
        gbc.insets = new Insets(GameConstants.INSET_MEDIUM, GameConstants.INSET_LARGE, 
                               GameConstants.INSET_LARGE, GameConstants.INSET_LARGE);
        agregarEtiqueta(gbc, GameConstants.TEXT_INSTRUCTION, GameConstants.FONT_INSTRUCTION, 
                       new Color(150, 150, 150), 2);
    }

    /**
     * Crea un GridBagConstraints con valores iniciales.
     */
    private GridBagConstraints crearGridBagConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(GameConstants.INSET_LARGE, GameConstants.INSET_LARGE, 
                               GameConstants.INSET_LARGE, GameConstants.INSET_LARGE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        return gbc;
    }

    /**
     * Agrega una etiqueta al panel.
     */
    private void agregarEtiqueta(GridBagConstraints gbc, String texto, Font fuente, 
                                 Color color, int gridwidth) {
        JLabel label = new JLabel(texto);
        label.setFont(fuente);
        label.setForeground(color);
        gbc.gridwidth = gridwidth;
        add(label, gbc);
    }

    /**
     * Agrega el título principal.
     */
    private void agregarTítulo(GridBagConstraints gbc) {
        agregarEtiqueta(gbc, GameConstants.TEXT_TITLE, GameConstants.FONT_TITLE, 
                       GameConstants.COLOR_TITLE, 2);
    }

    /**
     * Agrega el campo de nombre.
     */
    private void agregarCampoNombre(GridBagConstraints gbc) {
        // Etiqueta
        agregarEtiqueta(gbc, GameConstants.TEXT_LABEL_NAME, GameConstants.FONT_LABEL, 
                       Color.WHITE, 1);

        // Campo de texto
        gbc.gridx = 1;
        txtNombre = new JTextField(20);
        txtNombre.setFont(GameConstants.FONT_INPUT);
        txtNombre.setText(GameConstants.TEXT_DEFAULT_NAME);
        txtNombre.setCaretColor(Color.WHITE);
        txtNombre.setBackground(GameConstants.COLOR_INPUT_BG);
        txtNombre.setForeground(Color.WHITE);
        add(txtNombre, gbc);
    }

    /**
     * Agrega el botón de jugar.
     */
    private void agregarBotónJugar(GridBagConstraints gbc) {
        btnJugar = new JButton(GameConstants.TEXT_BUTTON_PLAY);
        btnJugar.setFont(GameConstants.FONT_BUTTON);
        btnJugar.setForeground(Color.WHITE);
        btnJugar.setBackground(GameConstants.COLOR_BUTTON_PLAY);
        btnJugar.setFocusPainted(false);
        btnJugar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(btnJugar, gbc);
    }
}
