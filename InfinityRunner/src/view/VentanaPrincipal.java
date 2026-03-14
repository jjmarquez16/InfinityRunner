package view;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    public CardLayout tarjetas = new CardLayout();
    public JPanel contenedor = new JPanel(tarjetas);

    public PanelMenu panelMenu = new PanelMenu();
    public PanelJuego panelJuego = new PanelJuego();

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