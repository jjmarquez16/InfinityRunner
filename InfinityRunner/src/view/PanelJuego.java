package view;

import javax.swing.*;
import java.awt.*;
import model.Modelo;
import java.net.URL;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * Panel encargado de renderizar la vista del juego.
 * Dibuja el fondo, el personaje, los obstáculos y la interfaz de Usuario.
 * 
 * @author Equipo de Desarrollo
 * @version 1.0
 */
public class PanelJuego extends JPanel {
    /** Referencia al modelo de datos */
    private Modelo modelo;
    /** Imagen del personaje principal (tortuga) */
    private BufferedImage imgProtagonista;

    /**
     * Constructor del panel de juego.
     * Inicializa y carga los recursos necesarios (imágenes del protagonista y fondo).
     */
    public PanelJuego() {
        setBackground(new Color(57, 255, 20)); 
        cargarRecursos();
    }

    /**
     * Carga los recursos gráficos del juego.
     * Intenta cargar las imágenes del protagonista y el fondo desde los recursos de la aplicación.
     */
    private void cargarRecursos() {
        try {
            // Cargamos la imagen del protagonista (Protagonista.png)
            imgProtagonista = intentarCargar("/resources/Protagonista.png", "src/resources/Protagonista.png");
            System.out.println("Protagonista cargada: " + (imgProtagonista != null ? "SÍ" : "NO"));
        } catch (Exception e) {
            System.err.println("Error al cargar los recursos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Intenta cargar una imagen desde múltiples ubicaciones.
     * Primero intenta desde los recursos de la aplicación, luego desde el sistema de archivos.
     * 
     * @param pathRecurso la ruta del recurso dentro de la aplicación
     * @param pathArchivo la ruta del archivo en el sistema de archivos
     * @return la imagen cargada o null si no se encuentra
     */
    private BufferedImage intentarCargar(String pathRecurso, String pathArchivo) {
        try {
            // Intenta desde los recursos de la clase
            URL url = getClass().getResource(pathRecurso);
            if (url != null) {
                System.out.println("Imagen encontrada desde recursos: " + pathRecurso);
                return ImageIO.read(url);
            }
            
            // Intenta desde el sistema de archivos (ruta relativa)
            File f = new File(pathArchivo);
            if (f.exists()) {
                System.out.println("Imagen encontrada desde archivo: " + f.getAbsolutePath());
                return ImageIO.read(f);
            }
            
            System.err.println("Imagen no encontrada: " + pathRecurso + " o " + pathArchivo);
        } catch (Exception e) {
            System.err.println("Error cargando imagen: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Actualiza el panel con el modelo actual y lo redibuja.
     * 
     * @param modelo el modelo de datos actual
     */
    public void actualizar(Modelo modelo) {
        this.modelo = modelo;
        repaint();
    }

    /**
     * Dibuja los componentes del juego en el panel.
     * Incluye el fondo, el personaje, los obstáculos y la interfaz de usuario.
     * 
     * @param g el contexto gráfico
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (modelo == null) return;

        // 1. DIBUJAR EL FONDO
        g.setColor(new Color(57, 255, 20));
        g.fillRect(0, 0, getWidth(), getHeight());

        // 2. DIBUJAR EL SUELO
        g.setColor(new Color(34, 139, 34)); // Verde oscuro para contraste
        g.fillRect(0, 350, getWidth(), 150);

        // 3. DIBUJAR AL PROTAGONISTA
        if (imgProtagonista != null) {
            g.drawImage(imgProtagonista, 50, modelo.personajeY, 60, 60, null);
        } else {
            // Fallback: Cuadrado rojo si no carga la imagen
            g.setColor(Color.RED);
            g.fillRect(50, modelo.personajeY, 50, 50);
        }

        // 4. DIBUJAR OBSTÁCULO
        g.setColor(Color.BLACK);
        g.fillRect(modelo.obstaculoX, 305, 30, 45);

        // 5. DIBUJAR OBSTÁCULO DEL CIELO
        if (modelo.obstaculoCieloActivo) {
            g.setColor(new Color(255, 100, 0)); // Naranja para diferenciarlo
            g.fillRect(modelo.obstaculoCieloX, modelo.obstaculoCieloY, 30, 35);
        }

        // 6. INTERFAZ (UI)
        g.setFont(new Font("Arial", Font.BOLD, 25));
        g.setColor(Color.BLUE);
        g.drawString("Puntos: " + modelo.puntuacion, 20, 40);
    }
}
