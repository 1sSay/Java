import java.io.IOException;
import java.util.Arrays;


public class ReverseSumHexAbc {
    public static int parseInt(String strNumber) throws NumberFormatException {
        if (strNumber.startsWith("0x") || strNumber.startsWith("0X")) {
            return Integer.parseUnsignedInt(strNumber.substring(2), 16);
        }

        int result = 0;
        int sign = 1;
        int digit = 1;
        for (int i = strNumber.length() - 1; i >= 0; i--) {
            if (i == 0 && strNumber.charAt(i) == '-') {
                sign = -1;
                continue;
            }
            result += digit * (strNumber.charAt(i) - 'a');
            digit *= 10;
        }
        return sign * result;
    }

    public static String toAbc(int number) {
        if (number == 0) {
            return "a";
        }

        boolean isNegative = false;

        if (number < 0) {
            isNegative = true;
            number *= -1;
        }

        StringBuilder builder = new StringBuilder();
        while (number > 0) {
            builder.append(Character.toString((number % 10) + 97));
            number /= 10;
        }

        if (isNegative) {
            builder.append('-');
        }

        builder.reverse();
        return builder.toString();
    }

    public static void main(String args[]) {
        Scanner scanner;
        try {
            scanner = new Scanner(System.in);

            int[] columnSum = new int[16];
            int[] rectangleSum = new int[16];
            int numberIdx = 0;

            while (scanner.hasNext()) {
                if (scanner.isFirst()) {
                    numberIdx = 0;
                    for (int i = 0; i < scanner.newLinesCount(); i++) {
                        System.out.println();
                    }
                }
                String next = scanner.next();

                columnSum[numberIdx] += parseInt(next);
                rectangleSum[numberIdx] = columnSum[numberIdx];
                if (numberIdx > 0) {
                    rectangleSum[numberIdx] += rectangleSum[numberIdx - 1];
                }

                System.out.print(ReverseSumHexAbc.toAbc(rectangleSum[numberIdx]));
                System.out.print(' ');

                numberIdx++;
                if (numberIdx == columnSum.length) {
                    columnSum = Arrays.copyOf(columnSum, columnSum.length * 2);
                    rectangleSum = Arrays.copyOf(rectangleSum, rectangleSum.length * 2);
                }
            }

            for (int i = 0; i < scanner.newLinesCount(); i++) {
                System.out.println();
            }

            scanner.close();
        } catch (IOException e) {
            System.err.println("Cannot read: " + e);
        } catch (NumberFormatException e) {
            System.err.println("Wrong number format: " + e);
        }
    }
}