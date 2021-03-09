import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int first = scanner.nextInt() * 3600 + scanner.nextInt() * 60 + scanner.nextInt();
        int second = scanner.nextInt() * 3600 + scanner.nextInt() * 60 + scanner.nextInt();

        System.out.println(second - first);
    }
}