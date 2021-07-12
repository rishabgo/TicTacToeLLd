import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class Player {

    private String playerId;
    private String playerName;
    private char playerSymbol;
    private Position position;
    Map<String, Integer> playerStats = new HashMap<>();

    public Player(String playerId, String playerName, char playerSymbol, Position position) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.playerSymbol = playerSymbol;
        this.position = position;
    }

    public void viewStatistics() {

        System.out.println(playerName + " Won Games: " + playerStats.get(Constants.GAMES_WON));
        System.out.println(playerName + " Lost Games: " + playerStats.get(Constants.GAMES_LOOSE));
        //System.out.println(playerName + " Draw Games: " + playerStats.get(Constants.GAMES_DRAW));
    }
}
