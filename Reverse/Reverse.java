import java.io.IOException;
import java.util.Arrays;

public class Reverse {
    public static void main(String args[]) {
        Scanner scanner;
        try {
            scanner = new Scanner(System.in);

            int[] numbers = new int[16];
            int[] numberLines = new int[16];
            int numberIdx = 0;
            int lineIdx = 0;

            while (scanner.hasNextInt()) {
                
                if (scanner.isFirst()) {
                    lineIdx += scanner.newLinesCount();
                }

                while (lineIdx >= numberLines.length) {
                    numberLines = Arrays.copyOf(numberLines, numberLines.length * 2);
                }

                numbers[numberIdx] = scanner.nextInt();
                numberLines[lineIdx]++;

                numberIdx++;
                if (numberIdx == numbers.length) {
                    numbers = Arrays.copyOf(numbers, numbers.length * 2);
                }
            }

            if (scanner.newLinesCount() > 0) {
                lineIdx += scanner.newLinesCount() - 1;
                while (lineIdx >= numberLines.length) {
                    numberLines = Arrays.copyOf(numberLines, numberLines.length * 2);
                }
            }
            scanner.close();

            int i = numberIdx - 1;
            for (int j = lineIdx; j >= 0; j--) {
                for (int k = 0; k < numberLines[j]; k++) {
                    System.out.print(numbers[i]);
                    System.out.print(' ');
                    i--;
                }
                System.out.println();
            }

        } catch (IOException e) {
            System.err.println("Cannot read: " + e);
        }
    }
}