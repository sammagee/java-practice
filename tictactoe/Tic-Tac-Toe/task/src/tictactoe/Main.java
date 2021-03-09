package tictactoe;

import java.util.*;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static char player = 'X';
    public static char[][] board = new char[3][3];

    public static void display() {
        System.out.println("---------");
        System.out.printf("| %c %c %c |\n", board[0][0], board[0][1], board[0][2]);
        System.out.printf("| %c %c %c |\n", board[1][0], board[1][1], board[1][2]);
        System.out.printf("| %c %c %c |\n", board[2][0], board[2][1], board[2][2]);
        System.out.println("---------");
    }

    public static void initialize() {
        for (char[] row : board) {
            Arrays.fill(row, ' ');
        }

        display();
    }

    public static boolean mark(int x, int y) {
        if (x > 3 || x < 1 || y > 3 || y < 1) {
            System.out.println("Coordinates should be from 1 to 3!");

            return false;
        }

        if (board[y - 1][x - 1] != ' ') {
            System.out.println("This cell is occupied! Choose another one!");

            return false;
        }

        board[y - 1][x - 1] = player;

        return true;
    }

    public static void play() {
        System.out.print("Enter the coordinates: ");

        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("You should enter numbers!");
        }

        int y = scanner.nextInt();
        int x = scanner.nextInt();
        
        if (!mark(x, y)) {
            play();
            
            return;
        };

        display();
        
        player = player == 'X' ? 'O' : 'X';
    }

    public static boolean check() {
        for (int y = 0; y < 3; y++) {
            if (board[y][0] != ' ' && board[y][0] == board[y][1] && board[y][1] == board[y][2]) {
                System.out.printf("%c wins\n", board[y][0]);

                return true;
            }
        }

        for (int x = 0; x < 3; x++) {
            if (board[0][x] != ' ' && board[0][x] == board[1][x] && board[1][x] == board[2][x]) {
                System.out.printf("%c wins\n", board[0][x]);

                return true;
            }
        }

        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            System.out.printf("%c wins\n", board[0][0]);

            return true;
        }

        if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            System.out.printf("%c wins\n", board[0][2]);

            return true;
        }

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (board[y][x] == ' ') {
                    return false;
                }
            }
        }

        System.out.println("Draw");

        return true;
    }

    public static void main(String[] args) {
        initialize();

        while (!check()) {
            play();
        };
    }
}
