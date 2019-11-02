package pieces;

import model.Color;

public class Rook extends Piece {
    public Rook(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(int sourceX, int sourceY, int targetX, int targetY) {
        if(Moves.isRookMove(sourceX, sourceY, targetX, targetY))
            return true;
        return false;
    }
}
