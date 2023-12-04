package md2html;

import java.io.IOException;

public class Md2Html {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Wrong arguments count. Expected input and output filenames");
            return;
        }

        try {
            MarkdownToHtmlConverter converter = new MarkdownToHtmlConverter(args[0]);
            converter.write(args[1]);
        } catch (IOException e) {
            System.err.println("Error while reading file: " + e);
        }
    }
}
