package projeto1;


import java.util.Scanner;

public class JogoDaVelha{
    private char[][] board;
    private char currentPlayer;

    public JogoDaVelha() {
        board = new char[3][3];
        currentPlayer = 'X';
        initializeBoard();
    }

    public void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    public boolean isBoardFull() {
        boolean isFull = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    isFull = false;
                }
            }
        }
        return isFull;
    }


    public boolean checkForWin() {
        return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
    }

    private boolean checkRowsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumnsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[0][i], board[1][i], board[2][i])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalsForWin() {
        return (checkRowCol(board[0][0], board[1][1], board[2][2]) || checkRowCol(board[0][2], board[1][1], board[2][0]));
    }

    private boolean checkRowCol(char c1, char c2, char c3) {
        return ((c1 != '-') && (c1 == c2) && (c2 == c3));
    }


    public void changePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

  
    public boolean makeMove(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-') {
            board[row][col] = currentPlayer;
            return true;
        } else {
            return false;
        }
    }

    // Método principal
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JogoDaVelha game = new JogoDaVelha();
        boolean gameOver = false;

        System.out.println("Bem vindos ao Jogo da Velha jogadores");

        while (!gameOver) {
            System.out.println("Jogador " + game.currentPlayer + ", é a sua vez.");
            game.printBoard();
            System.out.print("Digite a linha (0-2): ");
            int row = scanner.nextInt();
            System.out.print("Digite a coluna (0-2): ");
            int col = scanner.nextInt();

            if (game.makeMove(row, col)) {
                if (game.checkForWin()) {
                    System.out.println("Parabéns, jogador " + game.currentPlayer + "! Você ganhou!");
                    gameOver = true;
                } else if (game.isBoardFull()) {
                    System.out.println("O jogo terminou em empate!");
                    gameOver = true;
                } else {
                    game.changePlayer();
                }
            } else {
                System.out.println("Jogada inválida. Tente novamente.");
            }
        }
        scanner.close();
        game.printBoard();
    }
}
