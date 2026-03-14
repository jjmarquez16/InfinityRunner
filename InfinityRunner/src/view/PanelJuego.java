package view;

import javax.swing.*;
import java.awt.*;
import model.Modelo;

public class PanelJuego extends JPanel {
    private Modelo modelo;

    public PanelJuego() {
        setBackground(Color.CYAN);
    }

    public void actualizar(Modelo modelo) {
        this.modelo = modelo;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (modelo == null) return;

        // Suelo
        g.setColor(Color.GRAY);
        g.fillRect(0, 350, 800, 150);

        // Personaje
        g.setColor(Color.RED);
        g.fillRect(50, modelo.personajeY, 50, 50);

        // Obstáculo
        g.setColor(Color.BLACK);
        g.fillRect(modelo.obstaculoX, 300, 30, 50);

        // UI
        g.setColor(Color.BLUE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Puntos: " + modelo.puntuacion, 20, 30);
    }
}
