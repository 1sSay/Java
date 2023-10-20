import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class WordStatCountMiddleL {
    public static boolean checkChar(int code) {
        return Character.isLetter(code) ||
            Character.getType(code) == Character.DASH_PUNCTUATION ||
            '\'' == code;
    }

    public static LinkedHashMap<String, Integer> readFromFile(String filename) throws IOException {
        LinkedHashMap<String, Integer> stat = new LinkedHashMap<String, Integer>();
        StringBuilder wordBuilder;

        BufferedReader inputReader = new BufferedReader(new InputStreamReader(
            new FileInputStream(filename), 
            StandardCharsets.UTF_8
        ));

        try {
            char[] buffer = new char[2048];
            wordBuilder = new StringBuilder();
            int readCharCount = inputReader.read(buffer);

            while (readCharCount > 0) {
                for (int i = 0; i < readCharCount; i++) {
                    char character = buffer[i];

                    if (WordStatInput.checkChar(character)) {
                        wordBuilder.append(character);
                    } else if (wordBuilder.length() > 0) {
                        if (wordBuilder.length() >= 5) {
                            String word = wordBuilder.substring(2, wordBuilder.length() - 2).toLowerCase();
                            stat.put(word, stat.getOrDefault(word, 0) + 1);
                        }
                        wordBuilder = new StringBuilder();
                    }
                }

                readCharCount = inputReader.read(buffer);
            }

            if (wordBuilder.length() > 0) {
                if (wordBuilder.length() >= 5) {
                    String word = wordBuilder.substring(2, wordBuilder.length() - 2).toLowerCase();
                    stat.put(word, stat.getOrDefault(word, 0) + 1);
                }
                wordBuilder = new StringBuilder();
            }
        } finally {
            inputReader.close();
        }

        return stat;
    }

    public static void writeToFile(String filename, ArrayList<Entry<String, Integer>> statList) throws IOException {
        BufferedWriter outputWriter = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filename), 
                StandardCharsets.UTF_8
            ) 
        );

        try {
            for (Map.Entry<String, Integer> entry : statList) {
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

        try {
            stat = WordStatCountMiddleL.readFromFile(args[0]);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Cannot read file: " + e.getMessage());
        }

        ArrayList<Entry<String, Integer>> statList = new ArrayList<Entry<String, Integer>>(stat.entrySet());
        Collections.sort(statList, Entry.comparingByValue());

        try {
            WordStatCountMiddleL.writeToFile(args[1], statList);
        } catch (IOException e) {
            System.err.println("Cannot write to file: " + e.getMessage());
        }
    }
}
