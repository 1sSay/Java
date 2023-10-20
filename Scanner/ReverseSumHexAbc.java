import java.io.IOException;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class ReverseSumHexAbc {
    public static void main(String args[]) {
        Scanner lineScanner;

        try {
            lineScanner = new Scanner(System.in);
            String line;
            int numberLength;
            int columnSum[] = new int[16];
            int rectangleSum[] = new int[16];
            int numberIdx;
            int number;

            while (lineScanner.hasNextLine()) {
                line = lineScanner.nextLine();

                numberIdx = 0;
                numberLength = 0;

                for (int i = 0; i < line.length(); i++) {
                    if (!Character.isWhitespace(line.charAt(i))) {
                        numberLength++;
                    }

                    if ((i + 1 == line.length() || Character.isWhitespace(line.charAt(i + 1))) && 
                         numberLength > 0) {
                        number = Integer.parseUnsignedInt(
                            line.substring(i - numberLength + 1, i + 1),
                            16
                        );

                        columnSum[numberIdx] += number;

                        rectangleSum[numberIdx] = columnSum[numberIdx];
                        if (numberIdx > 0) {
                            rectangleSum[numberIdx] += rectangleSum[numberIdx - 1];
                        }

                        System.out.print(Integer.toHexString(rectangleSum[numberIdx]));
                        System.out.print(" ");

                        numberLength = 0;
                        numberIdx++;
                        if (numberIdx == columnSum.length) {
                            columnSum = Arrays.copyOf(columnSum, columnSum.length * 2);
                            rectangleSum = Arrays.copyOf(rectangleSum, rectangleSum.length * 2);
                        }
                    }
                }

                System.out.println();
            }

            lineScanner.close();
        } catch (NoSuchElementException e) {
            System.err.println("No such element: " + e.toString());
        } catch (IOException e) {
            System.err.println("Cannot read:" + e.toString());
        }
    }
}
