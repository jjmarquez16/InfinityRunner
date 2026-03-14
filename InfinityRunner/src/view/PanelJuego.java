package view;

import javax.swing.*;
import java.awt.*;
import model.Modelo;
import java.net.URL;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class PanelJuego extends JPanel {
    private Modelo modelo;
    private BufferedImage imgProtagonista;

    public PanelJuego() {
        // Fondo Verde Neón llamativo
        setBackground(new Color(57, 255, 20)); 
        cargarRecursos();
    }

    private void cargarRecursos() {
        try {
            // Cargamos la imagen de la tortuga (Protagonista.png)
            imgProtagonista = intentarCargar("/resources/Protagonista.png", "src/resources/Protagonista.png");
        } catch (Exception e) {
            System.err.println("Error al cargar la tortuga: " + e.getMessage());
        }
    }

    private BufferedImage intentarCargar(String pathRecurso, String pathArchivo) {
        try {
            URL url = getClass().getResource(pathRecurso);
            if (url != null) return ImageIO.read(url);
            File f = new File(pathArchivo);
            if (f.exists()) return ImageIO.read(f);
        } catch (Exception e) {}
        return null;
    }

    public void actualizar(Modelo modelo) {
        this.modelo = modelo;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Esto pintará el fondo Verde Neón
        if (modelo == null) return;

        // 1. DIBUJAR EL SUELO
        g.setColor(new Color(34, 139, 34)); // Verde oscuro para contraste
        g.fillRect(0, 350, getWidth(), 150);

        // 2. DIBUJAR AL PROTAGONISTA (La Tortuga)
        if (imgProtagonista != null) {
            g.drawImage(imgProtagonista, 50, modelo.personajeY, 60, 60, null);
        } else {
            // Fallback: Cuadrado rojo si no carga la imagen
            g.setColor(Color.RED);
            g.fillRect(50, modelo.personajeY, 50, 50);
        }

        // 3. DIBUJAR OBSTÁCULO
        g.setColor(Color.BLACK);
        g.fillRect(modelo.obstaculoX, 305, 30, 45);

        // 4. INTERFAZ (UI)
        g.setFont(new Font("Arial", Font.BOLD, 25));
        g.setColor(Color.BLUE);
        g.drawString("Puntos: " + modelo.puntuacion, 20, 40);
    }
}
