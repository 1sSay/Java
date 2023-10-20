public class SumLongSpace {
    public static boolean isWhitespace(char character) {
        return Character.getType(character) == Character.SPACE_SEPARATOR;
    }

    public static void main(String[] args) {
        long sum = 0;
        int numberLength;

        for (String arg : args) {
            numberLength = 0;
            for (int i = 0; i < arg.length(); i++) {
                if (!isWhitespace(arg.charAt(i))) {
                    numberLength++;
                }

                if ((i + 1 == arg.length() || isWhitespace(arg.charAt(i + 1))) && numberLength > 0) {
                    sum += Long.parseLong(arg.substring(i - numberLength + 1, i + 1));
                    numberLength = 0;
                }
            }
        }

        System.out.println(sum);
    }
}