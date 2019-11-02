package pieces;

import model.Color;

public class King extends Piece {
    public King(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(int sourceX, int sourceY, int targetX, int targetY) {
        int dx= Math.abs(sourceX - targetX);
        int dy = Math.abs(sourceY - targetY);
        return dx <= 1 && dy <= 1;
    }
}
