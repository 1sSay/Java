import java.io.IOException;
import java.util.Arrays;

public class Reverse {
    public static void main(String args[]) {
        try {
            Scanner lineScanner = new Scanner(System.in);
            String line;
    
            char character;
            int numberLength;
    
            int numbers[] = new int[16];
            int numberCountInLine[] = new int[16];
            int newNumbers[];

            int numberIdx = 0;
            int lineIdx = 0;
            
            System.out.println(lineScanner.hasNextLine());
            
            while (lineScanner.hasNextLine()) {
                line = lineScanner.nextLine();
                System.out.println(line);
            }
            lineScanner.close();

            numberIdx = 0;
            for (int i = 0; i < lineIdx; i++) {
                for (int j = 0; j < numberCountInLine[i]; j++) {
                    System.out.print(numbers[numberIdx]);
                    numberIdx++;
                    System.out.print(" ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}