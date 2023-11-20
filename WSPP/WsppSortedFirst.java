import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;


public class WsppSortedFirst {
    private static boolean checkChar(int code) {
        return Character.isLetter(code) ||
               Character.getType(code) == Character.DASH_PUNCTUATION ||
               '\'' == code;
    }

    private static int addWords(String word, Map<String, IntList> stat, int wordNumber, HashSet<String> wordsInLine) {
        word = word.toLowerCase();

        String sub;
        int length = 0;
        for (int j = 0; j <= word.length(); j++) {
            if (j < word.length() && checkChar(word.charAt(j))) {
                length += 1;
            } else if (length > 0) {
                sub = word.substring(j - length, j);
                if (!wordsInLine.contains(sub)) {
                    stat.put(sub, stat.getOrDefault(
                        sub,
                        new IntList()).push(wordNumber)
                    );
                    wordsInLine.add(sub);
                } else {
                    stat.put(sub, stat.get(sub).increaseTrueSize());
                }
                wordNumber++;
                length = 0;
            }
        }

        return wordNumber;
    }

     private static Map<String, IntList> getStat(String filename) throws IOException {
        Map<String, IntList> stat = new TreeMap<String, IntList>();
        Scanner scanner = new Scanner(new FileInputStream(filename));
        String token;
        int wordNumber = 1;
        HashSet<String> wordsInLine = new HashSet<String>();

        while (scanner.hasNext()) {
            if (scanner.isFirst()) {
                wordsInLine.clear();
            }

            token = scanner.next();
            wordNumber = addWords(token, stat, wordNumber, wordsInLine);
        }

        return stat;
    }

    private static void writeStat(String filename, Map<String, IntList> stat) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(filename),
            StandardCharsets.UTF_8
        ));

        try {
            for (Map.Entry<String, IntList> entry : stat.entrySet()) {
                writer.write(entry.getKey());
                writer.write(" ");
                writer.write(entry.getValue().toString());
                writer.newLine();
            }
        } finally {
            writer.close();
        }
    }

    public static void main(String[] args) {
        try {
            Map<String, IntList> stat = getStat(args[0]);

            try {
                writeStat(args[1], stat);
            } catch (IOException e) {
                System.err.println("Cannot write to file: " + e);
            }
        } catch (NoSuchElementException e) {
            System.err.println("Wrong arguments: " + e);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e);
        } catch (IOException e) {
            System.err.println("Cannot read from file: " + e);
        }
    }
}