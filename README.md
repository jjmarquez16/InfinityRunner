# InfinityRunner: Individual Edition

Bienvenido a **InfinityRunner**, un juego arcade de plataformas infinitas donde el objetivo es saltar entre obstáculos y acumular la mayor cantidad de puntos posible antes de perder.

## Descripción del Juego

InfinityRunner es un juego de carreras infinitas basado en saltos donde:

- Controlas un personaje que salta evitando obstáculos
- Acumulas puntos por cada obstáculo evitado
- La dificultad aumenta progresivamente cada 3 obstáculos
- Comparte tus mejores puntuaciones

## Características

- Mecánica simple y adictiva (solo usa SPACE)
- Dificultad progresiva automática
- Interfaz gráfica intuitiva con Swing
- Sistema de puntuación en tiempo real
- Arquitectura MVC bien estructurada

## Cómo Jugar

1. **Iniciar el juego**: Ejecuta `java main.Main`
2. **Ingresa tu nombre** en el menú
3. **Presiona SPACE** para saltar
4. **Evita los obstáculos** que se acercan
5. **Acumula puntos** saltando sobre obstáculos
6. **Compite** por el mejor record

## Controles

| Tecla | Acción         |
| ----- | -------------- |
| SPACE | Saltar         |
| ESC   | Salir (futuro) |

## Diagrama de Clases

```
┌─────────────────────────────────────────────────────────────────────────┐
│                            ARQUITECTURA MVC                              │
└─────────────────────────────────────────────────────────────────────────┘

                          ┌──────────────────┐
                          │      Main        │
                          │   (Punto Entrada)│
                          └────────┬─────────┘
                                   │
                  ┌────────────────┼────────────────┐
                  ↓                ↓                ↓
            ┌─────────┐    ┌──────────────┐    ┌──────────────┐
            │ Modelo  │    │MotorJuego    │    │VentanaPrinc. │
            └─────────┘    │(Controlador) │    │   (Vista)    │
                           └──────────────┘    └──────────────┘
                                   ↑                    ↑
                                   └────────┬───────────┘
                                            │
                                    ┌───────┴────────┐
                                    ↓                ↓
                              ┌──────────┐    ┌──────────────┐
                              │PanelMenu │    │ PanelJuego   │
                              │ (Vista)  │    │   (Vista)    │
                              └──────────┘    └──────────────┘


┌────────────────────────────────────────────────────────────────────┐
│                          DIAGRAMA UML                               │
└────────────────────────────────────────────────────────────────────┘

                        ┌─────────────────┐
                        │    <<Main>>     │
                        │                 │
                        ├─────────────────┤
                        │                 │
                        ├─────────────────┤
                        │ + main(String[])│
                        └────────┬────────┘
                                 │ crea
                                 ↓
┌──────────────────────┐  ┌──────────────────────┐  ┌───────────────────┐
│      Modelo          │  │   MotorJuego         │  │ VentanaPrincipal  │
├──────────────────────┤  ├──────────────────────┤  ├───────────────────┤
│ + nombreJugador:Str  │  │ - modelo: Modelo     │  │ + panelMenu       │
│ + personajeY: int    │  │ - vista: VentanaP.   │  │ + panelJuego      │
│ + obstaculoX: int    │◄─┤ - timer: Timer       │─→│ + contenedor      │
│ + puntuacion: int    │  │                      │  │ + tarjetas        │
│ + enJuego: boolean   │  ├──────────────────────┤  ├───────────────────┤
│ + velocidadObstaculo │  │ + iniciarJuego()     │  │VentanaPrincipal() │
├──────────────────────┤  │ + actionPerformed()  │  │                   │
│                      │  │ + finalizarJuego()   │  │ <<implements>>    │
│ + reiniciar()        │  │ + keyPressed()       │  │   ActionListener  │
│                      │  │ + keyReleased()      │  │   KeyListener     │
└──────────────────────┘  │ + keyTyped()         │  └───────────────────┘
          ▲               └──────────────────────┘           ↑
          │ actualiza                                        │ contains
          │                                                  │
          └──────────────────┬───────────────────────────────┘
                             │
                    ┌────────┴────────┐
                    ↓                 ↓
            ┌──────────────┐    ┌─────────────────┐
            │ PanelMenu    │    │  PanelJuego     │
            ├──────────────┤    ├─────────────────┤
            │ + btnJugar   │    │ - modelo:Modelo │
            │ + txtNombre  │    │ - imgProtag.    │
            ├──────────────┤    ├─────────────────┤
            │              │    │ + actualizar()  │
            │ Panel común  │    │ + paintComponent│
            │              │    │                 │
            └──────────────┘    │ Panel común     │
                                │                 │
                                └─────────────────┘

          ┌─────────────────────────────────────────────────┐
          │  Relaciones                                      │
          ├─────────────────────────────────────────────────┤
          │ → Composición (contains)                        │
          │ ← Dependencia (usa/accede)                      │
          │ ▲ Herencia (extends)                            │
          │ ◆ Implementación (implements)                   │
          └─────────────────────────────────────────────────┘


┌──────────────────────────────────────────────────────────────────┐
│              FLUJO DE DATOS DEL PROGRAMA                         │
└──────────────────────────────────────────────────────────────────┘

     Main
       │
       ├─ Crea Modelo
       │   (estado del juego)
       │
       ├─ Crea VentanaPrincipal
       │   (interfaz gráfica)
       │
       └─ Crea MotorJuego
           (controlador)
               │
               ├─ Lee input (KeyListener)
               │   └─ SPACE → Salta personaje
               │
               ├─ Timer cada 20ms (ActionListener)
               │   ├─ Mueve obstáculos
               │   ├─ Aplica gravedad
               │   ├─ Detecta colisiones
               │   ├─ Suma puntos
               │   └─ Aumenta velocidad
               │
               └─ Actualiza vista (repaint)
                   └─ PanelJuego dibuja todo


┌──────────────────────────────────────────────────────────────────┐
│                    COMPONENTES VISUALES                          │
└──────────────────────────────────────────────────────────────────┘

VentanaPrincipal (800x500)
│
├─ CardLayout (tarjetas)
│  │
│  ├─ PanelMenu ("MENU")
│  │  ├─ Título: "INFINITY RUNNER"
│  │  ├─ TextField: txtNombre
│  │  └─ Button: btnJugar
│  │
│  └─ PanelJuego ("JUEGO")
│     ├─ Fondo: Verde Neón (RGB 57, 255, 20)
│     ├─ Suelo: Verde oscuro (Y=350)
│     ├─ Personaje: Imagen o rectángulo rojo
│     ├─ Obstáculo: Rectángulo negro
│     ├─ Puntos: Texto azul (arriba izquierda)
│     └─ [Velocidad: Oculta]
```

