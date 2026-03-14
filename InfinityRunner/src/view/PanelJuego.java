package view;

import javax.swing.*;
import java.awt.*;
import model.Modelo;
import java.net.URL;

public class PanelJuego extends JPanel {
    private Modelo modelo;
    private Image imgPersonaje;
    private Image imgFondo;

    public PanelJuego() {
        setBackground(Color.BLACK);
        cargarRecursos();
    }

    private void cargarRecursos() {
        try {
            // Cargar Protagonista
            imgPersonaje = cargarImagen("/resources/Protagonista.png", "src/resources/Protagonista.png");
            
            // Cargar Fondo Selva
            imgFondo = cargarImagen("/resources/FondoSelva.png", "src/resources/FondoSelva.png");
            
        } catch (Exception e) {
            System.err.println("Error al cargar recursos: " + e.getMessage());
        }
    }

    private Image cargarImagen(String pathRecurso, String pathArchivo) {
        URL url = getClass().getResource(pathRecurso);
        if (url != null) {
            return new ImageIcon(url).getImage();
        } else {
            return new ImageIcon(pathArchivo).getImage();
        }
    }

    public void actualizar(Modelo modelo) {
        this.modelo = modelo;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (modelo == null) return;

        // Dibujar Fondo
        if (imgFondo != null) {
            g.drawImage(imgFondo, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.setColor(new Color(39, 174, 96));
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        // Suelo (semi-transparente o sutil para que se vea el fondo)
        g.setColor(new Color(0, 0, 0, 100));
        g.fillRect(0, 350, 800, 150);

        // Personaje
        if (imgPersonaje != null) {
            g.drawImage(imgPersonaje, 50, modelo.personajeY, 50, 50, this);
        } else {
            g.setColor(Color.RED);
            g.fillRect(50, modelo.personajeY, 50, 50);
        }

        // Obstáculo
        g.setColor(new Color(192, 57, 43));
        g.fillRoundRect(modelo.obstaculoX, 300, 30, 50, 10, 10);

        // UI con sombra para legibilidad sobre el fondo
        g.setFont(new Font("Verdana", Font.BOLD, 20));
        
        // Sombra
        g.setColor(Color.BLACK);
        g.drawString("Score: " + modelo.puntuacion, 22, 42);
        g.drawString("Player: " + modelo.nombreJugador, 22, 72);
        
        // Texto principal
        g.setColor(Color.YELLOW);
        g.drawString("Score: " + modelo.puntuacion, 20, 40);
        g.drawString("Player: " + modelo.nombreJugador, 20, 70);
    }
}
