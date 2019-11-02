package pieces;

import model.Color;

public class Queen extends Piece {
    public Queen(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(int sourceX, int sourceY, int targetX, int targetY) {
        if(Moves.isQuuenMove(sourceX, sourceY, targetX, targetY)){
            return true;
        }
        return false;
    }
}