## Estructura de Directorios

```
InfinityRunner/
├── src/
│   ├── main/
│   │   └── Main.java              # Punto de entrada
│   ├── model/
│   │   └── Modelo.java            # Lógica del juego
│   ├── controller/
│   │   └── MotorJuego.java        # Controlador
│   ├── view/
│   │   ├── VentanaPrincipal.java  # Ventana principal
│   │   ├── PanelMenu.java         # Menú
│   │   └── PanelJuego.java        # Panel de juego
│   └── Doc/
│       ├── README.md              # Este archivo
│       ├── GDD.md                 # Game Design Document
│       └── Design.md              # Architecture Document
└── resources/
    └── Protagonista.png           # Imagen del personaje
```

## Requisitos

- **Java**: JDK 8 o superior
- **Bibliotecas**: Swing (incluido en JDK)
- **Sistema Operativo**: Windows, macOS, Linux

## Compilación

```bash
cd src
javac -encoding UTF-8 main/Main.java
```

## Ejecución

```bash
cd src
java main.Main
```

## Estadísticas del Código

| Métrica                  | Valor                             |
| ------------------------ | --------------------------------- |
| Clases                   | 6                                 |
| Métodos                  | 15+                               |
| Líneas de Código         | ~450                              |
| Packages                 | 4 (main, model, controller, view) |
| FPS                      | 50                                |
| Velocidad Base           | 8 píxeles/frame                   |
| Incremento cada 3 puntos | +1 píxel/frame                    |

## Mecánicas de Juego

### Movimiento

- El personaje se encuentra fijo en X = 50-90
- Salta verticalmente al presionar SPACE (altura: 150px)
- Cae por gravedad simula (5px por frame)

### Obstáculos

- Aparecen en X = 800 (derecha)
- Se mueven hacia X = 0 (izquierda)
- Velocidad inicial: 8 píxeles/frame
- Velocidad aumenta cada 3 obstáculos evitados
- Desaparecen en X < -20

### Colisiones

- Contacto con obstáculo = Game Over
- Se detectan por rango: X (50-90) y Y (>300)

### Puntuación

- +1 punto por cada obstáculo completamente evitado
- Se muestra en tiempo real
- Se reinicia en cada partida

## Futuras Mejoras

