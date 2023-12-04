package game;

import java.util.Arrays;
import java.util.List;

public class Olympiad {
    private final List<Player> players;
    private final PlayerStatus[] place;
    private final int playerCount;
    public Olympiad(List<Player> players) {
        this.players = players;
        this.place = new PlayerStatus[players.size()];
        this.playerCount = players.size();
    }

    public void play(int m, int n, int k) {
        Arrays.fill(place, PlayerStatus.UNKNOWN);

        int playoffGames = 1;
        while (playerCount < 2 * playoffGames) {
            playoffGames *= 2;
        }
        
    }
}
