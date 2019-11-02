package pieces;

import model.Color;

public class Pawn extends Piece {
    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(int sourceX, int sourceY, int targetX, int targetY) {
        if(sourceX==targetX){
            if((targetX - sourceX) == 1){
                return true;
            }
        }
        return false;
    }
}
