package view;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana principal de la aplicación InfinityRunner.
 * Contiene el CardLayout para navegar entre el menú, el panel de juego y resultados.
 * Implementa una estructura modular con diferentes paneles reutilizables.
 * 
 * @author Equipo de Desarrollo
 * @version 1.0
 */
public class VentanaPrincipal extends JFrame {
    /** CardLayout para cambiar entre las diferentes vistas */
    public CardLayout tarjetas = new CardLayout();
    /** Panel contenedor principal que alberga los diferentes paneles de la aplicación */
    public JPanel contenedor = new JPanel(tarjetas);

    /** Panel del menú principal */
    public PanelMenu panelMenu;
    /** Panel de juego */
    public PanelJuego panelJuego;
    /** Panel de resultados */
    public PanelResultados panelResultados;

    /**
     * Constructor de la ventana principal.
     * Inicializa la ventana, configura los parámetros y añade los paneles.
     */
    public VentanaPrincipal() {
        setTitle("InfinityRunner - El Juego del Corredor Infinito");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // Configurar contenedor principal
        contenedor.setBackground(new Color(20, 20, 20));

        // Inicializar paneles
        panelMenu = new PanelMenu();
        panelJuego = new PanelJuego();
        panelResultados = new PanelResultados();

        // Agregar paneles al contenedor
        contenedor.add(panelMenu, "MENU");
        contenedor.add(panelJuego, "JUEGO");
        contenedor.add(panelResultados, "RESULTADOS");

        // Usar BorderLayout para la ventana principal
        setLayout(new BorderLayout());
        add(contenedor, BorderLayout.CENTER);

        // Barra de información (opcional)
        JPanel panelInfo = crearPanelInfo();
        add(panelInfo, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Crea un panel de información en la parte inferior de la ventana.
     * @return panel con información del juego
     */
    private JPanel crearPanelInfo() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setBackground(new Color(30, 30, 30));
        
        JLabel info = new JLabel("InfinityRunner © 2024 - Usa ESPACIO para saltar");
        info.setForeground(Color.LIGHT_GRAY);
        info.setFont(new Font("Arial", Font.PLAIN, 10));
        
        panel.add(info);
        return panel;
    }

    /**
     * Cambia a un panel específico.
     * @param nombrePanel el nombre del panel a mostrar
     */
    public void mostrarPanel(String nombrePanel) {
        tarjetas.show(contenedor, nombrePanel);
    }
}