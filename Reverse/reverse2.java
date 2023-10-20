import java.util.Scanner;
import java.util.Stack;

public class Reverse {
    public static void main(String args[]) {
        Scanner lineScanner = new Scanner(System.in);
        Scanner numberScanner;
        String line;
        int numberCount;

        Stack<Integer> numbers = new Stack<Integer>();
        Stack<Integer> numberCountInLine = new Stack<Integer>();

        while (lineScanner.hasNextLine()) {
            numberCount = 0;
            line = lineScanner.nextLine();

            numberScanner = new Scanner(line);
            while (numberScanner.hasNextInt()) {
                numbers.push(numberScanner.nextInt());
                numberCount++;
            }

            numberCountInLine.push(numberCount);
        }
        lineScanner.close();

        while (!numberCountInLine.empty()) {
            numberCount = numberCountInLine.pop();
            for (int i = 0; i < numberCount; i++) {
                System.out.print(numbers.pop());
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}