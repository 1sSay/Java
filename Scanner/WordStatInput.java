import java.util.Map;
import java.util.LinkedHashMap;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;


public class WordStatInput {
    public static boolean checkChar(int code) {
        return Character.isLetter(code) ||
            Character.getType(code) == Character.DASH_PUNCTUATION ||
            '\'' == code;
    }

    public static void addWord(LinkedHashMap<String, Integer> stat, StringBuilder wordBuilder) {
        String word = wordBuilder.toString().toLowerCase();
        stat.put(word, stat.getOrDefault(word, 0) + 1);
    }

    public static void main(String[] args) {
        LinkedHashMap<String, Integer> stat = new LinkedHashMap<String, Integer>();
        StringBuilder wordBuilder;

        try {
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(
                new FileInputStream(args[0]), 
                "UTF-8"
            ));
            
            try {
                char[] buffer = new char[1024];
                wordBuilder = new StringBuilder();
                int readCharCount = inputReader.read(buffer);

                while (readCharCount > 0) {
                    for (int i = 0; i < readCharCount; i++) {
                        char character = buffer[i];

                        if (WordStatInput.checkChar(character)) {
                            wordBuilder.append(character);
                        } else if (wordBuilder.length() > 0) {
                            WordStatInput.addWord(stat, wordBuilder);
                            wordBuilder = new StringBuilder();
                        }
                    }

                    readCharCount = inputReader.read(buffer);
                }

                if (wordBuilder.length() > 0) {
                            WordStatInput.addWord(stat, wordBuilder);
                            wordBuilder = new StringBuilder();
                }
            } finally {
                inputReader.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Cannot read file: " + e.getMessage());
        }

        try {
            BufferedWriter outputWriter = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(args[1]), 
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
        } catch (IOException e) {
            System.err.println("Cannot write to file: " + e.getMessage());
        }
    }
}
