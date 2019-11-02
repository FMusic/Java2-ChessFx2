package pieces;

import model.Color;

public class Bishop extends Piece {
    public Bishop(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(int sourceX, int sourceY, int targetX, int targetY) {
        if (Moves.isBishopMove(sourceX, sourceY, targetX, targetY)){
            return true;
        }
        return false;
    }
}
