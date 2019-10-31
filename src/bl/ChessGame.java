package bl;

import model.ChessBoard;
import model.GameException;
import model.Player;

import java.util.List;
import java.util.Map;

public class ChessGame {
    private ChessBoard board;
    private Player player1;
    private Player player2;
    private Map<Player, List<Integer[][]>> moveHistory;
    private Map<Player, String> selectedPieceColor;
    private ChessGame cg;

    public void selectPieceType(Player player){

    }

    public void startTheGame() throws GameException {
        if(cg == null){
            throw new GameException("You have one game in progress");
        }
        else{
            cg = new ChessGame();
        }
    }

    public Player announceWinner(){
        return player1;
    }

}
