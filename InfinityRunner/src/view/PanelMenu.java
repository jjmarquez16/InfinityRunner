package view;

import javax.swing.*;
import java.awt.*;

public class PanelMenu extends JPanel {
    public JButton botonJugar = new JButton("Jugar");
    public JButton botonSalir = new JButton("Salir");

    public PanelMenu() {
        setBackground(Color.LIGHT_GRAY);
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        add(botonJugar);
        add(botonSalir);
    }
}
