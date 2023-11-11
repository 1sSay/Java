package md2html;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Md2Html {
    private static boolean checkNewLine(StringBuilder builder) {
        return builder.substring(builder.length() - System.lineSeparator().length()).equals(System.lineSeparator());
    }

    private static List<String> readFile(String filename) throws IOException {
        try (Reader reader = new FileReader(filename, StandardCharsets.UTF_8)) {
            List<String> lines = new ArrayList<>();
            StringBuilder builder = new StringBuilder();

            int read;
            char[] buffer = new char[8192];

            read = reader.read(buffer);
            while (read != -1) {
                for (int i = 0; i < read; i++) {
                    builder.append(buffer[i]);

                    if (checkNewLine(builder)) {
                        lines.add(builder.substring(0, builder.length() - System.lineSeparator().length()));
                        builder = new StringBuilder();
                    }
                }

                read = reader.read(buffer);
            }

            return lines;
        }
    }
    public static void Main(String[] args) {
        if (args.length != 2) {
            System.err.println("Wrong arguments! Need input and output files");
            return;
        }

        try {
            List<String> lines = readFile(args[0]);

            for (String line : lines) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input File not found: " + e);
        } catch (IOException e) {
            System.err.println("Error while reading file: " + e);
        }
    }
}
