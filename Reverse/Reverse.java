public class Reverse {
    public static void main(String args[]) {
        Scanner lineScanner = new Scanner(System.in);
        String line;

        char character;
        int numberLength;

        int numbers[] = new int[16];
        int numberCountInLine[] = new int[16];
        int newNumbers[];

        int numberIdx = 0;
        int lineIdx = 0;

        while (lineScanner.hasNextLine()) {
            numberCount = 0;
            line = lineScanner.nextLine();

            numberLength = 0;
            for (int i = 0; i < line.length(); i++) {
                character = line.charAt(i);

                if (Character.isDigit(character) | character == '-' | character == '+') {
                    numberLength++;
                }

                if (Character.isWhitespace(character) & numberLength > 0) {
                    numbers[numberIdx] = Integer.parseInt(line.substring(i - numberLength, i));
                    numberLength = 0;
                    
                    numberCountInLine[lineIdx]++;
                    numberIdx++;
                }
                else if (i == line.length() - 1 && numberLength > 0) {
                    numbers[numberIdx] = Integer.parseInt(line.substring(i + 1 - numberLength, i + 1));
                    
                    numberCountInLine[lineIdx]++;
                    numberIdx++;
                }

                if (numberIdx == numbers.length) {
                    numbers = new int[numbers.length * 2];
                }
            }

            lineIdx++;
            if (lineIdx == numberCountInLine) {
                
            }
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