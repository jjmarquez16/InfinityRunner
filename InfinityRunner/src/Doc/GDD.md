# Game Design Document - InfinityRunner

## 1. Información General

| Aspecto | Descripción |
|--------|-------------|
| **Título del Juego** | InfinityRunner: Individual Edition |
| **Género** | Arcade / Endless Runner |
| **Plataforma** | PC (Java Swing) |
| **Público Objetivo** | Jugadores casuales de todas las edades |
| **Modo de Juego** | Un jugador (Single Player) |

---

## 2. Visión General

**InfinityRunner** es un juego de carreras infinitas basado en saltos donde el jugador controla un personaje que debe esquivar obstáculos mientras salta entre plataformas. El objetivo es acumular la mayor cantidad de puntos posible sin colisionar con los obstáculos. Es un juego de corta duración orientado a conseguir el récord personal.

---

## 3. Mecánica de Juego

### 3.1 Movimiento del Personaje
- **Control Principal**: Barra espaciadora para saltar
- **Altura de Salto**: Fija (150 píxeles)
- **Posición Base**: El personaje permanece horizontalmente en el centro-izquierda de la pantalla (coordenada X = 50-90)
- **Gravedad**: Atracción gravitatoria simple que mantiene al personaje en la plataforma base (Y = 300)
- **Velocidad de Caída**: 5 píxeles por frame

### 3.2 Sistema de Obstáculos
- **Tipo**: Objetos que se desplazan horizontalmente de derecha a izquierda
- **Velocidad**: 8 píxeles por frame
- **Posición de Spawn**: Coordenada X = 800 (fuera de pantalla a la derecha)
- **Posición de Eliminación**: X < -20 (fuera de pantalla a la izquierda)
- **Rango de Peligro**: X: 50-90, Y: 300 (colisión con personaje)

### 3.3 Sistema de Puntuación
- **Puntos por Obstáculo Evitado**: +1 punto
- **Condición**: El obstáculo debe pasar completamente sin colisionar
- **Reinicio**: La puntuación se reinicia a 0 en cada nueva partida

---

## 4. Flujo de Juego

```
INICIO
   ↓
┌─────────────────────────┐
│   PANTALLA DE MENÚ      │
│  - Nombre del Jugador   │
│  - Botón "Jugar"        │
└─────────────────────────┘
   ↓
┌─────────────────────────┐
│   DURANTE LA PARTIDA    │
│  - Obstáculos se mueven │
│  - Jugador salta        │
│  - Se suman puntos      │
└─────────────────────────┘
   ↓
┌─────────────────────────┐
│   CONDICIÓN DE PÉRDIDA  │
│  Colisión = Game Over   │
└─────────────────────────┘
   ↓
┌─────────────────────────┐
│   PANTALLA DE GAME OVER │
│  - Mostrar puntos       │
│  - Reiniciar juego      │
└─────────────────────────┘
   ↓
VOLVER AL MENÚ
```

---

## 5. Dinámicas de Juego

### 5.1 Curva de Dificultad
- **Actual**: Dificultad constante (obstáculos a velocidad fija)
- **Frecuencia de Obstáculos**: Uno cada aproximadamente 4.5 segundos

### 5.2 Desafío del Jugador
- **Timing**: El jugador debe calcular el momento exacto para saltar
- **Precisión**: La altura de salto es fija, requiriendo sincronización perfecta
- **Reflejos**: A medida que aumentan los puntos, la presión psicológica aumenta

---

## 6. Interfaz de Usuario

### 6.1 Pantalla de Menú
```
┌──────────────────────────┐
│   JAVA RUNNER            │
│  Individual Edition       │
│                          │
│  Ingrese su nombre:      │
│  [________________]      │
│         [JUGAR]          │
└──────────────────────────┘
```

### 6.2 Pantalla de Juego
```
┌──────────────────────────┐
│  Puntos: [XX]            │
│                          │
│                          │
│  ◼ (personaje)          |
│═════════════════════════ │
│  │ (obstáculo)         │
│                          │
└──────────────────────────┘
```

