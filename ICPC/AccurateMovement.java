import java.util.Scanner;
public class AccurateMovement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int n = scanner.nextInt();

        int answer = 2 * ((n - b - 1) / (b - a) + 1) + 1;

        System.out.println(answer);
    }
}
