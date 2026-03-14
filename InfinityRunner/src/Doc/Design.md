# Design Document - InfinityRunner

## 1. Visión General de la Arquitectura

**InfinityRunner** sigue el patrón **Model-View-Controller (MVC)** para separar responsabilidades y facilitar el mantenimiento y extensión del código.

```
┌─────────────────────────────────────────────────┐
│                   MAIN                           │
│          Punto de entrada de la aplicación       │
└─────────────────────────────────────────────────┘
                        ↓
        ┌───────────────┼───────────────┐
        ↓               ↓               ↓
    ┌───────┐      ┌────────┐      ┌──────────┐
    │ MODEL │◄─────┤CONTROLLER├─────►│   VIEW   │
    └───────┘      └────────┘      └──────────┘
   Lógica del         Motor          Interfaz
    Juego            Gráfica         Gráfica
```

---

## 2. Estructura de Componentes

### 2.1 Estructura de Directorios

```
InfinityRunner/
├── src/
│   ├── main/
│   │   └── Main.java                 # Punto de entrada
│   ├── model/
│   │   └── Modelo.java               # Lógica del juego
│   ├── controller/
│   │   └── MotorJuego.java           # Controlador principal
│   └── view/
│       ├── VentanaPrincipal.java     # Ventana principal
│       ├── PanelMenu.java            # Panel del menú
│       └── PanelJuego.java           # Panel de juego
├── GDD.md                            # Game Design Document
└── Design.md                         # Este archivo
```

---

## 3. Descripción de Clases

### 3.1 Main.java
**Responsabilidad**: Punto de entrada de la aplicación

```java
public class Main {
    public static void main(String[] args)
}
```

**Responsabilidades:**
- Instanciar el Modelo
- Instanciar la Vista (VentanaPrincipal)
- Instanciar el Controlador (MotorJuego)
- Inicializar las conexiones entre componentes

---

### 3.2 Modelo.java
**Responsabilidad**: Almacenar y gestionar el estado del juego

```java
public class Modelo {
    public String nombreJugador;      // Nombre del jugador actual
    public int personajeY;            // Posición Y del personaje
    public int obstaculoX;            // Posición X del obstáculo
    public int puntuacion;            // Puntos acumulados en la partida
    public boolean enJuego;           // Estado: ¿está el juego activo?
    
    public void reiniciar()           // Reset de valores para nueva partida
}
```

**Atributos:**
| Atributo | Tipo | Descripción | Rango |
|----------|------|-------------|-------|
| `nombreJugador` | String | Nombre ingresado en el menú | - |
| `personajeY` | int | Posición vertical del personaje | 0-500 píxeles |
| `obstaculoX` | int | Posición horizontal del obstáculo | -20 a 800+ píxeles |
| `puntuacion` | int | Puntos acumulados | 0+ |
| `enJuego` | boolean | Indica si la partida está en curso | true/false |

**Métodos:**
- `reiniciar()`: Resetea todos los valores para iniciar una nueva partida

---

### 3.3 MotorJuego.java
**Responsabilidad**: Controlador que gestiona la lógica de juego y entrada del usuario

```java
public class MotorJuego implements ActionListener, KeyListener {
    private Modelo modelo;
    private VentanaPrincipal vista;
    private Timer timer;
    
    public MotorJuego(Modelo modelo, VentanaPrincipal vista)
    private void iniciarJuego()
    public void actionPerformed(ActionEvent e)
    private void finalizarJuego()
    public void keyPressed(KeyEvent e)
    public void keyReleased(KeyEvent e)
    public void keyTyped(KeyEvent e)
}
```

**Interfaz Implementada**: 
- `ActionListener`: Para eventos del Timer (actualización del juego)
- `KeyListener`: Para eventos de teclado (entrada del jugador)

**Atributos:**
| Atributo | Tipo | Descripción |
|----------|------|-------------|
| `modelo` | Modelo | Referencia al modelo de datos |
| `vista` | VentanaPrincipal | Referencia a la vista principal |
| `timer` | Timer | Timer que actualiza el juego cada 20ms |

**Métodos:**

