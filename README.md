# 🎮 Tic Tac Toe Game (Java Swing)

A simple **Tic Tac Toe** game developed in **Java** using the **Swing GUI framework**. The player competes against a computer opponent that makes random moves. The game features score tracking, turn indicators, win detection, and the ability to restart after each match.

---

## 📌 Features

- 🎲 Single-player mode (User vs Computer)
- 🖥️ Interactive graphical user interface (GUI)
- 🔴 User plays as **X**
- 🔵 Computer plays as **O**
- 📊 Live score tracking
- 🔄 Turn indicator
- ✅ Automatic win and draw detection
- 🔁 Play Again option after each game
- ⏳ 1-second delay before the computer makes its move

---

## 🛠️ Technologies Used

- Java
- Java Swing (GUI)
- AWT
- Event Handling (ActionListener)
- SwingWorker (for delayed computer move)

---

## 📂 Project Structure

```
TicTacToe/
│
├── TicTacToe.java      # Main game source code
└── README.md
```

---

## 🎮 Game Rules

1. The user always starts first.
2. The user plays as **X**.
3. The computer plays as **O**.
4. Take turns placing symbols on the 3×3 board.
5. The first player to get **three symbols in a row** (horizontal, vertical, or diagonal) wins.
6. If all cells are filled without a winner, the game ends in a draw.

---

## 🚀 How to Run

### Requirements

- Java Development Kit (JDK 8 or above)

### Steps

1. Clone the repository.

```bash
git clone https://github.com/your-username/tictactoe-java.git
```

2. Navigate to the project folder.

```bash
cd tictactoe-java
```

3. Compile the program.

```bash
javac TicTacToe.java
```

4. Run the game.

```bash
java TicTacToe
```

---

## 🖼️ Gameplay

### Main Window

- 3×3 Tic Tac Toe board
- Current player's turn displayed at the top
- User and computer scores displayed
- Interactive buttons for each cell

### End Game

When a game finishes:

- User Wins 🎉
- Computer Wins 🤖
- Draw 🤝

A dialog box appears asking whether the player wants to play another round.

---

## 📖 Learning Objectives

This project demonstrates the following Java concepts:

- Object-Oriented Programming (OOP)
- Java Swing GUI development
- Event-driven programming
- Arrays (2D arrays)
- Conditional statements
- Loops
- Random number generation
- Multithreading using SwingWorker

---

## 🔮 Future Improvements

- Add different difficulty levels (Easy, Medium, Hard)
- Implement Minimax AI for smarter gameplay
- Add sound effects
- Add game timer
- Add player name customization
- Save high scores
- Improve the user interface with modern styling

---

## 👨‍💻 Author

Developed as a Java programming project.