- [ ] Sistema de récords persistente (archivo JSON)
- [ ] Pantalla de ranking
- [ ] Power-ups y bonificaciones
- [ ] Múltiples tipos de obstáculos
- [ ] Efectos de sonido
- [ ] Animaciones mejoradas
- [ ] Multijugador online
- [ ] Sistema de logros

## Documentación Adicional

- **[GDD.md](GDD.md)** - Game Design Document completo
- **[Design.md](Design.md)** - Documento de arquitectura técnica

## Licencia

Este proyecto es de código abierto para propósitos educativos.

## Autor

Development Team - InfinityRunner
Marzo 2026

---

## Ingeniería Inversa del Sistema

Esta sección describe el análisis técnico y la arquitectura interna del juego InfinityRunner.

### Arquitectura General del Sistema

```
┌─────────────────────────────────────────────────────────────────┐
│                    FLUJO DE CONTROL GENERAL                      │
└─────────────────────────────────────────────────────────────────┘

Main.main()
    ↓
Crea instancias:
    ├─ Modelo modelo = new Modelo()
    ├─ VentanaPrincipal vista = new VentanaPrincipal()
    └─ MotorJuego motor = new MotorJuego(modelo, vista)
        ↓
    MotorJuego inicializa:
        ├─ Timer (20ms) → actionPerformed()
        ├─ KeyListener → keyPressed(KeyEvent)
        └─ ActionListener → botones del menú
```

### Ciclo Principal del Juego

```
CICLO DEL TIMER (cada 20ms = 50 FPS)
    │
    └─ Si modelo.enJuego == true:
        ├─ 1. Mover obstáculos
        │      modelo.obstaculoX -= modelo.velocidadObstaculo
        │
        ├─ 2. Aplicar gravedad
        │      if (personajeY < 300) personajeY += 5
        │
        ├─ 3. Verificar colisiones
        │      if (obstaculoX < 90 && obstaculoX > 50 && personajeY + 50 > 300)
        │         finalizarJuego()
        │
        ├─ 4. Sumar puntos
        │      if (obstaculoX < -20) {
        │         obstaculoX = 800
        │         puntuacion++
        │      }
        │
        ├─ 5. Aumentar dificultad
        │      if (puntuacion % 3 == 0)
        │         velocidadObstaculo++
        │
        └─ 6. Redibujar pantalla
               vista.panelJuego.actualizar(modelo)
               → paintComponent() dibuja todo
```

### Estructura de Datos Clave (Modelo.java)

```java
public class Modelo {
    // ESTADO DEL JUEGO
    public boolean enJuego;                    // Flag: juego activo
    public String nombreJugador;               // Nombre ingresado

    // POSICIONES Y MOVIMIENTO
    public int personajeY;                     // Y del personaje (0-500)
    public int obstaculoX;                     // X del obstáculo (0-800+)
    public int obstaculoY = 300;               // Y fijo del obstáculo
    public int velocidadObstaculo = 8;         // Píxeles/frame

    // OBSTÁCULO DEL CIELO
    public boolean obstaculoCieloActivo;       // Flag: activo
    public int obstaculoCieloX;                // X del obstáculo cielo
    public int obstaculoCieloY = 140;          // Y fijo del obstáculo cielo

    // PUNTUACIÓN
    public int puntuacion;                     // Puntos acumulados

    // MÉTODOS
    public void reiniciar() {
        // Resetea todos los valores al estado inicial
    }
}
```

### Flujo de Eventos de Entrada

```
KeyEvent (Usuario presiona SPACE)
    ↓
MotorJuego.keyPressed(KeyEvent e)
    ↓
if (e.getKeyCode() == KeyEvent.VK_SPACE &&
    modelo.personajeY >= 300)
    ├─ modelo.personajeY -= 150  // Salta inmediatamente
    └─ En el siguiente ciclo, la gravedad lo bajará gradualmente
```

### Detección de Colisiones - Análisis Matemático

```
COLISIÓN CON OBSTÁCULO (SUELO):
    ├─ Condición: obstaculoX < 90 &&
    │             obstaculoX > 50 &&
    │             (personajeY + 50) > 300
    │
    ├─ Explicación:
    │   • X del personaje: 50-110 (ancho 60px)
    │   • X del obstáculo: 0-800 (ancho 30px)
    │   • Colisión si su rango X se superpone: [50,90]
    │   • Y del personaje: 0-500
    │   • Colisión si Y+altura > 300 (zona del suelo)
    │
    └─ Resultado: finalizarJuego()

COLISIÓN CON OBSTÁCULO (CIELO):
    ├─ Condición: obstaculoCieloX < 90 &&
    │             obstaculoCieloX > 50 &&
    │             personajeY < 140
    │
    ├─ Explicación:
    │   • Mismo chequeo X que arriba
    │   • Si personaje está muy arriba (Y < 140)
    │   • El obstáculo del cielo está en Y = 140 (altura 35px)
    │
    └─ Resultado: finalizarJuego()
```

