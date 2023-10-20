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
        this.canRead = false;
    }

    private void checkBuffer() throws IOException {
        if (charsRead == charsInBuffer) {
            charsRead = 0;
            charsInBuffer = reader.read(buffer);
        }
    }

    private void readToken() throws IOException {
        token = new StringBuilder();
        canRead = false;

        checkBuffer();
        while (charsRead < charsInBuffer) {
            lastSymbol = buffer[charsRead];

            if (Character.isWhitespace(lastSymbol)) {
                if (canRead) {
                    break;
                }
            } else if (!canRead) {
                tokenStart = charsRead;
                canRead = true;
            }

            token.append(lastSymbol);
            charsRead++;
            checkBuffer();
        }
    }

    public boolean hasNext() throws IOException {
        if (!canRead) {
            readToken();
        }
        return canRead;
    }

    public boolean hasNextInt() throws IOException {
        if (!hasNext()) {
            return false;
        }

        try {
            Integer.parseUnsignedInt(token.substring(tokenStart));
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public boolean hasNextLine() throws IOException {
        hasNext();
        return token.length() > 0;
    }

    public String next() throws IOException, NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        canRead = false;
        return token.substring(tokenStart);
    }

    public int nextInt() throws IOException, NoSuchElementException {
        if (!hasNextInt()) {
            throw new NoSuchElementException();
        }

        canRead = false;
        return Integer.parseUnsignedInt(token.substring(tokenStart));
    }

    public String nextLine() throws IOException, NoSuchElementException {
        if (!hasNextLine()) {
            throw new NoSuchElementException();
        }

        checkBuffer();
        while (charsRead < charsInBuffer) {
            lastSymbol = buffer[charsRead];

            token.append(lastSymbol);

            if (checkEndOfLine()) {
                return token.toString();
            }

            charsRead++;
            checkBuffer();
        }

        canRead = false;
        return token.toString();
    }

    private boolean checkEndOfLine() {
        return lastSymbol == '\n';
    }
}
