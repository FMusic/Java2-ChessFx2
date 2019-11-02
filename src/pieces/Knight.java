package pieces;

import model.Color;

public class Knight extends Piece {
    public Knight(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(int sourceX, int sourceY, int targetX, int targetY) {
        int dx = Math.abs(targetX - sourceX);
        int dy = Math.abs(targetY - sourceY);
        if (dx == 2){
            return dy == 1;
        }
        if (dx ==1){
            return dy == 2;
        }
        return false;
    }
}