| Método | Descripción |
|--------|-------------|
| `iniciarJuego()` | Obtiene el nombre del jugador, reinicia el modelo y cambia a la vista de juego |
| `actionPerformed()` | Actualiza la lógica del juego (movimiento obstáculos, gravedad, colisiones) |
| `finalizarJuego()` | Detiene el juego, muestra puntuación y regresa al menú |
| `keyPressed()` | Detecta SPACE para hacer saltar al personaje |
| `keyReleased()` | Sin implementación actual |
| `keyTyped()` | Sin implementación actual |

**Flujo de Lógica en actionPerformed():**
```
┌─────────────────────────────────────┐
│   Timer (cada 20ms)                 │
└─────────────────────────────────────┘
                 ↓
        ┌─────────────────┐
        │ ¿Juego activo?  │
        └────────┬────────┘
         No→ Retornar
                 │ Sí
                 ↓
    ┌────────────────────────┐
    │ Mover obstáculo -8px   │
    └───────────┬────────────┘
                ↓
    ┌────────────────────────────┐
    │ ¿Obstáculo fuera pantalla?  │
    └───────────┬───────────────┬┘
     No→ Continuar    Sí→ Reiniciar X, +1 punto
                ↓
    ┌────────────────────────┐
    │ Aplicar gravedad (+5Y) │
    └───────────┬────────────┘
                ↓
    ┌────────────────────────┐
    │ Detectar colisiones    │
    └───────────┬────────────┘
                ↓
    ┌────────────────────────┐
    │ Actualizar pantalla    │
    └────────────────────────┘
```

---

### 3.4 VentanaPrincipal.java
**Responsabilidad**: Ventana principal y contenedor de paneles

```java
public class VentanaPrincipal extends JFrame {
    public CardLayout tarjetas;
    public JPanel contenedor;
    public PanelMenu panelMenu;
    public PanelJuego panelJuego;
    
    public VentanaPrincipal()
}
```

**Características:**
- Hereda de `JFrame`
- Usa `CardLayout` para cambiar entre pantallas (menú/juego)
- Tamaño: 800×500 píxeles
- Titulo: "Java Runner: Individual Edition"

**Layout:**
```
VentanaPrincipal (JFrame)
├── contenedor (JPanel - CardLayout)
│   ├── panelMenu (PanelMenu) [ID: "MENU"]
│   └── panelJuego (PanelJuego) [ID: "JUEGO"]
```

---

### 3.5 PanelMenu.java
**Responsabilidad**: Panel de interfaz del menú principal

**Componentes esperados:**
- Título del juego
- Campo de texto para ingresar nombre (`txtNombre`)
- Botón "Jugar" (`btnJugar`)
- Botón "Ver Récords" (futuro)

**Interfaz:**
```
┌─────────────────────────┐
│   INFINITY RUNNER       │
│ Individual Edition      │
│                         │
│ Nombre del Jugador:     │
│ [_________________]     │
│                         │
│    [JUGAR] [RÉCORDS]    │
└─────────────────────────┘
```

---

### 3.6 PanelJuego.java
**Responsabilidad**: Panel donde se dibuja el juego

**Métodos clave esperados:**
- `actualizar(Modelo modelo)`: Actualiza el estado visual basado en el modelo
- `paintComponent(Graphics g)`: Dibuja todos los elementos del juego

**Elementos a Dibujar:**
1. Fondo (Color sólido)
2. Plataforma base (línea/rectángulo en Y=300)
3. Personaje (rectángulo en X≈60-90, Y=personajeY)
4. Obstáculo (rectángulo en X=obstaculoX, Y=300)
5. Puntuación (texto en esquina superior)
6. Nombre del jugador (texto en esquina)

**Flujo de renderizado:**
```
actualizar(modelo)
    ↓
Guardar estado del modelo
    ↓
repaint()
    ↓
paintComponent()
    ↓
Dibujar fondo
Dibujar plataforma
Dibujar personaje
Dibujar obstáculo
Dibujar puntuación
```

---

## 4. Flujo de Datos

### 4.1 Inicialización
```
Main.main()
    ↓
    ├─ new Modelo()
    ├─ new VentanaPrincipal()
    │   └─ Crea panelMenu y panelJuego
    └─ new MotorJuego(modelo, vista)
        ├─ Vincula listener a btnJugar
        └─ Añade KeyListener a la ventana
```

