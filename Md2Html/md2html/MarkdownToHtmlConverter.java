package md2html;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Writer;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MarkdownToHtmlConverter {
    private List<String> paragraphs;
    private List<String> marks;
    private List<String> specialSymbols;
    private Map<String, String> conversations;
    private String preMark;
    private boolean isInPre;
    private StringBuilder html;
    private int maxHeaderLevel;

    public MarkdownToHtmlConverter(String filename) throws IOException {
        readMarkdown(filename);
        this.maxHeaderLevel = 6;

        this.preMark = "```";
        this.marks = List.of(
            "**", 
            "__", 
            "--",
            "*", 
            "_", 
            "`"
        );

        this.specialSymbols = List.of(
            "<", ">", "&"
        );
        this.conversations = Map.of(
            "```", "pre",
            "**", "strong",
            "__", "strong",
            "--", "s",
            "*", "em",
            "_", "em",
            "`", "code",
            "<", "&lt;",
            ">", "&gt;",
            "&", "&amp;"
        );

        this.html = new StringBuilder();
        convert();

        System.out.println(this.html.toString());
    }

    private void convert() {
        for (String paragraph : this.paragraphs) {
            Map<String, Integer> paragraphMarks = getMarksMap(paragraph);

            int headerLevel = getHeaderLevel(paragraph);
            if (headerLevel == 0) {
                convertParagraph(paragraph, paragraphMarks);
            } else {
                convertHeader(paragraph.substring(headerLevel + 1), headerLevel, paragraphMarks);
            }
        }
    }

    private int getHeaderLevel(String paragraph) {
        for (int i = 1; i <= this.maxHeaderLevel; i++) {
            if (paragraph.startsWith("#".repeat(i) + " ")) {
                return i;
            }
        }

        return 0;
    }

    private void convertParagraph(String paragraph, Map<String, Integer> paragraphMarks) {
        this.html.append("<p>");
        replace(paragraph, paragraphMarks);
        this.html.append("</p>\n");
    }

    private void convertHeader(String header, int headerLevel, Map<String, Integer> headerMarks) {
        this.html.append("<h").append(headerLevel).append(">");
        replace(header, headerMarks);
        this.html.append("</h").append(headerLevel).append(">\n");
    }

    private void addMark(String mark, boolean isClose) {
        this.html.append('<');
        if (isClose) {
            html.append('/');
        }
        this.html.append(this.conversations.get(mark));
        this.html.append('>');
    }

    private void replace(String paragraph, Map<String, Integer> paragraphMarks) {
        Map<String, Integer> previousMarks = new HashMap<>();
        fillMapWithMarks(previousMarks);

        int i = 0;
        boolean isMark;
        boolean inPre = false;
        
        while (i < paragraph.length()) {
            if (isSpecial(paragraph.charAt(i))) {
                this.html.append(this.conversations.get(paragraph.substring(i, i + 1)));
                i++;
                continue;
            }

            if (paragraph.startsWith(this.preMark, i)) {
                inPre = !inPre;
                addMark(this.preMark, !inPre);
                i += this.preMark.length();
                continue;
            }

            if (paragraph.startsWith("\\", i)) {
                i++;
                continue;
            }

            if (!inPre) {
                isMark = false;
                for (String mark : marks) {
                    if (paragraph.startsWith(mark, i) && (i == 0 || paragraph.charAt(i - 1) != '\\') && paragraphMarks.get(mark) != 1 &&
                            paragraphMarks.get(mark) - previousMarks.get(mark) > 0) {
                        if (previousMarks.get(mark) % 2 == 1) {
                            addMark(mark, true);
                        } else {
                            addMark(mark, false);
                        }
                        previousMarks.put(mark, previousMarks.get(mark) + 1);
                        i += mark.length();
                        isMark = true;
                        break;
                    }
                }

                if (!isMark) {
                    this.html.append(paragraph.charAt(i));
                    i++;
                }
            } else {
                this.html.append(paragraph.charAt(i));
                i++;
            }
        }
    }

    private boolean isSpecial(char symbol) {
        return symbol == '>' || symbol == '<' || symbol == '&';
    }

    private void fillMapWithMarks(Map<String, Integer> marksCount) {
        for (String mark : marks) {
            marksCount.put(mark, 0);
        }
    }

    private void migrateMarks(Map<String, Integer> from, Map<String, Integer> to) {
        for (String mark : marks) {
            to.put(mark, to.get(mark) + from.get(mark));
        }
    }

    private void checkMark(String paragraph, int i, Map<String, Integer> marksMap) {
        for (String mark : marks) {
             if (paragraph.startsWith(mark, i) && (i == 0 || paragraph.charAt(i - 1) != '\\')) {
                marksMap.put(mark, marksMap.get(mark) + 1);
                return;
            }
        }
    }

    private Map<String, Integer> getMarksMap(String paragraph) {
        Map<String, Integer> marksCount = new HashMap<>();
        isInPre = false;
        marksCount.put(this.preMark, 0);
        fillMapWithMarks(marksCount);

        Map<String, Integer> marksInPre = new HashMap<>();
        fillMapWithMarks(marksInPre);

        for (int i = 0; i < paragraph.length(); i++) {
            if (paragraph.startsWith(this.preMark)) {
                marksCount.put(this.preMark, marksCount.get(this.preMark) + 1);
                isInPre = !isInPre;

                if (isInPre) {
                    fillMapWithMarks(marksInPre);
                } else {
                    migrateMarks(marksInPre, marksCount);
                }

                continue;
            }

            if (isInPre) {
                checkMark(paragraph, i, marksInPre);
            } else {
                checkMark(paragraph, i, marksCount);
            }
        }

        return marksCount;
    }

    private void addLinesToParagraph(StringBuilder paragraph, List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            paragraph.append(lines.get(i));
            if (i < lines.size() - 1) {
                paragraph.append(System.lineSeparator());
            }
        }
    }

    private void readMarkdown(String filename) throws IOException {
        this.paragraphs = new ArrayList<>();
        StringBuilder paragraph;
        
        List<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename), StandardCharsets.UTF_8)) {
            paragraph = new StringBuilder();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (!line.isEmpty()) {
                    lines.add(line);
                } else if (!lines.isEmpty()) {
                    addLinesToParagraph(paragraph, lines);
                    lines.clear();
                    this.paragraphs.add(paragraph.toString());
                    paragraph = new StringBuilder();
                }
            }
        }

        if (!lines.isEmpty()) {
            addLinesToParagraph(paragraph, lines);
            this.paragraphs.add(paragraph.toString());
        }
    }

    public void write(String filename) throws IOException {
        try (Writer writer = new BufferedWriter(
            new OutputStreamWriter(new FileOutputStream(filename),
            StandardCharsets.UTF_8
        ))) {
            writer.write(html.toString());
        }
    }
}
