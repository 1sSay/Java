import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedHashMap;
import java.util.Map;


public class WordStatInput {
    private static boolean checkChar(int code) {
        return Character.isLetter(code) ||
               Character.getType(code) == Character.DASH_PUNCTUATION ||
               '\'' == code;
    }

    private static void addWord(LinkedHashMap<String, Integer> stat, String word) {
        word = word.toLowerCase();

        String sub;
        int length = 0;
        System.err.println(word);
        for (int j = 0; j <= word.length(); j++) {
            if (j < word.length() && checkChar(word.charAt(j))) {
                length += 1;
            } else if (length > 0) {
                sub = word.substring(j - length, j);
                stat.put(sub, stat.getOrDefault(sub, 0) + 1);
                length = 0;
            }
        }

    }

    private static void writeStat(LinkedHashMap<String, Integer> stat, String filename) throws IOException {
        BufferedWriter outputWriter = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filename), 
                "UTF-8"
            )
        );

        try {
            for (Map.Entry<String, Integer> entry : stat.entrySet()) {
                outputWriter.write(entry.getKey());
                outputWriter.write(" ");
                outputWriter.write(entry.getValue().toString());
                outputWriter.newLine();
            }
        } finally {
            outputWriter.close();
        }
    }

    public static void main(String[] args) {
        LinkedHashMap<String, Integer> stat = new LinkedHashMap<String, Integer>();
        String word;

        try {
            Scanner scanner = new Scanner(new FileInputStream(args[0]));
            
            try {
                while (scanner.hasNext()) {
                    word = scanner.next();
                    addWord(stat, word);
                }
            } finally {
                scanner.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Cannot read file: " + e.getMessage());
        }

        try {
            writeStat(stat, args[1]);
        } catch (IOException e) {
            System.err.println("Cannot write to file: " + e.getMessage());
        }
    }
}