### 4.2 Inicio de Partida
```
Usuario clicks [JUGAR]
    ↓
btnJugar.ActionListener dispara
    ↓
iniciarJuego()
    ├─ Obtiene nombre del texto
    ├─ modelo.reiniciar()
    ├─ Cambia vista a JUEGO
    └─ timer.start()
```

### 4.3 Loop de Juego (cada 20ms)
```
Timer.actionPerformed()
    ↓
    ├─ Mueve obstáculo
    ├─ Maneja colisiones
    ├─ Aplica gravedad
    ├─ Suma puntos
    └─ vista.panelJuego.actualizar(modelo)
        └─ Redibuja pantalla
```

### 4.4 Entrada del Usuario
```
Usuario presiona SPACE
    ↓
keyListener.keyPressed()
    ↓
¿Personaje en suelo? (Y >= 300)
    ├─ Sí → modelo.personajeY -= 150
    └─ No → Ignorar (no salto múltiple)
```

### 4.5 Fin de Partida
```
Colisión detectada
    ↓
finalizarJuego()
    ├─ timer.stop()
    ├─ modelo.enJuego = false
    ├─ Mostrar dialog con puntuación
    └─ Cambiar vista a MENU
```

---

## 5. Patrones de Diseño Utilizados

### 5.1 Patrón MVC
- **Separación de responsabilidades**
- **Facilita testing y mantenimiento**
- **Permite cambiar vistas sin afectar lógica**

### 5.2 Observer (Swing)
- `ActionListener` en Timer para actualizaciones periódicas
- `KeyListener` para entrada del usuario
- Los listeners están desacoplados de la lógica

### 5.3 CardLayout
- Gestión de múltiples pantallas
- Cambio fluido entre menú y juego
- Facilita añadir nuevas pantallas (ranking, pausa, etc.)

---

## 6. Constantas Técnicas

| Concepto | Valor | Notas |
|----------|-------|-------|
| **Frame Rate** | 50 FPS | Timer de 20ms |
| **Velocidad Obstáculo** | 8 píxeles/frame | 400 píxeles/segundo |
| **Altura Salto** | 150 píxeles | Fija |
| **Gravedad** | 5 píxeles/frame | 250 píxeles/segundo |
| **Ancho Pantalla** | 800 píxeles | - |
| **Alto Pantalla** | 500 píxeles | - |
| **Posición Plataforma** | Y = 300 | - |
| **Posición Personaje** | X = 50-90 | Fija horizontalmente |
| **Spawn Obstáculo** | X = 800 | Fuera de pantalla |
| **Tiempo Entre Obstáculos** | ~4.5 segundos | A velocidad actual |

---

## 7. Decisiones de Diseño

### 7.1 ¿Por qué MVC?
- **Ventaja**: Claridad sobre qué componente hace qué
- **Ventaja**: Fácil de testear cada componente
- **Ventaja**: Permite cambios futuros sin afectar todo

### 7.2 ¿Por qué Timer en lugar de Thread?
- **Ventaja**: Timing más preciso en Swing
- **Ventaja**: Thread-safe para operaciones de GUI
- **Ventaja**: Integración natural con eventos

### 7.3 ¿Por qué CardLayout?
- **Ventaja**: Cambio de pantalla fluido
- **Ventaja**: Ambos paneles existen al mismo tiempo
- **Ventaja**: Facilita animaciones de transición futuras

### 7.4 ¿Por qué atributos públicos en Modelo?
- **Ventaja Actual**: Acceso directo y simple
- **Mejora Futura**: Cambiar a getters/setters con validación

---

## 8. Problemas Potenciales y Mejoras Futuras

### 8.1 Problemas Actuales
| Problema | Impacto | Solución |
|----------|--------|----------|
| Posición X personaje no controlable | Limitado | Agregar movimiento izq/derecha |
| Un solo obstáculo a la vez | Fácil | Generar múltiples obstáculos |
| Colisión simple (sin offset) | Impreciso | Usar rectángulos de colisión mejores |
| Sin persistencia de datos | Crítico | Guardarsistema de archivos JSON |
| Dificultad constante | Aburidor | Aumentar velocidad con tiempo |

### 8.2 Mejoras Propuestas

**Corto Plazo:**
1. Sistema de récords con archivo persistente
2. Pantalla de ranking
3. Dificultad progresiva
4. Múltiples obstáculos