### 6.3 Pantalla de Game Over
```
Dialog:
¡Game Over, [Nombre]!
Puntos: [XX]

[Aceptar]
→ Regresa al Menú
```

---

## 7. Personaje

### 7.1 Protagonista
- **Tipo**: Corredor/Saltador
- **Tamaño**: 40 × 50 píxeles (estimado)
- **Posición Fija**: Horizontalmente en X = 50-90
- **Animación**: Cambio de posición Y (salto simple)

### 7.2 Comportamiento
- Solo se mueve verticalmente (arriba/abajo)
- Puede saltar cuando está en la plataforma base (Y ≥ 300)
- Cae automáticamente por gravedad

---

## 8. Enemigos/Obstáculos

### 8.1 Obstáculo Genérico
- **Representación**: Rectángulo/Objeto visual
- **Tamaño**: 20 × 50 píxeles (estimado)
- **Velocidad**: 8 píxeles por frame (constante)
- **Comportamiento**: Movimiento horizontal, eliminación al salir de pantalla
- **Colisión**: Cualquier contacto con el personaje = fin del juego

---

## 9. Sistema de Puntuación y Récords

### 9.1 Puntuación Actual
- **Método de Cálculo**: +1 por cada obstáculo completamente evitado
- **Visualización**: En tiempo real durante la partida

### 9.2 Sistema de Récords (PENDIENTE DE IMPLEMENTACIÓN)
- **Almacenamiento**: Archivo persistente o Base de datos
- **Información Guardada**: 
  - Nombre del jugador
  - Puntuación alcanzada
  - Fecha/Hora de la partida
- **Ranking**: Top 10 mejores puntuaciones
- **Pantalla de Récords**: Accesible desde el menú principal

---

## 10. Requisitos Técnicos

### 10.1 Arquitectura
- **Patrón**: MVC (Model-View-Controller)
  - **Model** (`Modelo.java`): Lógica del juego y estado
  - **View** (`VentanaPrincipal.java`, `PanelJuego.java`, `PanelMenu.java`): Interfaz gráfica
  - **Controller** (`MotorJuego.java`): Manejo de entrada y actualización de lógica

### 10.2 Tecnologías
- **Lenguaje**: Java
- **Framework GUI**: Swing
- **Tiempo de Frame**: 20ms por actualización (50 FPS)
- **Listeners**: 
  - `ActionListener`: Para el Timer
  - `KeyListener`: Para entrada del teclado

### 10.3 Dependencias
- JDK 8 o superior
- Swing (incluido en JDK)

---

## 11. Arte y Estética

### 11.1 Estilo Visual
- **Género**: Minimalista/Retro
- **Colores**: Paleta simple (fondos planos, formas geométricas)
- **Resolución**: 800 × 500 píxeles

### 11.2 Sonido (Futuro)
- Efectos de sonido para saltos
- Sonido de colisión
- Música de fondo continua

---

## 12. Multijugador (FUERA DEL ALCANCE ACTUAL)
- Modo actual: Solo jugador
- Futuro: Comparación de récords con otros jugadores

---

## 13. Changelog / Versión Actual

**Versión**: 0.1.0 (Alpha)

### Implementado:
- ✅ Mecánica básica de saltos
- ✅ Sistema de obstáculos
- ✅ Sistema de puntuación simple
- ✅ Interfaz de menú
- ✅ Control por teclado (SPACE)
- ✅ Game Over por colisión

### Pendiente de Implementación:
- 🔄 Sistema de récords persistente
- 🔄 Aumentar dificultad progresiva
- 🔄 Múltiples plataformas
- 🔄 Efectos visuales mejorados
- 🔄 Sistema de sonido
- 🔄 Pantalla de ranking
- 🔄 Animaciones del personaje

---

## 14. Referencias y Inspiración

**Juegos Similares:**
- Flappy Bird
- Chrome Dino Run
- Geometry Dash
- Jump King

**Elementos Clave Adoptados:**
- Mecánica simple de un botón
- Dificultad basada en sincronización
- Replayabilidad mediante competetencia de puntos

---

**Último Actualizado**: Marzo 2026  
**Desarrollador**: InfinityRunner Team
