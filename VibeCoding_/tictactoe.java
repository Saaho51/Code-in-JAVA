import java.util.Scanner;

public class TicTacToe {
    private char[][] board; // 3x3 game board
    private char currentPlayer; // 'X' or 'O'
    private boolean gameWon;
    private boolean gameDraw;

    // Constructor to initialize the game
    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';
        gameWon = false;
        gameDraw = false;
        initializeBoard();
    }

    // Initialize empty board with spaces
    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // Display the board
    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
        System.out.println();
    }

    // Main game loop
    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("Enter a number (1-9) to make a move:");
        System.out.println("1|2|3\n-----\n4|5|6\n-----\n7|8|9\n");

        while (!gameWon && !gameDraw) {
            printBoard();
            System.out.println("Player " + currentPlayer + "'s turn. Enter a position (1-9): ");
            int position;
            try {
                position = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number between 1 and 9.");
                continue;
            }

            if (makeMove(position)) {
                checkWin();
                checkDraw();
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; // Switch player
            } else {
                System.out.println("Invalid move! Position already taken or out of range. Try again.");
            }
        }

        printBoard();
        if (gameWon) {
            System.out.println("Player " + (currentPlayer == 'X' ? 'O' : 'X') + " wins!");
        } else if (gameDraw) {
            System.out.println("It's a draw!");
        }
        scanner.close();
    }

    // Make a move based on position (1-9)
    private boolean makeMove(int position) {
        if (position < 1 || position > 9) {
            return false;
        }
        // Convert position (1-9) to board indices
        int row = (position - 1) / 3;
        int col = (position - 1) % 3;
        if (board[row][col] == ' ') {
            board[row][col] = currentPlayer;
            return true;
        }
        return false;
    }

    // Check for a win
    private void checkWin() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                gameWon = true;
                return;
            }
        }
        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                gameWon = true;
                return;
            }
        }
        // Check diagonals
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            gameWon = true;
            return;
        }
        if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            gameWon = true;
            return;
        }
    }

    // Check for a draw
    private void checkDraw() {
        if (gameWon) return;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return; // Empty space exists, not a draw
                }
            }
        }
        gameDraw = true;
    }

    // Main method to run the game
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.playGame();
    }
}
