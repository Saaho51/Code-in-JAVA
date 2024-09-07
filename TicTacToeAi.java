import java.util.Random;
import java.util.Scanner;

public class TicTacToeAi {
    private static final int BOARD_SIZE = 3;
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';
    private static final char EMPTY_SPACE = ' ';

    private char[][] board;
    private Random random;
    private Scanner scanner;

    public TicTacToeAi() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        random = new Random();
        scanner = new Scanner(System.in);
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY_SPACE;
            }
        }
    }

    public void playGame() {
        while (true) {
            printBoard();
            makeAIMove();
            if (checkWin(PLAYER_O)) {
                System.out.println("AI wins!");
                break;
            }
            makeHumanMove();
            if (checkWin(PLAYER_X)) {
                System.out.println("Human wins!");
                break;
            }
        }
    }

    private void makeAIMove() {
        int row, col;
        do {
            row = random.nextInt(BOARD_SIZE);
            col = random.nextInt(BOARD_SIZE);
        } while (board[row][col] != EMPTY_SPACE);
        board[row][col] = PLAYER_O;
    }

    private void makeHumanMove() {
        System.out.print("Enter row (0-2): ");
        int row = scanner.nextInt();
        System.out.print("Enter column (0-2): ");
        int col = scanner.nextInt();
        if (board[row][col] != EMPTY_SPACE) {
            System.out.println("Invalid move, try again.");
            makeHumanMove();
        } else {
            board[row][col] = PLAYER_X;
        }
    }

    private boolean checkWin(char player) {
        // Check rows
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }
        // Check columns
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        // Check diagonals
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
            return true;
        }
        return false;
    }

    private void printBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TicTacToeAi ai = new TicTacToeAi();
        ai.playGame();
    }
}
