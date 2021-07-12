import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    private static Integer moveCount = 0;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter board size");
        int boardSize = sc.nextInt();
        Board board = new Board(boardSize);

        Queue<Player> playerQueue = new LinkedList<>();

        playerQueue.add(new Player("p1", "rishabh", 'X', new Position(0, 0)));
        playerQueue.add(new Player("p2", "gaurav", 'O', new Position(0, 0)));

        while (playerQueue.size() == Constants.NUM_OF_PLAYERS) {

            var currentPlayer = playerQueue.poll();
            System.out.println("Player " + currentPlayer.getPlayerName() + " turn");
            for (var i = 0; i < boardSize; i++) {
                for (var j = 0; j < boardSize; j++) {
                    System.out.print(board.getBoard()[i][j] + " ");
                }
                System.out.println();
            }

            var x = sc.nextInt();
            var y = sc.nextInt();
            currentPlayer.setPosition(new Position(x, y));
            moveCount++;
            if (board.makeMove(currentPlayer)) {
                System.out.println("Player " + currentPlayer.getPlayerName() + " wins game!!");
                currentPlayer.getPlayerStats().put(Constants.GAMES_WON, currentPlayer.getPlayerStats().getOrDefault(Constants.GAMES_WON, 0) + 1);
                Player loosingPlayer = playerQueue.poll();
                loosingPlayer.getPlayerStats().put(Constants.GAMES_LOOSE, currentPlayer.getPlayerStats().getOrDefault(Constants.GAMES_LOOSE, 0) + 1);
                currentPlayer.viewStatistics();
                loosingPlayer.viewStatistics();
                break;
            } else {
                playerQueue.offer(currentPlayer);
                if(moveCount.equals(boardSize*boardSize)){
                    System.out.println("Match Draw");
                    break;
                }
            }
        }
    }
}
