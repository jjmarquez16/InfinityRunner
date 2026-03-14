# InfinityRunner: Individual Edition

Bienvenido a **InfinityRunner**, un juego arcade de plataformas infinitas donde el objetivo es saltar entre obstáculos y acumular la mayor cantidad de puntos posible antes de perder.

## Descripción del Juego

InfinityRunner es un juego de carreras infinitas basado en saltos donde:
- 🎮 Controlas un personaje que salta evitando obstáculos
- 📊 Acumulas puntos por cada obstáculo evitado
- ⚡ La dificultad aumenta progresivamente cada 3 obstáculos
- 🏆 Comparte tus mejores puntuaciones

## Características

- ✅ Mecánica simple y adictiva (solo usa SPACE)
- ✅ Dificultad progresiva automática
- ✅ Interfaz gráfica intuitiva con Swing
- ✅ Sistema de puntuación en tiempo real
- ✅ Arquitectura MVC bien estructurada

## Cómo Jugar

1. **Iniciar el juego**: Ejecuta `java main.Main`
2. **Ingresa tu nombre** en el menú
3. **Presiona SPACE** para saltar
4. **Evita los obstáculos** que se acercan
5. **Acumula puntos** saltando sobre obstáculos
6. **Compite** por el mejor record

## Controles

| Tecla | Acción |
|-------|--------|
| SPACE | Saltar |
| ESC | Salir (futuro) |

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

| Métrica | Valor |
|---------|-------|
| Clases | 6 |
| Métodos | 15+ |
| Líneas de Código | ~450 |
| Packages | 4 (main, model, controller, view) |
| FPS | 50 |
| Velocidad Base | 8 píxeles/frame |
| Incremento cada 3 puntos | +1 píxel/frame |

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

**¿Necesitas ayuda?** Consulta los documentos GDD.md o Design.md para más detalles técnicos.
