import java.io.IOException;
import java.util.Arrays;

public class Reverse {
    public static void main(String args[]) {

        

        try {
            Scanner scanner = new Scanner(System.in);

            int[] numbers = new int[16];
            int[] numberLines = new int[16];
            int numberIdx = 0;
            int lineIdx = 0;

            while (scanner.hasNextInt()) {
                if (numberIdx == numbers.length) {
                    numbers = Arrays.copyOf(numbers, numbers.length * 2);
                }
                numbers[numberIdx] = scanner.nextInt();

                if (lineIdx == numberLines.length) {
                    numberLines = Arrays.copyOf(numberLines, numberLines.length * 2);
                }

                numberIdx++;
            }

        } catch (IOException e) {
            System.err.println("Cannot read: " + e);
        }
    }
}