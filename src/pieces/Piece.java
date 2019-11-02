package pieces;

import model.Color;
import model.PathTrace;

public abstract class Piece {
    private Color pieceColor;

    Piece(Color color) {
        pieceColor = color;
    }

    public void display(){

    }

    public PathTrace tracePaths(int sourceX, int sourceY, int targetX, int targetY){
        return new PathTrace();
    }

    public abstract boolean canMove(int sourceX, int sourceY, int targetX, int targetY);
}
