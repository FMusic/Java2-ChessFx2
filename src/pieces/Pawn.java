package pieces;

import model.Color;

public class Pawn extends Piece {
    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(int sourceX, int sourceY, int targetX, int targetY) {
        return false;
    }
}
