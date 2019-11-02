package model;

import pieces.*;

import java.util.List;

public class ChessBoard {
    private Box[][] boxes = new Box[8][8];
    private List<Piece> removedPieces;

    public Box[][] getBoxes() {
        return boxes;
    }

    public void setBoxes(Box[][] boxes) {
        this.boxes = boxes;
    }

    public List<Piece> getRemovedPieces() {
        return removedPieces;
    }

    public void setRemovedPieces(List<Piece> removedPieces) {
        this.removedPieces = removedPieces;
    }

    public ChessBoard(){
        //setting up the board
        for (int i=1; i<9; i++){
            for (int j=1; j<9;j++){
                if ((i+j)%2 == 0){
                    boxes[i][j].setColor(Color.BLACK);
                }else{
                    boxes[i][j].setColor(Color.WHITE);
                }
                if ((i==1 && j==1) || (i==8 && j==1)){
                    boxes[i][j].setPiece(new Rook(Color.WHITE));
                }
                if((i==1 && j==8)|| (i==8 && j==8)){
                    boxes[i][j].setPiece(new Rook(Color.BLACK));
                }
                if((i==2 && j==1) || (i==7 && j==1)){
                    boxes[i][j].setPiece(new Bishop(Color.WHITE));
                }
                if((i==3 && j==1) || (i==6 && j==1)){
                    boxes[i][j].setPiece(new Knight(Color.WHITE));
                }
                if(i==4 && j==1){
                    boxes[i][j].setPiece(new Queen(Color.WHITE));
                }
                if(i==5 && j==1){
                    boxes[i][j].setPiece(new King(Color.WHITE));
                }
                if(j==2){
                    boxes[i][j].setPiece(new Pawn(Color.WHITE));
                }
                if(j==7){
                    boxes[i][j].setPiece(new Pawn(Color.BLACK));
                }
                if((i==2 && j==8) || (i==7 && j==8)){
                    boxes[i][j].setPiece(new Bishop(Color.BLACK));
                }
                if((i==3 && j==8) || (i==6 && j==8)){
                    boxes[i][j].setPiece(new Knight(Color.BLACK));
                }
                if(i==4 && j==8){
                    boxes[i][j].setPiece(new Queen(Color.BLACK));
                }
                if(i==5 && j==8){
                    boxes[i][j].setPiece(new King(Color.BLACK));
                }
            }
        }
    }

    public void movePiece(Color pieceColor, int sourceX, int sourceY, int targetX, int targetY) throws GameException {
        Piece piece = boxes[sourceX][sourceY].getPiece();
        if(piece.canMove(sourceX, sourceY, targetX, targetY)){
            if(boxesFromToAreEmpty(sourceX, sourceY, targetX, targetY)) {
                if (boxes[sourceX][sourceY].getPiece() != null) {
                    removePiece(targetX, targetY);
                }
                boxes[targetX][targetY].setPiece(piece);
                removePiece(sourceX, sourceY);
                checkForCheck();
                return;
            }
        }
        throw new GameException("Invalid move");
    }

    private boolean boxesFromToAreEmpty(int fromX, int fromY, int toX, int toY) {
        //only on Y axis
        if ((fromY - toY) == 0){
            for(int i=fromX+1;i<toX;i++){
                if(!isBoxEmpty(i, fromY)){
                    return false;
                }
            }
            return true;
        }
        //only on X axis
        if ((fromX - toX) == 0 ){
            for(int i=fromY+1;i<toY;i++){
                if(!isBoxEmpty(fromX, i)){
                    return false;
                }
            }
            return true;
        }
        //diagonal move
        int dX = Math.abs(toX - fromX);
        int dY = Math.abs(toY - fromY);
        if(dX == dY){
            if (fromX < toX){
                for(int i=fromX+1;i<toX;i++){
                    if (checkY(fromY, toY, i)) return false;
                }
            }
            else{
                for(int i=fromX -1;i>toX;i--){
                    if(checkY(fromY, toY, i)) return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean checkY(int fromY, int toY, int i) {
        if (fromY < toY){
            for(int j=fromY+1; j<toY;j++){
                if (!isBoxEmpty(i,j)){
                    return true;
                }
            }
        }else{
            for (int j =fromY-1;j>toY;j--){
                if(!isBoxEmpty(i, j)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isBoxEmpty(int x, int y) {
        if (boxes[x][y].getPiece() == null){
            return true;
        }
        return false;
    }

    public boolean checkForCheck(){
        return false;
    }

    public void removePiece(int sourceX, int sourceY){
        removedPieces.add(boxes[sourceX][sourceY].getPiece());
        boxes[sourceX][sourceY].setPiece(null);
    }


}
