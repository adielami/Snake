# 🐍 Classic Snake Game (Java Edition)

A robust implementation of the classic arcade game Snake, built entirely in **Java** using the **Swing** and **AWT** libraries.
This project demonstrates strong Object-Oriented Programming (OOP) principles, custom graphical rendering, and dynamic data structure management.

---

## ⚡ Key Features

### 🎮 Gameplay Mechanics
* **Dynamic Movement:** Smooth grid-based movement control using keyboard listeners.
* **Growing Mechanic:** The snake increases in length dynamically each time it consumes food.
* **Collision Detection:**
    * **Self-Collision:** Detects if the head hits any part of the body.
    * **Border Collision:** Detects if the snake hits the walls of the game window.
* **Score Tracking:** Real-time score updates displayed on the screen.

### 🎨 Graphical Interface
* **Custom Rendering:** Overrides the `paintComponent()` method to draw the grid, snake body, and food items manually.
* **Game Loop:** Utilizes `javax.swing.Timer` to handle the game tick rate and refresh speed efficiently.

---

## 🛠️ Tech Stack & Concepts

| Component | Technology | Concept Demonstrated |
|-----------|------------|----------------------|
| **Language** | **Java** | OOP, Strict Typing |
| **GUI Library** | **Swing / AWT** | `JFrame`, `JPanel`, Graphics methods |
| **Data Structures** | **Arrays / ArrayList** | Managing the snake's body coordinates |
| **Events** | **ActionListener** | Handling game ticks and keyboard input |

---

## 🔬 How It Works (The Logic)

### 1. Data Structures (The Snake Body)
Unlike simple sprite movement, a snake requires tracking multiple coordinates.
This project typically uses two arrays (or an `ArrayList`) to store the **x** and **y** positions of every body part:
```java
// Example logic
int[] x = new int[GAME_UNITS];
int[] y = new int[GAME_UNITS];
// x[0] is the head, x[1] is the first body part, etc.
