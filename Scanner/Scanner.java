import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;

class Scanner {
    private BufferedReader reader;

    private StringBuilder token;
    private int tokenStart;
    private boolean canRead;

    private int charsInBuffer;
    private int charsRead;
    private char lastSymbol;
    private char[] buffer;

    public Scanner(InputStream stream) throws IOException {
        this.reader = new BufferedReader(
            new InputStreamReader(
                stream, 
                StandardCharsets.UTF_8
            )
        );

        this.buffer = new char[8192];
        this.charsInBuffer = 0;
        this.charsRead = 0;
    }

    public boolean hasNext() throws IOException {
        if (!canRead) {
            parseNextWord();
        }

        return canRead;
    }

    public boolean hasNextInt() throws IOException {
        if (!hasNext()) {
            return false;
        }

        try {
            Integer.parseUnsignedInt(token.substring(tokenStart));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean hasNextLine() throws IOException {
        if (!hasNext()) {
            return false;
        }

        return true;
    }

    public String next() throws NoSuchElementException, IOException {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        String result = nextWord;
        return result;
    }

    public int nextInt() throws NoSuchElementException, IOException {
        if (!hasNextInt()) {
            throw new NoSuchElementException();
        }

        int number = Integer.parseInt(nextWord);
        return number;
    }

    public String nextLine() throws NoSuchElementException, IOException {
        if (!hasNextLine()) {
            throw new NoSuchElementException();
        }

        line = new StringBuilder(nextWord);
        nextWord = new String();

        while (charsRead < charsInBuffer) {
            lastSymbol = buffer[charsRead];
            line.append(lastSymbol);

            if (checkEndOfLine()) {
                break;
            }

            charsRead++;
            if (charsRead == charsInBuffer) {
                int charsInBuffer = reader.read(buffer);
                charsRead = 0;
            }
        }

        canParse = false;
        return line.toString();
    }

    public void close() throws IOException {
        reader.close();
    }

    private void parseNextWord() throws IOException {
        StringBuilder builder = new StringBuilder();

        if (charsRead == charsInBuffer) {
            charsInBuffer = reader.read(buffer);
            charsRead = 0;
        }

        while (charsRead < charsInBuffer) {
            lastSymbol = buffer[charsRead];
            if (builder.length() > 0 && Character.isWhitespace(lastSymbol)) {
                break;
            }

            builder.append(lastSymbol);
            charsRead++;

            if (charsRead == charsInBuffer) {
                int charsInBuffer = reader.read(buffer);
                charsRead = 0;
            }
        }

        if (builder.length() > 0) {
            canParse = true;
            nextWord = builder.toString();
        } else {
            canParse = false;
        }

    }
}