**Mediano Plazo:**
1. Power-ups
2. Diferentes tipos de obstáculos
3. Efectos de sonido
4. Mejoras visuales (sprites, animaciones)

**Largo Plazo:**
1. Multijugador online
2. Sistema de logros
3. Temas personalizables
4. Editor de niveles

---

## 9. Guía de Extensión

### 9.1 Agregar Nueva Pantalla
```
1. Crear clase que extienda JPanel
   public class PanelNuevo extends JPanel { }

2. Agregarla a VentanaPrincipal
   public PanelNuevo panelNuevo = new PanelNuevo();
   contenedor.add(panelNuevo, "NUEVO");

3. Cambiar a la pantalla desde MotorJuego
   vista.tarjetas.show(vista.contenedor, "NUEVO");
```

### 9.2 Agregar Nueva Mecánica
```
1. Agregar atributos al Modelo
   public int velocidadObstaculo = 8;

2. Usar en MotorJuego.actionPerformed()
   modelo.obstaculoX -= modelo.velocidadObstaculo;

3. Actualizar visualización en PanelJuego
   // Dibujar basado en nuevos valores
```

### 9.3 Agregar Sistema de Almacenamiento
```
1. Crear clase Persistencia
   public class GestorRecords { }

2. Métodos: guardarPartida(), cargarRecords()

3. Integrar en MotorJuego.finalizarJuego()
   gestorRecords.guardarPartida(
       modelo.nombreJugador, 
       modelo.puntuacion
   );
```

---

## 10. Dependencias

### 10.1 Externas
- **JDK**: 8 o superior
- **Swing**: Incluido en JDK

### 10.2 Internas
```
Main
  ├─→ Modelo
  ├─→ VentanaPrincipal
  │   ├─→ PanelMenu
  │   └─→ PanelJuego
  └─→ MotorJuego
      ├─→ Modelo
      └─→ VentanaPrincipal
          ├─→ PanelMenu
          └─→ PanelJuego
```

---

## 11. Compilación y Ejecución

### 11.1 Compilar
```bash
cd src
javac main/Main.java
```

### 11.2 Ejecutar
```bash
java main.Main
```

### 11.3 Estructura de Packages
```
main.Main (punto de entrada)
model.Modelo
controller.MotorJuego
view.VentanaPrincipal
view.PanelMenu
view.PanelJuego
```

---

## 12. Diagrama UML Simplificado

```
┌──────────────────────┐
│      Modelo          │
├──────────────────────┤
│ + nombreJugador      │
│ + personajeY         │
│ + obstaculoX         │
│ + puntuacion         │
│ + enJuego            │
├──────────────────────┤
│ + reiniciar()        │
└──────────────────────┘
           ▲
           │ usa
           │
┌──────────────────────────────┐
│      MotorJuego              │
├──────────────────────────────┤
│ - modelo: Modelo             │
│ - vista: VentanaPrincipal    │
│ - timer: Timer               │
├──────────────────────────────┤
│ + iniciarJuego()             │
│ + actionPerformed()          │
│ + finalizarJuego()           │
│ + keyPressed()               │
└──────────────────────────────┘
           ▲
           │ usa
           │
┌──────────────────────────────┐
│   VentanaPrincipal           │
├──────────────────────────────┤
│ + panelMenu: PanelMenu       │
│ + panelJuego: PanelJuego     │
│ + contenedor: JPanel         │
│ + tarjetas: CardLayout       │
└──────────────────────────────┘
      ▲           ▲
      │ contiene  │
      │           │
┌──────────┐  ┌──────────────┐
│PanelMenu │  │ PanelJuego   │
└──────────┘  └──────────────┘
```

---

## 13. QA y Testing

### 13.1 Casos de Prueba Manual
- [ ] Iniciar juego y ingresar nombre
- [ ] SPACE salta correctamente
- [ ] Obstáculos se mueven a velocidad constante
- [ ] Colisión termina el juego
- [ ] Puntuación aumenta correctamente
- [ ] Regresar al menú muestra puntuación

### 13.2 Bugs Conocidos
- Ninguno reportado actualmente

---

**Versión**: 1.0  
**Fecha**: Marzo 2026  
**Autor**: Development Team InfinityRunner
