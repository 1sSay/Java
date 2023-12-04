package game;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Main {
    public static void main(String[] args) {
        final int m = 10;
        final int n = 1;
        final int k = 6;

        final Game game = new Game(false, new HumanPlayer(), new RandomPlayer(m, n));
        int result;
        do {
            result = game.play(new MNKBoard(m, n, k));
            System.out.println("Game result: " + result);
        } while (result != 0);
    }
}