### Sistema de Puntuación y Dificultad

```
EVOLUCIÓN A LO LARGO DEL JUEGO:

Punto 0:   velocidadObstaculo = 8 p/f
Punto 3:   velocidadObstaculo = 9 p/f (aumento)
Punto 5:   obstaculoCieloActivo = true (aparece obstáculo del cielo)
Punto 6:   velocidadObstaculo = 10 p/f
Punto 9:   velocidadObstaculo = 11 p/f
Punto 10:  obstaculoCieloActivo = true (nuevo ciclo)
...

FÓRMULA: velocidadObstaculo = 8 + floor(puntuacion / 3)
         obstaculoCieloActivo = true cada 5 puntos
```

### Transformación de Estados

```
MÁQUINA DE ESTADOS:

[MENÚ]
  │ clicks btnJugar
  ↓
[JUEGO]
  │ timer corre
  │ salta personaje
  │ se mueven obstáculos
  │ detecta colisión
  ↓
[RESULTADOS]
  │ muestra score
  │ clicks btnReiniciar ──→ vuelve a [JUEGO]
  │ clicks btnVolverAlMenu ──→ vuelve a [MENÚ]
```

### Gestión de Memoria

```
OBJETOS PRINCIPALES EN MEMORIA:

1. Main
   └─ Modelo (1 instancia)
   └─ VentanaPrincipal (1 instancia)
      ├─ PanelMenu (1 instancia)
      │  ├─ JButton btnJugar
      │  └─ JTextField txtNombre
      ├─ PanelJuego (1 instancia)
      │  ├─ PanelCanvasJuego (1 instancia interno)
      │  ├─ BufferedImage imgProtagonista
      │  ├─ BufferedImage imgFondo
      │  └─ JLabel lblPuntuacion
      └─ PanelResultados (1 instancia)
         ├─ JLabel lblNombreJugador
         ├─ JLabel lblPuntuacion
         ├─ JButton btnReiniciar
         └─ JButton btnVolverAlMenu
   └─ MotorJuego (1 instancia)
      └─ Timer (1 instancia)
```

### Comunicación Entre Componentes

```
PATRÓN OBSERVADOR/MVC:

Modelo (datos)
    ↑
    │ modifica
    │
MotorJuego (controlador)
    │
    └─→ Vista (VentanaPrincipal)
        ├─ PanelMenu (muestra entrada)
        ├─ PanelJuego (muestra estado)
        └─ PanelResultados (muestra resultados)

FLUJO:
1. Usuario input → MotorJuego.keyPressed()
2. MotorJuego modifica Modelo
3. MotorJuego llama vista.panelJuego.actualizar(modelo)
4. Panel redibuja con new Modelo datos
```

### Optimizaciones Realizadas

| Aspecto    | Técnica                 | Beneficio          |
| ---------- | ----------------------- | ------------------ |
| Rendering  | Doble buffering (Swing) | Sin parpadeos      |
| FPS        | Timer de 20ms           | 50 FPS constantes  |
| Física     | Valores enteros         | Cálculos rápidos   |
| Colisiones | Rango boxes             | O(1) por frame     |
| Memoria    | CardLayout              | Solo panel visible |
| Graphics   | Graphics2D antialiasing | Mejor visual       |

### Puntos de Extensión Futuros

```
1. Sistema de Sonido
   ├─ SoundManager.java
   ├─ Efectos: salto, colisión, punto
   └─ Música de fondo

2. Sistema de Récords
   ├─ FileManager.java (JSON/Properties)
   ├─ Persistencia en disco
   └─ Leaderboard

3. Power-ups
   ├─ PowerUp.java (clase)
   ├─ Tipos: escudo, ralentización, puntos
   └─ Detección de colisión adicional

4. Animaciones
   ├─ AnimationManager.java
   ├─ Sprites del personaje
   └─ Efectos visuales (polvillo, etc)

5. Modos de Juego
   ├─ Modo clásico
   ├─ Modo tiempo limitado
   └─ Modo desafío
```

---

**¿Necesitas ayuda?** Consulta los documentos GDD.md o Design.md para más detalles técnicos.
