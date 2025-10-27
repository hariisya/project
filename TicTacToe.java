import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class TicTacToe extends JFrame implements ActionListener {
    //data member
    private JButton[][] buttons = new JButton[3][3]; 
    private JLabel scoreLabel, turnLabel; 
    private char currentPlayer = 'X'; 
    private boolean isUserTurn = true; 
    private int userScore = 0, computerScore = 0;   
    private Random random = new Random();     

    
    public TicTacToe() {
        //design
        setTitle("Tic Tac Toe");       
        setSize(400, 500);           
        setLocation(600,200);          
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setLayout(new BorderLayout());      

        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BorderLayout());  
        scoreLabel = new JLabel("USER: 0   vs   COMPUTER: 0", SwingConstants.CENTER);         
        scoreLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
        scorePanel.add(scoreLabel, BorderLayout.NORTH);
        
        turnLabel = new JLabel("User's turn", SwingConstants.CENTER);
        turnLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
        scorePanel.add(turnLabel, BorderLayout.SOUTH);
        add(scorePanel, BorderLayout.NORTH);   

        
        
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));  
        
        for (int i = 0; i < 3; i++) {   
             for (int j = 0; j < 3; j++) {   
                buttons[i][j] = new JButton("");   
                buttons[i][j].setFont(new Font("Arial Black", Font.BOLD, 60)); 
                buttons[i][j].setFocusPainted(false); 
                buttons[i][j].addActionListener(this);
                panel.add(buttons[i][j]);
            }
        }
        add(panel, BorderLayout.CENTER);
    }

    @Override //
    public void actionPerformed(ActionEvent e) {
        
        if (!isUserTurn) return;                           

        JButton buttonClicked = (JButton) e.getSource();
        if (buttonClicked.getText().equals("")){
            buttonClicked.setText(String.valueOf(currentPlayer));
            buttonClicked.setForeground(Color.RED);
            
            //Check if the current player has won.
            if (checkForWin()){
                userScore++;            // Increase the user's score.
                scoreLabel.setText("User: " +  userScore + " - Computer: " + computerScore);  //Update the score display.
                announceResult("User wins!");
            } else if (isBoardFull()) {    //Check if the board is full (draw)
                announceResult("The game is a draw!");
            } else {
                isUserTurn = false;  //It's now the computer's turn.
                currentPlayer = 'O';
                turnLabel.setText("Computer's turn");

                // Add delay before computer's move
                new ComputerMoveWorker().execute();
            }
        }
    }
     
    //Makes a random move for the computer
    private void computerMove() {
        //generates random positions (i, j) until it finds an empty button.
        int i, j;
        do {
            i = random.nextInt(3);
            j = random.nextInt(3);
        } while (!buttons[i][j].getText().equals(""));
        buttons[i][j].setText(String.valueOf(currentPlayer));
        buttons[i][j].setForeground(Color.BLUE);
        
        //Check if the computer has won
        if (checkForWin()) {
            computerScore++;
            scoreLabel.setText("User: " + userScore + " - Computer: " + computerScore);
            announceResult("Computer wins!");     
        } else if (isBoardFull()) {
            announceResult("The game is a draw!");
        } else { 
            //If no win or draw
            currentPlayer = 'X';
            isUserTurn = true;
            turnLabel.setText("User's turn");
        }
    }
        

    //checks all rows, columns, and diagonals for a win condition
    private boolean checkForWin(){
        
        for (int i = 0; i < 3; i++) {
            // Check rows for a win
            // i = for row,  buttons[row][column]
            if (buttons[i][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[i][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[i][2].getText().equals(String.valueOf(currentPlayer))) 
                {
                return true;
            }
            // Check columns for a win
            //i = for column,  buttons[row][column]
            if (buttons[0][i].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][i].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][i].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }
            //Check main diagonal (top-left to bottom-right) for a win
            if (buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][2].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
            // Check anti-diagonal (top-right to bottom-left) for a win
            if (buttons[0][2].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][0].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        // No win found
        return false;
    }
    
    //checks if the board is full (no empty cells left)
    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }
    //shows a dialog box with the game result and asks if the user wants to play again.
    //If the user chooses yes, the board is reset. Otherwise, the program exits.
    private void announceResult(String message) {
        int response = JOptionPane.showConfirmDialog(this, message + " Play again?", "Game Over",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            resetBoard();
        } else {
            System.exit(0);
        }
    }
 
    //resets the board and game state to start a new game.
    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        currentPlayer = 'X';
        isUserTurn = true;
        turnLabel.setText("User's turn");
    }
    
    /* - This is an inner class that handles the computer's move
        after a delay to simulate thinking time.
       - It waits for 1 second and then makes the computer's move.
       */
    private class ComputerMoveWorker extends SwingWorker<Void, Void> {
        @Override
        protected Void doInBackground() throws InterruptedException {
            Thread.sleep(1000); // Simulate thinking time 1000 milliseconds
            return null;
        }

        @Override
        protected void done() {
            computerMove();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {    
            TicTacToe game = new TicTacToe();
            game.setVisible(true);
        });
    }
}
