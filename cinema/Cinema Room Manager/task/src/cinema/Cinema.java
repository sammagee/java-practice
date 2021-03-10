package cinema;

import java.util.*;

public class Cinema {
    public static Scanner scanner = new Scanner(System.in);
    public static int rows;
    public static int seats;
    public static char[][] room;
    public static int regularTickets = 0;
    public static int discountTickets = 0;

    public static void initializeRoom() {
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = scanner.nextInt();
        
        room = new char[rows][seats];
        
        for (char[] row : room) {
            Arrays.fill(row, 'S');
        }
    }

    public static void displayMenu() {
        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");

        int choice = scanner.nextInt();

        switch (choice) {
            case 0:
                return;
            case 1:
                displayRoom();
                break;
            case 2:
                buySeat();
                break;
            case 3:
                displayStatistics();
                break;
        }

        displayMenu();
    }

    public static void displayRoom() {
        System.out.println();
        System.out.println("Cinema:");

        for (int i = 0; i < seats + 1; i++) {
            System.out.printf(i == 0 ? "  " : "%d ", i);
        }

        System.out.println();

        for (int y = 1; y < rows + 1; y++) {
            System.out.printf("%d ", y);

            for (int x = 0; x < seats; x++) {
                System.out.printf("%c ", room[y - 1][x]);
            }

            System.out.print("\n");
        }

        System.out.println();
    }
    
    public static void displayStatistics() {
        int frontRows = rows / 2;
        int backRows = rows - frontRows;
        int ticketsPurchased = regularTickets + discountTickets;
        int totalSeats = rows * seats;
        
        System.out.printf("Number of purchased tickets: %d\n", ticketsPurchased);
        System.out.printf("Percentage: %.2f%%\n", (float) ticketsPurchased / totalSeats * 100);
        System.out.printf("Current income: $%d\n", regularTickets * 10 + discountTickets * 8);
        System.out.printf("Total income: $%d\n", frontRows * seats * 10 + backRows * seats * 8);
    }
    
    public static void buySeat() {
        System.out.println("Enter a row number:");
        int row = scanner.nextInt() - 1;
        System.out.println("Enter a seat number in that row:");
        int seat = scanner.nextInt() - 1;

        if (row >= rows || seat >= seats || row < 0 || seat < 0) {
            System.out.println("Wrong input!");
            buySeat();
            
            return;
        }

        if (room[row][seat] == 'B') {
            System.out.println("That ticket has already been purchased!");
            buySeat();
            
            return;
        }

        int total = rows * seats;
        int ticketPrice = 0;

        if (total <= 60) {
            ticketPrice = 10;
            regularTickets++;
        } else {
            int frontRows = rows / 2;
            int backRows = rows - frontRows;

            if (row < frontRows) {
                ticketPrice = 10;
                regularTickets++;
            } else {
                ticketPrice = 8;
                discountTickets++;
            }
        }

        room[row][seat] = 'B';

        System.out.printf("\nTicket price: $%d\n", ticketPrice);
        
        displayRoom();
    }
    
    public static void main(String[] args) {
        initializeRoom();
        
        displayMenu();
    }
}