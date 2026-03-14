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
 * Utiliza un diseño modular con separación entre el lienzo de juego y la interfaz.
 * 
 * @author Equipo de Desarrollo
 * @version 1.0
 */
public class PanelJuego extends JPanel {
    /** Panel para dibujar el juego */
    private PanelCanvasJuego canvasJuego;
    /** Etiqueta para mostrar la puntuación */
    private JLabel lblPuntuacion;

    /**
     * Constructor del panel de juego.
     * Inicializa y carga los recursos necesarios (imágenes del protagonista y fondo).
     */
    public PanelJuego() {
        setLayout(new BorderLayout());
        setBackground(new Color(20, 20, 20));

        // Panel superior con información
        JPanel panelInfo = crearPanelInfo();
        add(panelInfo, BorderLayout.NORTH);

        // Canvas del juego
        canvasJuego = new PanelCanvasJuego();
        add(canvasJuego, BorderLayout.CENTER);
    }

    /**
     * Crea el panel de información superior.
     * @return panel con información del juego
     */
    private JPanel crearPanelInfo() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(new Color(30, 30, 30));
        panel.setBorder(BorderFactory.createLineBorder(new Color(100, 150, 200), 2));

        JLabel lblTitulo = new JLabel("INFINITY RUNNER - ");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitulo.setForeground(new Color(100, 200, 255));
        panel.add(lblTitulo);

        lblPuntuacion = new JLabel("Puntos: 0");
        lblPuntuacion.setFont(new Font("Arial", Font.BOLD, 16));
        lblPuntuacion.setForeground(new Color(100, 255, 100));
        panel.add(lblPuntuacion);

        return panel;
    }

    /**
     * Actualiza el panel con el modelo actual y lo redibuja.
     * 
     * @param modelo el modelo de datos actual
     */
    public void actualizar(Modelo modelo) {
        lblPuntuacion.setText("Puntos: " + modelo.puntuacion);
        canvasJuego.actualizar(modelo);
    }

    /**
     * Panel interno que dibuja el canvas del juego.
     */
    private static class PanelCanvasJuego extends JPanel {
        /** Referencia al modelo de datos */
        private Modelo modelo;
        /** Imagen del personaje principal (tortuga) */
        private BufferedImage imgProtagonista;
        /** Imagen de fondo del escenario */
        private BufferedImage imgFondo;

        /**
         * Constructor del panel canvas.
         */
        public PanelCanvasJuego() {
            setBackground(new Color(57, 255, 20));
            cargarRecursos();
        }

        /**
         * Carga los recursos gráficos del juego.
         */
        private void cargarRecursos() {
            try {
                imgProtagonista = intentarCargar("/resources/Protagonista.png", "src/resources/Protagonista.png");
                System.out.println("Protagonista cargada: " + (imgProtagonista != null ? "SÍ" : "NO"));

                imgFondo = intentarCargar("/resources/FondoSelva.png", "src/resources/FondoSelva.png");
                System.out.println("Fondo cargado: " + (imgFondo != null ? "SÍ" : "NO"));
            } catch (Exception e) {
                System.err.println("Error al cargar los recursos: " + e.getMessage());
                e.printStackTrace();
            }
        }

        /**
         * Intenta cargar una imagen desde múltiples ubicaciones.
         * 
         * @param pathRecurso la ruta del recurso dentro de la aplicación
         * @param pathArchivo la ruta del archivo en el sistema de archivos
         * @return la imagen cargada o null si no se encuentra
         */
        private BufferedImage intentarCargar(String pathRecurso, String pathArchivo) {
            try {
                URL url = getClass().getResource(pathRecurso);
                if (url != null) {
                    System.out.println("Imagen encontrada desde recursos: " + pathRecurso);
                    return ImageIO.read(url);
                }

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
         * Actualiza el panel con el modelo actual.
         * 
         * @param modelo el modelo de datos actual
         */
        public void actualizar(Modelo modelo) {
            this.modelo = modelo;
            repaint();
        }

        /**
         * Dibuja los componentes del juego.
         * 
         * @param g el contexto gráfico
         */
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (modelo == null) return;

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // 1. DIBUJAR EL FONDO
            dibujarFondo(g2d);

            // 2. DIBUJAR EL SUELO
            dibujarSuelo(g2d);

            // 3. DIBUJAR AL PROTAGONISTA
            dibujarProtagonista(g2d);

            // 4. DIBUJAR OBSTÁCULOS
            dibujarObstaculos(g2d);
        }

        /**
         * Dibuja el fondo del juego.
         */
        private void dibujarFondo(Graphics2D g) {
            if (imgFondo != null) {
                g.drawImage(imgFondo, 0, 0, getWidth(), getHeight(), null);
            } else {
                g.setColor(new Color(57, 255, 20));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        }

        /**
         * Dibuja el suelo del juego.
         */
        private void dibujarSuelo(Graphics2D g) {
            g.setColor(new Color(34, 139, 34));
            g.fillRect(0, 350, getWidth(), 150);
        }

        /**
         * Dibuja el protagonista.
         */
        private void dibujarProtagonista(Graphics2D g) {
            if (imgProtagonista != null) {
                g.drawImage(imgProtagonista, 50, modelo.personajeY, 60, 60, null);
            } else {
                g.setColor(Color.RED);
                g.fillRect(50, modelo.personajeY, 50, 50);
            }
        }

        /**
         * Dibuja los obstáculos del juego.
         */
        private void dibujarObstaculos(Graphics2D g) {
            // Obstáculo del suelo
            g.setColor(Color.BLACK);
            g.fillRect(modelo.obstaculoX, 305, 30, 45);

            // Obstáculo del cielo
            if (modelo.obstaculoCieloActivo) {
                g.setColor(new Color(255, 100, 0));
                g.fillRect(modelo.obstaculoCieloX, modelo.obstaculoCieloY, 30, 35);
            }
        }
    }
}
