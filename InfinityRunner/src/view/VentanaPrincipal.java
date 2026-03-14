package view;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana principal de la aplicación InfinityRunner.
 * Contiene el CardLayout para navegar entre el menú y el panel de juego.
 * 
 * @author Equipo de Desarrollo
 * @version 1.0
 */
public class VentanaPrincipal extends JFrame {
    /** CardLayout para cambiar entre las diferentes vistas */
    public CardLayout tarjetas = new CardLayout();
    /** Panel contenedor que alberga los diferentes paneles de la aplicación */
    public JPanel contenedor = new JPanel(tarjetas);

    /** Panel del menú principal */
    public PanelMenu panelMenu = new PanelMenu();
    /** Panel de juego */
    public PanelJuego panelJuego = new PanelJuego();

    /**
     * Constructor de la ventana principal.
     * Inicializa la ventana, configura los parámetros y añade los paneles.
     */
    public VentanaPrincipal() {
        setTitle("Java Runner: Individual Edition");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        contenedor.add(panelMenu, "MENU");
        contenedor.add(panelJuego, "JUEGO");

        add(contenedor);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}