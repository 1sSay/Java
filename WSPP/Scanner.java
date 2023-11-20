import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;

class Scanner {
    private InputStreamReader reader;

    private StringBuilder token;
    private int tokenStart;
    private boolean canRead;
    private int newLines;
    private boolean firstWord;

    private int charsInBuffer;
    private int charsRead;
    private char lastSymbol;
    private char[] buffer;

    public Scanner(InputStream stream) throws IOException {
        this.reader = new InputStreamReader(
            stream,
            StandardCharsets.UTF_8
        );

        this.buffer = new char[8192];
        this.charsInBuffer = 0;
        this.charsRead = 0;
        this.canRead = false;
        this.firstWord = true;
        this.newLines = 0;
    }

    private void checkBuffer() throws IOException {
        if (this.charsRead == this.charsInBuffer) {
            this.charsRead = 0;
            this.charsInBuffer = this.reader.read(buffer);
        }
    }

    private void readToken() throws IOException {
        this.token = new StringBuilder();
        this.canRead = false;

        this.checkBuffer();
        while (this.charsRead < this.charsInBuffer) {
            this.lastSymbol = this.buffer[charsRead];

            if (Character.isWhitespace(this.lastSymbol)) {

                if (this.canRead) {
                    break;
                }
            }
            else if (!this.canRead) {
                this.tokenStart = token.length();
                this.canRead = true;
            }

            if (this.checkEndOfLine()) {
                this.newLines++;
                this.firstWord = true;
            }

            this.token.append(lastSymbol);
            this.charsRead++;
            this.checkBuffer();
        }
    }

    public boolean isFirst() {
        return this.canRead && this.firstWord;
    }

    public boolean hasNext() throws IOException {
        if (!this.canRead) {
            this.readToken();
        }
        return this.canRead;
    }

    public boolean hasNextInt() throws IOException {
        if (!this.hasNext()) {
            return false;
        }

        boolean isSignedInt = true;
        boolean isUnsignedInt = true;

        try {
            Integer.parseInt(this.token.substring(this.tokenStart));
        } catch (NumberFormatException e) {
            isSignedInt = false;
        }

        try {
            Integer.parseInt(this.token.substring(this.tokenStart));
        } catch (NumberFormatException e) {
            isUnsignedInt = false;
        }

        return isSignedInt || isUnsignedInt;
    }

    public boolean hasNextLine() throws IOException {
        this.hasNext();
        return this.token.length() > 0;
    }

    public String next() throws IOException, NoSuchElementException {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }

        this.newLines = 0;
        this.firstWord = false;
        this.canRead = false;
        return this.token.substring(this.tokenStart);
    }

    public int nextInt() throws IOException, NoSuchElementException {
        if (!this.hasNextInt()) {
            throw new NoSuchElementException();
        }

        this.newLines = 0;
        this.canRead = false;
        return Integer.parseInt(this.token.substring(this.tokenStart));
    }

    public String nextLine() throws IOException, NoSuchElementException {
        if (!this.hasNextLine()) {
            throw new NoSuchElementException();
        }

        this.checkBuffer();
        while (this.charsRead < this.charsInBuffer) {
            this.lastSymbol = this.buffer[this.charsRead];

            this.token.append(this.lastSymbol);

            if (this.checkEndOfLine()) {
                return this.token.toString();
            }

            this.charsRead++;
            this.checkBuffer();
        }

        this.canRead = false;
        this.newLines = 1;
        this.firstWord = true;
        return this.token.toString();
    }

    private boolean checkEndOfLine() {
        if (System.lineSeparator().length() == 1) {
            return  (this.lastSymbol == '\n') ||
                    (this.lastSymbol == '\r') ||
                    (this.lastSymbol == '\u000B') ||
                    (this.lastSymbol == '\u000C') ||
                    (this.lastSymbol == '\u0085') ||
                    (this.lastSymbol == '\u2028') ||
                    (this.lastSymbol == '\u2029');
        } else if (System.lineSeparator().length() == 2) {
            return (this.lastSymbol == '\n' && !this.token.isEmpty() && this.token.charAt(this.token.length() - 1) == '\r') ||
                    (this.lastSymbol == '\r' && !this.token.isEmpty() && this.token.charAt(this.token.length() - 1) == '\n');
        } else {
            return false;
        }
    }

    public int newLinesCount() {
        return this.newLines;
    }

    public void close() throws IOException {
        this.reader.close();
    }
}
