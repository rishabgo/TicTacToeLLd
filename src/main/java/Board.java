import lombok.Getter;
import lombok.NonNull;

@Getter
public class Board {

    private char board[][];
    private int boardSize;

    public Board(int size) {

        this.boardSize = size;
        board = new char[size][size];
    }

    public boolean makeMove(@NonNull Player player) {

        Position position = player.getPosition();

        if (position != null && position.getX() > boardSize || position.getY() > boardSize)
            throw new RuntimeException("Illegal Move");

        board[position.getX()][position.getY()] = player.getPlayerSymbol();

        if (checkWinningCondition(player, position)) {
            return true;
        }
        return false;
    }

    private boolean checkWinningCondition(Player player, Position position) {

        boolean winRow = true, winColumn = true, winDiagonal = true, winRevDiagonal = true;
        //check row& column and 2Diagonals
        for (int i = 0; i < boardSize; i++) {

            if (board[position.getX()][i] != player.getPlayerSymbol()) {
                winRow = false;
            }
            if (board[i][position.getY()] != player.getPlayerSymbol()) {
                winColumn = false;
            }
            if (board[i][boardSize - i - 1] != player.getPlayerSymbol()) {
                winRevDiagonal = false;
            }
            if (board[i][i] != player.getPlayerSymbol()) {
                winDiagonal = false;
            }
        }
        return winRow || winColumn || winDiagonal || winRevDiagonal;
    }
}
