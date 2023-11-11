import java.util.Scanner;

public class BadTreap {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int step = 710;
        int maxN = 50000;

        int currentValue = -(step * maxN / 2);

        for (int i = 0; i < n; i++) {
            System.out.println(currentValue);
            currentValue += step;
        }
    }
}
