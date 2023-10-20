public class Sum {
    public static void main(String[] args) {
        int sum = 0;
        int numberLength;
        char character;

        for (String arg : args) {
            numberLength = 0;
            for (int i = 0; i < arg.length(); i++) {
                character = arg.charAt(i);

                if (Character.isDigit(character) | character == '-' | character == '+') {
                    numberLength++;
                }

                if (Character.isWhitespace(character) & numberLength > 0) {
                    sum += Integer.parseInt(arg.substring(i - numberLength, i));
                    numberLength = 0;
                }
                else if (i == arg.length() - 1 && numberLength > 0) {
                    sum += Integer.parseInt(arg.substring(i + 1 - numberLength, i + 1));
                }
            }
        }

        System.out.println(sum);
    }
}