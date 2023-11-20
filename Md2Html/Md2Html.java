import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;


public class Md2Html {
    private static List<String> readMarkdown(String filename) throws IOException {
        List<String> paragraphs = new ArrayList<>();
        StringBuilder paragraph;

        try (Scanner scanner = new Scanner(new File(filename), StandardCharsets.UTF_8)) {
            paragraph = new StringBuilder();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (!line.isEmpty()) {
                    paragraph.append(line);
                    paragraph.append(System.lineSeparator());
                } else if (!paragraph.isEmpty()) {
                    paragraphs.add(paragraph.toString());
                    paragraph = new StringBuilder();
                }
            }
        }

        return paragraphs;
    }

    private static void fillMapWithMarks(Map<String, String> marks) {
        marks.put("**", "strong");
        marks.put("__", "strong");
        marks.put("--", "s");
        marks.put("*", "em");
        marks.put("_", "em");
        marks.put("`", "code");
    }

    private static boolean isHeader(String paragraph, int headerLevels) {
        for (int i = 1; i <= headerLevels; i++) {
            StringBuilder prefix = new StringBuilder();
            prefix.repeat('#', i);
            prefix.append(' ');
            if (paragraph.startsWith(prefix.toString())) {
                return true;
            }
        }
        return false;
    }

    private static void toHtml(List<String> paragraphs, StringBuilder html) {
        Map<String, String> marks = new LinkedHashMap<>();
        fillMapWithMarks(marks);

        for (String paragraph : paragraphs) {
            System.out.println(isHeader(paragraph, 6));
        }
    }

    private static void writeHtml(String filename) {

    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Wrong arguments count. Expected input and output filenames");
            return;
        }

        try {
            List<String> paragraphs = readMarkdown(args[0]);
            StringBuilder html = new StringBuilder();
            toHtml(paragraphs, html);
            writeHtml(args[1]);
        } catch (IOException e) {
            System.err.println("Error while reading file: " + e);
        }
    }
}
